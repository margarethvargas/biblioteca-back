# 🐳 Guía de Despliegue con Docker - Sistema de Biblioteca

## 📋 Requisitos Previos

- ✅ Docker Desktop instalado y corriendo
- ✅ Java 17 o superior
- ✅ Gradle (incluido en el proyecto con gradlew)

---

## 🚀 Iniciar el Sistema

### 1. Levantar Base de Datos MySQL

```bash
# Desde el directorio del proyecto
docker-compose up -d
```

**Verificar que MySQL esté corriendo:**
```bash
docker ps
```

Deberías ver:
```
CONTAINER ID   IMAGE       PORTS                               NAMES
1b2e55c84c28   mysql:8.0   0.0.0.0:3306->3306/tcp             mysql-biblioteca
```

### 2. Levantar la API de Spring Boot

**Opción A - Modo Normal (con logs visibles):**
```bash
.\gradlew.bat bootRun
```

**Opción B - Modo Producción (en segundo plano):**
```bash
.\gradlew.bat build
java -jar build\libs\cibertec.biblioteca-widen-0.0.1-SNAPSHOT.jar
```

### 3. Verificar que la API está corriendo

La API estará disponible en: **http://localhost:4000**

**Prueba rápida:**
```bash
# PowerShell
Invoke-WebRequest http://localhost:4000/api/libro

# o abre en el navegador
http://localhost:4000/api/libro
```

---

## 🔧 Configuración

### Base de Datos MySQL

- **Host:** localhost
- **Puerto:** 3306
- **Base de Datos:** biblioteca
- **Usuario:** root
- **Contraseña:** 1234

### API Spring Boot

- **Puerto:** 4000
- **Base URL:** http://localhost:4000
- **JWT Secret:** your_jwt_secret
- **JWT Expiration:** 3600 segundos (1 hora)

---

## 📡 Endpoints Disponibles

### Públicos (sin autenticación)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/authenticate | Iniciar sesión |
| POST | /api/register | Registrarse |

**Ejemplo de Login:**
```bash
curl -X POST http://localhost:4000/api/authenticate `
  -H "Content-Type: application/json" `
  -d '{\"email\":\"admin@biblioteca.com\",\"password\":\"admin123\"}'
```

### Protegidos (requieren JWT Token)

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | /api/libro | Listar libros | Lector, Bibliotecario |
| GET | /api/libro?tipo_material=Libro&nombre=Java | Buscar libros | Lector, Bibliotecario |
| POST | /api/libro | Crear libro | Bibliotecario |
| PUT | /api/libro | Actualizar libro | Bibliotecario |
| DELETE | /api/libro/{id} | Eliminar libro | Bibliotecario |
| POST | /api/reserva | Crear reserva | Lector, Bibliotecario |
| GET | /api/reserva | Listar todas las reservas | Bibliotecario |
| GET | /api/reserva/lector/{idLector} | Mis reservas | Lector |
| PUT | /api/reserva | Actualizar reserva | Bibliotecario |
| DELETE | /api/reserva/{id} | Cancelar reserva | Lector, Bibliotecario |
| POST | /api/prestamo | Crear préstamo | Bibliotecario |
| GET | /api/prestamo | Listar préstamos | Bibliotecario |

**Ejemplo con Token:**
```bash
curl -X GET http://localhost:4000/api/libro `
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## 🛠️ Comandos Útiles

### Docker

**Ver logs de MySQL:**
```bash
docker logs mysql-biblioteca
docker logs mysql-biblioteca --follow  # Ver logs en tiempo real
```

**Detener MySQL:**
```bash
docker-compose down
```

**Detener y eliminar datos:**
```bash
docker-compose down -v
```

**Reiniciar MySQL:**
```bash
docker-compose restart
```

**Conectarse a MySQL desde línea de comandos:**
```bash
docker exec -it mysql-biblioteca mysql -uroot -p1234 biblioteca
```

### Spring Boot

**Compilar el proyecto:**
```bash
.\gradlew.bat build
```

**Limpiar y compilar:**
```bash
.\gradlew.bat clean build
```

**Ver tareas disponibles:**
```bash
.\gradlew.bat tasks
```

**Ejecutar tests:**
```bash
.\gradlew.bat test
```

---

## 🗄️ Gestión de Base de Datos

### Conectarse a MySQL

```bash
docker exec -it mysql-biblioteca mysql -uroot -p1234 biblioteca
```

### Comandos SQL Útiles

