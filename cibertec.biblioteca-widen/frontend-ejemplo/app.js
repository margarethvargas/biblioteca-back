// Configuración de la API
const API_URL = 'http://localhost:4000/api';

// Elementos del DOM
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const messageDiv = document.getElementById('message');
const tokenDisplay = document.getElementById('token-display');
const tokenValue = document.getElementById('token-value');

// ==========================================
// FUNCIONES DE UI
// ==========================================

function showMessage(text, type) {
    messageDiv.textContent = text;
    messageDiv.className = `message ${type}`;
    messageDiv.style.display = 'block';
    
    // Ocultar mensaje después de 5 segundos
    setTimeout(() => {
        messageDiv.style.display = 'none';
    }, 5000);
}

function showLogin() {
    document.getElementById('login-form').classList.remove('hidden');
    document.getElementById('register-form').classList.add('hidden');
}

function showRegister() {
    document.getElementById('login-form').classList.add('hidden');
    document.getElementById('register-form').classList.remove('hidden');
}

function showToken(token) {
    tokenValue.textContent = token;
    tokenDisplay.classList.remove('hidden');
}

// ==========================================
// FUNCIONES DE API
// ==========================================

// LOGIN
async function login(email, password) {
    try {
        const response = await fetch(`${API_URL}/authenticate`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            throw new Error('Credenciales inválidas');
        }

        const data = await response.json();
        
        // Guardar token en localStorage
        localStorage.setItem('token', data.token);
        
        showMessage('✅ Login exitoso! Token guardado', 'success');
        showToken(data.token);
        
        // Redirigir después de 2 segundos
        setTimeout(() => {
            window.location.href = 'dashboard.html';
        }, 2000);

        return data;
    } catch (error) {
        showMessage(`❌ Error: ${error.message}`, 'error');
        throw error;
    }
}

// REGISTRO
async function register(userData) {
    try {
        const response = await fetch(`${API_URL}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorData = await response.text();
            throw new Error(errorData || 'Error al registrarse');
        }

        const data = await response.json();
        showMessage('✅ Registro exitoso! Ahora puedes iniciar sesión', 'success');
        
        // Cambiar a formulario de login después de 2 segundos
        setTimeout(() => {
            showLogin();
        }, 2000);

        return data;
    } catch (error) {
        showMessage(`❌ Error: ${error.message}`, 'error');
        throw error;
    }
}

// ==========================================
// EVENT LISTENERS
// ==========================================

// LOGIN FORM SUBMIT
loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    await login(email, password);
});

// REGISTER FORM SUBMIT
registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const userData = {
        nombre: document.getElementById('reg-nombre').value,
        apellido: document.getElementById('reg-apellido').value,
        dni: document.getElementById('reg-dni').value,
        telefono: '999999999', // Valor por defecto
        direccion: 'Sin dirección', // Valor por defecto
        email: document.getElementById('reg-email').value,
        password: document.getElementById('reg-password').value,
        role: document.getElementById('reg-role').value
    };

    await register(userData);
});

// ==========================================
// FUNCIONES ÚTILES PARA OTROS ARCHIVOS
// ==========================================

// Obtener token almacenado
function getToken() {
    return localStorage.getItem('token');
}

// Verificar si está autenticado
function isAuthenticated() {
    return !!getToken();
}

// Cerrar sesión
function logout() {
    localStorage.removeItem('token');
    window.location.href = 'index.html';
}

// Hacer petición autenticada
async function fetchWithAuth(url, options = {}) {
    const token = getToken();
    
    if (!token) {
        throw new Error('No hay token de autenticación');
    }

    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
        ...options.headers
    };

    const response = await fetch(url, {
        ...options,
        headers
    });

    if (response.status === 401 || response.status === 403) {
        // Token inválido o expirado
        logout();
        throw new Error('Sesión expirada. Por favor inicia sesión nuevamente');
    }

    return response;
}

// Exportar funciones para uso global
window.API = {
    URL: API_URL,
    login,
    register,
    getToken,
    isAuthenticated,
    logout,
    fetchWithAuth
};


