 Sistema de Gestión de Inventario con Alertas de Stock

Descripcion 

Aplicación desarrollada en Java con Spring Boot para administrar un inventario de productos.

El sistema permite:

- Registrar productos.
- Consultar productos.
- Aumentar el stock.
- Reducir el stock.
- Validar que el stock no sea negativo.
- Detectar cuando el stock está por debajo del mínimo permitido.
- Generar una alerta automática cuando un producto requiere reposición.

El proyecto fue desarrollado aplicando TDD, principios SOLID, pruebas unitarias, Mockito, pruebas de integración y CI mediante GitHub Actions.


Tecnologias usadas

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Mockito
- MockMvc
- Spring Boot Test
- GitHub Actions

# Clonar el repositorio:
git clone https://github.com/CSebas01/SistemaGestion
Entrar al proyecto:
cd inventario-alertas
Para compilar:
mvn clean install
Y para ejecutar:
mvn clean install
