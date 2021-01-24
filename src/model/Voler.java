package model;

/**Classe qui hérite de Thread et qui fait redescendre progressivement la valeur de la hauteur*/
public class Voler extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Voler(Etat etat) {
		this.etat=etat;
	}
	
	/**Méthode run*/
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