```sql
-- Ver todas las tablas
SHOW TABLES;

-- Ver estructura de tabla
DESCRIBE usuario;

-- Ver datos de usuarios
SELECT * FROM usuario;

-- Crear un usuario bibliotecario de prueba
INSERT INTO usuario (nombre, apellido, dni, telefono, direccion, email, password, role) 
VALUES ('Admin', 'Biblioteca', '12345678', '987654321', 'Av. Principal 123', 
        'admin@biblioteca.com', '$2a$10$ejemplo_hash_bcrypt', 'BIBLIOTECARIO');

-- Ver reservas con información de libros
SELECT r.*, l.titulo, u.nombre, u.apellido 
FROM reserva r 
JOIN libro l ON r.id_libro = l.id 
JOIN usuario u ON r.id_lector = u.id;
```

### Backup de Base de Datos

```bash
# Exportar
docker exec mysql-biblioteca mysqldump -uroot -p1234 biblioteca > backup.sql

# Importar
docker exec -i mysql-biblioteca mysql -uroot -p1234 biblioteca < backup.sql
```

---

## ⚠️ Solución de Problemas

### Problema: "Port 3306 is already in use"

**Solución:** Ya tienes MySQL corriendo localmente

```bash
# Opción 1: Detener MySQL local
net stop MySQL80

# Opción 2: Cambiar puerto en docker-compose.yml
ports:
  - "3307:3306"  # Cambiar puerto externo a 3307

# Actualizar application.properties
spring.datasource.url = jdbc:mysql://localhost:3307/biblioteca?serverTimezone=UTC
```

### Problema: "Cannot connect to database"

**Solución 1:** Verificar que MySQL esté corriendo
```bash
docker ps
docker logs mysql-biblioteca
```

**Solución 2:** Esperar a que MySQL termine de inicializarse (30-60 segundos)

**Solución 3:** Verificar credenciales en application.properties

### Problema: "Port 4000 is already in use"

**Solución:** Cambiar puerto en application.properties
```properties
server.port=4001
```

### Problema: Gradle falla al compilar

**Solución:**
```bash
# Limpiar caché de Gradle
.\gradlew.bat clean

# Actualizar wrapper
.\gradlew.bat wrapper --gradle-version 8.5

# Verificar versión de Java
java -version  # Debe ser 17 o superior
```

---

## 🔐 Seguridad

### Cambiar Credenciales para Producción

**1. Cambiar contraseña de MySQL:**

En `docker-compose.yml`:
```yaml
environment:
  MYSQL_ROOT_PASSWORD: tu_password_segura_aqui
```

En `application.properties`:
```properties
spring.datasource.password = tu_password_segura_aqui
```

**2. Cambiar JWT Secret:**

En `application.properties`:
```properties
jwt.secret=tu_secret_seguro_de_minimo_256_bits_aqui
```

**3. Cambiar tiempo de expiración del token:**

```properties
jwt.expiration=7200  # 2 horas en segundos
```

---

## 📊 Estado del Sistema

### Ver Estado de Servicios

```bash
# Docker
docker ps
docker stats

# API Spring Boot
# Verificar en http://localhost:4000/actuator/health
# (requiere agregar spring-boot-starter-actuator)
```

---

## 🎯 Flujo Completo de Inicio

```bash
# 1. Levantar MySQL
docker-compose up -d

# 2. Verificar que MySQL esté listo
docker logs mysql-biblioteca --follow
# Esperar mensaje: "ready for connections"

# 3. Levantar API
.\gradlew.bat bootRun

# 4. Verificar que la API esté lista
# Esperar mensaje: "Started Application in X.XXX seconds"

# 5. Probar endpoint público
Invoke-WebRequest http://localhost:4000/api/libro
```

---

## 📚 Recursos Adicionales

- **Documentación Spring Boot:** https://spring.io/projects/spring-boot
- **Documentación MySQL:** https://dev.mysql.com/doc/
- **Documentación Docker:** https://docs.docker.com/
- **JWT.io:** https://jwt.io/

---

## 🧪 Testing con Postman

1. Importar colección de Postman (crear archivo JSON)
2. Configurar variables de entorno:
   - `base_url`: http://localhost:4000
   - `token`: (se obtiene al hacer login)

3. Crear requests:
   - Login → Obtener token → Guardarlo en variable
   - Usar {{token}} en header de requests protegidos

---

## 📝 Notas Finales

- La base de datos se crea automáticamente gracias a `spring.jpa.hibernate.ddl-auto = update`
- Los datos persisten en un volumen Docker (`mysql-data`)
- Para reset completo: `docker-compose down -v` + `.\gradlew.bat clean`

---

## ✅ Checklist de Despliegue

- [ ] Docker Desktop está corriendo
- [ ] MySQL container está UP
- [ ] Base de datos `biblioteca` existe
- [ ] API Spring Boot está corriendo en puerto 4000
- [ ] Endpoint público responde: http://localhost:4000/api/libro
- [ ] JWT authentication funciona correctamente

---

**¡Sistema listo para usar! 🎉**

Para cualquier problema, revisar los logs:
- MySQL: `docker logs mysql-biblioteca`
- Spring Boot: Ver consola donde se ejecutó `gradlew bootRun`


