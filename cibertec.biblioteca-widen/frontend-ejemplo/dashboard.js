// Verificar autenticación al cargar la página
if (!API.isAuthenticated()) {
    window.location.href = 'index.html';
}

// Elementos del DOM
const booksGrid = document.getElementById('books-grid');
const loadingDiv = document.getElementById('loading');
const errorDiv = document.getElementById('error');
const searchInput = document.getElementById('searchInput');

// Variable para almacenar todos los libros
let allBooks = [];

// ==========================================
// FUNCIONES PARA MANEJAR LIBROS
// ==========================================

// Obtener todos los libros
async function fetchBooks() {
    try {
        loadingDiv.style.display = 'block';
        errorDiv.style.display = 'none';
        booksGrid.innerHTML = '';

        const response = await API.fetchWithAuth(`${API.URL}/libro`);
        
        if (!response.ok) {
            throw new Error('Error al obtener los libros');
        }

        const books = await response.json();
        allBooks = books;
        displayBooks(books);
        
        loadingDiv.style.display = 'none';
    } catch (error) {
        console.error('Error:', error);
        loadingDiv.style.display = 'none';
        errorDiv.textContent = `Error: ${error.message}`;
        errorDiv.style.display = 'block';
    }
}

// Mostrar libros en el grid
function displayBooks(books) {
    booksGrid.innerHTML = '';

    if (books.length === 0) {
        booksGrid.innerHTML = '<p style="text-align: center; color: #666;">No se encontraron libros</p>';
        return;
    }

    books.forEach(book => {
        const bookCard = createBookCard(book);
        booksGrid.appendChild(bookCard);
    });
}

// Crear tarjeta de libro
function createBookCard(book) {
    const card = document.createElement('div');
    card.className = 'book-card';
    
    const isAvailable = book.cant_dispon > 0;
    const availabilityClass = isAvailable ? 'available' : 'not-available';
    const availabilityText = isAvailable ? 
        `✅ Disponible (${book.cant_dispon})` : 
        '❌ No disponible';

    card.innerHTML = `
        <h3>${book.titulo}</h3>
        <p><strong>Autor:</strong> ${book.autor}</p>
        <p><strong>Editorial:</strong> ${book.editorial}</p>
        <p><strong>Año:</strong> ${book.ano_public}</p>
        <p><strong>Género:</strong> ${book.genero}</p>
        <p><strong>ISBN:</strong> ${book.isbn}</p>
        <p><strong>Categoría:</strong> ${book.categoria}</p>
        <div class="availability ${availabilityClass}">
            ${availabilityText}
        </div>
        ${isAvailable ? `<button onclick="reservarLibro(${book.id})">Reservar</button>` : ''}
    `;

    return card;
}

// Buscar libros
function searchBooks(query) {
    const filtered = allBooks.filter(book => 
        book.titulo.toLowerCase().includes(query.toLowerCase()) ||
        book.autor.toLowerCase().includes(query.toLowerCase()) ||
        book.categoria.toLowerCase().includes(query.toLowerCase())
    );
    displayBooks(filtered);
}

// Reservar libro
async function reservarLibro(idLibro) {
    if (!confirm('¿Deseas reservar este libro?')) {
        return;
    }

    try {
        const fechaInicio = new Date();
        const fechaFin = new Date();
        fechaFin.setDate(fechaFin.getDate() + 7); // 7 días de préstamo

        const reservaData = {
            usuario: {
                id: getUsuarioIdFromToken()
            },
            libro: {
                id: idLibro
            },
            fch_solicitud: fechaInicio.toISOString().split('T')[0],
            fch_inicio: fechaInicio.toISOString().split('T')[0],
            fch_fin: fechaFin.toISOString().split('T')[0],
            estado_reserva: 'Pendiente'
        };

        const response = await API.fetchWithAuth(`${API.URL}/reserva`, {
            method: 'POST',
            body: JSON.stringify(reservaData)
        });

        if (!response.ok) {
            throw new Error('Error al crear la reserva');
        }

        alert('✅ Reserva creada exitosamente!');
        fetchBooks(); // Recargar libros
    } catch (error) {
        alert(`❌ Error: ${error.message}`);
        console.error('Error:', error);
    }
}

// Obtener ID de usuario desde el token (simplificado)
function getUsuarioIdFromToken() {
    try {
        const token = API.getToken();
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.userId || 1; // Retornar userId del token
    } catch (error) {
        console.error('Error al decodificar token:', error);
        return 1; // ID por defecto
    }
}

// ==========================================
// EVENT LISTENERS
// ==========================================

// Búsqueda en tiempo real
searchInput.addEventListener('input', (e) => {
    searchBooks(e.target.value);
});

// Hacer la función global para que funcione desde el HTML
window.reservarLibro = reservarLibro;

// ==========================================
// INICIALIZACIÓN
// ==========================================

// Cargar libros al iniciar
fetchBooks();


