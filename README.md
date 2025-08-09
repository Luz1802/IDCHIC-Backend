# 🖥️ Backend - Plataforma de Ventas Informáticas

Este repositorio contiene el código **backend** de la Plataforma de Ventas Informáticas, desarrollado en **Java** siguiendo el patrón de arquitectura **MVC (Modelo - Vista - Controlador)**.

## 📌 Descripción

El backend gestiona la lógica de negocio y los datos de la plataforma, incluyendo operaciones con productos, manejo de stock, cálculo de totales, descuentos y validaciones.  
Está construido usando **Java** y **Maven**, e implementa principios de **Programación Orientada a Objetos (POO)** como encapsulamiento, sobrecarga de métodos y manejo de excepciones.

## 📂 Estructura del proyecto

src/
 └── main/
     ├── java/
     │   └── com/
     │       └── ventas/
     │           ├── app/         # Clase principal (MainApp)
     │           ├── modelo/      # Clases de datos y lógica (Producto, Cliente, etc.)
     │           ├── controlador/ # Controladores que conectan modelo y vista
     │           └── vista/       # Clases para salida por consola (ConsoleLogger)
     └── resources/               # Archivos de configuración
pom.xml                           # Configuración de Maven


## 📦 Funcionalidades principales

- Creación y gestión de productos
- Actualización y validación de stock
- Cálculo de totales y descuentos
- Sobrecarga de métodos para diferentes operaciones
- Manejo de excepciones para entradas inválidas
- Estructura organizada con patrón MVC

## 👨‍💻 Autores

- **Juan David Garcia Castañeda**  
- **Luz Maria Bonilla Escobar**
