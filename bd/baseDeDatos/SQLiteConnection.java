package baseDeDatos;

import java.sql.*;
import javax.swing.*;

import peticiones.CodigoPeticion;

public class SQLiteConnection {
	private static Connection conn = null;
	
	public SQLiteConnection () {
		conn = SQLiteConnection.dbConnector();
	}
		
	public static Connection dbConnector(){ //va a devolver la conexión
		conn = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:.\\bd\\baseDeDatos\\BDPrueba.sqlite");
			//BORRAR
			JOptionPane.showMessageDialog(null,"Conexión exitosa"); //se supone que debería mostrar el error
			//BORRAR
			return conn;
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null,e); //se supone que debería mostrar el error
			 return null;
		}
	}
	
	public int login(String usuario, String password){
		try{
			String query = "select * from UsuarioData where nombre = ? and password = ?";
			PreparedStatement pst= conn.prepareStatement(query); 
			pst.setString(0, usuario); //0 es el primer ? que pongo en la query
			pst.setString(1, password); //0 es el primer ? que pongo en la query
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()){
				count++;
			}
			if (count == 1){
				return 0;
			}
			else {
				 return 1;
			}
		}catch(	Exception e)
		{
			return CodigoPeticion.LOGEO_INCORRECTO;
		}
	}
}
