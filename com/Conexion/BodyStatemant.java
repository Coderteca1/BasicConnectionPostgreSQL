package com.Conexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Interfaz funcional que nos ayudara a dar cuerpo a los tipos de datos que
 * enviaremos a la tabla
 * 
 * @author Zona Programable.
 * 
 */
public interface BodyStatemant {

	/**
	 * @param statemant Objeto que nos servira para preparar la consulta a la base
	 *                  de datos.
	 * @throws SQLException Puede que los tipos de datos no sean compatibles.
	 */
	public void body(PreparedStatement statemant) throws SQLException;
}
