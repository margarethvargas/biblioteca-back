# 🌐 Frontend de Ejemplo - Sistema de Biblioteca

## 📁 Archivos Incluidos

```
frontend-ejemplo/
├── index.html          # Página de login/registro
├── app.js             # Lógica de autenticación y API
├── dashboard.html     # Dashboard con lista de libros
├── dashboard.js       # Lógica del dashboard
└── README.md          # Este archivo
```

---

## 🚀 Cómo Usar

### Opción 1: Usar Live Server (Recomendado)

1. **Instalar Live Server en VS Code:**
   - Abre VS Code
   - Ve a Extensions (Ctrl+Shift+X)
   - Busca "Live Server" de Ritwick Dey
   - Instala la extensión

2. **Abrir el proyecto:**
   - Abre la carpeta `frontend-ejemplo` en VS Code
   - Haz clic derecho en `index.html`
   - Selecciona "Open with Live Server"

3. **Se abrirá automáticamente en tu navegador:**
   ```
   http://127.0.0.1:5500/index.html
   ```

### Opción 2: Abrir Directamente

1. **Doble clic en `index.html`**
   - El archivo se abrirá en tu navegador predeterminado
   - Funcionará perfectamente porque CORS está configurado en el backend

---

## 🔐 Credenciales de Prueba

### Bibliotecario
```
Email:    admin@biblioteca.com
Password: admin123
```

### Para crear un nuevo usuario
Usa el formulario de registro en la página principal.

---

## 📝 Funcionalidades Implementadas

### ✅ Página de Login/Registro (`index.html`)
- Formulario de inicio de sesión
- Formulario de registro
- Validación de formularios
- Mensajes de éxito/error
- Cambio entre formularios
- Almacenamiento de token JWT en localStorage

### ✅ Dashboard (`dashboard.html`)
- Lista de todos los libros disponibles
- Búsqueda en tiempo real por título, autor o categoría
- Tarjetas de libros con información completa
- Indicador de disponibilidad
- Botón para reservar libros
- Cerrar sesión
- Protección de ruta (requiere autenticación)

---

## 🔧 Configuración de la API

La URL de la API está configurada en `app.js`:

```javascript
const API_URL = 'http://localhost:4000/api';
```

Si tu API corre en otro puerto o URL, cámbialo aquí.

---

## 📡 Endpoints Utilizados

### Públicos
- `POST /api/authenticate` - Login
- `POST /api/register` - Registro

### Protegidos (requieren token)
- `GET /api/libro` - Listar libros
- `POST /api/reserva` - Crear reserva

---

## 🛠️ Cómo Extender

### Agregar nuevas funciones en `app.js`:

```javascript
// Ejemplo: Listar reservas
async function fetchReservas() {
    const response = await API.fetchWithAuth(`${API.URL}/reserva/lector/${userId}`);
    const reservas = await response.json();
    return reservas;
}
```

### Usar las funciones desde cualquier archivo:

```javascript
// Las funciones están disponibles globalmente en window.API
API.login(email, password);
API.register(userData);
API.logout();
API.fetchWithAuth(url, options);
```

---

## 🎨 Personalizar Estilos

Todos los estilos están en las etiquetas `<style>` dentro de cada HTML. Puedes:

1. **Cambiar colores:**
```css
/* En lugar de purple/violet */
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);

/* Cambiar a azul */
background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
```

2. **Cambiar fuentes:**
```css
font-family: 'Arial', sans-serif;
```

3. **Crear archivo CSS separado:**
```html
<link rel="stylesheet" href="styles.css">
```

---

## 🔒 Seguridad

### Token JWT
- El token se almacena en `localStorage`
- Se incluye automáticamente en todas las peticiones autenticadas
- Si el token expira (401/403), el usuario es redirigido al login

### Ejemplo de petición con token:
```javascript
const response = await fetch('http://localhost:4000/api/libro', {
    headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
});
```

---

## 🐛 Solución de Problemas

### Error: "CORS policy blocked"
**Solución:** Asegúrate de que el backend esté corriendo en `http://localhost:4000`

### Error: "Failed to fetch"
**Solución:** 
1. Verifica que la API esté corriendo: `docker ps` (debe mostrar MySQL)
2. Verifica el puerto en `app.js`

### Error: "401 Unauthorized"
**Solución:**
1. El token expiró, cierra sesión y vuelve a iniciar
2. Verifica que el token se esté enviando en el header

### No aparecen los libros
**Solución:**
1. Abre la consola del navegador (F12)
2. Verifica si hay errores en la pestaña Console
3. Verifica la pestaña Network para ver las peticiones

---

## 📖 Estructura del Código

### app.js (Funciones Principales)

```javascript
// Configuración
const API_URL = 'http://localhost:4000/api';

// Funciones de UI
showMessage(text, type)  // Mostrar mensajes
showLogin()              // Mostrar formulario de login
showRegister()           // Mostrar formulario de registro

// Funciones de API
login(email, password)   // Iniciar sesión
register(userData)       // Registrarse
getToken()              // Obtener token almacenado
isAuthenticated()       // Verificar si está autenticado
logout()                // Cerrar sesión
fetchWithAuth(url, opts) // Hacer petición autenticada
```

### dashboard.js (Funciones del Dashboard)

```javascript
fetchBooks()            // Obtener todos los libros
displayBooks(books)     // Mostrar libros en el grid
createBookCard(book)    // Crear tarjeta de libro
searchBooks(query)      // Buscar libros
reservarLibro(idLibro)  // Reservar un libro
```

---

## 🎯 Próximos Pasos

### Funcionalidades que puedes agregar:

1. **Mis Reservas:**
```javascript
// En dashboard.html, agregar botón:
<button onclick="verMisReservas()">Mis Reservas</button>

// En dashboard.js:
async function verMisReservas() {
    const userId = getUsuarioIdFromToken();
    const response = await API.fetchWithAuth(`${API.URL}/reserva/lector/${userId}`);
    const reservas = await response.json();
    // Mostrar reservas...
}
```

2. **Panel de Administración (Bibliotecarios):**
   - Crear/Editar/Eliminar libros
   - Gestionar préstamos
   - Ver todas las reservas

3. **Filtros Avanzados:**
   - Por categoría
   - Por autor
   - Por disponibilidad

4. **Paginación:**
   - Mostrar 10 libros por página
   - Botones siguiente/anterior

---

## 📚 Recursos Útiles

- **Fetch API:** https://developer.mozilla.org/es/docs/Web/API/Fetch_API
- **LocalStorage:** https://developer.mozilla.org/es/docs/Web/API/Window/localStorage
- **JWT:** https://jwt.io/
- **Live Server:** https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer

---

## ✅ Checklist

- [x] Login funcional
- [x] Registro funcional
- [x] Lista de libros
- [x] Búsqueda de libros
- [x] Reservar libros
- [x] Cerrar sesión
- [x] Protección de rutas
- [x] Manejo de tokens
- [ ] Ver mis reservas (por implementar)
- [ ] Panel de administración (por implementar)
- [ ] Cancelar reservas (por implementar)

---

## 🎉 ¡Listo para usar!

1. Asegúrate de que el backend esté corriendo
2. Abre `index.html` con Live Server
3. Inicia sesión con: admin@biblioteca.com / admin123
4. ¡Explora el sistema!

---

**¿Necesitas ayuda?** Revisa la consola del navegador (F12) para ver los logs y errores.


