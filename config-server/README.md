# backend-bootstrap

### Backend Bootstrap Project with Spring Boot
## CONFIG-SERVER

```text

Configuration server connection url must be given.
bitbucket-git profile added to maven' setting.xml

spring:
  cloud:
    config:
      server:
        git:
          uri: https://bitbucket.org/semihunaldi/config-server-properties.git
          username: @bitbucket-git-username@
          password: @bitbucket-git-password@
  profiles:
    active: bitbucket-git
```