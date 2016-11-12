package conexionBD;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JTextArea;

public class MySqlConnection {

	private static Connection conn;
	
	private MySqlConnection() {
	}
	
	public static Connection getConnection(JTextArea txtLog) {
		try {
			if(conn == null) {	
				String driver="com.mysql.jdbc.Driver"; //el driver varia segun la BD que usemos
				String url="jdbc:mysql://localhost/zombierush?autoReconnect=true";
				String usr="root";
				String pwd="admin";
				Class.forName(driver);
				conn = DriverManager.getConnection(url,usr,pwd);
			}
			else{
				txtLog.append("La conexión se encuentra realizada.\n");
				System.out.println("La conexión se encuentra realizada.");
			}
		} catch (ClassNotFoundException cnfe) {
			txtLog.append("No se encuentra el Driver.\n");
			System.err.println("No se encuentra el Driver.");
		} catch (SQLException sqle) {
			txtLog.append("Error al intentar la conexion. \n");
			System.err.println("Error al intentar la conexión.");
		}	
		return conn;
	}
	
	public static void close() {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("Desconexión de la BD exitosa.");
			}
		} catch (SQLException sqle) {
			System.err.println("Error al intentar la conexión.");
		}
	}
}
