# Script para crear usuarios de prueba en la API

$baseUrl = "http://localhost:4000"

Write-Host "Creando usuarios de prueba..." -ForegroundColor Cyan

# Usuario 1: Bibliotecario
$biblio1 = @{
    nombre = "Admin"
    apellido = "Biblioteca"
    dni = "11111111"
    telefono = "999000111"
    direccion = "Av. Principal 100"
    email = "admin@biblioteca.com"
    password = "admin123"
    role = "BIBLIOTECARIO"
} | ConvertTo-Json

try {
    $response1 = Invoke-WebRequest -Uri "$baseUrl/api/register" `
        -Method POST `
        -ContentType "application/json" `
        -Body $biblio1 `
        -UseBasicParsing
    Write-Host "✓ Usuario Bibliotecario creado: admin@biblioteca.com / admin123" -ForegroundColor Green
} catch {
    Write-Host "✗ Error al crear bibliotecario: $($_.Exception.Message)" -ForegroundColor Red
}

# Usuario 2: Lector
$lector1 = @{
    nombre = "Juan"
    apellido = "Pérez"
    dni = "22222222"
    telefono = "999000222"
    direccion = "Calle Luna 200"
    email = "lector@biblioteca.com"
    password = "lector123"
    role = "LECTOR"
} | ConvertTo-Json

try {
    $response2 = Invoke-WebRequest -Uri "$baseUrl/api/register" `
        -Method POST `
        -ContentType "application/json" `
        -Body $lector1 `
        -UseBasicParsing
    Write-Host "✓ Usuario Lector creado: lector@biblioteca.com / lector123" -ForegroundColor Green
} catch {
    Write-Host "✗ Error al crear lector: $($_.Exception.Message)" -ForegroundColor Red
}

# Usuario 3: Otro Bibliotecario
$biblio2 = @{
    nombre = "María"
    apellido = "García"
    dni = "33333333"
    telefono = "999000333"
    direccion = "Jr. Sol 300"
    email = "maria@biblioteca.com"
    password = "maria123"
    role = "BIBLIOTECARIO"
} | ConvertTo-Json

try {
    $response3 = Invoke-WebRequest -Uri "$baseUrl/api/register" `
        -Method POST `
        -ContentType "application/json" `
        -Body $biblio2 `
        -UseBasicParsing
    Write-Host "✓ Usuario Bibliotecario creado: maria@biblioteca.com / maria123" -ForegroundColor Green
} catch {
    Write-Host "✗ Error al crear bibliotecario: $($_.Exception.Message)" -ForegroundColor Red
}

# Usuario 4: Otro Lector
$lector2 = @{
    nombre = "Carlos"
    apellido = "Rodríguez"
    dni = "44444444"
    telefono = "999000444"
    direccion = "Av. Arequipa 400"
    email = "carlos@biblioteca.com"
    password = "carlos123"
    role = "LECTOR"
} | ConvertTo-Json

try {
    $response4 = Invoke-WebRequest -Uri "$baseUrl/api/register" `
        -Method POST `
        -ContentType "application/json" `
        -Body $lector2 `
        -UseBasicParsing
    Write-Host "✓ Usuario Lector creado: carlos@biblioteca.com / carlos123" -ForegroundColor Green
} catch {
    Write-Host "✗ Error al crear lector: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n==================================" -ForegroundColor Cyan
Write-Host "Resumen de Usuarios Creados" -ForegroundColor Cyan
Write-Host "==================================`n" -ForegroundColor Cyan

Write-Host "BIBLIOTECARIOS:" -ForegroundColor Yellow
Write-Host "  Email: admin@biblioteca.com" -ForegroundColor White
Write-Host "  Password: admin123`n" -ForegroundColor White

Write-Host "  Email: maria@biblioteca.com" -ForegroundColor White
Write-Host "  Password: maria123`n" -ForegroundColor White

Write-Host "LECTORES:" -ForegroundColor Yellow
Write-Host "  Email: lector@biblioteca.com" -ForegroundColor White
Write-Host "  Password: lector123`n" -ForegroundColor White

Write-Host "  Email: carlos@biblioteca.com" -ForegroundColor White
Write-Host "  Password: carlos123`n" -ForegroundColor White

Write-Host "==================================`n" -ForegroundColor Cyan


