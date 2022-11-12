CREATE TABLE sucursal(
    codigo NUMBER(8) PRIMARY KEY,
    nombre VARCHAR2(20) UNIQUE NOT NULL
);

CREATE TABLE vendedor(
    codigo NUMBER(8) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL
);

CREATE TABLE cliente(
    codigo NUMBER(8) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL,
    genero CHAR(1) CHECK (genero IN ('m ', 'f '))
);

CREATE TABLE producto(
    codigo NUMBER(8) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL,
    tipo VARCHAR2(20) NOT NULL,
    marca VARCHAR2(20) NOT NULL
);

CREATE TABLE venta(
    codventa NUMBER(15) PRIMARY KEY,
    sucursal NUMBER(8) NOT NULL REFERENCES sucursal,
    vendedor NUMBER(8) NOT NULL REFERENCES vendedor,
    cliente NUMBER(8) NOT NULL REFERENCES cliente,
    producto NUMBER(8) NOT NULL REFERENCES producto,
    nro_unidades NUMBER(10) NOT NULL CHECK(nro_unidades > 0)
);
