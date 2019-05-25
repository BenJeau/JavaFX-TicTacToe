package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	// Base of the game
	private Line line1, line2, line3, line4, line5, line6;
	private Circle circle1, circle2;
	private Button mainMenu, tryAgain;

	// Every tokens
	private Circle circleUN, circleDEUX, circleTROIS, circleQUATRE, circleCINQ, circleSIX, circleSEPT, circleHUIT,
			circleNEUF, circleUN1, circleDEUX1, circleTROIS1, circleQUATRE1, circleCINQ1, circleSIX1, circleSEPT1,
			circleHUIT1, circleNEUF1;
	private Line lineUN, lineUN1, lineDEUX, lineDEUX1, lineTROIS, lineTROIS1, lineQUATRE, lineQUATRE1, lineCINQ,
			lineCINQ1, lineSIX, lineSIX1, lineSEPT, lineSEPT1, lineHUIT, lineHUIT1, lineNEUF, lineNEUF1;

	// Every possibilities of winning
	private ArrayList<Integer> win1, win2, win3, win4, win5, win6, win7, win8;

	// Text displayed at the end of the game
	private Label winner;
	private String text1, text2;

	// Where token has already been played and where it is now
	private static int loc;
	private Boolean place1 = true, place2 = true, place3 = true, place4 = true, place5 = true, place6 = true,
			place7 = true, place8 = true, place9 = true;

	// Stores if someone won or tied
	private Boolean win = false, tie = false;

	// Stupid computer
	private int computerId = 1;

	// Places where AI can block
	private ArrayList<Integer> block1, block2, block3, block4, block5, block6, block7, block8, block9, block10, block11,
			block12, block13, block14, block15, block16, block17, block18, block19, block20, block21, block22, block23,
			block24;
	private Boolean loop1 = true, loop2 = true, loop3 = true, loop4 = true, loop5 = true, loop6 = true, loop7 = true,
			loop8 = true, loop9 = true, loop10 = true, loop11 = true, loop12 = true, loop13 = true, loop14 = true,
			loop15 = true, loop16 = true, loop17 = true, loop18 = true, loop19 = true, loop20 = true, loop21 = true,
			loop22 = true, loop23 = true, loop24 = true, loop25 = true, loop26 = true, loop27 = true, loop28 = true,
			loop29 = true, loop30 = true, loop31 = true, loop32 = true, loop33 = true, loop34 = true, loop35 = true;

	// Pointer - click
	private double x, y;
	private EventHandler<MouseEvent> mouseHandler;

	// Sound
	private MediaPlayer mainMenuSound, winSound, lostSound, clickSound;

	// Application icon
	private Image applicationIcon;

	// Layouts/scenes of the main menu (Base)
	private Pane hbox1, hbox2, hbox3, root;
	private Stage stage;
	private Scene scene1;
	private Button back;

	// Layouts/scenes of the game (Base)
	private Pane group, vbox;
	private Scene scene;
	private ArrayList<Integer> resultat1, resultat2;
	private int person;

	// Base of the main menu
	private Label label1;
	private Button button1, button2, button3, next;
	private String title = "Tic Tac Toe";

	// Elements of the name scene
	private Label label2, label3;
	private TextField textField1, textField2;

	// Game IDs
	private int gameId = 1;
	private int startId;

	// Score & names
	private int player1Score, player2Score;
	private Label score1, score2, player1, player2;
	public String player1Name;
	public String player2Name;

	// Difficulty screen
	private Pane pane;
	private Button easy, medium, hard;
	private Label select;

	// To know if use selected easy/medium/hard
	private int selection;

	// Tie score in the corner
	private int tieScore;
	private Label tieLabel;

	public Scene scene3;
	public Button back2;

	public Scene sceneButton1;
	public Scene sceneButton2;
	public Scene sceneButton3;

	public Pane hb;
	public TextField tf, tf1;

	public Button next2;
	public Label tx, tx1;

	public static PrintWriter out = null;
	public static BufferedReader in = null;

	@Override
	public void start(Stage primaryStage) {
		startId = 0;

		// Declaration of multiple important variables
		stage = primaryStage;
		root = new Pane();
		label1 = new Label(title);
		label1.setLayoutX(13.0);

		// Puts the application icon
		applicationIcon = new Image(getClass().getResource("/images/icon.png").toExternalForm());
		stage.getIcons().add(applicationIcon);

		// Plays main menu sound
		mainMenuSound = new MediaPlayer(new Media(getClass().getResource("/sound/jeux.mp3").toExternalForm()));
		mainMenuSound.play();
		mainMenuSound.setVolume(0);

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new KeyValue(mainMenuSound.volumeProperty(), 1)));
		timeline.play();

		// To get names
		label2 = new Label("What is the first player's name?");
		label2.setLayoutX(20.0);
		label2.setLayoutY(5.0);
		label3 = new Label("What is the second player's name?");
		label3.setLayoutX(5.0);
		label3.setLayoutY(70.0);
		textField1 = new TextField();
		textField1.setLayoutX(50.0);
		textField1.setLayoutY(35.0);
		textField2 = new TextField();
		textField2.setLayoutX(50.0);
		textField2.setLayoutY(100.0);
		next = new Button("Next");
		hbox1 = new Pane();
		hbox2 = new Pane();
		hbox3 = new Pane();

		// Selection of game difficulty
		pane = new Pane();
		easy = new Button();
		medium = new Button();
		hard = new Button();
		select = new Label("Select the difficulty of the computer");
		select.setLayoutX(10.0);
		select.setLayoutY(5.0);
		easy.setText("Novice");
		easy.setLayoutX(100.0);
		easy.setLayoutY(40.0);
		medium.setText("Intermediate");
		medium.setLayoutX(85.0);
		medium.setLayoutY(84.0);
		hard.setText("Expert");
		hard.setLayoutX(102.0);
		hard.setLayoutY(128.0);
		pane.setId("background");
		pane.getChildren().addAll(easy, medium, hard, select);

		// Back button
		back = new Button("Back");
		back.setOnAction(e -> {
			start(stage);
			mainMenuSound.stop();
		});

		tf = new TextField();
		tf1 = new TextField();
		hb = new Pane();
		tx = new Label();
		tx1 = new Label();

		// Back1 button
		back1 = new Button("Back");
		back1.setOnAction(e -> {
			stage.setScene(sceneButton3);
		});

		// Next1 button
		next1 = new Button("Next");
		// next1.setDisable(true);
		next1.setOnAction(e -> {
			player1Name = textField1.getText();
			if (startId == 3) {
				game(player1Name, player2Name);
				stage.setScene(scene);
			}
		});

		// Next2 button
		next2 = new Button("Next");
		next2.setLayoutX(165.0);
		next2.setLayoutY(155.0);
		next2.setOnAction(e -> {
			player1Name = textField1.getText();

			tin = 1;
			ip(player1Name);
			stage.setScene(scene2);

		});
		
		MediaPlayer md = new MediaPlayer(new Media(getClass().getResource("/sound/type.mp3").toExternalForm()));
		
		// Plays sound textField1
		textField1.textProperty().addListener(e -> {
			md.seek(Duration.ZERO);
			md.play();
		});

		// Plays sound textField2
		textField2.textProperty().addListener(e -> {
			md.seek(Duration.ZERO);
			md.play();
		});

		// Plays sound tf
		tf.textProperty().addListener(e -> {
			md.seek(Duration.ZERO);
			md.play();
		});
		tf1.setText("127.0.0.1");

		// Plays sound tf1
		tf1.textProperty().addListener(e -> {
			md.seek(Duration.ZERO);
			md.play();
		});

		button1 = new Button("Single Player");
		button1.setLayoutX(70.0);
		button1.setLayoutY(84.0);
		button1.setOnAction(e -> {
			if (startId == 0) {
				back.setLayoutX(70.0);
				back.setLayoutY(85.0);
				next.setLayoutX(165.0);
				next.setLayoutY(85.0);
				hbox1.getChildren().addAll(label2, textField1, next, back);
				sceneButton1 = new Scene(hbox1, 300, 130);
			}
			startId = 1;
			stage.setScene(sceneButton1);
		});

		easy.setOnAction(e -> {
			selection = 1;
			game(player1Name, player2Name);
			stage.setScene(scene);
		});

		medium.setOnAction(e -> {
			selection = 2;
			game(player1Name, player2Name);
			stage.setScene(scene);
		});

		hard.setOnAction(e -> {
			selection = 3;
			game(player1Name, player2Name);
			stage.setScene(scene);
		});

		button2 = new Button("Multiplayer");
		button2.setLayoutX(76.0);
		button2.setLayoutY(128.0);
		button2.setOnAction(e -> {
			if (startId == 0) {
				back.setLayoutX(70.0);
				back.setLayoutY(155.0);
				next.setLayoutX(165.0);
				next.setLayoutY(155.0);
				hbox2.getChildren().addAll(label2, textField1, label3, textField2, next, back);
				sceneButton2 = new Scene(hbox2, 300, 200);
			}
			startId = 2;
			stage.setScene(sceneButton2);
		});

		tx = new Label("Select your port");
		tx.setLayoutX(72.0);
		tx.setLayoutY(135.0);

		tf.setLayoutX(50.0);
		tf.setLayoutY(165.0);

		tx1 = new Label("Select your address");
		tx1.setLayoutX(60.0);
		tx1.setLayoutY(70.0);

		tf1.setLayoutX(50.0);
		tf1.setLayoutY(100.0);

		button3 = new Button("Network multiplayer");
		button3.setLayoutX(52.0);
		button3.setLayoutY(172.0);
		button3.setOnAction(e -> {
			if (startId == 0) {
				back.setLayoutX(70.0);
				back.setLayoutY(215.0);
				next2.setLayoutX(165.0);
				next2.setLayoutY(215.0);
				hbox3.getChildren().addAll(label2, textField1, tx, tx1, tf, tf1, next2, back);
				sceneButton3 = new Scene(hbox3, 300, 260);
			}
			startId = 3;
			stage.setScene(sceneButton3);
		});

		verification = new Button("Connect");
		verification.setOnAction(e -> {
			verification();
			te = new Label("Succesful!");
			best = new Pane();
			next1.setDisable(false);
			con.setId("server");
			te.setId("mainTitle");
			te.setLayoutX(68);
			te.setLayoutY(60);
			best.setId("background");
			best.getChildren().addAll(con, next1, back1, te);
			stage.setScene(new Scene(best, 300, 200));
		});

		// Defines what the "Next" button does
		next.setOnAction(e -> {
			player1Name = textField1.getText();
			player2Name = textField2.getText();
			if (startId == 1) {
				player2Name = "Computer";
				scene = new Scene(pane, 300, 180);
				stage.setScene(scene);
			} else if (startId == 2) {
				game(player1Name, player2Name);
				stage.setScene(scene);
			} else if (startId == 3) {
				stage.setScene(scene3);
			}
		});

		// Setting IDs for CSS
		label1.setId("mainTitle");
		root.setId("background");
		label2.setId("name");
		label3.setId("name");
		textField1.setId("field");
		textField2.setId("field");
		tf.setId("field");
		tf1.setId("field");
		tx.setId("name");
		tx1.setId("name");
		hbox1.setId("background");
		hbox2.setId("background");
		hbox3.setId("background");
		hb.setId("background");
		Application.setUserAgentStylesheet(getClass().getResource("application.css").toExternalForm());
		
		root.getChildren().addAll(label1, button1, button2, button3);
		scene1 = new Scene(root, 262, 215);
		stage.setTitle("Client");
		stage.setScene(scene1);
		stage.setResizable(false);
		stage.show();
	}

	public Pane best;

	public void checkTie() {
		if (gameId > 9 && win != true && tie == false) {
			lostSound.play();
			tie = true;
			winner.setText("TIE!");
			tieScore = tieScore + 1;
			vbox.getChildren().addAll(winner, tryAgain, mainMenu);
			group.getChildren().addAll(vbox);
		}
	}

	public void checkLocationClick() {
		if (x > 570 && x < 796) {
			if (y > 17 && y < 243) {
				loc = 4;
			} else if (y > 247 && y < 473) {
				loc = 5;
			} else if (y > 477 && y < 703) {
				loc = 6;
			}
		} else if (x > 340 && x < 566) {
			if (y > 17 && y < 243) {
				loc = 1;
			} else if (y > 247 && y < 473) {
				loc = 2;
			} else if (y > 477 && y < 703) {
				loc = 3;
			}
		} else if (x > 800 && x < 1026) {
			if (y > 17 && y < 243) {
				loc = 7;
			} else if (y > 247 && y < 473) {
				loc = 8;
			} else if (y > 477 && y < 703) {
				loc = 9;
			}
		}
	}

	public void placeTokens() {
		if (loc == 1 && place1) {
			clickSound.seek(Duration.ZERO);
			place1 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleUN);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineUN, lineUN1);
				resultat2.add(loc);
			}
		} else if (loc == 2 && place2) {
			clickSound.seek(Duration.ZERO);
			place2 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleDEUX);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineDEUX, lineDEUX1);
				resultat2.add(loc);
			}
		} else if (loc == 3 && place3) {
			clickSound.seek(Duration.ZERO);
			place3 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleTROIS);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineTROIS, lineTROIS1);
				resultat2.add(loc);
			}
		} else if (loc == 4 && place4) {
			clickSound.seek(Duration.ZERO);
			place4 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleQUATRE);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineQUATRE, lineQUATRE1);
				resultat2.add(loc);
			}
		} else if (loc == 5 && place5) {
			clickSound.seek(Duration.ZERO);
			place5 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleCINQ);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineCINQ, lineCINQ1);
				resultat2.add(loc);
			}
		} else if (loc == 6 && place6) {
			clickSound.seek(Duration.ZERO);
			place6 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleSIX);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineSIX, lineSIX1);
				resultat2.add(loc);
			}
		} else if (loc == 7 && place7) {
			clickSound.seek(Duration.ZERO);
			place7 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleSEPT);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineSEPT, lineSEPT1);
				resultat2.add(loc);
			}
		} else if (loc == 8 && place8) {
			clickSound.seek(Duration.ZERO);
			place8 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleHUIT);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineHUIT, lineHUIT1);
				resultat2.add(loc);
			}
		} else if (loc == 9 && place9) {
			clickSound.seek(Duration.ZERO);
			place9 = false;
			if (person == 1) {
				clickSound.play();
				group.getChildren().add(circleNEUF);
				resultat1.add(loc);
			} else {
				clickSound.play();
				group.getChildren().addAll(lineNEUF, lineNEUF1);
				resultat2.add(loc);
			}
		} else {
			gameId--;
		}
	}

	public void stupidComputer() {
		if (person == 2) {
			int rand = 1 + (int) (Math.random() * ((9 - 1) + 1));
			while (resultat1.contains(rand) || resultat2.contains(rand)) {
				rand = 1 + (int) (Math.random() * ((9 - 1) + 1));
			}
			loc = rand;
			resultat2.add(rand);
		}
	}

	public void smartComputer() {
		block1 = new ArrayList<Integer>();
		block2 = new ArrayList<Integer>();
		block3 = new ArrayList<Integer>();
		block4 = new ArrayList<Integer>();
		block5 = new ArrayList<Integer>();
		block6 = new ArrayList<Integer>();
		block7 = new ArrayList<Integer>();
		block8 = new ArrayList<Integer>();
		block9 = new ArrayList<Integer>();
		block10 = new ArrayList<Integer>();
		block11 = new ArrayList<Integer>();
		block12 = new ArrayList<Integer>();
		block13 = new ArrayList<Integer>();
		block14 = new ArrayList<Integer>();
		block15 = new ArrayList<Integer>();
		block16 = new ArrayList<Integer>();
		block17 = new ArrayList<Integer>();
		block18 = new ArrayList<Integer>();
		block19 = new ArrayList<Integer>();
		block20 = new ArrayList<Integer>();
		block21 = new ArrayList<Integer>();
		block22 = new ArrayList<Integer>();
		block23 = new ArrayList<Integer>();
		block24 = new ArrayList<Integer>();

		block1.add(1);
		block1.add(2);
		block2.add(4);
		block2.add(5);
		block3.add(7);
		block3.add(8);
		block4.add(2);
		block4.add(3);
		block5.add(5);
		block5.add(6);
		block6.add(8);
		block6.add(9);
		block7.add(7);
		block7.add(4);
		block8.add(8);
		block8.add(5);
		block9.add(9);
		block9.add(6);
		block10.add(1);
		block10.add(4);
		block11.add(2);
		block11.add(5);
		block12.add(3);
		block12.add(6);
		block13.add(5);
		block13.add(9);
		block14.add(1);
		block14.add(5);
		block15.add(7);
		block15.add(5);
		block16.add(5);
		block16.add(3);
		block17.add(1);
		block17.add(7);
		block18.add(2);
		block18.add(8);
		block19.add(3);
		block19.add(9);
		block20.add(1);
		block20.add(3);
		block21.add(4);
		block21.add(6);
		block22.add(7);
		block22.add(9);
		block23.add(1);
		block23.add(9);
		block24.add(7);
		block24.add(3);

		// Puts computer token
		if (resultat1.isEmpty() == false) {
			if (resultat2.containsAll(block1) && loop1 && place3) {
				loop1 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat2.containsAll(block2) && loop2 && place6) {
				loop2 = false;
				place6 = false;
				resultat2.add(6);
				group.getChildren().addAll(lineSIX, lineSIX1);
			} else if (resultat2.containsAll(block3) && loop3 && place9) {
				loop3 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat2.containsAll(block4) && loop4 && place1) {
				loop4 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat2.containsAll(block5) && loop5 && place4) {
				loop5 = false;
				place4 = false;
				resultat2.add(4);
				group.getChildren().addAll(lineQUATRE, lineQUATRE1);
			} else if (resultat2.containsAll(block6) && loop6 && place7) {
				loop6 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat2.containsAll(block7) && loop7 && place1) {
				loop7 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat2.containsAll(block8) && loop8 && place2) {
				loop8 = false;
				place2 = false;
				resultat2.add(2);
				group.getChildren().addAll(lineDEUX, lineDEUX1);
			} else if (resultat2.containsAll(block9) && loop9 && place3) {
				loop9 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat2.containsAll(block10) && loop10 && place7) {
				loop10 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat2.containsAll(block11) && loop11 && place8) {
				loop11 = false;
				place8 = false;
				resultat2.add(8);
				group.getChildren().addAll(lineHUIT, lineHUIT1);
			} else if (resultat2.containsAll(block12) && loop12 && place9) {
				loop12 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat2.containsAll(block13) && loop13 && place1) {
				loop13 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat2.containsAll(block14) && loop14 && place9) {
				loop14 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat2.containsAll(block15) && loop15 && place3) {
				loop15 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat2.containsAll(block16) && loop16 && place7) {
				loop16 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat2.containsAll(block17) && loop22 && place4) {
				loop22 = false;
				place4 = false;
				resultat2.add(4);
				group.getChildren().addAll(lineQUATRE, lineQUATRE1);
			} else if (resultat2.containsAll(block18) && loop23 && place5) {
				loop23 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat2.containsAll(block19) && loop24 && place6) {
				loop24 = false;
				place6 = false;
				resultat2.add(6);
				group.getChildren().addAll(lineSIX, lineSIX1);
			} else if (resultat2.containsAll(block20) && loop25 && place2) {
				loop25 = false;
				place2 = false;
				resultat2.add(2);
				group.getChildren().addAll(lineDEUX, lineDEUX1);
			} else if (resultat2.containsAll(block21) && loop26 && place5) {
				loop26 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat2.containsAll(block22) && loop27 && place8) {
				loop27 = false;
				place8 = false;
				resultat2.add(8);
				group.getChildren().addAll(lineHUIT, lineHUIT1);
			} else if (resultat2.containsAll(block23) && loop28 && place5) {
				loop28 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat2.containsAll(block24) && loop29 && place5) {
				loop29 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.containsAll(block1) && loop1 && place3) {
				loop1 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat1.containsAll(block2) && loop2 && place6) {
				loop2 = false;
				place6 = false;
				resultat2.add(6);
				group.getChildren().addAll(lineSIX, lineSIX1);
			} else if (resultat1.containsAll(block3) && loop3 && place9) {
				loop3 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat1.containsAll(block4) && loop4 && place1) {
				loop4 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat1.containsAll(block5) && loop5 && place4) {
				loop5 = false;
				place4 = false;
				resultat2.add(4);
				group.getChildren().addAll(lineQUATRE, lineQUATRE1);
			} else if (resultat1.containsAll(block6) && loop6 && place7) {
				loop6 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat1.containsAll(block7) && loop7 && place1) {
				loop7 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat1.containsAll(block8) && loop8 && place2) {
				loop8 = false;
				place2 = false;
				resultat2.add(2);
				group.getChildren().addAll(lineDEUX, lineDEUX1);
			} else if (resultat1.containsAll(block9) && loop9 && place3) {
				loop9 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat1.containsAll(block10) && loop10 && place7) {
				loop10 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat1.containsAll(block11) && loop11 && place8) {
				loop11 = false;
				place8 = false;
				resultat2.add(8);
				group.getChildren().addAll(lineHUIT, lineHUIT1);
			} else if (resultat1.containsAll(block12) && loop12 && place9) {
				loop12 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat1.containsAll(block13) && loop13 && place1) {
				loop13 = false;
				place1 = false;
				resultat2.add(1);
				group.getChildren().addAll(lineUN, lineUN1);
			} else if (resultat1.containsAll(block14) && loop14 && place9) {
				loop14 = false;
				place9 = false;
				resultat2.add(9);
				group.getChildren().addAll(lineNEUF, lineNEUF1);
			} else if (resultat1.containsAll(block15) && loop15 && place3) {
				loop15 = false;
				place3 = false;
				resultat2.add(3);
				group.getChildren().addAll(lineTROIS, lineTROIS1);
			} else if (resultat1.containsAll(block16) && loop16 && place7) {
				loop16 = false;
				place7 = false;
				resultat2.add(7);
				group.getChildren().addAll(lineSEPT, lineSEPT1);
			} else if (resultat1.contains(1) && loop17 && place5) {
				loop17 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.contains(3) && loop18 && place5) {
				loop18 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.contains(7) && loop19 && place5) {
				loop19 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.contains(9) && loop20 && place5) {
				loop20 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.contains(5) && loop21) {
				loop21 = false;
				if (place1) {
					place1 = false;
					resultat2.add(1);
					group.getChildren().addAll(lineUN, lineUN1);
				} else if (place3) {
					place3 = false;
					resultat2.add(3);
					group.getChildren().addAll(lineTROIS, lineTROIS1);
				} else if (place7) {
					place7 = false;
					resultat2.add(7);
					group.getChildren().addAll(lineSEPT, lineSEPT1);
				} else if (place9) {
					place9 = false;
					resultat2.add(9);
					group.getChildren().addAll(lineNEUF, lineNEUF1);
				}
			} else if (resultat1.containsAll(block17) && loop22 && place4) {
				loop22 = false;
				place4 = false;
				resultat2.add(4);
				group.getChildren().addAll(lineQUATRE, lineQUATRE1);
			} else if (resultat1.containsAll(block18) && loop23 && place5) {
				loop23 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.containsAll(block19) && loop24 && place6) {
				loop24 = false;
				place6 = false;
				resultat2.add(6);
				group.getChildren().addAll(lineSIX, lineSIX1);
			} else if (resultat1.containsAll(block20) && loop25 && place2) {
				loop25 = false;
				place2 = false;
				resultat2.add(2);
				group.getChildren().addAll(lineDEUX, lineDEUX1);
			} else if (resultat1.containsAll(block21) && loop26 && place5) {
				loop26 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.containsAll(block22) && loop27 && place8) {
				loop27 = false;
				place8 = false;
				resultat2.add(8);
				group.getChildren().addAll(lineHUIT, lineHUIT1);
			} else if (resultat1.containsAll(block23) && loop28 && place5) {
				loop28 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.containsAll(block24) && loop29 && place5) {
				loop29 = false;
				place5 = false;
				resultat2.add(5);
				group.getChildren().addAll(lineCINQ, lineCINQ1);
			} else if (resultat1.contains(2) && loop30) {
				loop30 = false;
				if (place1) {
					place1 = false;
					resultat2.add(1);
					group.getChildren().addAll(lineUN, lineUN1);
				} else if (place3) {
					place3 = false;
					resultat2.add(3);
					group.getChildren().addAll(lineTROIS, lineTROIS1);
				} else if (place7) {
					place7 = false;
					resultat2.add(7);
					group.getChildren().addAll(lineSEPT, lineSEPT1);
				} else if (place9) {
					place9 = false;
					resultat2.add(9);
					group.getChildren().addAll(lineNEUF, lineNEUF1);
				}
			} else if (resultat1.contains(4) && loop31) {
				loop31 = false;
				if (place1) {
					place1 = false;
					resultat2.add(1);
					group.getChildren().addAll(lineUN, lineUN1);
				} else if (place3) {
					place3 = false;
					resultat2.add(3);
					group.getChildren().addAll(lineTROIS, lineTROIS1);
				} else if (place7) {
					place7 = false;
					resultat2.add(7);
					group.getChildren().addAll(lineSEPT, lineSEPT1);
				} else if (place9) {
					place9 = false;
					resultat2.add(9);
					group.getChildren().addAll(lineNEUF, lineNEUF1);
				}
			} else if (resultat1.contains(8) && loop32) {
				loop32 = false;
				if (place1) {
					place1 = false;
					resultat2.add(1);
					group.getChildren().addAll(lineUN, lineUN1);
				} else if (place3) {
					place3 = false;
					resultat2.add(3);
					group.getChildren().addAll(lineTROIS, lineTROIS1);
				} else if (place7) {
					place7 = false;
					resultat2.add(7);
					group.getChildren().addAll(lineSEPT, lineSEPT1);
				} else if (place9) {
					place9 = false;
					resultat2.add(9);
					group.getChildren().addAll(lineNEUF, lineNEUF1);
				}
			} else if (resultat1.contains(6) && loop33) {
				loop33 = false;
				if (place1) {
					place1 = false;
					resultat2.add(1);
					group.getChildren().addAll(lineUN, lineUN1);
				} else if (place3) {
					place3 = false;
					resultat2.add(3);
					group.getChildren().addAll(lineTROIS, lineTROIS1);
				} else if (place7) {
					place7 = false;
					resultat2.add(7);
					group.getChildren().addAll(lineSEPT, lineSEPT1);
				} else if (place9) {
					place9 = false;
					resultat2.add(9);
					group.getChildren().addAll(lineNEUF, lineNEUF1);
				}
			} else if (resultat1.containsAll(block24) && loop34) {
				loop34 = false;
				if (place2) {
					place2 = false;
					resultat2.add(2);
					group.getChildren().addAll(lineDEUX, lineDEUX1);
				} else if (place4) {
					place4 = false;
					resultat2.add(4);
					group.getChildren().addAll(lineQUATRE, lineQUATRE1);
				} else if (place6) {
					place6 = false;
					resultat2.add(6);
					group.getChildren().addAll(lineSIX, lineSIX1);
				} else if (place8) {
					place8 = false;
					resultat2.add(8);
					group.getChildren().addAll(lineHUIT, lineHUIT1);
				}
			} else if (resultat1.containsAll(block23) && loop35) {
				loop35 = false;
				if (place2) {
					place2 = false;
					resultat2.add(2);
					group.getChildren().addAll(lineDEUX, lineDEUX1);
				} else if (place4) {
					place4 = false;
					resultat2.add(4);
					group.getChildren().addAll(lineQUATRE, lineQUATRE1);
				} else if (place6) {
					place6 = false;
					resultat2.add(6);
					group.getChildren().addAll(lineSIX, lineSIX1);
				} else if (place8) {
					place8 = false;
					resultat2.add(8);
					group.getChildren().addAll(lineHUIT, lineHUIT1);
				}
			}
		}
	}

	public void tokensDimension() {
		// Defines the dimensions of the tokens
		circleUN = new Circle(453, 130, 99);
		circleDEUX = new Circle(453, 360, 99);
		circleTROIS = new Circle(453, 590, 99);
		circleQUATRE = new Circle(683, 130, 99);
		circleCINQ = new Circle(683, 360, 99);
		circleSIX = new Circle(683, 590, 99);
		circleSEPT = new Circle(913, 130, 99);
		circleHUIT = new Circle(913, 360, 99);
		circleNEUF = new Circle(913, 590, 99);
		circleUN1 = new Circle(453, 130, 98);
		circleDEUX1 = new Circle(453, 360, 98);
		circleTROIS1 = new Circle(453, 590, 98);
		circleQUATRE1 = new Circle(683, 130, 98);
		circleCINQ1 = new Circle(683, 360, 98);
		circleSIX1 = new Circle(683, 590, 98);
		circleSEPT1 = new Circle(913, 130, 98);
		circleHUIT1 = new Circle(913, 360, 98);
		circleNEUF1 = new Circle(913, 590, 98);
		circleUN1.setFill(Color.WHITE);
		circleDEUX1.setFill(Color.WHITE);
		circleTROIS1.setFill(Color.WHITE);
		circleQUATRE1.setFill(Color.WHITE);
		circleCINQ1.setFill(Color.WHITE);
		circleSIX1.setFill(Color.WHITE);
		circleSEPT1.setFill(Color.WHITE);
		circleHUIT1.setFill(Color.WHITE);
		circleNEUF1.setFill(Color.WHITE);
		lineUN = new Line(353, 30, 553, 230);
		lineUN1 = new Line(353, 230, 553, 30);
		lineDEUX = new Line(353, 260, 553, 460);
		lineDEUX1 = new Line(353, 460, 553, 260);
		lineTROIS = new Line(353, 490, 553, 690);
		lineTROIS1 = new Line(353, 690, 553, 490);
		lineQUATRE = new Line(583, 30, 783, 230);
		lineQUATRE1 = new Line(583, 230, 783, 30);
		lineCINQ = new Line(583, 260, 783, 460);
		lineCINQ1 = new Line(583, 460, 783, 260);
		lineSIX = new Line(583, 490, 783, 690);
		lineSIX1 = new Line(583, 690, 783, 490);
		lineSEPT = new Line(813, 30, 1013, 230);
		lineSEPT1 = new Line(813, 230, 1013, 30);
		lineHUIT = new Line(813, 260, 1013, 460);
		lineHUIT1 = new Line(813, 460, 1013, 260);
		lineNEUF = new Line(813, 490, 1013, 690);
		lineNEUF1 = new Line(813, 690, 1013, 490);
	}

	public void checkWinning() {
		// Defines every possibilities of winning
		win1 = new ArrayList<Integer>();
		win2 = new ArrayList<Integer>();
		win3 = new ArrayList<Integer>();
		win4 = new ArrayList<Integer>();
		win5 = new ArrayList<Integer>();
		win6 = new ArrayList<Integer>();
		win7 = new ArrayList<Integer>();
		win8 = new ArrayList<Integer>();

		win1.add(1);
		win1.add(2);
		win1.add(3);
		win2.add(4);
		win2.add(5);
		win2.add(6);
		win3.add(7);
		win3.add(8);
		win3.add(9);
		win4.add(1);
		win4.add(4);
		win4.add(7);
		win5.add(2);
		win5.add(5);
		win5.add(8);
		win6.add(3);
		win6.add(6);
		win6.add(9);
		win7.add(1);
		win7.add(5);
		win7.add(9);
		win8.add(3);
		win8.add(5);
		win8.add(7);

		// Checks if someone wins
		if (resultat1.containsAll(win1)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win1)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win2)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win2)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win3)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win3)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win4)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win4)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win5)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win5)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win6)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win6)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win7)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win7)) {
			win = true;
			winner.setText(text2);
			player2Score = player2Score + 1;
		} else if (resultat1.containsAll(win8)) {
			win = true;
			winner.setText(text1);
			player1Score = player1Score + 1;
		} else if (resultat2.containsAll(win8)) {
			win = true;
			player2Score = player2Score + 1;
			winner.setText(text2);
		}

		// Plays sound when someone wins
		if (win) {
			gameId = 10;
			if (startId == 1 || startId == 3) {
				if (winner.getText().equals(text2)) {
					lostSound.play();
				} else {
					winSound.play();
				}
			} else {
				winSound.play();
			}
			vbox.getChildren().addAll(winner, tryAgain, mainMenu);
			group.getChildren().addAll(vbox);
		}
	}

	public static Socket clientSocket = null;

	public void game(String player1, String player2) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new KeyValue(mainMenuSound.volumeProperty(), 0)));
		timeline.play();
		
		base(player1, player2);
		System.out.println("client :" + player2Name);

		mouseHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (startId == 3) {

					gameIntel(mouseEvent);
				} else {
					gameIntel(mouseEvent);
					gameIntel(mouseEvent);
				}

			}
		};

		scene = new Scene(group, 1366, 720);
		scene.setOnMousePressed(mouseHandler);
	}

	public void gameIntel(MouseEvent mouseEvent) {

		if (gameId <= 9) {

			// Checks who's turn is
			if (gameId % 2 == 0) {
				person = 2;
			} else {
				person = 1;
			}

			// Sets the location of the click
			x = mouseEvent.getX();
			y = mouseEvent.getY();

			// Defines the dimensions of the tokens
			tokensDimension();

			// Checks where the player clicked
			if (startId == 1) {
				if (person == 1) {
					checkLocationClick();
				}
				// Computer brain
				if (person == 2) {
					if (selection == 1) {
						stupidComputer();
					} else if (selection == 2) {
						if (computerId == 2) {
							smartComputer();
							gameId = gameId + 1;
							computerId = 1;
						} else if (computerId == 1) {
							stupidComputer();
							computerId = 2;
						}
					} else if (selection == 3) {
						smartComputer();
						gameId = gameId + 1;
					}
				}
			} else if (startId == 2) {
				checkLocationClick();
			} else if (startId == 3) {

				if (person == 1) {
					checkLocationClick();
					out.println(loc);
				} else if (person == 2) {
					tin = 0;
					tin = 2;
					try {
						loc = Integer.parseInt(in.readLine());
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			// Checks where to put the tokens
			placeTokens();

			if (gameId >= 5) {
				text1 = player1Name + " WIN";
				text2 = player2Name + " WIN";

				checkWinning();
			}

			// Keeping up with how many turn there is
			gameId = gameId + 1;
		}

		// Checks if there's a tie
		checkTie();
	}

	public void restart() {

		if (startId == 3) {

		}
		gameId = 1;
		win = false;
		tie = false;
		resultat1.clear();
		resultat2.clear();
		place1 = true;
		place2 = true;
		place3 = true;
		place4 = true;
		place5 = true;
		place6 = true;
		place7 = true;
		place8 = true;
		place9 = true;
		loop1 = true;
		loop2 = true;
		loop3 = true;
		loop4 = true;
		loop5 = true;
		loop6 = true;
		loop7 = true;
		loop8 = true;
		loop9 = true;
		loop10 = true;
		loop11 = true;
		loop12 = true;
		loop13 = true;
		loop14 = true;
		loop15 = true;
		loop16 = true;
		loop17 = true;
		loop18 = true;
		loop19 = true;
		loop20 = true;
		loop21 = true;
		loop22 = true;
		loop23 = true;
		loop24 = true;
		loop25 = true;
		loop26 = true;
		loop27 = true;
		loop28 = true;
		loop29 = true;
		loop30 = true;
		loop31 = true;
		loop32 = true;
		loop33 = true;
		loop34 = true;
		loop35 = true;
		loc = 0;
		winSound.stop();
		lostSound.stop();
	}

	public static int getTin() {
		return tin;
	}

	public static int tin;

	public Button next1;
	public Button back1;
	public Scene scene2;
	public Button verification;

	public Label con, te, avis;

	public void ip(String player1) {
		// Declaration of multiple important variables
		group = new Pane();
		con = new Label("Connexion au client");
		te = new Label("RÉUSSI!");
		avis = new Label("Le serveur doit être actif");
		avis.setLayoutY(20);
		avis.setLayoutX(10);
		back1.setLayoutX(70.0);
		back1.setLayoutY(155.0);
		next1.setLayoutX(165.0);
		next1.setLayoutY(155.0);
		con.setId("server");

		next1.setDisable(true);
		te.setId("mainTitle");

		te.setLayoutX(68);
		te.setLayoutY(60);

		verification.setLayoutX(105.0);
		verification.setLayoutY(75.0);

		group.setId("background");
		avis.setId("attention");

		group.getChildren().addAll(con, next1, back1, verification, avis);
		scene2 = new Scene(group, 300, 200);

	}

	public void verification() {
		try {
			System.out.println("Demande de connexion");
			clientSocket = new Socket(tf1.getText(), Integer.valueOf(tf.getText()));
			System.out.println("Connexion etablie avec le serveur, authentification :");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// switch names
			player2Name = in.readLine();
			System.out.println("client :" + player2Name);
			out.println(player1Name);

			next1.setDisable(false);

		} catch (UnknownHostException f) {
			System.err.println("Impossible de se connecter a l'adresse " + clientSocket.getLocalAddress());
		} catch (IOException f) {
			System.err.println("Aucun serveur a l'ecoute du port " + clientSocket.getLocalPort());
		}
	}

	public void base(String player1, String player2) {
		// Declaration of multiple important variables
		group = new Pane();
		vbox = new Pane();
		winner = new Label();
		winner.setLayoutX(650);
		winner.setLayoutY(340);
		winner.setId("winner");

		// Stores the location of the tokens
		resultat1 = new ArrayList<Integer>();
		resultat2 = new ArrayList<Integer>();

		// Declaration of music
		winSound = new MediaPlayer(new Media(getClass().getResource("/sound/win.wav").toExternalForm()));
		lostSound = new MediaPlayer(new Media(getClass().getResource("/sound/gun.mp3").toExternalForm()));
		clickSound = new MediaPlayer(new Media(getClass().getResource("/sound/click.wav").toExternalForm()));

		// Defines what the "Try Again" button does
		tryAgain = new Button("Try Again");
		tryAgain.setLayoutX(25.0);
		tryAgain.setLayoutY(70.0);
		tryAgain.setOnAction(e -> {
			restart();
			game(player1Name, player2Name);
			stage.setScene(scene);
		});

		// Main Menu button while playing
		mainMenu = new Button("Main Menu");
		mainMenu.setLayoutX(21.0);
		mainMenu.setLayoutY(21.0);
		mainMenu.setOnAction(e -> {
			restart();
			tieScore = 0;
			player1Score = 0;
			player2Score = 0;
			start(stage);
		});

		// Draws table
		line1 = new Line(338, 245, 1028, 245);
		line2 = new Line(338, 475, 1028, 475);
		line3 = new Line(568, 15, 568, 705);
		line4 = new Line(798, 15, 798, 705);

		// Draws players icon (in the corner)
		circle1 = new Circle(100, 620, 75);
		circle2 = new Circle(100, 620, 74);
		circle2.setFill(Color.WHITE);
		line5 = new Line(1191, 545, 1341, 705);
		line6 = new Line(1341, 545, 1191, 705);

		// Draws their names
		this.player1 = new Label(player1);
		this.player2 = new Label(player2);
		this.player1.setLayoutX(70.0);
		this.player1.setLayoutY(470.0);
		this.player2.setLayoutX(1230.0);
		this.player2.setLayoutY(470.0);

		// Draws the score
		score1 = new Label("Score : " + String.valueOf(player1Score));
		score2 = new Label("Score : " + String.valueOf(player2Score));
		score1.setLayoutX(60.0);
		score1.setLayoutY(500.0);
		score2.setLayoutX(1220.0);
		score2.setLayoutY(500.0);

		// Draws score tie
		tieLabel = new Label("Tie : " + String.valueOf(tieScore));
		tieLabel.setLayoutX(1300.0);
		tieLabel.setLayoutY(10.0);

		// CSS IDs
		group.setId("background");
		mainMenu.setId("mainMenu");
		line1.setId("tableau");
		line2.setId("tableau");
		line3.setId("tableau");
		line4.setId("tableau");

		// Draws the basic elements
		group.getChildren().addAll(line1, line2, line3, line4, circle1, circle2, line5, line6, mainMenu, score1, score2,
				this.player1, this.player2, tieLabel);
	}

	public static void main(String[] args) {
		launch(args);
	}
}