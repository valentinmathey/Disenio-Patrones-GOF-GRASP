# Sistema de Premios - ComprasEnLaWeb

Este proyecto implementa un **sistema de premios** para la empresa **"ComprasEnLaWeb"**. El sistema permite a los clientes seleccionar premios basados en las compras realizadas, su tipo de cliente y la sucursal a la que están asociados. Los premios están categorizados y se determinan a través de diversas estrategias de negocio que varían según la sucursal y otros factores.

## Tecnologías utilizadas
- **Lenguaje:** Java
- **Patrones de diseño:** 
  - **Experto** 
  - **Factoría (Factory)**
  - **Estrategia (Strategy)**

## Patrones de diseño

### 1. **Experto (Information Expert)**
El patrón **Experto** se utiliza para delegar la responsabilidad de obtener los premios a la clase que tiene la información necesaria. En este caso, la clase `Experto` se encarga de utilizar la lógica de negocio para determinar la categoría de premios que un cliente puede recibir. Esta clase utiliza una estrategia basada en la sucursal del cliente para determinar la categoría correcta.

```java
public class Experto {

    public List<Premio> obtenerPremios(Cliente cliente){
        FactoriaEstrategiaObtenerCategoria factoria = new FactoriaEstrategiaObtenerCategoria();
        EstrategiaObtenerCategoria estrategia = factoria.obtenerEstrategia(cliente.getSucursalCliente());
        Categoria categoria = estrategia.obtenerCategoria();
        return categoria.getPremios();
    }
}
```

### 2. **Factoría (Factory)**
El patrón Factoría es utilizado para crear el objeto de estrategia adecuado según la sucursal del cliente. La clase `FactoriaEstrategiaObtenerCategoria` selecciona qué estrategia de negocio aplicar en función de la ubicación geográfica de la sucursal.

```java
public class FactoriaEstrategiaObtenerCategoria {

    public EstrategiaObtenerCategoria obtenerEstrategia(Sucursal sucursal){
        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("MENDOZA")){
            return new EstrategiaPorDistancia(); // Estrategia basada en la distancia del cliente a la sucursal
        }

        if (sucursal.getMetodoSucursal().getNombreMetodo().equals("BUENOS AIRES")){
            return new EstrategiaTipoCliente(); // Estrategia basada en el tipo de cliente
        } else {
            return new EstrategiaBase(); // Estrategia por defecto
        }
    }
}
```

### 3. **Estrategia (Strategy)**
El patrón **Estrategia** se usa para encapsular los distintos algoritmos para calcular la categoría de premios en función de diversos criterios, como la distancia o el tipo de cliente. Cada estrategia tiene su propia lógica para calcular la categoría de premios.

Ejemplo de Estrategias:

**EstrategiaPorDistancia:** Se utiliza para clientes en la sucursal de Mendoza, donde el premio se calcula en función de la distancia del cliente a la sucursal.

**EstrategiaTipoCliente:** Se usa para clientes en la sucursal de Buenos Aires, donde el tipo de cliente (mayorista o minorista) define la categoría de premios.

```java
public class EstrategiaPorDistancia implements EstrategiaObtenerCategoria {

    @Override
    public Categoria obtenerCategoria() {
        // Algoritmo para obtener la categoría basado en la distancia del cliente a la sucursal
        return new Categoria();
    }
}

public class EstrategiaTipoCliente implements EstrategiaObtenerCategoria {

    @Override
    public Categoria obtenerCategoria() {
        // Algoritmo para obtener la categoría basado en el tipo de cliente
        return new Categoria();
    }
}
```

### 4. **Flujo del Sistema**

1.	Cliente ingresa al portal: El cliente accede al sistema proporcionando su usuario, contraseña, sucursal, y tipo de cliente.
   
3.	El sistema determina la estrategia: Basado en la sucursal del cliente, el sistema selecciona la estrategia apropiada (distancia, tipo de cliente, etc.).
   
5.	Obtención de premios: El sistema calcula la categoría de premios utilizando la estrategia seleccionada y devuelve la lista de premios que el cliente puede seleccionar.
