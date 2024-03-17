package Model;
import java.util.LinkedList;

public class Piece {
	public boolean murTop=true;
	public boolean murBot=true;
	public boolean murLeft=true;
	public boolean murRight=true;
	boolean isChemin=false;
	Piece entree;
	LinkedList<Piece> sortie;
	LinkedList<Joueurs> joueurs;
	int i,j;

	Piece(int i,int j){
		this.i=i;
		this.j=j;
		sortie=new LinkedList<>();
		joueurs=new LinkedList<>();
		
	}

	//retourne true s'il y a la présence d'au moins un joueur
	public boolean estVide(){
		return this.joueurs.isEmpty();
	}

	//maj déplacements du joueur sur la case
	public void arriveJoueur(Joueurs j){
		this.joueurs.add(j);
		//modifier aussi les attributs de position du joueur 
	}

	public void partJoueur(Joueurs j){
		this.joueurs.remove(j);
		//modifier aussi les attributs de position du joueur 
	}
	
	public void setEntree(Piece p) {
		this.entree=p;
	}
	
	public Piece getEntree(Piece p) {
		return entree;
	}
	
	public void setSortie(Piece p) {
		this.sortie.add(p);
	}
	
	public boolean hasSortie() {
		return sortie!=null;
	}
	public boolean hasEntree() {
		return entree!=null;
	}
	
	public void supprimeMur(Piece p) {
		if(this.i>p.i) {
			murTop=false;
		}
		else if(this.i<p.i) {
			murBot=false;
		}
		else if(this.j>p.j) {
			murLeft=false;
		}
		else if(this.j<p.j) {
			murRight=false;
		}
	}
	public boolean isSortie(Piece p) {
		return sortie.contains(p);
	}

	
}
