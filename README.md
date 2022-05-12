# tp-dds-impacto-ambiental

## Informacion a tener en cuenta :nerd_face: :+1:
1. Para ejecutar el proyecto debes correr el `TpImpactoAmbientalApplication.java` (es el archivo `main()` del proyecto)
2. Host de prueba en `spring boot` http://localhost:8080/ (recomendable descargar postman)
3. Host Base de datos utilizado `H2` http://localhost:8080/h2-console/ (puede falcilmente cambiarse a otra BD si lo desean)
   - username: sa
   - password:

## Registro para el administrador
### registrar
* **_descripcion_:** Registra al usuario en base a las validaciones https://pages.nist.gov/800-63-3/sp800-63b.html#memsecret, caso contrario lanzara una excepcion.


* **_URL_:** http://localhost:8080/user/registrar (POST)


* **_Request_:**
```json
{
    "username" : "raul77",
    "password" : "raul923681"
}
```
### iniciarSesion
* **_descripcion_:** Abre la sesion de un usuario Administrador si es que coinciden el username y password caso contrario lanzara una excepcion.


* **_URL_:** http://localhost:8080/user/iniciar (POST)


* **_Request_:**
```json
{
    "username" : "bari",
    "password" : "wowesmivida12"
}
```