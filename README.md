# Tercer trabajo Bases de Datos II

Crear usuario con privilegios en oracle:
SQL> conn system
ingresar contraseña del ususario system

CREATE USER usuario IDENTIFIED BY password

GRANT CONNECT, RESOURCE, DBA TO usuario;
GRANT CREATE ANY TABLE TO usuario;
GRANT CREATE ANY PROCEDURE TO usuario;
GRANT CREATE ANY VIEW TO usuario;
GRANT UNLIMITED TABLESPACE TO sacastrot;

DROP TABLE empleado;

CREATE TABLE empleado(
  codigo NUMBER(3) PRIMARY KEY, 
  nom VARCHAR2(10) NOT NULL,
  salario NUMBER(3) NOT NULL
);

INSERT INTO empleado VALUES(10, 'Ana',100);
INSERT INTO empleado VALUES(22, 'Lisa', 200);
INSERT INTO empleado VALUES(33, 'Bill', 300);

COMMIT;


Acá está el archivo tnsnames.ora, donde se puede consultar el HOST
C:\oraclexe\app\oracle\product\11.2.0\server\network\ADMIN

incluir al project structure, el jar que esta en la carpeta lib


Datos de prueba

INSERT INTO sucursal (codigo, nombre) VALUES (1, 'Exito Robledo');
INSERT INTO sucursal (codigo, nombre) VALUES (2, 'Exito Envigado');
INSERT INTO sucursal (codigo, nombre) VALUES (3, 'Jumbo');
INSERT INTO sucursal (codigo, nombre) VALUES (4, 'Exito San Antonio');

INSERT INTO vendedor (codigo, nombre) VALUES (1, 'Ellen Barazzo');
INSERT INTO vendedor (codigo, nombre) VALUES (2, 'Chucky Damian');

INSERT INTO cliente (codigo, nombre, genero) VALUES (1, 'Santiago', 'm');
INSERT INTO cliente (codigo, nombre, genero) VALUES (2, 'Manuela', 'f');
INSERT INTO cliente (codigo, nombre, genero) VALUES (3, 'Camila', 'f');
INSERT INTO cliente (codigo, nombre, genero) VALUES (4, 'Michael', 'm');
INSERT INTO cliente (codigo, nombre, genero) VALUES (5, 'Valentin', 'm');

INSERT INTO producto (codigo, nombre, tipo, marca) VALUES (1, 'Salchichas', 'Carnicos', 'Colanta');
INSERT INTO producto (codigo, nombre, tipo, marca) VALUES (2, 'Chorizos', 'Carnicos', 'Colanta');
INSERT INTO producto (codigo, nombre, tipo, marca) VALUES (3, 'Queso', 'Lacteos', 'Colanta');
INSERT INTO producto (codigo, nombre, tipo, marca) VALUES (4, 'Quesito', 'Lacteos', 'Alpina');
INSERT INTO producto (codigo, nombre, tipo, marca) VALUES (5, 'Cerdo', 'Carnicos', 'Alpina');

-- Exito Robledo, Ellen Barazzo, Valentin, (4, Quesito), (50 unidades)
-- Exito Robledo, Chucky Damian, Valentin, (5, Cerdo), (20 unidades)
-- Jumbo, Ellen Barazzo, Michael, (2, Chorizos), (5 unidades)
-- Jumbo, Ellen Barazzo, Santiago, (1, Salchichas), (30 unidades)
-- Exito San Antonio, Chucky Damian, Santiago, (1, Salchichas), (70 unidades)
-- Exito San Antonio, Ellen Barazzo, Camila, (4, Quesito), (1 unidades)
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (1, 1, 1, 5, 4, 50);
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (2, 1, 2, 5, 5, 20);
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (3, 3, 1, 4, 2, 5);
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (4, 3, 1, 1, 1, 30);
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (5, 4, 2, 1, 1, 70);
INSERT INTO venta (codventa, sucursal, vendedor, cliente, producto, nro_unidades) VALUES (6, 4, 1, 3, 4, 1);

COMMIT;

-- Colección marcas
select p.marca, s.nombre, p.tipo, sum(nro_unidades) as TotalUni
from
venta v join producto p on p.codigo = v.producto
join sucursal s on s.codigo = v.sucursal
group by p.marca, s.nombre, p.tipo order by p.marca;

-- Colección Géneros
select c.genero, s.nombre, p.tipo, sum(nro_unidades) as TotalUni
from
cliente c join venta v on c.codigo = v.cliente
join producto p on p.codigo = v.producto
join sucursal s on s.codigo = v.sucursal
group by c.genero, s.nombre, p.tipo order by c.genero;

-- Colección Vendedores
select vd.codigo, vd.nombre, s.nombre as sucursal, sum(nro_unidades) as TotalUni
from
vendedor vd join venta v on v.vendedor = vd.codigo
join sucursal s on v.sucursal = s.codigo
group by vd.codigo, vd.nombre, s.nombre order by vd.codigo;