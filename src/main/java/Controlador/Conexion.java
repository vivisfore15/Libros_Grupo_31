package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conexion {
	
    public String db="Grupo44_Equipo_5";
    public String url="jdbc:mariadb://prestamosvf.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/"+db;
    public String user="admin";
    public String pass="Ciclo3_admi123";
    Connection conec=null;
	
	public Connection Conectar() {
		
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		conec= DriverManager.getConnection(url,user,pass);
		//JOptionPane.showMessageDialog(null, "Conexion ok.....");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la Conexion....."+e);
		}
		return conec;
	}

}
