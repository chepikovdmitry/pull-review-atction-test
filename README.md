# Geometry Area Service

REST-сервис на Spring Boot для расчёта площадей нескольких фигур.

## Поддерживаемые фигуры

- Квадрат
- Прямоугольник
- Круг
- Треугольник

## Технологии

- Java 17
- Spring Boot 3.2.0
- Maven

## Структура проекта

- `src/main/java/com/example/squarearea/SquareAreaApplication.java` — точка входа приложения.
- `src/main/java/com/example/squarearea/controller/SquareController.java` — API endpoint’ы для расчёта площадей.
- `src/main/java/com/example/squarearea/service/SquareService.java` — сервис выбора стратегии расчёта.
- `src/main/java/com/example/squarearea/service/strategy/*` — стратегии расчёта для каждой фигуры (паттерн Strategy).
- `src/main/resources/application.properties` — конфигурация приложения.

## Запуск

```bash
mvn clean package
mvn spring-boot:run
```

Сервис стартует на `http://localhost:8080`.

## API

Базовый путь: `/api/area`

### 1) Площадь квадрата

```bash
curl "http://localhost:8080/api/area/square?side=5"
```

### 2) Площадь прямоугольника

```bash
curl "http://localhost:8080/api/area/rectangle?width=4&height=7"
```

### 3) Площадь круга

```bash
curl "http://localhost:8080/api/area/circle?radius=3"
```

### 4) Площадь треугольника

```bash
curl "http://localhost:8080/api/area/triangle?base=10&height=6"
```

## Формат ответа

Успех:

```json
{
  "shape": "rectangle",
  "area": 28.0,
  "message": "Area calculated successfully"
}
```

Ошибка валидации (`400 Bad Request`):

```json
{
  "shape": "circle",
  "area": 0.0,
  "message": "Circle radius must be a positive number"
}
```
