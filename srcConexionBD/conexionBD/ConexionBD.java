package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;

import peticiones.CodigoPeticion;

public class ConexionBD {

	private Connection conn;
	
	public ConexionBD (JTextArea txtLog) {
		conn = MySqlConnection.getConnection(txtLog); 
	}
	
	public int login (String usuario, String password) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT nombre, password, permisos FROM Usuario"
							+ " WHERE nombre LIKE '"+usuario+"' "
							+ "AND password LIKE '"+password+"'");
			if(rs.next())
				return rs.getInt(3);
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return CodigoPeticion.LOGEO_INCORRECTO;
	}
	
	public boolean agregarJugador(String nombre, int minJ, int maxJ) {
		PreparedStatement pstmt = null;	    
		try {
			pstmt = conn.prepareStatement("INSERT INTO Partida VALUES (?,?,?,?,?)");
			pstmt.setString(1, nombre);
			pstmt.setInt(2, minJ);
			pstmt.setInt(3, maxJ);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.executeUpdate();
			return true;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return false;
	}
	
}
