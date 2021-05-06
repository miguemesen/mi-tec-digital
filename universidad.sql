-- crear base de datos

show databases;

-- bases de datos

drop database if exists universidad;
create database universidad;
use universidad;


drop table if exists curso;
drop table if exists profesor;
drop table if exists estudiante;
drop table if exists tutor;

-- tablas


create table curso(
    id int primary key,
    nombre varchar(30),
    departamento varchar(30),
    creditos int
);

create table profesor(
	id int primary key,
    nombre varchar(30),
    apellido varchar(30),
    ciudad varchar(30)
);


create table estudiante(
	id int primary key,
    nombre varchar(30),
    apellido varchar(30),
    fecha_nacimiento timestamp,
    total_creditos int check (total_creditos > 0)
);

create table tutor(
	estudiante_id int,
    profesor_id int, 
	foreign key(estudiante_id) references estudiante(id),
    foreign key(profesor_id) references profesor(id)
);


-- indices
create index estudianteApellidoIndex on estudiante(apellido);
create index profesorApellidoIndex on profesor(apellido);




-- ingresar cursos

insert into curso(id, nombre, departamento, creditos) VALUES (100, 'Bases de Datos I', 'Computacion', 4);
insert into curso(id, nombre, departamento, creditos) VALUES (101, 'Bases de Datos II', 'Computacion', 4);
insert into curso(id, nombre, departamento, creditos) VALUES (102, 'Estructuras de Datos', 'Computacion', 4);
insert into curso(id, nombre, departamento, creditos) VALUES (103, 'Analisis de Algoritmos', 'Computacion', 4);
insert into curso(id, nombre, departamento, creditos) VALUES (104, 'Lenguajes', 'Computacion', 4);
insert into curso(id, nombre, departamento, creditos) VALUES (301, 'Fisica I', 'Fisica', 3);
insert into curso(id, nombre, departamento, creditos) VALUES (302, 'Fisica II', 'Fisica', 3);
insert into curso(id, nombre, departamento, creditos) VALUES (303, 'Fisica III', 'Fisica', 3);
insert into curso(id, nombre, departamento, creditos) VALUES (304, 'Fisica IV', 'Fisica', 3);


-- ingresar profesores

insert into profesor(id, nombre, apellido, ciudad) values(1, 'Martin', 'Flores','Cartago');
insert into profesor(id, nombre, apellido, ciudad) values(2, 'Allan', 'Cascante','Alajuela');
insert into profesor(id, nombre, apellido, ciudad) values(3, 'Albert', 'Einstein','San José');
insert into profesor(id, nombre, apellido, ciudad) values(4, 'Marco', 'Calvo', 'Heredia');
insert into profesor(id, nombre, apellido, ciudad) values(5, 'Jose', 'Herrera', 'Santa Ana');
insert into profesor(id, nombre, apellido, ciudad) values(6, 'Carolina', 'Lizano', 'Escazu');
insert into profesor(id, nombre, apellido, ciudad) values(7, 'Raquel', 'Rodriguez', 'Tibas');


-- ingresa estudiantes

insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(1, 'Steven', 'Alvarado', '2000-01-01 00:00:00', 15);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(2, 'Lermith', 'Biarreta', '2000-01-01 00:00:00', 17);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(3, 'Maria', 'Biarreta','2000-01-01 00:00:00', 19);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(4, 'Valeria', 'Calderon','2000-01-01 00:00:00', 12);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(5, 'Sebastian', 'Campos','2000-01-01 00:00:00', 5);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(6, 'Josue', 'Castro', '2000-01-01 00:00:00', 10);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(7, 'Susana', 'Cen','2000-01-01 00:00:00', 20);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(8, 'Johan', 'Echeverria', '2000-01-01 00:00:00', 15);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(9, 'Junior', 'Herrera', '2000-01-01 00:00:00', 15);
insert into estudiante(id, nombre, apellido, fecha_nacimiento, total_creditos) values(11, 'Miguel', 'Leon', '2010-01-01 00:00:00', 17);
