# Evaluación Java

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios. 
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error. 
Todos los mensajes deben seguir el formato: 
 {"mensaje": "mensaje de error"} 

###  Registro 
* Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato: 
     ```sh
     { 
        "name": "Juan Rodriguez", 
        "email": "juan@rodriguez.org" , 
        "password": "hunter2", 
        "phones": [ 
            { 
                "number": "1234567", 
                "citycode": "1",	 
                "contrycode": "57" 
            } 
        ] 
    }
     ```
* Responder el código de status HTTP adecuado 
* En caso de éxito, retorne el usuario y los siguientes campos: 
    - id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID) 
    - created: fecha de creación del usuario 
    - modified: fecha de la última actualización de usuario 
    - last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación) 
    - token: token de acceso de la API (puede ser UUID o JWT) 
    - isactive: Indica si el usuario sigue habilitado dentro del sistema. 

* Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado"
* El correo debe seguir una expresión regular para validar que formato sea el correcto. (aaaaaaa@dominio.cl) 
* La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una Mayuscula, letras minúsculas, y dos numeros) 
* El token deberá ser persistido junto con el usuario 

### Requisitos 
* Plazo: 2 días 
* Banco de datos en memoria, como HSQLDB o H2. 
* Proceso de build via Gradle. 
* Persistencia con Hibernate. 
* Framework Spring. 
* Servidor Tomcat o Jetty Embedded 
* Java 8+ 
* Entrega en un repositorio público (github o bitbucket) con el código fuente y script de creación de BD. 
* Entrega diagrama de la solución. 

### Requisitos deseables 
* JWT cómo token 
* Pruebas de unidad 

### Solución

#### 1 Esquema BD - H2
- Al ejecutar el programa se abrirá el navegador mostrando el login de la base de datos H2
    ```sh
        http://172.27.160.1:8082/
     ```
#### 2 Esquema BD - H2
     ```sh
        create table user ( 
        id  varchar(255)  primary key not null,
        name varchar(50) not null, 
        email varchar(255) not null, 
        password varchar(20)  not null, 
        created timestamp null, 
        modified timestamp null,
        last_login timestamp null,
        is_active null,
        token varchar(300) not null
        );

        create table user_phones ( 
        id INT primary key not null,
        number  int null, 
        city_code int null, 
        country_code int null, 
        fk_user varchar(300),
        foreign key (fk_user ) references user(id)
        );
    ```

#### 3 Esquema Regex Email Validation
    ```sh
        ^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+cl
    ```

#### 4 Esquema Regex Email Validation
    ```sh
    ^([A-Z])([a-z]).{5}([0-9]{2})
    ```
#### 5 Dependencias Gradle
     ```sh
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        implementation 'org.springframework.boot:spring-boot-starter-web'
        implementation 'org.springframework.cloud:spring-cloud-starter-security'
        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        compile group: 'io.jsonwebtoken', name: 'jjwt', version: '0.2'
        runtimeOnly 'com.h2database:h2'
        testImplementation('org.springframework.boot:spring-boot-starter-test') {
            exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
        }
    }
     ```