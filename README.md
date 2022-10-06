# tp-dds-impacto-ambiental

## Informacion a tener en cuenta 	:smiley::+1:
1. Para ejecutar el proyecto debes correr el `TpImpactoAmbientalApplication.java` (es el archivo `main()` del proyecto)
2. Host de prueba en `spring boot` http://localhost:8080/ (recomendable descargar postman)
3. Host Base de datos utilizado `H2` http://localhost:8080/h2-console/ (puede falcilmente cambiarse a otra BD si lo desean)
   - JDBC URL: jdbc:h2:mem:testdb;MV_STORE=FALSE
   - username: sa
   - password:
5. [Diagrama de clases](https://app.diagrams.net/#G1KjyXDbRwMmgGRpaYPL_D5X6c7KRqHixG)
## Registro para el administrador
### registrar
* **_descripcion_:** Registra al usuario en base a las validaciones [de aqui](https://pages.nist.gov/800-63-3/sp800-63b.html#memsecret), caso contrario lanzara una excepcion.


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

## Organizaciones y Miembros
### registrar Miembro
* **_descripcion_:** Registra los datos personales de un miembro.


* **_URL_:** http://localhost:8080/member/registrar (POST)


* **_Request_:**
```json
{
    "nombre" : "Cornelio",
    "apellido" : "Del Rancho",
    "tipoDocumento" : "DNI",
    "nroDocumento" : "12345678"
}
```
### registrar Organizacion
* **_descripcion_:** El miembro registra la organizacion en la cual solicita.


* **_URL_:** http://localhost:8080/member/registro_org/{miembroId} (POST)


* **_Request_:**
```json
{
   "razonSocial" : "the coders",
   "tipo" : "Empresa",
   "clasificacion" : "Software factory"
}
```
### En Desarrollo (proximamente)
* **_Role de usuario_:** ya esta desarrollado, solo falta hacer la relacion con mienbro mas test.
* **_Trayectoria_:** ya esta desarrollado el modelado de trayectoria, tramo, y medioTransporte. 
tambien se hizo la relacion tramo x medioTransporte (OneToOne).
falta la relacion trayectoria x tramo (ManyToOne) y trayectoria x mienbro (OneToMany) mas test.