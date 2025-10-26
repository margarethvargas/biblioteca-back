# 🔐 Credenciales de Usuarios de Prueba

## ✅ Usuarios Disponibles

### 👨‍💼 BIBLIOTECARIO 1 (Admin)
```
Email:    admin@biblioteca.com
Password: admin123
Rol:      BIBLIOTECARIO
```

### 👨‍💼 BIBLIOTECARIO 2 (María)
```
Email:    maria@biblioteca.com
Password: maria123
Rol:      BIBLIOTECARIO
```

---

## 🧪 Probar Login (PowerShell)

### Opción 1: Login como Admin
```powershell
$body = @{
    email = "admin@biblioteca.com"
    password = "admin123"
} | ConvertTo-Json

$response = Invoke-WebRequest -Uri "http://localhost:4000/api/authenticate" `
  -Method POST `
  -ContentType "application/json" `
  -Body $body `
  -UseBasicParsing

$response.Content
```

### Opción 2: Login como María
```powershell
$body = @{
    email = "maria@biblioteca.com"
    password = "maria123"
} | ConvertTo-Json

$response = Invoke-WebRequest -Uri "http://localhost:4000/api/authenticate" `
  -Method POST `
  -ContentType "application/json" `
  -Body $body `
  -UseBasicParsing

$response.Content
```

---

## 🔑 Usar el Token JWT

Una vez que obtengas el token, úsalo en las peticiones:

```powershell
# Guardar token (reemplaza con tu token real)
$token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."

# Hacer petición con token
$headers = @{
    "Authorization" = "Bearer $token"
    "Content-Type" = "application/json"
}

# Listar libros
Invoke-WebRequest -Uri "http://localhost:4000/api/libro" `
  -Method GET `
  -Headers $headers `
  -UseBasicParsing
```

---

## 📝 Crear Más Usuarios

### Crear un Lector
```powershell
$nuevoLector = @{
    nombre = "Juan"
    apellido = "Pérez"
    dni = "77777777"
    telefono = "999777888"
    direccion = "Calle Test 100"
    email = "lector@test.com"
    password = "lector123"
    role = "LECTOR"
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:4000/api/register" `
  -Method POST `
  -ContentType "application/json" `
  -Body $nuevoLector `
  -UseBasicParsing
```

---

## 🧪 Probar con cURL (alternativa)

### Login
```bash
curl -X POST http://localhost:4000/api/authenticate \
  -H "Content-Type: application/json" \
  -d "{\"email\":\"admin@biblioteca.com\",\"password\":\"admin123\"}"
```

### Usar Token
```bash
# Guardar token
TOKEN="tu_token_aqui"

# Listar libros
curl -X GET http://localhost:4000/api/libro \
  -H "Authorization: Bearer $TOKEN"
```

---

## 🌐 Probar con Postman

1. **Login:**
   - Method: POST
   - URL: `http://localhost:4000/api/authenticate`
   - Body (JSON):
     ```json
     {
       "email": "admin@biblioteca.com",
       "password": "admin123"
     }
     ```
   - Click "Send"
   - Copiar el `token` del response

2. **Usar Token:**
   - Method: GET
   - URL: `http://localhost:4000/api/libro`
   - Headers:
     - Key: `Authorization`
     - Value: `Bearer [tu_token_aqui]`
   - Click "Send"

---

## 📊 Datos Disponibles

Tu base de datos ya tiene **15 libros** precargados:
- Cien Años de Soledad
- El Quijote
- 1984
- Orgullo y Prejuicio
- Matar a un ruiseñor
- El Gran Gatsby
- Crimen y Castigo
- En busca del tiempo perdido
- El Principito
- La Odisea
- La Divina Comedia
- Donde los árboles cantan
- La Sombra del Viento
- El amor en los tiempos del cólera
- Frankenstein

Puedes listarlos con:
```
GET http://localhost:4000/api/libro
```

---

## ⚠️ Notas Importantes

1. **Los usuarios antiguos (juan.perez@example.com, etc.) NO funcionan** porque:
   - No tienen roles asignados (role = NULL)
   - Las contraseñas están en texto plano (no encriptadas)

2. **Solo usa los usuarios nuevos:**
   - admin@biblioteca.com / admin123
   - maria@biblioteca.com / maria123

3. **Para crear más usuarios:**
   - Usa el endpoint `/api/register`
   - Asegúrate de incluir el campo `role` ("LECTOR" o "BIBLIOTECARIO")
   - Las contraseñas se encriptarán automáticamente

---

## 🔧 Solución de Problemas

### Error: "JWT Token no comienza con Bearer String"
**Solución:** Asegúrate de incluir "Bearer " antes del token:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Error: "INVALID_CREDENTIALS"
**Solución:** Verifica que estés usando las credenciales correctas:
- admin@biblioteca.com / admin123
- maria@biblioteca.com / maria123

### Error: "403 Forbidden"
**Solución:** Tu rol no tiene permisos para esa operación. Usa un usuario BIBLIOTECARIO.

---

## ✅ Resumen Rápido

**Para Autenticarte:**
1. POST a `/api/authenticate` con email y password
2. Guarda el token que recibes
3. Usa el token en el header `Authorization: Bearer {token}`
4. Ahora puedes acceder a los endpoints protegidos

**Credenciales que funcionan:**
- admin@biblioteca.com / admin123 ✅
- maria@biblioteca.com / maria123 ✅


