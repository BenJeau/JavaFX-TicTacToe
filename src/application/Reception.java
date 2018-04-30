package application;

import java.io.BufferedReader;
import java.io.IOException;

public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	private int loc;

	public Reception(BufferedReader in) {

		this.in = in;
	}

	public void run() {

		while (true) {
			try {

				loc = Integer.parseInt(in.readLine());
			//	Main.setLoc(loc);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}