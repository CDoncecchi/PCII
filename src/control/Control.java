package control;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import model.Etat;
import view.Affichage;
/**Le controleur du mod�le impl�mentant le MouseListener*/

public class Control implements MouseListener {
	/**Attributs*/
		/**Saut de l'ovale*/
		public static final int saut = 10;
		/**Vue du mod�le*/
		public Affichage aff;
		/**Mod�le*/
		public Etat etat;
	
	/**Constructeur*/
	public Control(Etat e,Affichage a) {
		this.etat=e;
		this.aff=a;
	}
	
	/**M�thode appel�e lorsque l'utilisatuer clique dans la zone d'affichage*/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		aff.etat.jump();
		aff.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
