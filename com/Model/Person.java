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
public class Person implements BodyStatemant {
	private String id;
	private String name;
	private String firtsName;
	private int age;
	private char gen;

	/**
	 * @param id
	 * @param name
	 * @param firtsName
	 * @param age
	 * @param gen
	 */
	public Person(String id, String name, String firtsName, int age, char gen) {
		super();
		this.id = id;
		this.name = name;
		this.firtsName = firtsName;
		this.age = age;
		this.gen = gen;
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
	 * @return the firtsName
	 */
	public String getFirtsName() {
		return firtsName;
	}

	/**
	 * @param firtsName the firtsName to set
	 */
	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gen
	 */
	public char getGen() {
		return gen;
	}

	/**
	 * @param gen the gen to set
	 */
	public void setGen(char gen) {
		this.gen = gen;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	@Override
	public void body(PreparedStatement statemant) throws SQLException {
		// TODO Auto-generated method stub

		// Verifica que los tipos de datos coincidan.
		statemant.setString(1, this.id);
		statemant.setString(2, this.name);
		statemant.setString(3, this.firtsName);
		statemant.setInt(3, this.age);
		statemant.setString(4, this.gen + "");
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", firtsName=" + firtsName + ", age=" + age + ", gen=" + gen
				+ "]";
	}

}
