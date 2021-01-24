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
		public static final int incr = 5;
		
		/**Coordonnées du dernier Point ajouté à la liste*/
		public int x;
		public int y;
		
		/**Attribut utilisé pour représenter l'inclinaison de la pente de la ligne brisée*/
		public int vitesse;
		
		/**Attribut représenter pour connaître la direction actuelle de la pente de la ligne brisée*/
		public boolean montee;
		
		/**Valeurs min et max des coordonnées x et y*/
		public int ymin;
		public int ymax;
		public int xmin;
		public int xmax;
		
		/**Attribut représentant la valeur que la pente actuelle veut atteindre*/
		public int objectifx;
	
	/**Constructeur*/
	public Parcours() {
		this.points = new ArrayList<Point>();
		this.position = 0;
		this.x = Affichage.x;
		
		/**Attribut y initialisé à +50 pour qu'elle soit en-dessous de l'ovale au début*/
		this.y = Affichage.y+50;
		
		this.vitesse = 0;
		this.montee = true;
		this.ymin = 200;
		this.ymax = Affichage.haut-200 ;
		this.xmin=60;
		this.xmax=90;
		this.objectifx=0;
		this.init();
		System.out.println(y);
		for(Point p: points) {
			System.out.println("X: "+p.x+" Y: "+p.y+"\n");
		}
	}
	
	/**Fonction utilisée pour ajouter des points à la liste lorsque objectifx n'est pas atteint*/
	public void sous_lignebrisee() {
		/**On vérifie la direction de la peinte*/
		if(this.montee) {
			int i = this.y-this.vitesse;
			/**On vérifie si la coordonnée y a atteint la limite*/
			if(i>this.ymin) {
				this.y=i;
				this.points.add(new Point(this.x,this.y));
			} else {
				/**On donne à la coordonnée y la valeur de la limite*/
				this.y=this.ymin;
				this.points.add(new Point(this.x,this.y));
				/**On change la direction de la pente car la limite est atteinte*/
				this.montee=false;
				/**On met à jour l'objectif et l'inclinaison de la pente*/
				int r = rand.nextInt(xmax-xmin)+xmin;
				this.objectifx = this.objectifx + r;
				this.vitesse = rand.nextInt(2)+1;
			}
		} else {
			int i = this.y+this.vitesse;
			/**On vérifie la direction de la pente*/
			if(i<this.ymax) {
				this.y=i;
				this.points.add(new Point(this.x,this.y));
			} else {
				/**On donne à la coordonnée y la valeur de la limite*/
				this.y = this.ymax;
				this.points.add(new Point(this.x,this.y));
				/**On change la direction de la pente car la limite est atteinte*/
				this.montee=true;
				/**On met à jour l'objectif et l'inclinaison de la pente*/ 
				int r = rand.nextInt(xmax-xmin)+xmin;
				this.objectifx = this.objectifx + r;
				this.vitesse = rand.nextInt(2)+1;
			}
		}
	}
	
	/**Fonction utilisée pour ajouter des points à la liste*/
	public void lignebrisee() {
		int r = 0;
		this.x=this.x+1;
		/**Cas où l'objectif est atteint*/
		if(this.x==this.objectifx) {
			this.sous_lignebrisee();
			if(rand.nextInt(2)==0) {
				this.montee=false;
			} else {
				this.montee=true;
			}
			r = rand.nextInt(xmax-xmin)+xmin;
			this.objectifx = this.objectifx + r;
			this.vitesse = rand.nextInt(2)+1;
		} else {
			this.sous_lignebrisee();
		}
	}
	
	/**Méthode qui initialise la liste de points*/
	public void init() {
		this.objectifx = this.objectifx + (rand.nextInt(xmax-xmin)+xmin);
		this.vitesse = 1;
		while(this.x<Affichage.larg) {
			this.lignebrisee();
		}
	}
	
	
	/**Méthode qui ne renvoie que les points visibles*/
	public ArrayList<Point> getParcours(){
		ArrayList<Point> res = new ArrayList<Point>();
		/**On ajoute les nouveaux points*/
		for(int i=0;i<Parcours.incr;i++) {
			this.lignebrisee();
		}
		/**On ajoute les points visibles de la liste à la liste qu'on va renvoyer à la fonction*/
		for(Point p : this.points) {
			if(p.x>=this.position) {
				res.add(new Point(p.x-this.position,p.y));
			}
		}
		return res;
	}
	
	/**Fonction utilisée pour récupérer la valeur courante de la position*/
	public int getPosition() {
		/**On divise par la valeur de la constante de classe avec laquelle on incrémente le score*/
		return (this.position/(Parcours.incr));
	}
	
	/**Fonction utilisée pour faire avancer la position*/
	public void setPosition() {
		 this.position+=Parcours.incr;
	}
	
	
	
	
	
}
