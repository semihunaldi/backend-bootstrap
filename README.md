# backend-bootstrap

### Backend Bootstrap Project with Spring Boot




# Security oauth2
users: oauth_admin, resource_admin, regular_user
pass: password


localhost:8081/oauth/token?grant_type=client_credentials


##### Header

```json
[{"key":"Content-Type","value":"application/x-www-form-urlencoded"},{"key":"Authorization","value":"Basic Y3VybF9jbGllbnQ6cGFzc3dvcmQ="}]
```

##### Params

```json
[{"key":"grant_type","value":"client_credentials"}]
```
curl_client:password  -BASE64> Y3VybF9jbGllbnQ6cGFzc3dvcmQ=


##### calling resource service

Header
```json
[{"key":"Authorization","value":"Bearer e995b814-712e-4241-87d2-8f5b0db401e3"}]
```

