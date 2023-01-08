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
### crear organizacion
* **_descripcion_:** crea una organizacion.


* **_URL_:** http://localhost:8080/organization/crear-organizacion (POST)


* **_Request_:**
```json
{
   "razonSocial" : "coders",
   "tipoOrganizacion" : {
      "text" : "EMPRESA"
   },
   "clasificacion" : {
      "text" : "Sofware factory"
   }
}
```
### crear sector
* **_descripcion_:** crea un sector de una organizacion existente.


* **_URL_:** http://localhost:8080/sector/crear-sector (POST)


* **_Request_:**
```json
{
   "nombre" : "coders",
   "idOrganizacion" : 2,
   "espacio" : {
      "nombre" : "ITR",
      "tipoEspacio" : "HOGAR",
      "direccion" : {
         "calle" : "Falsa",
         "altura" : "123",
         "localidad" : {
            "id" : 243
         }
      }
   }
}
```
### registrar Miembro
* **_descripcion_:** Registra el miembro a una organizacion.


* **_URL_:** http://localhost:8080/member/registrar (POST)


* **_Request_:**
```json
{
    "nombre" : "Cornelio",
    "apellido" : "Del Rancho",
    "tipoDocumento" : "DNI",
    "nroDocumento" : "12345678",
    "idOrganizacion" : 1,
    "idSector": 1
}
```
### Aceptar solicitud a una Organizacion
* **_descripcion_:** Acepta la solicitud de vinculacion de un miembro a una organizacion.


* **_URL_:** http://localhost:8080/organization/aceptar-solicitud (POST)


* **_Request_:**
```json
{
   "idSolicitud" : 1
}
```
### En Desarrollo (proximamente)
* **_Role de usuario_:** ya esta desarrollado, solo falta hacer la relacion con mienbro mas test.
* **_Trayectoria_:** ya esta desarrollado el modelado de trayectoria, tramo, y medioTransporte. 
tambien se hizo la relacion tramo x medioTransporte (OneToOne).
falta la relacion trayectoria x tramo (ManyToOne) y trayectoria x mienbro (OneToMany) mas test.