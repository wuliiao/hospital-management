# hospital-management

Лабораторная работа 1 по распределенным системам.

Вариант 20: учет больных. Сервис позволяет принять больного, назначить лечение,
выписать домой и получить отчет о количестве больных на лечении и выписанных.

## Стек

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Liquibase
- Swagger UI
- Docker Compose

## Запуск полностью в Docker

```bash
docker compose up --build
```

После запуска Swagger будет доступен по адресу:

```text
http://localhost:8080/swagger-ui.html
```

## Локальный запуск приложения

Сначала поднять только базу данных:

```bash
docker compose up -d db
```

Потом запустить приложение:

```bash
mvn spring-boot:run
```

Локально приложение подключается к PostgreSQL на `localhost:15432`.
Внутри Docker-сети сервис использует адрес `db:5432`.

## Основные эндпоинты

- `GET /api/patients` - список больных
- `GET /api/patients/{id}` - больной по id
- `POST /api/patients` - принять больного
- `PUT /api/patients/{id}` - отредактировать запись
- `PATCH /api/patients/{id}/treatment` - назначить лечение
- `PATCH /api/patients/{id}/discharge` - выписать домой
- `DELETE /api/patients/{id}` - удалить запись
- `GET /api/patients/report` - отчет

Пример создания больного:

```json
{
  "fullName": "Иванов Иван Иванович",
  "diagnosis": "ОРВИ",
  "treatment": "Постельный режим",
  "admissionDate": "2026-09-11"
}
```
