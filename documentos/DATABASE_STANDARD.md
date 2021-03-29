# Estándar de Nombres de mi-tec-digital

- 1.- [Introducción](#1)  
- 2.- [Definición de estándares](#2)  
	- 2.1.- [Entidades](#21)
	- 2.2.- [Atributos](#22)
	- 2.3.- [Tipos de datos a usar para atributos](#23)
	- 2.4.- [Llaves primarias](#24)
	- 2.5.- [Llaves foráneas](#25)
	- 2.6.- [Índices](#26)


## Introducción
<a name="1"/>

Las ventajas de utilizar un estándar de nombres para una base de datos es para que esta persista. Una estructura de datos bien definida con un diseño correcto no es necesario cambiarlo, pueden haber cambios drásticos en una aplicación que si su base de datos fue escrita de manera correcta esta no debería de verse afectada. 

Al utilizar el estándar de nombres de mi-tec-digital se ahorrará algunos dolores de cabeza, con la convención de nombres aplicada en este diccionario de datos mejorará la legibilidad de este y perderá menos tiempo  buscando lo que necesita. Además, al mantener un código legible cualquier persona debería de ser capaz de entender las relaciones entre los datos.


## Definición de estándares
<a name="2"/>

Preferiblemente se utilizarán minúsculas en los identificadores, ya sean nombres de views, tablas o columnas. 

No se utilizarán nombres de tipos de datos como nombres a identificadores.

Todos los objetos deben de estar escritos en el lenguaje Inglés.

No se pueden utilizar palabras reservadas que son reconocidas por la base de datos. 

### Entidades
<a name="21"/>

Estas deberán ser un sustantivo. Si es posible, que este sea una sola palabra y que sea autodescriptivo.

Ejemplo correcto: usuario, empleado, ciudad, rol, cliente.

Ejemplo erróneo: usuarios, empleados, ciudades, roles.

Excepciones: si hay alguna entidad que es imposible describirla en una sola palabra se pueden utilizar más, pero con un formato de cada palabra empezando en minúscula y separados por un guión.

### Atributos
<a name="22"/>

Los atributos deben ser palabras completas y no abreviaciones, si el atributo es más de una palabra, estas deben de ser en minúscula y separadas por un guion bajo y no camel case.  Los valores guardados bajo el nombre de estos atributos deben ser valores atómicos.

Ejemplo correcto: first_name, last_name, address.

Ejemplo erróneo: FirstName, lastName, Address

Excepciones: depende del contexto y la estructura de la base de datos si un elemento se considera de valor atómico. Por ejemplo, si no es necesario separar el nombre de los apellidos, el nombre completo puede considerarse como un valor atómico.

### Tipos de datos a usar para atributos
<a name="23"/>




### Llaves primarias
<a name="24"/>


### Llaves foráneas 
<a name="25"/>


### Índices 
<a name="26"/>
