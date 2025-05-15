<div align="center">

<p></p>

<a href="#-introducción">Introducción</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-funcionalidad">Funcionalidad</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-arquitectura">Arquitectura</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-stack">Stack</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-instalación-y-ejecución">Instalación y Ejecución</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-estado-del-proyecto">Estado del Proyecto</a>
<span>&nbsp;&nbsp;❖&nbsp;&nbsp;</span>
<a href="#-licencia">Licencia</a>

<p align="center">
  <img src="src/main/resources/static/images/clinica-logo.png" width="20%" alt="Logo de Clínica Dental">
</p>

![Spring Boot Badge](https://img.shields.io/badge/Spring_Boot-6DB33F?logo=spring-boot&logoColor=white&style=flat)
![Java Badge](https://img.shields.io/badge/Java-007396?logo=java&logoColor=white&style=flat)
![JWT Badge](https://img.shields.io/badge/JWT-000000?logo=json-web-tokens&logoColor=white&style=flat)
![MySQL Badge](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white&style=flat)
![Thymeleaf Badge](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white&style=flat)
![GitHub stars](https://img.shields.io/github/stars/mgrl39/clinica)
![GitHub issues](https://img.shields.io/github/issues/mgrl39/clinica)
![GitHub license](https://img.shields.io/github/license/mgrl39/clinica)
![Status](https://img.shields.io/badge/Status-En_Desarrollo-yellow)

</div>

## 🧑‍⚕ Introducción

**Clinica** es un sistema de gestión para clínicas dentales desarrollado con Spring Boot que facilita la administración de pacientes, odontólogos y citas médicas. La aplicación proporciona una interfaz intuitiva para gestionar los recursos de una clínica dental, optimizando el flujo de trabajo del personal administrativo y médico.

El proyecto utiliza una arquitectura moderna con un backend robusto en Spring Boot y una interfaz de usuario desarrollada con Thymeleaf, proporcionando una experiencia de usuario fluida y responsiva.

---

## 🌍 Funcionalidades deseadas para desarrollarse

- **Gestión de Usuarios**: 
  - Administradores con acceso total al sistema
  - Odontólogos con acceso a su agenda y pacientes asignados
  - (Actualmente los pacientes no tienen acceso al sistema)

- **Gestión de Citas**: 
  - Programación de citas médicas
  - Asignación de odontólogos a pacientes
  - Administración de horarios disponibles

- **Historial Clínico**: 
  - Registro y seguimiento de historial médico de pacientes
  - Documentación de tratamientos realizados

- **Autenticación y Seguridad**: 
  - Sistema de login seguro con JWT
  - Almacenamiento del token en localStorage

---

## 🏛️ Arquitectura

El sistema sigue una arquitectura de tres capas:

1. **Capa de Presentación**:
   - Interfaces de usuario desarrolladas con Thymeleaf
   - Comunicación con el backend mediante API REST

2. **Capa de Negocio**:
   - Controladores REST que manejan las peticiones HTTP
   - Servicios que implementan la lógica de negocio
   - Sistema de autenticación basado en JWT

3. **Capa de Datos**:
   - Entidades JPA para la persistencia de datos
   - Repositorios para el acceso a la base de datos
   - Integración con MySQL para el almacenamiento

---

## 🏗️ Stack

| Tecnología | Descripción |
|------------|-------------|
| **Java** | Lenguaje principal de desarrollo |
| **Spring Boot** | Framework para el desarrollo del backend |
| **Spring Security** | Gestión de autenticación y autorización |
| **Spring Data JPA** | Persistencia de datos con JPA |
| **JWT** | JSON Web Tokens para la autenticación |
| **MySQL** | Base de datos relacional |
| **Thymeleaf** | Motor de plantillas para vistas |
| **JavaScript** | Funcionalidades del lado del cliente |
| **CSS** | Estilos para la interfaz de usuario |
| **Maven** | Gestión de dependencias y construcción del proyecto |
| **Makefile** | Automatización de tareas de despliegue |

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior

### Configuración de la Base de Datos

Para facilitar la configuración del entorno de desarrollo, se ha creado un Makefile automatizado:

```bash
# Clonar el repositorio
git clone https://github.com/mgrl39/clinica.git
cd clinica

# Desplegar el contenedor LXC con MySQL configurado
make all
```

### Configuración del Proyecto

```bash
# Instalar dependencias
mvn clean install
```

### Ejecución

```bash
# Iniciar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

---

## 📊 Estado del Proyecto

El proyecto se encuentra actualmente en una fase de desarrollo inicial, con los siguientes componentes en progreso:

- ✅ Estructura básica del backend con Spring Boot
- ✅ Sistema de autenticación JWT inicial
- ✅ Persistencia de datos con JPA

Próximos pasos:
- Completar la implementación del backend
- Desarrollar las interfaces de usuario restantes
- Implementar la gestión de citas y pacientes

---

## 📄 Licencia

[MIT](LICENSE)
