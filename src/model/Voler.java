package model;

/**Classe qui h�rite de Thread et qui fait redescendre progressivement la valeur de la hauteur*/
public class Voler extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Voler(Etat etat) {
		this.etat=etat;
	}
	
	/**M�thode run*/
	@Override
	  public void run() {
	   while(true) {
	    	etat.moveDown();
	    	try { Thread.sleep(500);}
	    	catch (Exception e) { e.printStackTrace(); }
	    	etat.aff.repaint();	
	    }
	  }
}
