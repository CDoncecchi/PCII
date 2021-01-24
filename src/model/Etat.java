package model;

import control.Control;
import view.Affichage;

/**Classe chargée de représenter le modèle*/

public class Etat {
	
	/**Attributs*/
		/**Hauteur de l'ovale*/
		public int hauteur;
		
		public Affichage aff;
		public Parcours parcours;
	
	/**Constructeur*/
	public Etat() {
		this.hauteur=Affichage.y;
		this.parcours = new Parcours();
		this.aff = new Affichage(this);
	}
	
	/**Méthode renvoyant l'hauteur de l'ovale*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**méthode de mofification du modèle*/
	public void jump() {
		int nv = this.getHauteur()-Control.saut;
		/**Vérifie que l'ovale ne sort pas du cadre*/
		if(nv>0) {
			this.hauteur=nv;
		}
	}
	
	/**Méthode qui permet de modifier l'altitude de l'ovale*/
	public void moveDown() {
		int nv = this.getHauteur()+Control.saut;
		/**Verfie que l'ovale ne sorte pas du cadre*/
		if(nv<Affichage.haut) {
			this.hauteur=nv;
		}
	}
	
}
