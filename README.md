# PROYECTO POO EDUPLAN

Edwin Sarango

## Configuración 

Este proyecto fue desarrollado en JAVA con JDK 22, usando las configuraciones de MAVEN, principalmente para las librerías que se usaron, las cuales fueron:

- SQL.connector: Comando para obtener y utilizar la base de datos requerida como el programa utilizado que es PostgreSQL
- itextpdf: Generación PDF
- itextpdf.kernel: Generación PDF
- itextpdf.layout: Generación PDF
# Funcionamiento del Programa

## Ventana inicial de Login

Esta ventana buscará en MongoDB Atlas si las credenciales existen y son correctas para permitir un ingreso

![image](https://github.com/user-attachments/assets/90252a5d-042e-4dff-a93c-16ac8c44708c)

Si las credenciales existen y son correctas, se ingresará al sistemas y se verá una interfaz que mostrará opciones dependiendo el rol del usuario que ingresó

Rol Administrador:

![image](https://github.com/user-attachments/assets/4fd8fac4-726e-42d5-ad7e-ae52c29171bb)

Rol Profesor:

![image](https://github.com/user-attachments/assets/408df805-68c7-4681-86d7-8c9584fc2c8d)


Rol Estudiante:

![image](https://github.com/user-attachments/assets/20b505fa-73a9-401b-9b0e-6ff95065aede)


## Existencias en la Base de Datos

Estudiante:

![image](https://github.com/user-attachments/assets/e0078661-f7b4-4a22-8763-9f2e1cb57eca)

Profesor:

![image](https://github.com/user-attachments/assets/6603faa9-f82c-47e2-a375-1b96d4b613cb)

Administrador:

![image](https://github.com/user-attachments/assets/1b6bb9e9-7db3-44db-8384-4e6e68374f6e)


## Funcionalidades

Como usuario con rol estudiante, se podrá ver la información de:

Calificaciones

![image](https://github.com/user-attachments/assets/4e7f7d0d-6ff2-49ff-b5fe-50213976d3c1)

Curso al que pertenece

![image](https://github.com/user-attachments/assets/d160f429-2b5a-4091-a305-c7ad1c93aece)

Horario

![image](https://github.com/user-attachments/assets/2680827d-2d59-4f42-b78f-d9cc34b9a53a)

Al presionar sobre el botón Descargar Reporte de la interfaz de Estudiante, se generará un archivo PDF en la ruta que se haya definido

![image](https://github.com/user-attachments/assets/51cac192-9ce5-4b61-bc9b-b77646a6252d)
![image](https://github.com/user-attachments/assets/6bd0562e-d83e-4a4e-ad31-48e9dcbede08)


##

Como usuario con rol profesor, se podrá realizar los siguiente:

Registro de notas

![image](https://github.com/user-attachments/assets/9643abe6-1ab3-485e-9840-dd78c7937726)

![image](https://github.com/user-attachments/assets/6ca90d86-2902-4188-9092-d854498a767c)

Registro de Curso

![image](https://github.com/user-attachments/assets/648daea2-0796-467a-a0c4-1a80c197f9e5)


##

Como usuario con rol administrador, se podrá:

Realizar la eliminación de un usuario (Estudiante o Profesor), para esto se debe seleccionar el usuario y presionar el botón de eliminar

![image](https://github.com/user-attachments/assets/3d1d3101-72aa-43ee-ba20-ce8cc5544787)

Realizar la actualización de un usuario

![image](https://github.com/user-attachments/assets/5a6ae359-9666-42a2-bc99-575911ec8ef1)

## Ventanas para el registro de un Estudiante, Profesor y Administrador

Primero debemos seleccionar el rol que vamos a registrar

![image](https://github.com/user-attachments/assets/d02510a4-1258-4a2c-8245-e1bc190580bc)

Luego, se verificará el registro del nuevo usuario 

![image](https://github.com/user-attachments/assets/5a59a167-e871-4e19-871f-4634d9845f82)

Se debe ingresar la clase correcta para acceder a registrarse como administrador y tener total control del sistema (La clave correcta es "admin123")

![image](https://github.com/user-attachments/assets/3b97d12a-79be-46b6-8431-9d8e170a804e)

![image](https://github.com/user-attachments/assets/f72ca1e5-4ee8-4076-8c16-05d0a2487a7b)
