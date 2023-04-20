# Adivina la palabra


Adivina la palabra es un juego similar a Wordle, en el que el jugador debe adivinar una palabra secreta de cinco letras en seis intentos. El jugador introduce una palabra y recibe una retroalimentación de cuántas letras coinciden con la palabra secreta y en qué posición. El juego se puede jugar en modo individual o multijugador, con la opción de crear salas privadas o públicas.

Este repositorio contiene el código del back-end del juego, desarrollado con Java y Spring Boot. El back-end se encarga de gestionar la lógica del juego, la base de datos de palabras y usuarios, y la comunicación con el front-end mediante una API REST.



## Requisitos

Para ejecutar el proyecto se necesita:

- Java 11 o superior
- Maven
- MySQL

## Instalación

Para instalar el proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local:

# Adivina la palabra

Adivina la palabra es un juego similar a Wordle, en el que el jugador tiene que adivinar una palabra secreta de cinco letras en cinco intentos. El juego le da pistas al jugador sobre si las letras que ha introducido son correctas, incorrectas o están en la posición equivocada.

Este repositorio contiene el código del back-end del juego, desarrollado con Java y Spring Boot. El back-end se encarga de generar las palabras secretas, validar las entradas del jugador y enviar las respuestas al front-end.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-000000?style=for-the-badge&logo=apache-maven&logoColor=white)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/)

## Índice

- [Desarrolladores](#desarrolladores)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Documentación](#documentación)
- [Contribución](#contribución)
- [Contacto](#contacto)

## Desarrolladores

Este repositorio ha sido realizado por Nerea Balibrea y Enrique Boronat, como parte del dev team encargado del Back-End del proyecto.

## Requisitos

Para ejecutar el proyecto se necesita:

- Java 11 o superior
- Maven 3.6 o superior
- Un IDE compatible con Spring Boot (por ejemplo, Eclipse o IntelliJ IDEA)

## Instalación

Para instalar el proyecto, sigue estos pasos:

- Clona el repositorio en tu máquina local con el comando `git clone https://github.com/EnriqueBoronatSopra/AdivinaLaPalabra-Back-.git`
- Abre el proyecto con tu IDE y espera a que se resuelvan las dependencias de Maven
- Ejecuta la clase `AdivinaLaPalabraApplication.java` como una aplicación de Spring Boot

## Documentación

La documentación del código se puede generar con el comando `mvn javadoc:javadoc` y se puede consultar en la carpeta `target/site/apidocs`.

## Contribución

Este proyecto es privado y no acepta contribuciones externas. Si eres uno de los desarrolladores y quieres hacer cambios en el código, sigue estas recomendaciones:

- Crea una rama nueva con el nombre del cambio que quieres hacer (por ejemplo, `feature/nueva-palabra`)
- Haz tus modificaciones en la rama y comprueba que funcionan correctamente
- Haz un commit con un mensaje descriptivo de tu cambio (por ejemplo, `Añadida la opción de elegir la dificultad del juego`)
- Haz un push de tu rama al repositorio remoto con el comando `git push origin feature/nueva-palabra`
- Crea un pull request desde tu rama a la rama principal y espera a que sea revisado y aprobado por otro desarrollador
- Una vez aprobado, haz un merge de tu rama a la principal y borra la rama remota y local


## Contacto

Si tienes alguna duda o sugerencia sobre el proyecto, puedes contactar con los desarrolladores mediante sus correos electrónicos:

- Nerea Balibrea: nerea.balibrea@ext.soprasteria.com
- Enrique Boronat: ej.boronat@ext.soprasteria.com

