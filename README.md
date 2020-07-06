# quarkus-todo-rest project

This is a small coding exercise with the awesome [Quarkus](https://quarkus.io/) framework. It implements a simple Todo
CRUD REST application with some extra update methods.  

This project was developed following the
[Clean Code](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship-ebook/dp/B001GSTOAM) and 
[Clean Architecture](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164) 
guidelines from Robert C. Martin.

# Prerequisite

- Java 11+ (easiest install method in UNIX environments is via [SDKMAN](https://sdkman.io/))
- [Docker](https://www.docker.com/)
- [docker-compose](https://docs.docker.com/compose/install/)

# Run

First, in the root directory of this repository, start the local MySQL database with this command :
```bash
docker-compose up -d
```

You can see the database logs with:
```bash
docker logs -f todo-db
```
Once the database is ready (i.e. when the logs says `[...] /usr/sbin/mysqld: ready for connections. [...]`) start the app
by running this command in another terminal (still in the root directory of this repository) :
```bash
./mvnw clean compile quarkus:dev
```

This will start the app in `dev-mode`, meaning any change to the Java code or configuration will trigger a hot reload on
the next incomming HTTP request.

**WARNING:** your DB will be cleared on each hot reload ! 

# Usage

## Create

```bash
curl -H "Content-Type: application/json" --request POST -d '{"title":"test1", "description": "description1"}' http://localhost:8080/todo
```
Expected output :
```json
{"id":1,"title":"test1","description":"description1","expireAt":null,"createdAt":1594075831309,"updatedAt":null,"doneAt":null,"version":null}
```

## Update

```bash
curl -H "Content-Type: application/json" --request PUT -d '{"id": 1, "title":"changedTitle", "description": "changedUpdate"}' http://localhost:8080/todo
```
No output expected if update succeeds.

## Get all Todos

```bash
curl http://localhost:8080/todo
```
Expected output :
```json
[{"id":1,"title":"changedTitle","description":"changedUpdate","expireAt":null,"createdAt":1594076056491,"updatedAt":null,"doneAt":null,"version":null}]
```

## Get Todo by ID

```bash
curl http://localhost:8080/todo/1
```
Expected output :
```json
{"id":1,"title":"changedTitle","description":"changedUpdate","expireAt":null,"createdAt":1594076056491,"updatedAt":null,"doneAt":null,"version":null}
```

## Set expiryAt date

```bash
curl -H "Content-Type: application/json" --request PUT -d '{"expireAt": 159407605491}' http://localhost:8080/todo/expire/1
```
No output expected if update succeeds.

## Set done

```bash
curl --request PUT http://localhost:8080/todo/1/done 
```
No output expected if update succeeds.

## Delete

```bash
curl --request DELETE  http://localhost:8080/todo/1 
```
No output expected if update succeeds.
