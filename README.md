# 🎁 Sistema de Selección de Premios - ComprasEnLaWeb

## 📝 Descripción del problema

La empresa **"ComprasEnLaWeb"** desea implementar un sistema de premios basado en el monto de las compras realizadas por sus clientes. Los clientes pueden seleccionar premios desde el portal web ingresando con su usuario y contraseña. El sistema valida que no exista una solicitud de premio para el año en curso y muestra los productos que pueden elegir.

**Reglas del sistema:**

• Los premios ofrecidos están divididos en **4 categorías**.

• Las categorías de premios dependen de la **sucursal**, el **tipo de cliente** y el **monto facturado** en el año en curso.

• Las sucursales de **Buenos Aires** y del **Interior del país** (como Mendoza) tienen criterios diferentes para asignar las categorías.

• Para la distancia entre el cliente y la sucursal, se utiliza una **API de Google Maps** (en el futuro podrían utilizarse otras APIs)

## 🔧 Patrones GRASP Aplicados

### 1. **Experto (Information Expert)**

El patrón **Experto** se utiliza para delegar la responsabilidad de obtener los premios a la clase que tiene la información necesaria. En este caso, la clase `Experto` se encarga de utilizar la lógica de negocio para determinar la categoría de premios que un cliente puede recibir. Esta clase utiliza una estrategia basada en la sucursal del cliente para determinar la categoría correcta.

```java
public class Experto {

    public List<DtoPremio> obtenerPremios(String usuario, String contraseña) {
        // Lógica de negocio para obtener premios y determinar categoría del cliente
    }

    public void generarSolicitud(int codPremio, int dniCliente) {
        // Lógica para registrar la solicitud de premio
    }
}
```

### 2. **Indirección (Indirection)**

El patrón de **Indirección** se utiliza para desacoplar la lógica de negocio de los detalles de acceso a los datos. La clase `IndireccionPersistencia` es responsable de gestionar las consultas a la base de datos y otras operaciones de persistencia. Esta clase sigue el patrón Singleton.

```java
public class IndireccionPersistencia {
    private static IndireccionPersistencia instancia;

    private IndireccionPersistencia() {}

    public static IndireccionPersistencia getInstancia() {
        if (instancia == null) {
            instancia = new IndireccionPersistencia();
        }
        return instancia;
    }

    // Métodos de búsqueda y almacenamiento de entidades (Cliente, Factura, etc.)
}
```

### 3. **Controlador**

El **Controlador** es responsable de coordinar las solicitudes desde la interfaz de usuario y delegar la lógica al experto. El controlador actúa como intermediario entre la capa de presentación y la capa de servicio.

```java
public class ControladorSolicitarPremio {

    private Experto expertoSolicitarPremio;

    public List<DtoPremio> obtenerPremios(String usuario, String contraseña) {
        return expertoSolicitarPremio.obtenerPremios(usuario, contraseña);
    }

    public void generarSolicitud(int codPremio, int dniCliente) {
        expertoSolicitarPremio.generarSolicitud(codPremio, dniCliente);
    }
}
```

### 4. **DTO (Data Transfer Object)**

Los **DTO** se utilizan para transferir datos entre las capas del sistema. En este caso, `DtoPremio` encapsula los detalles del premio que se presenta al cliente, permitiendo que la información se transfiera sin exponer la entidad del modelo directamente.

```java
public class DtoPremio {
    private int codDtoPremio;
    private String descripcionDtoPremio;
    private String nombreDtoPremio;
    private double valorDtoPremio;

    // Getters y setters
}
```
## 🔧 Patrones GOF Aplicados

### **1. Factory Pattern 1 - FactoriaEstrategiaObtenerCategoria**

Utilizamos el patrón **Factory** para seleccionar la estrategia adecuada en función de la sucursal. Esto permite encapsular la lógica de elección de estrategia y simplificar la creación de nuevas estrategias en el futuro.

```java
public EstrategiaObtenerCategoria obtenerEstrategia(Sucursal sucursal) {
    if (sucursal.getMetodoSucursal().getNombreMetodo().equals("Interior")) {
        return new EstrategiaPorDistancia();
    } else {
        return new EstrategiaTipoCliente();
    }
}
```

### **2. Factory Pattern 2 - FactoriaAdaptadorCalculoDistancia**

El patrón **Factory** se usa también para decidir qué adaptador utilizar al calcular la distancia. Dependiendo del parámetro configurado, devuelve un adaptador de Google Maps o de Here Maps.

