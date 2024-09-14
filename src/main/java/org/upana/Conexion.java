package org.upana;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;//alamacenar la conexion
        var url = "jdbc:mysql://localhost:3306/prueba1";//url de la base de datos
        var usuario = "root";//el usuario de la bd
        var password = "";//contra
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//driver de mysql
            conexion = DriverManager.getConnection(url, usuario, password);// establecemos la conexion
        }catch (Exception e){
            System.out.println("Error no se pudo conectar: " + e.getMessage());//captura el error durante la conexion
        }
        return conexion;//devuelve la conexion
    }


}

