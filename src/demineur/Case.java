package demineur;

import java.util.List;

public class Case {

	private int ligne;
	private int colonne;
	private int value;
	private boolean discovered;
	private String printedValue;
	private List<Case> aroundCase;

	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.value = Demineur.ZERO_VALUE;
		this.discovered = false;
		this.printedValue = "X";
	}

	/**
	 * Lors d'un click droit sur la case:
	 * <ol>
	 * <li>"!" sur la case : on est sûr qu'il y a une bombe</li>
	 * <li>"?" sur la case : on est pas sûr qu'il y a une bombe</li>
	 * <li>"X" sur la case : affichage revenu à la normale</li>
	 * </ol>
	 */
	public void rightClick() {
		if (!discovered) {
			switch (printedValue) {
			case "!":
				this.printedValue = "?";
				break;
			case "?":
				this.printedValue = "X";
				break;
			case "X":
				this.printedValue = "!";
				break;
			default:
				this.printedValue = "ERROR";
				break;
			}
		}
	}

	/**
	 * Découvre la case
	 * @return TRUE si c'est une bombe, FALSE sinon
	 */
	public boolean leftClick() {
		discovered = true;
		this.printedValue = value + "";
		return value == Demineur.BOMB_VALUE;
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public String getPrintedValue() {
		return printedValue;
	}

	public void setPrintedValue(String printedValue) {
		this.printedValue = printedValue;
	}

	public List<Case> getAroundCase() {
		return aroundCase;
	}

	public void setAroundCase(List<Case> aroundCase) {
		this.aroundCase = aroundCase;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		//return discovered ? value+"" : printedValue;
		return value+"";
	}

}
