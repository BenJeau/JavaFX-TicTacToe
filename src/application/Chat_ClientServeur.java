package application;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Chat_ClientServeur implements Runnable {

	private Socket socket;
	private PrintWriter out = null, out1 = null;
	private BufferedReader in = null, in1 = null;
	private Scanner sc;
	private Emission em;
	private EmissionNom emn;
	private Reception re;
	private ReceptionNom ren;
	
	public Chat_ClientServeur(Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sc = new Scanner(System.in);
			
			if (Main.getTin() == 1){
				emn = new EmissionNom(out);
				ren = new ReceptionNom(in);
			} if (Main.getTin() == 0){
				re = new Reception(in);
				em = new Emission(out);
			}
		    
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}

