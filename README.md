## PRIMERA PARTE

## – SSO con Google + Docker

Este proyecto implementa un login con **Google SSO** usando **Spring Boot** y se entrega dockerizado para que pueda ejecutarse con un simple `docker compose up`.

---

### 🚀 Requisitos previos
- [Java 17+](https://adoptium.net/) (solo si quieres ejecutar sin Docker)
- [Maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

---

### ⚙️ Configuración de variables de entorno

El proyecto usa variables de entorno para configurar el acceso a Google OAuth2.


### ⚠️ Importante:

Para que funcione el login con Google debes crear tus propias credenciales en [Google Cloud Console](https://console.cloud.google.com/welcome?project=seventh-sunbeam-471715-g2):

- Tipo de credencial: OAuth 2.0 Client ID:
- En Google Cloud Console, añade como Redirect URI autorizado:
  http://localhost:9778/login/oauth2/code/google
  Opción para ejecutarlo sin Docker:
  http://localhost:8080/login/oauth2/code/google	

A continuación el archivo .env: 

- En la raíz del proyecto, crea un archivo llamado .env
- Copia el contenido de .env.example en .env y rellénalas con tus credenciales de Google 

GOOGLE_CLIENT_ID → ID de cliente OAuth2

GOOGLE_CLIENT_SECRET → Secreto OAuth2

REMEMBER_ME_KEY → Una cadena aleatoria segura

APP_BASE_URL → http://localhost:9778

### ▶️ Ejecución con Docker

- Empaquetar la aplicación en un JAR:

  ```bash
   mvn -DskipTests package 
```

- Levantar el contenedor:

   ```bash
   docker compose up
```

- Abrir en el navegador:

   ```bash
   http://localhost:9778
```
 

### 🧪 Flujo esperado

1. En la pantalla inicial aparece el botón Iniciar sesión con Google.

2. Tras iniciar sesión, se muestra el nombre del usuario autenticado.

3. Persistencia: al volver a abrir la app, sigues autenticada/o.

### 📂 Estructura

- Dockerfile: construye la imagen con el JAR de Spring Boot.

- docker-compose.yml: define el servicio prodigiosovolcan.

- .env.example: ejemplo de variables de entorno.

- src/: código fuente de la aplicación.

---

## PARTE EXTRA

## SSO + Docker + Nginx + subdominio local + HTTPS

Es creo un proxy inverso con Nginx que expone la app por https://app.localhost usando un certificado autofirmado.

- Proxy inverso: Nginx - prodigiosovolcan:9778

- Subdominio local: app.localhost

- HTTPS local (cert .crt/.key incluidos en el repo)

### Requisitos necesarios

- Haber seguido la parte base
- Docker + Docker Compose
- Java 17 + Maven (solo para empaquetar el JAR) (la versión se puede modificar en el pom y en Dockerfile)

### ▶️ Ejecución

- Añadir el subdominio a tú hosts

En C:\Windows\System32\drivers\etc\hosts, añadir:

  `127.0.0.1 app.localhost`

- Instalar el certificado .crt , ya creado en la carpeta nginx/certs como raíz de confianza, para evitar conflictos por privacidad en el navegador :

 1. Doble clic o clic derecho en el nginx/certs/app.localhost.crt 
 2. Instalar certificado:
	* Equipo local
	* Colocar todos los certificados en el siguiente almacén
	* Entidades de certificación raíz de confianza
	* Finalizar
	* Reinicia el navegador
 3. Para arrancar en la terminal:

  ```bash
   docker compose down (solo si antes tenías la base levantada)
   mvn -DskipTests package 
   docker compose --profile extra up --build 

 4. Abrir el navegador con: 

  ```bash
   https://app.localhost

⚠️ Nota : 
Aunque el frontal opcional expone https://app.localhost, el callback de OAuth sigue siendo http://localhost:9778/login/oauth2/code/google, porque Google solo permite localhost en HTTP para entornos locales. No es necesario registrar https://app.localhost/login/oauth2/code/google
 
### 🧪 El flujo esperado es el mismo que en la base (login con Google + persistencia), pero ahora servido en https://app.localhost

### 📂 Estructura importante 

- nginx/conf.d/app.conf: archivo con las variables y valores para redirigir la IP
- nginx/certs: certificados locales necesarios para https