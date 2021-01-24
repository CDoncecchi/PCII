package view;

import java.awt.Color;
import java.awt.Dimension;
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
    	public static final int larg = 800;
    	public static final int haut = 600;
    	/**Dimensions de l'ovale*/
    	public static final int x = 7;
    	public static final int y = 300 ;
    	public static final int largeur = 10;
    	public static final int hauteur = 40;
    	/**Modèle*/
    	public Etat etat;

    /** Constructeur */
    public Affichage(Etat etat) {
    	setPreferredSize(new Dimension(larg, haut));
    	/**Ajout du Listener*/
    	addMouseListener(new Control(etat,this));
    	this.etat=etat;
    }
    
    /**affichage*/
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	/**Affichage de l'ovale*/
    	g.drawOval(x, etat.hauteur, largeur, hauteur);
    	g.setColor(Color.RED);
    	/**Affichage de la ligne*/
    	for(Point p : etat.parcours.getParcours()) {
    		g.drawLine(p.x, p.y, p.x, p.y);
    	}
    	/**Affichage des points*/
    	g.setColor(Color.BLACK);
    	g.clearRect(300, 50, 200, 50);
    	String s = "Point:\n"+etat.parcours.getPosition();
    	g.drawString(s, 350, 70);
    }
}