```java
public AdaptadorCalculoDistancia obtenerAdaptadorCalculoDistancia() {
    ParametroAdaptadorCalculoDistancia parametro = indireccionPersistencia.buscarParametroAdaptadorCalculoDistancia("");

    if (parametro.getNombre().equals("Google Maps")) {
        return new AdaptadorCalculoDistanciaGoogleMaps();
    } else {
        return new AdapterCalculoDistanciaHereMaps();
    }
}
```

### **3. Strategy Pattern 1 - EstrategiaPorDistancia**

El patrón **Strategy** se implementa para manejar la lógica de selección de categoría en función de la distancia entre el cliente y la sucursal.

```java
public class EstrategiaPorDistancia implements EstrategiaObtenerCategoria {
    @Override
    public Categoria obtenerCategoria(Cliente cliente) {
        // Lógica de cálculo de distancia...
        return categoria;
    }
}
```

### **4. Strategy Pattern 2 - EstrategiaTipoCliente**

Este otro uso del patrón **Strategy** gestiona la selección de la categoría basada en el tipo de cliente (mayorista o minorista).

```java
public class EstrategiaTipoCliente implements EstrategiaObtenerCategoria {
    @Override
    public Categoria obtenerCategoria(Cliente cliente) {
        // Lógica de selección según el tipo de cliente...
        return categoria;
    }
}
```

### **5. Adapter Pattern 1 - AdaptadorCalculoDistanciaGoogleMaps**

El **Adapter Pattern** se utiliza para integrar la API de Google Maps en el sistema de cálculo de distancias. Este adaptador se asegura de que el sistema pueda utilizar **Google Maps** sin cambios en la lógica del negocio.

```java
public class AdaptadorCalculoDistanciaGoogleMaps implements AdaptadorCalculoDistancia {
    @Override
    public double obtenerDistancia(DtoCalcularDistancia dto) {
        // Simulamos una llamada a la API de Google Maps
        return 0.0;
    }
}
```

### **6. Adapter Pattern 2 - AdapterCalculoDistanciaHereMaps**

El otro adaptador se encarga de realizar el mismo cálculo utilizando **Here Maps**. Si en el futuro se añaden más servicios, simplemente se implementará otro adaptador.

```java
public class AdaptadorCalculoDistanciaGoogleMaps implements AdaptadorCalculoDistancia {
    @Override
    public double obtenerDistancia(DtoCalcularDistancia dto) {
        // Simulamos una llamada a la API de Google Maps
        return 0.0;
    }
}
```

### **7. Singleton Pattern - IndireccionPersistencia**

El patrón **Singleton** se aplica en la clase IndireccionPersistencia, que maneja las operaciones de persistencia de datos. Este patrón asegura que solo haya una única instancia de esta clase en todo el sistema, evitando la creación de múltiples conexiones o instancias de acceso a la base de datos.

```java
public class IndireccionPersistencia {
    private static IndireccionPersistencia instancia;

    private IndireccionPersistencia() {}

    public static IndireccionPersistencia getInstancia() {
        if (instancia == null) {
            instancia = new IndireccionPersistencia();
        }
        return instancia;
    }
}
```

## 🚀 Ejecución del sistema
El sistema permite que los clientes ingresen con su usuario y contraseña, verifiquen los premios disponibles según las condiciones de su sucursal y tipo de cliente, y registren una solicitud de premio.

**Funcionalidades:**

• **Selección de premios**: Muestra los premios disponibles según el tipo de cliente y la sucursal.

• **Registro de solicitud**: Permite registrar una solicitud de premio si no existe una para el año en curso.

• **Cálculo de distancia**: Se utiliza una API de mapas para calcular la distancia entre el cliente y la sucursal.

# 💻 Tech Stack:
<div align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="Java logo" /> 
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" alt="Spring logo" /> 
</div>

# 🧑🏻‍💻 Autor:

**Valentin Mathey** | <a href="https://github.com/valentinmathey">**@valentinmathey**</a>

[![Discord](https://img.shields.io/badge/Discord-%237289DA.svg?logo=discord&logoColor=white)](https://discord.gg/valentinmathey) [![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?logo=Facebook&logoColor=white)](https://facebook.com/ValentinEzequielMathey) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/valen.mathey/) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://linkedin.com/in/valentin-mathey) [![X](https://img.shields.io/badge/X-%231DA1F2.svg?logo=X&logoColor=white)](https://twitter.com/valen_mathey)

