# Transportes TC: Mantenciones

## Descripcion

Aplicacion sencilla hecha para la gestion de mantenciones de vehiculos.

## Requisitos

Se deben tener instaladas las siguientes dependencias:

* OpenJDK Runtime 25.0.1
* Apache Maven 3.9.12
* MariaDB 12.1.2

## Ejecucion

Primero crear el archivo de configuracion para la base de datos.
Esta se encuentra en _src/main/resources/application.properties_
desde la raiz del proyecto y tiene el siguiente formato:

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mariadb://localhost:3306/mantenciones
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.show-sql=true
```

En donde tienen que especificar el usuario y contrasena de su
configuracion de MySQL / MariaDB.

Posteriormente, deben crear la base de datos con el nombre
_mantenciones_:

```
MariaDB [(none)]> CREATE DATABASE mantenciones;
Query OK, 1 row affected (0.001 sec)
```

Opcionalmente, limpiar el entorno de desarrollo:

```
mvn clean
```

Luego, descargar las dependencias:

```
mvn install
```

Finalmente, ejecutar la API:

```
mvn spring-boot:run
```
