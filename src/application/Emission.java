package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Emission implements Runnable {

	private PrintWriter out;
	private String login = null, message = null;
	private Scanner sc = null;
	private String loc = null;

	public Emission(PrintWriter out) {
		this.out = out;

	}

	public void run() {

		sc = new Scanner(System.in);

		while (true) {			
			//loc = String.valueOf(Main.getLoc());
			out.println(loc);
			out.flush();
		}
	}
}
