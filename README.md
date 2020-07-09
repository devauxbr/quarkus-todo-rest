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

First, in the root directory of this repository, start the local MySQL database and Keycloak instance with this command :
```bash
docker-compose up -d
```

You can see the database logs with:
```bash
docker logs -f todo-db
```
Once the database is ready (i.e. when the logs say `[...] /usr/sbin/mysqld: ready for connections. [...]`, this might
take few seconds on first startup), start the app
by running this command in another terminal (still in the root directory of this repository) :
```bash
./mvnw clean compile quarkus:dev
```

This will start the app in `dev-mode`, meaning any change to the Java code or configuration will trigger a hot reload on
the next incomming HTTP request.

Flyway will bootstrap the database schema on first start.

Lastly, configure the Keycloak instance following configuration steps from the official 
[Quarkus OIDC guide](https://quarkus.io/guides/security-openid-connect#starting-and-configuring-the-keycloak-server).
Skip the `docker run` step, just access the Keycloak Administration Console and import the provided realm configuration file
(click on "Add realm", then import the file).
Make sure to [export the **access_token** environement variable](https://quarkus.io/guides/security-openid-connect#testing-the-application)
before executing the commands in the next paragraphs.

# Usage

## Create

```bash
curl -H "Content-Type: application/json" -H "Authorization: Bearer $access_token" --request POST -d '{"title":"test1", "description": "description1"}' http://localhost:8080/admin/todo
```
Expected output :
```json
{"id":0,"title":"test1","description":"description1","expireAt":null,"createdAt":1594075831309,"updatedAt":null,"doneAt":null,"version":null}
```

## Update

```bash
curl -H "Content-Type: application/json" --request PUT -d '{"id": 0, "title":"changedTitle", "description": "changedUpdate"}' http://localhost:8080/todo
```
No output expected if update succeeds.

## Get all Todos

```bash
curl http://localhost:8080/todo
```
Expected output :
```json
[{"id":0,"title":"changedTitle","description":"changedUpdate","expireAt":null,"createdAt":1594076056491,"updatedAt":null,"doneAt":null,"version":null}]
```

## Get Todo by ID

```bash
curl http://localhost:8080/todo/0
```
Expected output :
```json
{"id":1,"title":"changedTitle","description":"changedUpdate","expireAt":null,"createdAt":1594076056491,"updatedAt":null,"doneAt":null,"version":null}
```

## Set expiryAt date

```bash
curl -H "Content-Type: application/json" --request PUT -d '{"expireAt": 159407605491}' http://localhost:8080/todo/0/expire
```
No output expected if update succeeds.

## Set done

```bash
curl --request PUT http://localhost:8080/todo/0/done 
```
No output expected if update succeeds.

## Delete

```bash
curl --request DELETE -H "Authorization: Bearer $access_token" http://localhost:8080/admin/todo/0 
```
No output expected if update succeeds.
