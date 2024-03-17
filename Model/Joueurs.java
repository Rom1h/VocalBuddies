package Model;
import java.awt.*;
import javax.swing.*;

public class Joueurs {
    int moves;
    public int id;
    int x;
    int y;
    public JPanel panel;
    public int nbrTours=0;
    public int de;
    public boolean afini=false;
    public String name;
    
    public Joueurs(int i, int c){
        this.id = i;
        this.moves = 0;
        this.x = 0;
        this.y = 0;
        panel = new JPanel(null);
        panel.setSize(20,20);
        if(c==0) panel.setBackground(Color.RED);
        else if(c==1) panel.setBackground(Color.BLUE);
        else if(c==2) panel.setBackground(Color.GREEN);
        else if(c==3) panel.setBackground(Color.ORANGE);
    }
    public void setName(String name) {
    	this.name=name;
    }
    public String getName() {
    	return this.name;
    }
    public int getNbMoves(){
        return this.moves;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getID(){
        return this.id;
    }

    public void setPos(int posX, int posY){
        this.x = posX;
        this.y = posY;
    }
}
