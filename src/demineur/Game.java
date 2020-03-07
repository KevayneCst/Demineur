package demineur;

import java.awt.Point;
import java.util.Scanner;

public class Game {
	private Plateau p;
	private boolean rightClick;
	
	public Game(Plateau p) {
		this.p=p;
		this.rightClick=false;
	}
	
	public void play() {
		p.placeBombs();
		p.setRealValues();
		Point pt;
		while (true) {
			boolean isBombeDiscovered = false;
			System.out.println(p.toString());
			pt=saisie();
			if (rightClick) {
				p.cover(pt.x, pt.y);
				rightClick=false;
			} else {
				isBombeDiscovered = p.discover(pt.x, pt.y);
			}
			
			
			//Si c'est TRUE ==> C'est une bombe qui a été découverte !
			if (isBombeDiscovered) {
				System.out.println("Vous avez cliqué sur une bombe !");
				break;
			}
			clear();
		}
	}
	
	public Point saisie() {
		int x;
		int y;
		Scanner s = new Scanner(System.in);
		System.out.println("Rajoutez un \"p\" devant le numéro de la ligne/colonne si vous voulez placer un drapeau");
		System.out.println("Saisir le numéro de la ligne svp");
		while (true) {
			String saisie = s.nextLine().trim();
			if (saisie.contains("p")) {
				rightClick=true;
				saisie=saisie.replace("p", "");
			}
			if (isANumber(saisie)) {
				int theNumber = Integer.parseInt(saisie);
				if (theNumber >=0 && theNumber <= p.getMaxligne()-1) {
					x=theNumber;
					break;
				} else {
					System.out.println("Valeur incohérante");
				}
			} else {
				System.out.println("La valeur saisie n'est pas un chiffre");
			}
		}
		
		System.out.println("Saisir le numéro de la colonne svp");
		while (true) {
			String saisie = s.nextLine().trim();
			if (saisie.contains("p")) {
				rightClick=true;
				saisie=saisie.replace("p", "");
			}
			if (isANumber(saisie)) {
				int theNumber = Integer.parseInt(saisie);
				if (theNumber >=0 && theNumber <= p.getMaxcolonne()-1) {
					y=theNumber;
					break;
				} else {
					System.out.println("Valeur incohérante");
				}
			} else {
				System.out.println("La valeur saisie n'est pas un chiffre");
			}
		}
		
		return new Point(x,y);
	}
	
	public static boolean isANumber(String theStringToAnalyze) {
		return theStringToAnalyze.matches("[0-9]+");
	}
	
	public void clear() {
		for (int i=0; i<300; i++) {
			System.out.println("");
		}
	}
	
	
}
