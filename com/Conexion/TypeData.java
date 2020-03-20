package com.Conexion;

/**
 * Enumerados que servian para simular los tipos de datos.
 * 
 * @author Zona Programable.
 *
 */
public enum TypeData {
	TEXT(), FLOAT();

	/**
	 * Devuelve un arreglo de los enumerados (constantes de Clase)
	 * 
	 * @param type Arreglo de enumerados.
	 * @return Regresa un arreglo de Enumerados.F
	 */
	public static TypeData[] buildArray(TypeData... type) {
		return type;
	}
}
