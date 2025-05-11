# Sistema de Turnos

Este proyecto es una aplicación de gestión de turnos desarrollada en **Java 17** utilizando **Hibernate sin anotaciones JPA**.  
Permite administrar usuarios, empleados, clientes, turnos, especialidades, sucursales y más, con persistencia en base de datos MySQL.

---

## 👥 Grupo 22 - Integrantes

- **De La Via, Luca Ernesto**  
- **Mohamed, Valentina Belén**  
- **Sdrubolini, Sebastián Ariel**  
- **Szpitalnik, Gael Lautaro**

---

## 📁 Estructura del Proyecto

```
.
├── src/
│   ├── datos/              # Clases de modelo (entidades)
│   ├── dao/                # Clases de acceso a datos (Hibernate)
│   ├── negocio/            # Lógica de negocio (ABM)
│   ├── test/               # Tests de integración
│   ├── lib/                # Librerías .jar necesarias
│   ├── hibernate.cfg.xml   # Configuración de Hibernate
│   ├── *.hbm.xml           # Mappings XML de entidades
├── script.sql              # Script para crear base de datos y tablas
└── README.md               # Instrucciones completas
```

---

## ✅ Requisitos

- Java 17
- MySQL Server
- IDE (Eclipse, IntelliJ IDEA, NetBeans)
- Conexión local a MySQL (localhost, puerto 3306)
- Cliente MySQL (Workbench, DBeaver, o consola)
- Las librerías provistas en `/lib` deben ser agregadas manualmente

---

## 🛠 Paso a paso para ejecutar el proyecto

### 1. Clonar o descargar el proyecto

```bash
git clone https://github.com/lucadelavia/ProyectoTurnos-HibernateOO2-Grupo22
cd ProyectoTurnos-HibernateOO2-Grupo22
```

O descargalo como ZIP desde GitHub y descomprimilo.

---

### 2. Agregar librerías (dependencias)

Abrí el proyecto en tu IDE y agregá **todas las librerías .jar de la carpeta `lib/`** al classpath del proyecto.

**En Eclipse:**
- Clic derecho sobre el proyecto > Properties > Java Build Path > Libraries > Add JARs…

**En IntelliJ:**
- File > Project Structure > Modules > Dependencies > + JARs or directories

---

### 3. Crear la base de datos

Ejecutá el archivo `turnosDB.sql` con tu cliente de base de datos.

Verificá que la base de datos creada se llame `turnos` o el nombre que hayas configurado en `hibernate.cfg.xml`.

---

### 4. Configurar `hibernate.cfg.xml`

Asegurate de que el archivo `hibernate.cfg.xml` tenga los datos correctos de conexión:

```xml
<property name="connection.url">jdbc:mysql://localhost/turnos?serverTimezone=America/Argentina/Buenos_Aires</property>
<property name="connection.username">root</property>
<property name="connection.password">tu_contraseña</property>
```

Y que estén registrados todos los `.hbm.xml` necesarios con sus rutas correctas.

---

### 5. Ejecutar pruebas generales

Corré la clase `test/TestSistema.java` desde tu IDE. Este test realiza:

- Alta de usuarios, empleados y clientes
- Alta de especialidades y servicios
- Alta de establecimientos y sucursales
- Asignación de días de atención y especialidades
- Alta de turnos
- Consultas por fecha, sucursal y rango
- Modificaciones y Bajas a los registros

Todos los resultados se imprimen por consola.

(Cabe aclarar que los metodos de bajas permaneceran comentados para probar el alta y modificacion primero.
Ademas no se incluyen todos los metodos en el test, pero si se encuentran implementados en sus clases correspondientes.)

---

## 🔍 ¿Qué incluye el proyecto?

- Gestión de usuarios (`Usuario`, `Cliente`, `Empleado`)
- Gestión de turnos completos (`Turno`, `Servicio`, `Sucursal`)
- Hibernate puro con XML (`.hbm.xml`) sin anotaciones
- Relaciones: `many-to-one`, `many-to-many` mapeadas correctamente
- Estructura extensible y orientada a objetos

---
