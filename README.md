# Prueba técnica – SSO con Google + Docker

Este proyecto implementa un login con **Google SSO** usando **Spring Boot** y se entrega dockerizado para que pueda ejecutarse con un simple `docker compose up`.

---

## 🚀 Requisitos previos
- [Java 17+](https://adoptium.net/) (solo si quieres ejecutar sin Docker)
- [Maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ⚙️ Configuración de variables de entorno
El proyecto usa variables de entorno para configurar el acceso a Google OAuth2.


##### !🔹En el .env.example rellenar con tus credenciales de Google:

GOOGLE_CLIENT_ID → ID de cliente OAuth2

GOOGLE_CLIENT_SECRET → Secreto OAuth2

REMEMBER_ME_KEY → Una cadena aleatoria segura

APP_BASE_URL → http://localhost:9778



### ⚠️ Nota importante:

Para que funcione el login con Google debes crear tus propias credenciales en [Google Cloud Console](https://console.cloud.google.com/welcome?project=seventh-sunbeam-471715-g2):

- Tipo de credencial: OAuth 2.0 Client ID

- Redirect URI autorizado:


http://localhost:9778/login/oauth2/code/google

---

## ▶️ Ejecución con Docker

- Empaquetar la aplicación en un JAR:

  ```bash
   mvn -DskipTests package 

- Levantar el contenedor:

   ```bash
   docker compose up

- Abrir en el navegador:

   ```bash
   http://localhost:9778
 

### 🧪 Flujo esperado

1. En la pantalla inicial aparece el botón Iniciar sesión con Google.

2. Tras iniciar sesión, se muestra el nombre del usuario autenticado.

3. La aplicación recuerda la sesión (persistencia) - la próxima vez que abras http://localhost:9778, no necesitarás loguearte de nuevo.

---

### 📂 Estructura

- Dockerfile → construye la imagen con el JAR de Spring Boot.

- docker-compose.yml → define el servicio prodigiosovolcan.

- .env.example → ejemplo de variables de entorno.

- src/ → código fuente de la aplicación.