package baseDeDatos;

import java.sql.*;
import javax.swing.*;

public class SQLiteConnection {
	private static Connection conn = null;
	
	public static Connection dbConnector(){ //va a devolver la conexi�n
		conn = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:.\\bd\\baseDeDatos\\BDPrueba.sqlite");
			//BORRAR
			JOptionPane.showMessageDialog(null,"Conexi�n exitosa"); //se supone que deber�a mostrar el error
			//BORRAR
			return conn;
		}catch(Exception e){
			 JOptionPane.showMessageDialog(null,e); //se supone que deber�a mostrar el error
			 return null;
		}
	}
}
