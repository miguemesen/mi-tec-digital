# mi-tec-digital

[![Java CI with Maven](https://github.com/miguemesen/mi-tec-digital/actions/workflows/maven.yml/badge.svg)](https://github.com/miguemesen/mi-tec-digital/actions/workflows/maven.yml)

## Nombre
Miguel Mesen Molina

## Carné
2019227371


## Nota para profesores
Realicé el requerimieto extra de compartir la lógica de las consultas.

Esto lo logré agregando métodos de request a la clase abstracta GenericMySqlDAO que es padre de las clases MySqlDAO Curso, Estudiante y profesor.



## Revisión de tarea 1

Nota 10

#### Observaciones

1. Para mantener la división de tareas, el ordenamiento de los estudiantes se tuvo que haber hecho en EstudianteServiceImpl
2. Agregar un archivo .gitignore
3. En `EstudianteService` hizo uso del método `this.getAll()` para obtener un Estudiante en particular. Pudo haber utilizado `this.getById()` para no tener que recorrerlos todos.
4. En `EstudianteServiceTest` para las pruebas unitarias intente tomar un enfoque de: "lo que pasó antes" y "lo que va a pasar". Es que por ejemplo probar que ud hizo una actualización y luego verificar que la cantidad de estudiantes sigue siendo 3 es poco útil. Hubiera podido obtener de la "base de datos" actual por medio del carné (2), luego hacer la actualización (2) y, como método de verificación comparar que del paso (1) con el del paso (2). Intente un enfoque similar para las pruebas de borrado.

