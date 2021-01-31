package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import view.Affichage;

/**Classe qui représente la ligne brisée*/
public class Parcours {
	 
	/**Attributs*/
	
		/**Liste des points de la ligne brisée*/
		public ArrayList<Point> points;
		
		public static final Random rand = new Random();
		
		/**Attribut représentant le score du joueur*/
		public int position;
		
		/**Constante utilisée pour incrémenter le score*/
		public static final int MAXINCR = 11;
		public static final int INCR = 11;
		
		/**Coordonnées du dernier Point ajouté à la liste*/
		public int x;
		public int y;
		
		/**Attribut utilisé pour représenter l'inclinaison de la pente de la ligne brisée*/
		public int vitesse;
		
		/**Attribut représenter pour connaître la direction actuelle de la pente de la ligne brisée*/
		public boolean montee;
		
		/**Attribut pour savoir si la limite est atteinte*/
		public boolean limite;
		
		/**Valeurs min et max des coordonnées x et y*/
		public int ymin;
		public int ymax;
		public int xmin;
		public int xmax;
		
		/**Attribut représentant la valeur que la pente actuelle veut atteindre*/
		public int objectifx;
		public int newobjx;
		
		
	/**Constructeur*/
	public Parcours() {
		this.points = new ArrayList<Point>();
		this.position = 0;
		/**On initialise l'abscisse du premier point au milieu de l'ovale*/
		this.x = Affichage.X+(Affichage.LARG_OVAL/2);
		/**On initialise l'ordonnée du premier point au milieu de l'intervalee*/ 
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
	
	/**Méthode pour ajouter un points*/
	public void sous_init() {
		this.limite=false;
		/**On vérifie que l'objectif n'est pas atteint*/
		while(this.x!=this.objectifx) {
			this.x=this.x+1;
			/**On regarde si la pente monte ou descend*/
			if(this.montee) {
				this.y=this.y-this.vitesse;
				/**On vérifie si la limite est atteinte*/
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
		/**On prépare l'ajout du prochain point*/
		this.objectifx = this.objectifx + (rand.nextInt(xmax-xmin)+xmin);
		if(!this.limite) {
			if(rand.nextInt(2)==1) {
				this.montee=false;
			} else {
				this.montee=true;
			}
		}
	}
	
	
	
	/**Méthode qui intialise la liste de points*/
	public void init() {
		/**On calcule l'abscisse jusqu'à laquelle il y aura un changement de position*/
		this.objectifx = this.objectifx + (rand.nextInt(xmax-xmin)+xmin);
		this.vitesse = 1;
		/**On ajoute le premier point à la liste*/
		this.points.add(new Point(this.x,this.y));
		/**On ajoute tous les points situés à l'intérieur du cadre au début*/
		while(this.objectifx<Affichage.LARG) {
			this.sous_init();
		}
		/**On ajoute le point en dehors de l'écran*/
		this.sous_init();
	}
	
	
	
	
	/**Méthode qui ne renvoie que les points visibles*/
	public ArrayList<Point> getParcours(){
		ArrayList<Point> res = new ArrayList<Point>();
		/**Boucle utilisée pour ajouter les points visibles*/
		for(int i=0;i<this.points.size();i+=2) {
			if(i<this.points.size()-1) {
				Point p1 = this.points.get(i);
                Point p2 = this.points.get(i+1);
                /**On vérifie que le deuxième point soit visible pour savoir si on doit garder le premier*/
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
		
	
	/**Fonction utilisée pour récupérer la valeur courante de la position*/
	public int getPosition() {
		/**On divise par la valeur de la constante de classe avec laquelle on incrémente le score*/
		return (this.position/(Parcours.INCR));
	}
	
	/**Fonction utilisée pour faire avancer la position*/
	public void setPosition() {
		 this.position+=Parcours.INCR;
	}
	
	
	
	
	
}
