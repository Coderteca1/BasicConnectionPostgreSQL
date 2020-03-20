/**
 * 
 */
package com.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Conexion.BodyStatemant;

/**
 * Esta clase es solo un modelo (ejemplo )para que no utilices programacion
 * logica y funcional si no entiendes.
 * 
 * 
 * Es solo un ejemplo, los datos representados son solo solo eso una
 * representacion, si tu necesitas otro modelo solo tendras que implementar la
 * interface "BodyStatement" e indicarle los valores que le enviaras a la tabla
 * siguiendo las reglas de escritura que nos idica la API de Java
 * 
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html
 * 
 * @author Alexis Narvaez Ruiz
 *
 */
public class Product implements BodyStatemant {
	private String idProduct;
	private String name;
	private float pracie_buy;
	private float pracie_sale;

	/**
	 * @param idProduct
	 * @param name
	 * @param pracie_buy
	 * @param pracie_sale
	 */
	public Product(String idProduct, String name, float pracie_buy, float pracie_sale) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.pracie_buy = pracie_buy;
		this.pracie_sale = pracie_sale;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pracie_buy
	 */
	public float getPracie_buy() {
		return pracie_buy;
	}

	/**
	 * @param pracie_buy the pracie_buy to set
	 */
	public void setPracie_buy(float pracie_buy) {
		this.pracie_buy = pracie_buy;
	}

	/**
	 * @return the pracie_sale
	 */
	public float getPracie_sale() {
		return pracie_sale;
	}

	/**
	 * @param pracie_sale the pracie_sale to set
	 */
	public void setPracie_sale(float pracie_sale) {
		this.pracie_sale = pracie_sale;
	}

	/**
	 * @return the idProduct
	 */
	public String getIdProduct() {
		return idProduct;
	}

	@Override
	public void body(PreparedStatement statemant) throws SQLException {
		// TODO Auto-generated method stub

		// Ten mucho cuidado de que los tipos de datos coincidan
		statemant.setString(1, this.idProduct);
		statemant.setString(2, this.name);
		statemant.setFloat(3, this.pracie_buy);
		statemant.setFloat(4, this.pracie_sale);
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", name=" + name + ", pracie_buy=" + pracie_buy + ", pracie_sale="
				+ pracie_sale + "]";
	}

}
