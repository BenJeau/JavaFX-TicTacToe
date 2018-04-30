package application;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceptionNom implements Runnable {

	private BufferedReader in;
	private String message = null;
	private String loc;

	public ReceptionNom(BufferedReader in) {

		this.in = in;
	}

	public void run() {

		while (true) {
			try {

				loc = in.readLine();
			//	Main.setPlayer2Name(loc);
				System.out.println("Le serveur vous dit :" + loc);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}