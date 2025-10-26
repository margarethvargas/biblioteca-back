-- Script para limpiar tablas no utilizadas en la base de datos 'biblioteca'
-- Ejecutar con precaución - hacer backup antes

USE biblioteca;

-- Verificar qué tablas existen actualmente
SHOW TABLES;

-- Tablas que DEBEN existir y mantenerse:
-- usuario, libro, reserva, prestamo

-- Primero eliminar las restricciones de clave foránea que referencian a bibliotecario
SET FOREIGN_KEY_CHECKS = 0;

-- Si existe la tabla 'bibliotecario', eliminarla (ya no se usa)
DROP TABLE IF EXISTS bibliotecario;

-- Rehabilitar las restricciones de clave foránea
SET FOREIGN_KEY_CHECKS = 1;

-- Verificar nuevamente las tablas después de la limpieza
SHOW TABLES;

-- Mostrar estructura de las tablas activas para confirmar
DESCRIBE usuario;
DESCRIBE libro;
DESCRIBE reserva;
DESCRIBE prestamo;