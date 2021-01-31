package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import view.Affichage;

/**Classe qui repr�sente la ligne bris�e*/
public class Parcours {
	 
	/**Attributs*/
	
		/**Liste des points de la ligne bris�e*/
		public ArrayList<Point> points;
		
		public static final Random rand = new Random();
		
		/**Attribut repr�sentant le score du joueur*/
		public int position;
		
		/**Constante utilis�e pour incr�menter le score*/
		public static final int MAXINCR = 11;
		public static final int INCR = 11;
		
		/**Coordonn�es du dernier Point ajout� � la liste*/
		public int x;
		public int y;
		
		/**Attribut utilis� pour repr�senter l'inclinaison de la pente de la ligne bris�e*/
		public int vitesse;
		
		/**Attribut repr�senter pour conna�tre la direction actuelle de la pente de la ligne bris�e*/
		public boolean montee;
		
		/**Attribut pour savoir si la limite est atteinte*/
		public boolean limite;
		
		/**Valeurs min et max des coordonn�es x et y*/
		public int ymin;
		public int ymax;
		public int xmin;
		public int xmax;
		
		/**Attribut repr�sentant la valeur que la pente actuelle veut atteindre*/
		public int objectifx;
		public int newobjx;
		
		
	/**Constructeur*/
	public Parcours() {
		this.points = new ArrayList<Point>();
		this.position = 0;
		/**On initialise l'abscisse du premier point au milieu de l'ovale*/
		this.x = Affichage.X+(Affichage.LARG_OVAL/2);
		/**On initialise l'ordonn�e du premier point au milieu de l'intervalee*/ 
		this.y = Affichage.Y+(Affichage.LARG_HAUT/2)+10;
		this.vitesse = 0;
		this.montee = false;
		this.limite = false;
		this.ymin = 200;
		this.ymax =  Affichage.HAUT-200;
		this.xmin=60;
		this.xmax=120;
		this.objectifx=this.x;
		this.init();
		for(Point p: points) {
			//System.out.println("X: "+p.x+" Y: "+p.y+"\n");
		}
	}
	
	/**M�thode pour ajouter un points*/
	public void sous_init() {
		this.limite=false;
		/**On v�rifie que l'objectif n'est pas atteint*/
		while(this.x!=this.objectifx) {
			this.x=this.x+1;
			/**On regarde si la pente monte ou descend*/
			if(this.montee) {
				this.y=this.y-this.vitesse;
				/**On v�rifie si la limite est atteinte*/
				if(this.y<this.ymin) {
					this.limite=true;
					this.y=this.ymin;
					this.x=objectifx;
					this.montee=false;
				}
			/**La pente descend*/
			} else {
				this.y=this.y+this.vitesse;
				if(this.y>this.ymax) {
					this.limite=true;
					this.y=this.ymax;
					this.x=this.objectifx;
					this.montee=true;
				}
				
			}
		}
		/**On ajoute le point*/
		this.points.add(new Point(this.x,this.y));
		/**On pr�pare l'ajout du prochain point*/
		this.objectifx = this.objectifx + (rand.nextInt(xmax-xmin)+xmin);
		if(!this.limite) {
			if(rand.nextInt(2)==1) {
				this.montee=false;
			} else {
				this.montee=true;
			}
		}
	}
	
	
	
	/**M�thode qui intialise la liste de points*/
	public void init() {
		/**On calcule l'abscisse jusqu'� laquelle il y aura un changement de position*/
		this.objectifx = this.objectifx + (rand.nextInt(xmax-xmin)+xmin);
		this.vitesse = 1;
		/**On ajoute le premier point � la liste*/
		this.points.add(new Point(this.x,this.y));
		/**On ajoute tous les points situ�s � l'int�rieur du cadre au d�but*/
		while(this.objectifx<Affichage.LARG) {
			this.sous_init();
		}
		/**On ajoute le point en dehors de l'�cran*/
		this.sous_init();
	}
	
	
	
	
	/**M�thode qui ne renvoie que les points visibles*/
	public ArrayList<Point> getParcours(){
		ArrayList<Point> res = new ArrayList<Point>();
		/**Boucle utilis�e pour ajouter les points visibles*/
		for(int i=0;i<this.points.size();i+=2) {
			if(i<this.points.size()-1) {
				Point p1 = this.points.get(i);
                Point p2 = this.points.get(i+1);
                /**On v�rifie que le deuxi�me point soit visible pour savoir si on doit garder le premier*/
                if(p2.getX() >= this.position+Affichage.X+(Affichage.LARG_OVAL/2)) {
                	res.add(new Point(p1.x - this.position, p1.y));
                } else {
                	/**On retire le premier point*/
                	this.points.remove(i);
                	/**On ajoute un nouveau point*/
                	this.sous_init(); 
                	i-=1;
                }
                res.add(new Point(p2.x-this.position,p2.y));
			}
		}
		return res;
	}
		
	
	/**Fonction utilis�e pour r�cup�rer la valeur courante de la position*/
	public int getPosition() {
		/**On divise par la valeur de la constante de classe avec laquelle on incr�mente le score*/
		return (this.position/(Parcours.INCR));
	}
	
	/**Fonction utilis�e pour faire avancer la position*/
	public void setPosition() {
		 this.position+=Parcours.INCR;
	}
	
	
	
	
	
}
