import json
import os
import sys
import textwrap
import urllib.request

def read_text(path: str) -> str:
    with open(path, "r", encoding="utf-8", errors="replace") as f:
        return f.read()

def post_json(url: str, payload: dict, timeout: int = 900) -> dict:
    data = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(
        url,
        data=data,
        headers={"Content-Type": "application/json"},
        method="POST",
    )
    with urllib.request.urlopen(req, timeout=timeout) as resp:
        return json.loads(resp.read().decode("utf-8"))

def main() -> int:
    if len(sys.argv) != 3:
        print("Usage: local_llm_review.py <diff_file> <out_md>", file=sys.stderr)
        return 2

    diff_file, out_md = sys.argv[1], sys.argv[2]

    llm_base_url = os.getenv("LLM_BASE_URL", "http://127.0.0.1:11434")
    model = os.getenv("LLM_MODEL", "qwen3:8b")

    diff = read_text(diff_file)

    # Защита от огромных PR: режем diff, чтобы LLM не падала
    max_chars = int(os.getenv("MAX_DIFF_CHARS", "140000"))
    if len(diff) > max_chars:
        diff = diff[:max_chars] + "\n\n[TRUNCATED]\n"

    prompt = textwrap.dedent(f"""
    You are a senior Java code reviewer.
    Review the diff and produce a Markdown report with these sections:
    - High risk (security, correctness, data loss, concurrency)
    - Medium risk (maintainability, null-safety, logging, API design)
    - Tests (missing or incorrect tests, suggestions)
    - Suggested fixes (concrete, actionable)
    Rules:
    - Reference file paths seen in diff
    - Be concise, do not invent context not present in diff
    - If you are unsure, say so
    Diff:
    {diff}
    """).strip()

    url = llm_base_url.rstrip("/") + "/api/generate"
    payload = {
        "model": model,
        "prompt": prompt,
        "stream": False,
        "options": {
            "temperature": 0.2,
            "num_predict": 1200,
        },
    }

    resp = post_json(url, payload)
    text = (resp.get("response") or "").strip()
    if not text:
        text = "No output from model."

    with open(out_md, "w", encoding="utf-8") as f:
        f.write("# Local LLM PR Review\n\n")
        f.write(text)
        f.write("\n")

    return 0

if __name__ == "__main__":
    raise SystemExit(main())
