package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Control;
import model.Etat;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**Vue du modèle*/

public class Affichage extends JPanel{
	
	/** Attributs */

		/**Dimensions du composant*/
    	public static final int LARG = 800;
    	public static final int HAUT = 600;
    	/**Dimensions de l'ovale*/
    	public static final int X = 7;
    	public static final int Y = 300 ;
    	public static final int LARG_OVAL = 30;
    	public static final int LARG_HAUT = 40;
    	/**Modèle*/
    	public Etat etat;

    /** Constructeur */
    public Affichage(Etat etat) {
    	setPreferredSize(new Dimension(LARG, HAUT));
    	/**Ajout du Listener*/
    	addMouseListener(new Control(etat,this));
    	this.etat=etat;
    }
    
    /**Méthode qui gere l'affichage de l'ovale*/
    public void drawOval(Graphics g) {
    	g.drawOval(X, etat.hauteur, LARG_OVAL, LARG_HAUT);
    	g.setColor(Color.RED);
    }
    
    /**Méthode qui gere l'affichage de la ligne brisée*/
    public void drawLine(Graphics g) {
    	for(int i = 0; i < etat.parcours.getParcours().size(); i++) {
    		 if(i <etat.parcours.getParcours().size()-1) {
                Point p1 = etat.parcours.getParcours().get(i);
                Point p2 = etat.parcours.getParcours().get(i+1);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
    		 }
          }
    }
    
    /**Méthode qui gere l'affichage du score*/
    public void drawScore(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.clearRect(300, 50, 200, 50);
    	String s = "SCORE:\n"+etat.parcours.getPosition();
    	g.drawString(s, 350, 70);
    }
    
    
    /**affichage*/
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	/**Affichage de l'ovale*/
    	this.drawOval(g);
    	/**Affichage de la ligne*/
    	this.drawLine(g);
    	/**Affichage du score*/
    	this.drawScore(g);
    }
}
