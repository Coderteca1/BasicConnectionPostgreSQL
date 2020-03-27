package com.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfaz funcional que nos ayudara a dar cuerpo a los tipos de datos que
 * recuperaremos de la tabla
 * 
 * @author Zona Programable.
 * 
 */
public interface BodyResulSet {
	/**
	 * @param rslst Objeto que nos servira para preparar definir los tipos de datos
	 *              que obtendremos.
	 * @return regresa un arreglo con registros de la base de datos.
	 * @throws SQLException Puede que los tipos de datos no sean compatibles.
	 */
	public Object[] body(ResultSet rslst) throws SQLException;

}
