# Integracion-continua-ventas

## Mayerli Vargas Espinosa
## Faber Edison Ordoñez Alvarado
## Carlos Esteban Guzman Baquero

Repositorio creado para el desarrollo del modulo de integración continúa

El proyecto consiste en un sistema de ventas de productos desarrollado con Java
(Spring Boot Framework). Con pruebas unitarias (Junit) se validará la capa Repository que
extiende de CrudRepository con varios métodos para la creación de servicios la cual
implementa el patrón DAO (DATA ACCESS OBJECT).

Este proyecto se elaboró con una arquitectura orientada a servicios REST,
(Tecnología usada Spring Data JPA), se le realizaron pruebas funcionales con Postman , y a
la base de datos se le realizaron planes de ejecución. Y además se trabajó con SonarQube
para su respectiva calidad de Software.

Para la interacción del cliente con el aplicativo, se desarrolla una página web en
Angular con Typescript, a través del cual puede utilizar todas funcionalidades del sistema.

El versionamiento e integración del aplicativo se utiliza GitHub con GitHub
Actions (integración continua) en combinación con contenedores en Docker