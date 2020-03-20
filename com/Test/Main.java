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
			c.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
