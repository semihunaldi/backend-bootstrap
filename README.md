# backend-bootstrap

### Backend Bootstrap Project with Spring Boot

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.0.RELEASE</version>
</parent>
```

```text
Project Running Order

0. config-server (if used)
1. eureka-server
2. auth-server
3. ws
4. scheduler (only dependent to db's order doesn't matter)

```

[docker-compose.yml can give you a quick look to project structure.](./docker-compose.yml)<br/>

Modules
```xml
<modules>
    <module>entity-model</module>
    <module>ws</module>
    <module>services</module>
    <module>auth-server</module>
    <module>eureka-server</module>
    <module>config-server</module>
    <module>scheduler</module>
    <module>ui-jsf</module>
    <module>web-flux</module>
    <module>jwt-auth-common</module>
</modules>
```

[entity-model](./entity-model/README.md)<br/>
[ws](./ws/README.md)<br/>
[services](./services/README.md)<br/>
[auth-server](./auth-server/README.md)<br/>
[eureka-server](./eureka-server/README.md)<br/>
[config-server](./config-server/README.md)<br/>
[scheduler](./scheduler/README.md)<br/>
[ui-jsf](./ui-jsf/README.md)<br/>
[web-flux](./web-flux/README.md)<br/>
[jwt-auth-common](./jwt-auth-common/README.md)<br/>
