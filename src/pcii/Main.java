package pcii;

import java.awt.Component;

import javax.swing.JFrame;

import control.Control;
import model.Avancer;
import model.Etat;
import model.Parcours;
import model.Voler;
import view.Affichage;
/**La classe principale,pour ex�cuter le programme*/

public class Main {
	
	/**M�thode permettant de cr�er une fen�tre pour y ajouter un composant*/
	public static void ajouteComposant(Component comp,String s) {
		JFrame fenetre = new JFrame(s);
		fenetre.add(comp);
		fenetre.pack();
		/**On place la fen�tre au milieu de l'�cran*/
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**Main*/
	  public static void main(String [] args) {
		  Etat e = new Etat();
		  /**On declare les thread*/
		  Voler v = new Voler(e);
		  Avancer a = new Avancer(e);
		  /**On lance les thread*/
		  v.start();
		  a.start();
		  /**On execute l'affichage*/
          ajouteComposant(e.aff,"Test dessin");
	  }
}