/**
 * 
 */
package com.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zona Programable.
 * 
 * 
 *         Patron de diseño: singleton.
 * 
 * 
 *         Ten mucho cuidado al usar esta clase para realizar las conexiones, no
 *         te recomiendo que la uses en cosas mas serias, solo usala como un
 *         experiemento (Para la escuela) :D
 */
public class Conexion {

	// ----------------Variables globales.----------------------------
	private static Conexion instants;

	// ----------------Varaibles de Objeto.----------------------------
	private String host;
	private String usuario;
	private String clave;
	private int puerto;
	private String servidor;
	private String baseDatos;
	private Connection conexion;
	private PreparedStatement preStatemant;

	/**
	 * Instrancia un solo objeto de tipo conexion
	 * 
	 * @param host
	 * @param usuario
	 * @param clave
	 * @param puerto
	 * @param baseDatos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Conexion(String host, String usuario, String clave, int puerto, String baseDatos)
			throws ClassNotFoundException, SQLException {
		super();
		this.host = host;
		this.usuario = usuario;
		this.clave = clave;
		this.puerto = puerto;
		this.baseDatos = baseDatos;
		this.servidor = "jdbc:postgresql://" + host + ":" + puerto + "/" + baseDatos;
		// this.buildConnection();
		Class.forName("org.postgresql.Driver");
		this.conexion = DriverManager.getConnection(this.servidor, this.usuario, this.clave);

		this.preStatemant = null;
	}

	/**
	 * El metodo puede fallar puede que no inserte todos los tipo de valores en
	 * postgresql <br>
	 * Solo siver para insertar o modificar no para pedir datos.
	 * 
	 * @param sql    Instruccion en sql por ejemeplo<br>
	 *               update biblioteca.cliente set nombre = ?, apellidos = ? where
	 *               nif = ?;
	 * @param types  Tipos de datos a los que pertenecen los valores.
	 * @param values Valores por los cuales intercambiaremos los signos de
	 *               interrogacion (espaciados)
	 * @throws SQLException Puede que los tipos de datos no sean compatibles.
	 * @return True si toda la consulta fue hecha con exito.
	 */
	@Deprecated
	public boolean injectionQuery(String sql, TypeData[] types, String... values) throws SQLException {
		this.preStatemant = this.conexion.prepareStatement(sql);
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			switch (types[i]) {
			case TEXT:
				this.preStatemant.setString(1 + i, value);
				break;
			case FLOAT:
				this.preStatemant.setFloat(1 + i, Float.parseFloat(value));
				break;
			// Si hace falta un tipo de variable
			// Agrega el nuevo caso aqui
			default:
				break;
			}
		}
		this.preStatemant.executeUpdate();
		return true;
	}

	/**
	 * El metodo resive Objetos de tipo BodyStatement (recomendable usar una lamnda
	 * pera evitar crear clases adicionales.) postgresql <br>
	 * Solo siver para insertar, actualizar o modificar no para pedir datos.
	 * 
	 * @param sql  Instruccion en sql por ejemeplo<br>
	 *             update biblioteca.cliente set nombre = ?, apellidos = ? where nif
	 *             = ?;
	 * @param body Lamnda- en esta expresion podremos pasar todos los tipos de
	 *             datops que necesitemos puesto que su cuerpo no esta definido.
	 * @throws SQLException Los tipos de datos puede que no sean compatibles.
	 * @return True si toda la consulta fue hecha con exito.
	 */
	public boolean injectionQuery(String sql, BodyStatemant body) throws SQLException {
		this.preStatemant = this.conexion.prepareStatement(sql);
		body.body(this.preStatemant);
		this.preStatemant.executeUpdate();
		return true;
	}

	/**
	 * @param sql    Instruccion en sql por ejemeplo<br>
	 *               Select [] from baseDeDatsi.tabla;<br>
	 *               los [] son los nombre de los campos (segundo parametro)
	 * @param campos Campos de la tabla a seleccionar por ejemplo <br>
	 *               nombre,apellido,edad,curp
	 * @return Matriz con todas las filas correspondientes a los valores
	 *         consultados.
	 * @throws SQLException Ocurrio unproblema al consultar los datos.
	 */
	public Object[][] getValues(String sql, String... campos) throws SQLException {
		String fields = Arrays.toString(campos).replace("[", "").replace("]", "");
		this.preStatemant = this.conexion.prepareStatement(sql.replace("[]", fields));
		ResultSet rsl = this.preStatemant.executeQuery();

		List<Object[]> rows = new ArrayList<Object[]>();// Conjunto de filas.

		while (rsl.next()) {
			Object[] row = new Object[campos.length];
			for (int i = 0; i < row.length; i++) {
				row[i] = rsl.getString(campos[i]);
			}
			rows.add(row);
		}
		Object[][] values = new Object[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			values[i] = rows.get(i);
		}
		rows.clear();
		rows = null;
		return values;
	}

	public void closeConnection() throws SQLException {
		// verifica que la conexión esté activa
		if (this.conexion != null) {
			this.conexion.close();
		}
	}

	@Override
	public String toString() {
		return "Conexion [host=" + host + ", usuario=" + usuario + ", clave=" + clave + ", puerto=" + puerto
				+ ", servidor=" + servidor + ", baseDatos=" + baseDatos + ", conexion=" + conexion + "]";
	}

	// ---------------------- Metodos estaticos.-------------------------------
	/**
	 * @param clave     Clave de usario
	 * @param baseDatos Nombre de la base de datos.
	 * @return the instants Una solo instancia de de tipo "Conexion"
	 * @throws SQLException           Error de SQL
	 * @throws ClassNotFoundException Clase no encontrada (JDBC no agrtegado.)
	 */
	public static Conexion getInstans(String clave, String baseDatos) throws ClassNotFoundException, SQLException {
		return Conexion.getInstants("localhost", "postgres", clave, 5432, baseDatos);
	}

	/**
	 * @param clave     Clave de usario
	 * @param puerto    Puerto del servidor de la base de datos.
	 * @param baseDatos Nombre de la base de datos.
	 * @return the instants Una solo instancia de de tipo "Conexion"
	 * @throws SQLException           Error de SQL
	 * @throws ClassNotFoundException Clase no encontrada (JDBC no agrtegado.)
	 */
	public static Conexion getInstans(String clave, int puerto, String baseDatos)
			throws ClassNotFoundException, SQLException {
		return Conexion.getInstants("localhost", "postgres", clave, puerto, baseDatos);
	}

	/**
	 * @param host      Host
	 * @param usuario   Nombre de usuario.
	 * @param clave     Clave de usario
	 * @param puerto    Puerto del servidor de la base de datos.
	 * @param baseDatos Nombre de la base de datos.
	 * @return the instants Un solo objeto de tipo "Conexion"
	 * @throws SQLException           Error de SQL
	 * @throws ClassNotFoundException Clase no encontrada (JDBC no agrtegado.)
	 */
	public static Conexion getInstants(String host, String usuario, String clave, int puerto, String baseDatos)
			throws ClassNotFoundException, SQLException {
		if (instants == null) {
			instants = new Conexion(host, usuario, clave, puerto, baseDatos);
		}
		return instants;
	}

}
