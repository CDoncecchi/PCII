package pcii;

import java.awt.Component;

import javax.swing.JFrame;

import control.Control;
import model.Avancer;
import model.Etat;
import model.Parcours;
import model.Voler;
import view.Affichage;
/**La classe principale,pour exécuter le programme*/

public class Main {
	
	/**Méthode permettant de créer une fenêtre pour y ajouter un composant*/
	public static void ajouteComposant(Component comp,String s) {
		JFrame fenetre = new JFrame(s);
		fenetre.add(comp);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**Main*/
	  public static void main(String [] args) {
		  Etat e = new Etat();
		  Voler v = new Voler(e);
		  Avancer a = new Avancer(e);
		  v.start();
		  a.start();
		  Parcours p = new Parcours();
          ajouteComposant(e.aff,"Test dessin");
	  }
}