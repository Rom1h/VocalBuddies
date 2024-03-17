package Model;
import java.util.LinkedList;
import java.util.Stack;
public class Labyrinthe {
	Piece entree;
	Piece sortie;
	public Piece[][] labyrinthe;
	public Joueurs[] joueurs;
	int taille;

	public Labyrinthe(int n, int nbj){
		labyrinthe=new Piece[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				labyrinthe[i][j]=new Piece(i,j);
			}
		}
		taille=n;
		entree=labyrinthe[4][0];
		entree.isChemin=true;
		sortie=labyrinthe[n-1][n-1];
		sortie.murRight=false;
    joueurs = new Joueurs[nbj];
		for(int i = 0; i<nbj; i++){
			joueurs[i] = new Joueurs(i,i);
			labyrinthe[0][0].arriveJoueur(joueurs[i]);
		}
	}

	public void initialisation() {
		createChemin();
	}

	public Piece setSortie(Piece p) {
		LinkedList<Piece> l=new LinkedList<>();
		for(int x=p.i-1;x<=p.i+1;x++) {
			if(x>=0&&x<labyrinthe.length){
				for(int y=p.j-1;y<=p.j+1;y++) {
					if(y>=0&&y<labyrinthe.length&&(x==p.i||y==p.j)
						&&(x!=p.i||y!=p.j)&&!labyrinthe[x][y].isChemin) {
						l.add(labyrinthe[x][y]);
					}
				}
			}
		}
		int rd=(int)(Math.random()*l.size());
		if(l.size()>0) {
			Piece res=l.get(rd);
			res.setEntree(p);
			res.supprimeMur(p);
			res.isChemin=true;
			p.supprimeMur(res);
			return res;
		}
		else {
			return null;
		}
	}

	public void createChemin() {
		int x=0;
		Stack<Piece> chemin=new Stack<>();
		chemin.add(entree);
		x++;
		while(x<taille*taille) {
			Piece tmp =setSortie(chemin.peek());
			if(tmp!=null) {
				chemin.peek().setSortie(tmp);
				x++;
				chemin.push(tmp);
			}
			else {
				chemin.pop();
			}

		}

	}

	public void afficherLabyrinthe() {
	    for (int i = 0; i < labyrinthe.length; i++) {
	        for (int j = 0; j < labyrinthe[0].length; j++) {
	            Piece p = labyrinthe[i][j];
	            if (p.murTop) {
	                System.out.print("+---");
	            } else {
	                System.out.print("+   ");
	            }
	        }
	        System.out.println("+");
	        for (int j = 0; j < labyrinthe[0].length; j++) {
	            Piece p = labyrinthe[i][j];
	            if (p.murLeft) {
	                System.out.print("|   ");
	            } else {
	                System.out.print("    ");
	            }
	        }
	        System.out.println("|");
	    }
	    for (int j = 0; j < labyrinthe[0].length; j++) {
	        System.out.print("+---");
	    }
	    System.out.println("+");
	}

	public static void main(String[] args) {
		Labyrinthe test =new Labyrinthe(15,3);
		test.initialisation();
		test.afficherLabyrinthe();
	}



}
