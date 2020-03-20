/**
 * 
 */
package com.Test;

import java.sql.SQLException;
import java.util.Arrays;

import com.Conexion.Conexion;

/**
 * @author Usuario
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexion c = null;
		try {
			c = Conexion.getInstans("Contraseña", "NombreBaseDeDatos");
			c.injectionQuery("insert into esquema.tablaNombre values (?,?,?,?);", pst -> {
				pst.setString(1, "dbvuksdfbvjkdfs");// Tipo de dato - Dato
				pst.setString(2, "Tacos al pastor");// Tipo de dato - Dato
				pst.setFloat(3, 60);// Tipo de dato - Dato
				pst.setFloat(4, 70);// Tipo de dato - Dato
			});
			c.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
