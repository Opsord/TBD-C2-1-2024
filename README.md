# TBD-C2-1-2024

Control 2 de Taller de Base de Datos

----BACKEND----

1- Crear previamente la base de datos en pgAdmin (PostgreSQL) con el nombre "TBDC2".
2- Desde Intellij se abre la carpeta del backend. El proyecto se encuentra dentro de la carpeta de nombre "control2TBD" y el nombre del backend es "control2TBD" (efectivamente, tienen el mismo nombre).
3- Una vez cargado todo el proyecto, debe ir a la carpeta resources y desplegar el archivo "application.properties", aqui debe cambiar los datos para que concuerden con los datos configurados en su pgAdmin.
Los datos que debe cambiar son:
-localhost: por defecto deberia ser 5432, sino, introduzca el correspondiente a donde escucha su base de dato.
-username: su usuario de pgadmin.
-password: su contraseña de pgadmin.
-puerto: por defecto es 8090, si no tiene el puerto escuchando otro programa puede dejarlo igual.
4-Ejecutar el backend
-opcion 1: Mayus + F10
-opcion 2 (recomendada): pinchar el icono de run en la barra superior (a un lado del nombre del proyecto)

nota: Las tablas y la población inicial se crean al momento de ejecutar el backend

----FrontEnd----

## Requisitos

- Tener instalado algún package manager
- node,bun,yarn,pnpm

## Ir a la carpeta en la terminal

```
cd ./frontendC2Vue
```

## Instalar paquetes

```sh
npm i
# En caso de usar otro package manager
# bun
bun i
# pnpm
pnpm i
```

## Iniciar proyecto en modo dev

```sh
npm run dev
# bun
bun run dev

```
