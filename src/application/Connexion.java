package application;

import java.net.*;
import java.util.Scanner;

import java.io.*;

public class Connexion implements Runnable {

	private Socket socket = null;
	public static String login = null, pass = null, message1 = null, message2 = null, message3 = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc = null;
	private Chat_ClientServeur ccs;

	public Connexion(Socket s) {

		socket = s;
	}

	public void run() {

		try {

			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sc = new Scanner(System.in);

			ccs = new Chat_ClientServeur(socket);

		} catch (IOException e) {

			System.err.println("Le serveur ne répond plus ");
		}
	}

}
