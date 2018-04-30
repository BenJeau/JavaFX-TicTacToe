package application;

import java.io.PrintWriter;
import java.util.Scanner;

public class EmissionNom implements Runnable {

	private PrintWriter out;
	private String login = null, message = null;
	private Scanner sc = null;
	private String loc = null;

	public EmissionNom(PrintWriter out) {
		this.out = out;

	}

	public void run() {

		sc = new Scanner(System.in);

		while (true) {			
			//loc = String.valueOf(Main.getPlayer1Name());
			if (loc != null) {
				out.println(loc);
			//	System.out.println(Main.getPlayer1Name() +" : " + loc);

			} else {
				System.out.println("Le serveur a : loc");
			}
			out.flush();
		}
	}
}
