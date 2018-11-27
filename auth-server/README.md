# backend-bootstrap

### Backend Bootstrap Project with Spring Boot
## AUTH-SERVER


This module contains 2 authorization samples with oauth2<br/>
One with client_credentials, other with JWT tokens.<br/>
<br/>
These two samples can be switched by spring profiles on runtime; 'jwt' & 'oauth2' at bootstrap.yml
<br/><br/>
'dev' profile activates a TCP server and allowing other modules to access in-memory H2 DB.
<br/><br/>

#### JWT Auth.
```text
localhost:8081/api/auth/signup
```
##### Request Body
```json
{
    "name":"Test User",
    "username":"testuser",
    "email":"test@test.com",
    "password":"password"
}   
```

##### Response Body
```json
{
    "success": true,
    "message": "User registered successfully"
}
```

---

```text
localhost:8081/api/auth/signin
```
##### Request Body
```json
{
	"usernameOrEmail":"testuser",
	"password":"password"
} 
```

##### Response Body
```json
{
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5QjE4NTkxQS1DNTE4LTRDREUtQkEzQi05QURDMzE0NUQ2NzciLCJpYXQiOjE1NDMzNDEyMzUsImV4cCI6MTU0Mzk0NjAzNX0.mc_jFyI-qqeGW3bLpC16wHR6Bg3lnvvJ5fGwysr8yCToT8LHAskIFxXsRdUsriCCzOqGevPqibncQqKi3Zb8CQ",
    "tokenType": "Bearer"
}
```

***
***
***
***

#### OAUTH2 client_credentials Auth.
```text
users: oauth_admin, resource_admin, regular_user
pass: password
```

```text
localhost:8081/oauth/token?grant_type=client_credentials
```

##### Header
```text
curl_client:password  -> base64 encoded-> Y3VybF9jbGllbnQ6cGFzc3dvcmQ=
```
```json
{"key":"Authorization","value":"Basic Y3VybF9jbGllbnQ6cGFzc3dvcmQ="}
```

***
***
***
***

##### Calling Resource Service

With Header
```json
[{"key":"Authorization","value":"Bearer e995b814-712e-4241-87d2-8f5b0db401e3"}]
```