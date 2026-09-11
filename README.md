# hospital-management

Лабораторные работы по предмету "Распределенные вычисления и приложения".

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
- Spring Cloud Gateway
- Docker Compose

## Запуск полностью в Docker

```bash
docker compose up --build
```

После запуска Swagger будет доступен по адресу:

```text
http://localhost:8081/swagger-ui.html
http://localhost:8082/swagger-ui.html
```

## Локальный запуск приложения

Сначала поднять только базу данных:

```bash
docker compose up -d db
```

Потом запустить нужные сервисы:

```bash
mvn -pl main-service spring-boot:run
mvn -pl report-service spring-boot:run
mvn -pl gateway-service spring-boot:run
```

Локально приложение подключается к PostgreSQL на `localhost:15432`.
Внутри Docker-сети сервис использует адрес `db:5432`.

## Сервисы

- `main-service` - основной сервис, порт `8081`
- `report-service` - сервис отчетов, порт `8082`
- `gateway-service` - шлюз для доступа к сервисам, порт `8080`

## Основные эндпоинты

Через gateway:

- `GET http://localhost:8080/api/patients` - список больных из основного сервиса
- `GET http://localhost:8080/api/patients/{id}` - больной по id
- `POST http://localhost:8080/api/patients` - принять больного
- `PUT http://localhost:8080/api/patients/{id}` - отредактировать запись
- `PATCH http://localhost:8080/api/patients/{id}/treatment` - назначить лечение
- `PATCH http://localhost:8080/api/patients/{id}/discharge` - выписать домой
- `DELETE http://localhost:8080/api/patients/{id}` - удалить запись
- `GET http://localhost:8080/api/patients/report` - краткий отчет из основного сервиса
- `GET http://localhost:8080/api/reports/patients` - список данных из сервиса отчетов

Пример создания больного:

```json
{
  "fullName": "Иванов Иван Иванович",
  "diagnosis": "ОРВИ",
  "treatment": "Постельный режим",
  "admissionDate": "2026-09-11"
}
```

## Отчеты и gateway

В проекте есть второй сервис, работающий с той же базой данных и формирующий
отчет в виде списка больных. Также добавлен gateway, через который доступны
основной сервис и сервис отчетов. Проверка выполняется через Postman по адресу
`http://localhost:8080`.

Готовую коллекцию для Postman можно импортировать из файла:

```text
postman/hospital-management-lab2.postman_collection.json
```
