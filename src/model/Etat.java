package model;

import control.Control;
import view.Affichage;

/**Classe charg�e de repr�senter le mod�le*/

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
	
	/**M�thode renvoyant l'hauteur de l'ovale*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**m�thode de mofification du mod�le*/
	public void jump() {
		int nv = this.getHauteur()-Control.saut;
		/**V�rifie que l'ovale ne sort pas du cadre*/
		if(nv>0) {
			this.hauteur=nv;
		}
	}
	
	/**M�thode qui permet de modifier l'altitude de l'ovale*/
	public void moveDown() {
		int nv = this.getHauteur()+Control.saut;
		/**Verfie que l'ovale ne sorte pas du cadre*/
		if(nv<Affichage.haut) {
			this.hauteur=nv;
		}
	}
	
}
