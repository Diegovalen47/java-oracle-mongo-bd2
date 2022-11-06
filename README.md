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
