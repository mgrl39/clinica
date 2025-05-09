# Clínica

Sistema de gestión para clínicas dentales que facilita la administración de pacientes, odontólogos y citas médicas.

![Java](https://img.shields.io/badge/Java-100%25-orange)
![License](https://img.shields.io/badge/License-MIT-blue)

## 📋 Descripción

Clínica es una aplicación web desarrollada para gestionar de forma eficiente los datos y visitas de pacientes en una clínica odontológica. Permite a los administradores y odontólogos controlar citas, pacientes, horarios y tratamientos en un solo sistema integrado.

## ✨ Características principales

- **Gestión de odontólogos**
  - Registro de especialidades
  - Configuración de horarios personalizados
  - Asignación de citas según disponibilidad

- **Administración de pacientes**
  - Datos personales completos
  - Registro de método de pago (particular o mutua)
  - Gestión especial para pacientes menores de edad con tutores

- **Sistema de citas**
  - Programación respetando horarios de odontólogos
  - Validación de disponibilidad en tiempo real
  - Registro de motivos de consulta

- **Seguimiento de tratamientos**
  - Registro de observaciones post-visita
  - Prescripción de tratamientos
  - Historial médico completo

- **Control de acceso**
  - Diferentes roles de usuario (administrativo, odontólogo)
  - Autenticación segura
  - Permisos basados en roles

## 🚀 Tecnologías utilizadas

- **Backend**: Java (Spring Boot)
- **Base de datos**: MySQL
- **Frontend**: HTML, CSS, JavaScript (Thymeleaf)
- **Seguridad**: Spring Security

## 🛠️ Instalación y configuración

### Requisitos previos

- Java 11 o superior
- Maven 3.6+
- MySQL 8.0+

### Pasos para instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/mgrl39/clinica.git
   cd clinica
   ```

2. Configurar la base de datos MySQL:
   ```bash
   # Crear base de datos
   mysql -u root -p
   CREATE DATABASE clinica;
   EXIT;
   
   # O utilizar el script incluido
   ./scripts/setup-database.sh
   ```

3. Configurar el archivo de propiedades:
   ```bash
   # Editar src/main/resources/application.properties con los datos de conexión
   spring.datasource.url=jdbc:mysql://localhost:3306/clinica
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

4. Compilar y ejecutar la aplicación:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. Acceder a la aplicación:
   ```
   http://localhost:8080
   ```

## 📘 Uso

### Acceso al sistema

1. Utilice las credenciales proporcionadas por el administrador
2. Para desarrollo, usuario predeterminado:
   - Usuario: `admin`
   - Contraseña: `admin`

### Administración de odontólogos

- Acceda a "Gestión de Odontólogos" en el menú principal
- Cree nuevos perfiles con sus especialidades y horarios
- Consulte la disponibilidad de cada profesional

### Gestión de pacientes

- Registre nuevos pacientes con sus datos completos
- Asocie tutores para pacientes menores de edad
- Consulte el historial de visitas y tratamientos

### Programación de citas

- Seleccione fecha, hora y odontólogo
- El sistema validará automáticamente la disponibilidad
- Registre el motivo de la consulta

### Seguimiento de tratamientos

- Los odontólogos pueden registrar observaciones post-visita
- Prescribir tratamientos
- Consultar historial médico completo del paciente

## 📁 Estructura del proyecto

TODO 

## 📝 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.
