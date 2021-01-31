package model;

import java.awt.Point;

import javax.swing.JOptionPane;

import control.Control;
import view.Affichage;

/**Classe chargée de représenter le modèle*/

public class Etat {
	
	/**Attributs*/
		/**Hauteur actuelle de l'ovale*/
		public int hauteur;
		
		public Affichage aff;
		public Parcours parcours;
		
		/**Attribut qui permer de savoir si la partie est perdue*/
		public boolean continuer;
	
	/**Constructeur*/
	public Etat() {
		this.continuer=true;
		this.hauteur=Affichage.Y;
		this.parcours = new Parcours();
		this.aff = new Affichage(this);
	}
	
	/**Méthode renvoyant l'hauteur de l'ovale*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**méthode de mofification du modèle*/
	public void jump() {
		/**On vérifie que le joueur n'a paas perdu*/
		if(this.continuer) {
			int nv = this.getHauteur()-Control.SAUT;
			/**Vérifie que l'ovale ne sort pas du cadre*/
			if(nv>0) {
				this.hauteur=nv;
				this.testPerdu();
			}
		}
	}
	
	/**Méthode qui permet de modifier l'altitude de l'ovale*/
	public void moveDown() {
		int nv = this.getHauteur()+Control.SAUT;
		/**Verfie que l'ovale ne sorte pas du cadre*/
		if(nv<Affichage.HAUT) {
			this.hauteur=nv;
			this.testPerdu();
		}
	}
	
	/**Méthode qui permet de savoir si le joueur est perdu*/
	public boolean testPerdu() {
	/**Point à gauche de l'ovale pour le calcul de la pente*/
        Point p1 = this.parcours.getParcours().get(0);
        /**Point à droite de l'ovale pour le calcul de la pente*/
        Point p2 = this.parcours.getParcours().get(1);
        /**Calcul de la pente*/
        float pente = ((p2.y) - (p1.y)) / ((float)p2.x - (float)p1.x);
        /**Ordonnée de l'ovale sur la pente*/
        float y = p1.y - pente*(p1.x-(Affichage.X+(Affichage.LARG_OVAL/2)));
        /**Ordonnée min de l'ovale*/
        int yMin_ovale = this.hauteur;
        /**Ordonnée max de l'ovale*/
        int yMax_ovale = this.hauteur + (Affichage.LARG_HAUT);
        /**On vérifie que le joueur n'a pas perdu grâce de l'encadrement des valeurs max et min*/
        if(y >= yMin_ovale && y <= yMax_ovale) {
            return false;
        } else {
        	this.continuer=false;
        	/**On affiche le message avec le score de l'utilisateur*/
        	JOptionPane.showMessageDialog(null,"Score: "+this.parcours.getPosition()+" !");
            return true;
        }
    }
	
}
