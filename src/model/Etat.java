package model;

import java.awt.Point;

import javax.swing.JOptionPane;

import control.Control;
import view.Affichage;

/**Classe charg�e de repr�senter le mod�le*/

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
	
	/**M�thode renvoyant l'hauteur de l'ovale*/
	public int getHauteur() {
		return this.hauteur;
	}
	
	/**m�thode de mofification du mod�le*/
	public void jump() {
		/**On v�rifie que le joueur n'a paas perdu*/
		if(this.continuer) {
			int nv = this.getHauteur()-Control.SAUT;
			/**V�rifie que l'ovale ne sort pas du cadre*/
			if(nv>0) {
				this.hauteur=nv;
				this.testPerdu();
			}
		}
	}
	
	/**M�thode qui permet de modifier l'altitude de l'ovale*/
	public void moveDown() {
		int nv = this.getHauteur()+Control.SAUT;
		/**Verfie que l'ovale ne sorte pas du cadre*/
		if(nv<Affichage.HAUT) {
			this.hauteur=nv;
			this.testPerdu();
		}
	}
	
	/**M�thode qui permet de savoir si le joueur est perdu*/
	public boolean testPerdu() {
		/**Point � gauche de l'ovale pour le calcul de la pente*/
        Point p1 = this.parcours.getParcours().get(0);
        /**Point � droite de l'ovale pour le calcul de la pente*/
        Point p2 = this.parcours.getParcours().get(1);
        /**Calcul de la pente*/
        float pente = ((p2.y) - (p1.y)) / ((float)p2.x - (float)p1.x);
        /**Ordonn�e de l'ovale sur la pente*/
        float y = p1.y - pente*(p1.x-(Affichage.X+(Affichage.LARG_OVAL/2)));
        /**Ordonn�e min de l'ovale*/
        int yMin_ovale = this.hauteur;
        /**Ordonn�e max de l'ovale*/
        int yMax_ovale = this.hauteur + (Affichage.LARG_HAUT);
        /**On v�rifie que le joueur n'a pas perdu gr�ce de l'encadrement des valeurs max et min*/
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
