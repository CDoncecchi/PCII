package model;

/**Classe qui impl�mente un thread pour faire avancer la position*/
public class Avancer extends Thread {
	
	/**Attributs*/
	public Etat etat;
	
	/**Constructeur*/
	public Avancer(Etat etat) {
		this.etat=etat;
	}
	
	/**M�thode run*/
	@Override
	  public void run() {
		/**On v�rifie que le joueur n'a pas perdu*/
	   while(etat.continuer) {
	    	etat.parcours.setPosition();
	    	try { Thread.sleep(700);}
	    	catch (Exception e) { e.printStackTrace(); }
	    	etat.aff.repaint();
	    }
	  }

}
