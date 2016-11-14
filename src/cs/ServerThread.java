package cs;		
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import peticiones.CodigoPeticion;
import peticiones.PeticionLogueo;

public class ServerThread extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server sv;	

	public ServerThread(Socket s, Server sv) {
		super();
		this.sv = sv;
		this.s = s;
		try {
			this.oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OOS");
		}
		try {
			this.ois = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
			System.out.println("Problema generando el OIS");
		}
	}

	@Override
	public void run() {
		while(true){
			Mensaje mjeIn = null;
			try {
				mjeIn = (Mensaje) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("El thread del sv no encontró la clase Mensaje al hacer readObject");
			} catch (IOException e) {
				System.out.println("IOException en el thread del server al recibir un mensaje");
				e.printStackTrace();
			}
			switch (mjeIn.getCodigo()){
			case CodigoPeticion.LOGEO:
				PeticionLogueo petLog = (PeticionLogueo) mjeIn.getObj();
				try {
					oos.writeObject(new Mensaje(sv.getConexionBD().login(petLog.getUsuario().toString(), petLog.getPassword().toString()),null));		//manda mje con el código que devuelva el intento de login en la BD
					oos.flush();
					System.out.println("Mandó la respuesta eeeeeeeeee (después hay que borrar esto para que Lucas no nos golpee)");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
		
}