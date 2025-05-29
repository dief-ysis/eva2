-- Tabla para el microservicio de USUARIOS (entidad 'usuario')
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY, -- Usamos BIGSERIAL para claves primarias auto-incrementales en PostgreSQL
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL, -- Almacenar contraseñas hasheadas
    rol VARCHAR(50) DEFAULT 'CLIENTE', -- Ejemplo: 'ADMIN', 'CLIENTE'
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para el microservicio de INVENTARIO (entidades 'inventario' y 'producto')
-- Esta es la tabla que representa el inventario de productos.
CREATE TABLE IF NOT EXISTS productos (
    id BIGSERIAL PRIMARY KEY, -- Usamos BIGSERIAL para claves primarias auto-incrementales en PostgreSQL
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla para el microservicio de CARRITO (entidad 'carrito')
-- Esta tabla representa los ítems individuales dentro de los carritos de los usuarios.
CREATE TABLE IF NOT EXISTS carritos (
    id BIGSERIAL PRIMARY KEY, -- Usamos BIGSERIAL para claves primarias auto-incrementales en PostgreSQL
    usuario_id BIGINT NOT NULL, -- ID del usuario al que pertenece este ítem del carrito
    producto_id BIGINT NOT NULL, -- ID del producto añadido al carrito
    cantidad INT NOT NULL CHECK (cantidad >= 1),
    precio_unitario_al_momento DECIMAL(10, 2) NOT NULL, -- Precio del producto cuando se añadió al carrito
    fecha_adicion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha en que se añadió este ítem al carrito
    estado VARCHAR(50) DEFAULT 'ACTIVO', -- Ejemplo: 'ACTIVO', 'COMPRADO', 'ABANDONADO'

    -- Claves foráneas para asegurar la integridad referencial dentro de la misma base de datos
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE RESTRICT
);
