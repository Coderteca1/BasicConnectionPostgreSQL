# BasicConnectionPostgreSQL
Conexion basica y sencilla a PostgreSQL desde Java :D
 
## Estructura Basica.


- Agrega a tu proyecto el driver que nos ayudara a realizar la conexion.
  - [Descargalo aqui](https://jdbc.postgresql.org/download.html)
- Copia la carpeta __com__ (de este mismo proyecto) en la carpeta __src__ de tu proyecto, quedando de la sigueinte manera.
  * src
    * com.Conexion
    * com.Model
    * om.Test
_____


## Como realizo la conexión?
En el paquete __com.Test__ se encuentra una clase llamada  __Main__ (Aunque lo puedes realizar en otra clase si tu quieres :D), en la que encontraremos el siguiente código.

~~~java
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexion c = null;
		try {
			c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
			c.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

~~~

En la line donde obtenemos un Objeto de tipo __Conexion__ 

~~~Java
  c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
~~~

- El primer parámetro es la contraseña con la cual ingresamos al servidor de base de datos (Pon tu contraseña con que inicias sesión en postgreSQL).
- El segundo parámetro es el nombre de la base de datos a la que nos conectaremos.
- Si necesitas más parámetros como el "host" el "puerto" o "nombre de usuario", existen otros dos métodos en la clase __Conexion__ que nos pueden ayudar con eso.
  
  
  ~~~java
    getInstans(String clave, int puerto, String baseDatos);
  ~~~
  
  ~~~java
    getInstants(String host, String usuario, String clave, int puerto, String baseDatos);
  ~~~
  
______

## Cómo realizo una consulta a una Tabla?
Esto se puede puede realizar de dos maneras
- Programación Orientada a Objetos.
  - Dentro del paquete __com.Model__ econtraras un par de clase de ejemplo de como se deben de acomodar los tipos de datos a la hora de realizar la consulta.
  - Ejemplos
    - [Person.java](com/Model/Person.java)
    - [Product.java](com/Model/Product.java)
    ~~~java
    //Ejemplo de productos.
    c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
    Product p = new Product("fvfdsvd", "Carne", 50.5f, 55f);
    boolean f = c.injectionQuery("insert into esquema.tabalNombre values (?,?,?,?);", p);
    //esquema - Nombre del esquema donde se almacenan las tablas.
    //tablaNombre - Nombre de la tabla.
    ~~~
  - Recuerda que estas clases implementan la interface __BodyStatement__ y que por cada Tabla existente en tu base de datos tendrás que crear un clase e implementar la misma interfaz (como en los ejemplos)
  
    
- Programación Lógica y Funcional.
Con este paradigma es más sencillo hacer las cosnsultas puesto que no tienes que crear una nueva clase, solo le indicamos los valores que vamos a agregar.
  ~~~java
  c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
  c.injectionQuery("insert into esquema.tablaNombre values (?,?,?,?);", pst -> {
        //... aignacion de los datos 
  });
  
   //esquema - Nombre del esquema donde se almacenan las tablas.
   //tablaNombre - Nombre de la tabla.
  ~~~
- Dentro de las llaves tendremos que escribir las posiciones y los valores que llevará la consulta, quedando de la siguiente  forma
~~~java
c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
c.injectionQuery("insert into esquema.tablaNombre values (?,?,?,?);", pst -> {
			
  pst.setString(1, "dbvuksdfbvjkdfs");// Tipo de dato - Dato
  pst.setString(2, "Tacos al pastor");// Tipo de dato - Dato
  pst.setFloat(3, 60);// Tipo de dato - Dato
  pst.setFloat(4, 70);// Tipo de dato - Dato
        
});

   //esquema - Nombre del esquema donde se almacenan las tablas.
   //tablaNombre - Nombre de la tabla.
~~~
- Recuerda que el metodo __set__ debe de coincidir con el tipo de dato que se encuentra en la tabla, puedes consultar esa informacion [aquin](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html) 


  
  
  
  
  
  

