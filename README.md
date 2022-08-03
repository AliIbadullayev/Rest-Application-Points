# Rest-Application-Points (Spring + Angular RESTful application)
## Configuration
In `backend/src/main/resources` configure file `application.properties`. Connect DB, port e.t.c.
``` bash
spring.profiles.active=
server.port=<port>
server.servlet.context-path=

spring.datasource.url=jdbc:postgresql://localhost:5432/<db_name>
spring.datasource.name=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database=postgresql
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL95Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

logging.level.root=INFO
```
## Endpoints
* `/api/rest/addpoint`
A request to this endpoint will allow you **to add point sended from client**
* `/api/rest/getpoints`
A request to this endpoint will allow you **to get points of logged user**
* `/api/rest/login`
A request to this endpoint will allow you **to log into the service and receive a token that allows you to make requests to other endpoints**
* `/api/rest/register`
A request to this endpoint will allow you **to register on service**

``` bash
// TODO
1. compress data on server side (gzip)
2. handle exceptions (Access denied exception)
3. OAuth2 realize 
```
