/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jsear
 */
public class Metodos_sql {

    public static ConexionBase conexion = new ConexionBase();

    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero;

    public int guardar(String nombre, String usuario, String contraseña) {

        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = ("INSERT INTO usuarios(nombre, usuario, contraseña) VALUES(?,?,?)");
        try {
            conexion = ConexionBase.conectar();
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1, nombre);
            sentencia_preparada.setString(2, usuario);
            sentencia_preparada.setString(3, contraseña);
            
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e);

        }
        return resultado;
    }
    
    public static String buscarNombre(String usuario){
       String busqueda_nombre = null;
       Connection conexion = null;
       try{
           conexion = ConexionBase.conectar();
           String sentencia_buscar = ("SELECT nombre FROM usuarios Where usuario ='" + usuario + "'");
           sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
           resultado = sentencia_preparada.executeQuery();
           if(resultado.next()){
               String nombre = resultado.getString("nombre");
               busqueda_nombre = (nombre);
           }
           conexion.close();
           
           
       } catch (Exception e){
           System.out.println();
       }
       return busqueda_nombre;
    }
    public static String buscarUsuarioRegistrado (String usuario, String contraseña){
        String busqueda_usuario = null;
        Connection conexion = null;
        try{
           conexion = ConexionBase.conectar();
           String sentencia_buscar_usuario = ("SELECT nombre,usuario,contraseña FROM usuarios WHERE usuario = '"+usuario+"' && contraseña = '"+contraseña+"'");
           resultado = sentencia_preparada.executeQuery();
           if(resultado.next()){
               busqueda_usuario="usuario encontrado";
           }else{
               busqueda_usuario="usuario no encontrado";
           }
           conexion.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return busqueda_usuario; 
    }
}
