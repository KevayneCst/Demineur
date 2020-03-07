package demineur;

import java.util.Scanner;

public class Demineur {

	public static final int BOMB_VALUE = -1;
	public static final int ZERO_VALUE = 0;
	public static final int ONE_VALUE = 1;
	public static final int TWO_VALUE = 2;
	public static final int THREE_VALUE = 3;
	public static final int FOUR_VALUE = 4;
	public static final int FIVE_VALUE = 5;
	public static final int SIX_VALUE = 6;
	public static final int SEVEN_VALUE = 7;
	public static final int EIGHT_VALUE = 8;

	private double startTime;
	private int choose;

	public Demineur() {
		this.startTime = System.currentTimeMillis();
	}

	public void start() {
		chooseTypeOfGame();
		launchPlateau();
	}

	public void chooseTypeOfGame() {
		Scanner s = new Scanner(System.in);
		System.out.println("Tapez 1, 2, 3 ou 4 selon le mode que vous voulez choisir");
		System.out.println("1: Facile (9x9 ; 25 bombes)");
		System.out.println("2: Moyen (11x11 ; 40 bombes)");
		System.out.println("3: Difficile (16x16;  99 bombes)");
		while (true) {
			String saisie = s.nextLine().trim();
			if (Game.isANumber(saisie)) {
				if (Integer.parseInt(saisie) >= 0 && Integer.parseInt(saisie) <= 4) {
					choose = Integer.parseInt(saisie);
					break;
				}
			} else {
				System.out.println("Erreur, veuillez saisir un nombre compris entre 1 et 4 uniquement");
			}
		}
	}

	public void launchPlateau() {
		Game g = null;
		switch (choose) {
		case 1:
			g = new Game(new Plateau(9, 9, 25));
			break;
		case 2:
			g = new Game(new Plateau(11, 11, 40));
			break;
		case 3:
			g = new Game(new Plateau(16, 16, 99));
			break;
		case 4:
			break;
		default:
			break;
		}
		System.out.println("OHHO");
		g.play();
		System.out.println("OHHGEGGZO");
	}
}
