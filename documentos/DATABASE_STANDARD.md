# Estándar de Nombres de mi-tec-digital

- 1.- [Introducción](#1)  
- 2.- [Definición de estándares](#2)  
	- 2.1.- [Entidades](#21)
	- 2.2.- [Atributos](#22)
	- 2.3.- [Tipos de datos a usar para atributos](#23)
	- 2.4.- [Llaves primarias](#24)
	- 2.5.- [Llaves foráneas](#25)
	- 2.6.- [Índices](#26)

<a name="1"/>

## Introducción


Las ventajas de utilizar un estándar de nombres para una base de datos es para que esta persista. Una estructura de datos bien definida con un diseño correcto no es necesario cambiarlo, pueden haber cambios drásticos en una aplicación que si su base de datos fue escrita de manera correcta esta no debería de verse afectada. 

Al utilizar el estándar de nombres de mi-tec-digital se ahorrará algunos dolores de cabeza, con la convención de nombres aplicada en este diccionario de datos mejorará la legibilidad de este y perderá menos tiempo  buscando lo que necesita. Además, al mantener un código legible cualquier persona debería de ser capaz de entender las relaciones entre los datos.

***

<a name="2"/>

## Definición de estándares


Preferiblemente se utilizarán minúsculas en los identificadores, ya sean nombres de views, tablas o columnas. 

No se utilizarán nombres de tipos de datos como nombres a identificadores.

Todos los objetos deben de estar escritos en el lenguaje Inglés.

No se pueden utilizar palabras reservadas que son reconocidas por la base de datos. 

***

<a name="21"/>

### Entidades


Estas deberán ser un sustantivo. Si es posible, que este sea una sola palabra y que sea autodescriptivo.

Ejemplo correcto: user, employee, cite, role, client.

Ejemplo erróneo: users, employees, cities, roles.

Excepciones: si hay alguna entidad que es imposible describirla en una sola palabra se pueden utilizar más, pero con un formato de cada palabra empezando en minúscula y separados por un guión.

***

<a name="22"/>

### Atributos


Los atributos deben ser palabras completas y no abreviaciones, si el atributo es más de una palabra, estas deben de ser en minúscula y separadas por un guion bajo y no camel case.  Los valores guardados bajo el nombre de estos atributos deben ser valores atómicos.

Ejemplo correcto: first_name, last_name, address.

Ejemplo erróneo: FirstName, lastName, Address

Excepciones: depende del contexto y la estructura de la base de datos si un elemento se considera de valor atómico. Por ejemplo, si no es necesario separar el nombre de los apellidos, el nombre completo puede considerarse como un valor atómico.

***

<a name="23"/>

### Tipos de datos a usar para atributos


Para los id de las entidades se utilizará el **INT**, esto debido a que los identificadores de los estudiantes contienen 10 dígitos y el INT cumple con ese requisito.

Ejemplo:  id_student INT NOT NULL, 2019227371

Para los nombres y las direcciones de las entidades se deben de utilizar el **VARCHAR**, esto debido a que se desconoce la longitud de la información que se va ingresar en este espacio.

Ejemplo: name_sutdent VARCHAR NOT NULL, Juan Vega Albaladejo.
address_profesor VARCHAR NOT NULL

Para las fechas que deben almacenar para las entidades se utilzará **DATE**, este nos permite almacenar fechas en el formato AAAA-MM-DD.

Ejemplo: subscription_student DATE, 2021-09-21

Para los nombres de los cursos se utilizará **CHAR**(32), esto debido a que no deberían de existir cursos con nombres más largos que esos y se asegura que no habrá un gasto innecesario de almacenamiento haciendo que este sea un VARCHAR.

Ejemplo: name_subject CHAR(32), Bases de Datos

Para los números de créditos se utilizará el **TINYINT**, ya que un curso no va exceder los 255 créditos.

Ejemplo: credits_subject TINYINT, 4
***

<a name="24"/>

### Llaves primarias


Las llaves primarias deben de llevar un nombre sencillo, preferiblemente que sea "id", así será fácil de identificar, es corta, simple y no ambigua. 

Ejemplo: dentro de una tabla de estudiantes, si quisiera poner la identificación del estudiante como la llave primaria esta deberá llamarse sólo "id" y no "id_student".

Excepciones: algunas veces se utiliza un prefix del nombre de la table dentro de la llave primaria, ejemplo: client_id.

***

<a name="25"/>

### Llaves foráneas 


Las llaves foráneas deben de ser una combinación del nombre de la tabla referida y del nombre del campo que se hace referencia.

Ejemplo: class_member_pkey

Excepciones: para columnas singulares, la llave foránea debe verse así foo_id.

***

<a name="26"/>

### Índices 


Los índices explícitamente deben de tener nombres que incluya tanto el nombre de la tabla como el nombre de la columna que se está indexando.  Esto hará el código más legible. 

Ejemplo: student_index_first_name_last_name

Excepciones: se pueden utilizar abreviaciones como "ix" en lugar de la palabra completa "index", ya que se ha estandarizado mucho esa abreviación para los índices, ejemplo: student_ix_first_name_last_name.

***
