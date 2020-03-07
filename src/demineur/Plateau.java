package demineur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {

	private int maxligne;
	private int maxcolonne;
	private int numberOfBombs;
	private int discoveredBombs;
	private Random r = new Random();
	private Case[][] plateau;

	public Plateau(int maxligne, int maxcolonne, int numberOfBombs) {
		this.maxligne = maxligne;
		this.maxcolonne = maxcolonne;
		this.numberOfBombs = numberOfBombs;
		this.discoveredBombs = 0;
		this.plateau = new Case[maxligne][maxcolonne];
		for (int i = 0; i < maxligne; i++)
			for (int k = 0; k < maxcolonne; k++)
				plateau[i][k] = new Case(i, k);
	}

	public int setRealValueToCase(int ligne, int colonne) {
		List<Case> around = new ArrayList<>();
		int count = 0;
		
			for (int i = -1; i <= 1; i++) {
				if (ligne + i > 0 && ligne + i < maxligne) {
					if (colonne + 1 < maxcolonne) {
						around.add(plateau[ligne + i][colonne + 1]);
						if (plateau[ligne + i][colonne + 1].getValue() == Demineur.BOMB_VALUE) {
							count++;
						}
					}
					if (colonne - 1 > 0) {
						around.add(plateau[ligne + i][colonne - 1]);
						if (plateau[ligne + i][colonne - 1].getValue() == Demineur.BOMB_VALUE) {
							count++;
						}
					}
				}
			}
			if (ligne - 1 > 0) {
				around.add(plateau[ligne - 1][colonne]);
				if (plateau[ligne - 1][colonne].getValue() == Demineur.BOMB_VALUE) {
					count++;
				}
			}
			if (ligne + 1 < maxligne) {
				around.add(plateau[ligne + 1][colonne]);
				if (plateau[ligne + 1][colonne].getValue() == Demineur.BOMB_VALUE) {
					count++;
				}
			}
			plateau[ligne][colonne].setAroundCase(around);
			if (plateau[ligne][colonne].getValue() != Demineur.BOMB_VALUE) {
				return -1;
			}
			return count;


	}

	public void setRealValues() {
		for (int i = 0; i < maxligne; i++) {
			for (int k = 0; k < maxcolonne; k++) {
				int v = setRealValueToCase(i, k);
				if (v != -1) {
					plateau[i][k].setValue(v);
				}
			}
		}
	}

	public void placeBombs() {
		List<Point> p = new ArrayList<>();
		for (int i = 0; i < numberOfBombs; i++) {
			p.add(new Point(getRandomNumber(maxligne), getRandomNumber(maxcolonne)));
		}
		while (p.size() != numberOfBombs) {
			p.clear();
			for (int i = 0; i < numberOfBombs; i++) {
				p.add(new Point(getRandomNumber(maxligne), getRandomNumber(maxcolonne)));
			}
		}
		for (Point pt : p) {
			plateau[pt.x][pt.y].setValue(Demineur.BOMB_VALUE);
		}
	}

	public int getRandomNumber(int max) {
		return r.nextInt(max);
	}

	/**
	 * Découvre la case (Attention aux bombes !)
	 * 
	 * @param ligne
	 * @param colonne
	 * @return TRUE si bombe, FALSE sinon
	 */
	public boolean discover(int ligne, int colonne) {
		boolean notABomb = plateau[ligne][colonne].leftClick();
		if (!notABomb) { //Si false
			recDiscover(ligne, colonne);
		}
		return notABomb;
	}

	/**
	 * Ne découvre pas mais couvre (place un drapeau)
	 * 
	 * @param ligne
	 * @param colonne
	 */
	public void cover(int ligne, int colonne) {
		plateau[ligne][colonne].rightClick();
	}
	
	public void recDiscover(int ligne, int colonne) {
		Case laCase = plateau[ligne][colonne];
		if (laCase.getValue()==Demineur.ZERO_VALUE) { 
			laCase.leftClick();
			for (Case c : laCase.getAroundCase()) {
				recDiscover(c.getLigne(), c.getColonne());
			}
		}
	}

	/*
	 * Retourne la chaîne de caractère représentant le plateau de jeu du démineur
	 * (affiche chacune des Case de la matrice
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n     ");
		for (int i = 0; i < maxcolonne; i++) {
			if (i>=100) {
				sb.append(i + "|");
			} else if (i>=10) {
				sb.append(" " + i + "|");
			} else {
				sb.append(" " + i + " |");
			}
			


		}
		sb.append("\n    -");
		for (int i = 0; i < maxcolonne; i++) {
			sb.append("----");
		}
		sb.append("\n");

		for (int i = 0; i < maxligne; i++) {
			if (i >= 100) {
				sb.append(i + " |");
			} else if (i >= 10) {
				sb.append(i + "  |");
			} else {
				sb.append(i + "   |");
			}

			for (int k = 0; k < maxcolonne; k++) {

				sb.append(" " + plateau[i][k].toString() + " |");

			}
			sb.append("\n");
		}
		sb.append("    -");
		for (int i = 0; i < maxcolonne; i++) {
			sb.append("----");
		}
		return sb.toString();
	}

	public int getMaxligne() {
		return maxligne;
	}

	public void setMaxligne(int maxligne) {
		this.maxligne = maxligne;
	}

	public int getMaxcolonne() {
		return maxcolonne;
	}

	public void setMaxcolonne(int maxcolonne) {
		this.maxcolonne = maxcolonne;
	}

	public int getNumberOfBombs() {
		return numberOfBombs;
	}

	public void setNumberOfBombs(int numberOfBombs) {
		this.numberOfBombs = numberOfBombs;
	}

	public int getDiscoveredBombs() {
		return discoveredBombs;
	}

	public void setDiscoveredBombs(int discoveredBombs) {
		this.discoveredBombs = discoveredBombs;
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	public static void main(String[] args) {
		Plateau p = new Plateau(16, 16, 20);
		System.out.println(p.toString());
	}

}
