# projet_bulles

package projet_bulles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utilisateur {
	private String identifiant;
	private static File fichier = new File(
			"/home/ann2/daviaudl/Bureau/utilisateurs.txt");
	private Score[] scores;

	public Utilisateur(String p_identifiant) {
		this.identifiant = p_identifiant;
		this.scores = new Score[10];
		for (int i = 0; i < this.scores.length; i++) {
			this.scores[i] = new Score();
		}
	}

	/**
	 * public Utilisateur trouverUtilisateur() throws IOException { try { int i
	 * = 0; StringBuilder ligne = new StringBuilder(); BufferedReader lecteur =
	 * new BufferedReader(new FileReader(fichier)); lecteur = new
	 * BufferedReader(new FileReader(fichier)); while (true) {
	 * ligne.setLength(0); try { ligne.append(lecteur.readLine()); i++; } catch
	 * (IOException e) { } } return ligne.toString(); } catch
	 * (FileNotFoundException e) { } return null; } }
	 * 
	 * @throws IOException
	 **/

	private StringBuilder recupLigne() throws IOException {
		String ligne = new String();
		BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
		StringBuilder identifiant = new StringBuilder();
		while ((ligne = lecteur.readLine()) != null) {
			identifiant.append(recupIdentifiant(ligne));
			if (identifiant.toString().equals(this.identifiant)) {
				lecteur.close();
				identifiant.setLength(0);
				identifiant.append(ligne);
				return identifiant;
			} else {
				identifiant.setLength(0);
			}
		}
		lecteur.close();
		return null;
	}

	private StringBuilder recupIdentifiant(String ligne) {
		StringBuilder identifiant = new StringBuilder();
		int i = 0;
		while (ligne.charAt(i) != '|') {
			identifiant.append(ligne.charAt(i));
			i++;
		}
		return identifiant;
	}

	private void recupScores(StringBuilder ligne) {
		StringBuilder scores = new StringBuilder();
		StringBuilder strb_ligne = new StringBuilder();
		int i = 0;
		while (ligne.charAt(i) != '|') {
			i++;
		}
		strb_ligne.append(ligne);
		strb_ligne.delete(0, i);
		for (i = 0; i < this.scores.length; i++) {
			this.scores[i].recupScore(strb_ligne);
		}
	}

	private void sauvegarderUtilisateur() throws IOException {
		FileWriter fw = new FileWriter(fichier, true);
		fw.write(this.identifiant.toString() + '|');
		for (int i = 0; i < 10; i++) {
			fw.write("0-0|");
		}
		fw.write(System.getProperty("line.separator"));
		fw.close();
	}

	private void Identification() throws IOException {
		if (this.UtilisateurExistant()) {
			StringBuilder informations = this.recupLigne();
			this.recupIdentifiant(informations.toString());
			this.recupScores(informations);
		} else {
			this.sauvegarderUtilisateur();
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean UtilisateurExistant() throws IOException {
		String ligne = new String();
		BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
		StringBuilder identifiant = new StringBuilder();
		while ((ligne = lecteur.readLine()) != null) {
			identifiant.append(recupIdentifiant(ligne));
			if (identifiant.toString().equals(this.identifiant)) {
				lecteur.close();
				return true;
			} else {
				identifiant.setLength(0);
			}
		}
		lecteur.close();
		return false;
	}

	private static String lireFichier(File fichier, int numero_ligne) {
		try {
			int i = 0;
			StringBuilder ligne = new StringBuilder();
			BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
			lecteur = new BufferedReader(new FileReader(fichier));
			while (i <= numero_ligne) {
				ligne.setLength(0);
				try {
					ligne.append(lecteur.readLine());
					i++;
				} catch (IOException e) {
				}
			}
			return ligne.toString();
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public String toString() {
		StringBuilder utilisateur = new StringBuilder();
		utilisateur.append(this.identifiant
				+ System.getProperty("line.separator"));
		for (int i = 0; i < this.scores.length; i++) {
			switch (i) {
			default:
				utilisateur.append("Pallier " + (i + 1) + "  ~ "
						+ this.scores[i].toString()
						+ System.getProperty("line.separator"));
				break;
			case 9:
				utilisateur.append("Pallier " + (i + 1) + " ~ "
						+ this.scores[i].toString()
						+ System.getProperty("line.separator"));
				break;
			}
		}
		return utilisateur.toString();
	}

	public static void main(String[] Args) throws IOException {
		Utilisateur u1 = new Utilisateur("Alain");
		Utilisateur u2 = new Utilisateur("Robin");
		Utilisateur u3 = new Utilisateur("Remi");
		Utilisateur u4 = new Utilisateur("Meddy");
		Utilisateur u5 = new Utilisateur("Meddy");
		u1.Identification();
		u2.Identification();
		// System.out.println(u1.toString());
	}
}
