package View;
import javax.swing.*;
import java.awt.*;
import Model.*;
public class CasePanel extends JPanel{

    int labResolution = 0;   
    int x = 0;
    int y = 0;    
    Labyrinthe laby;
    Image image;

    CasePanel(int x, int y, Labyrinthe l, int labResolution){
        this.x = x;
        this.y = y;
        this.laby = l;
        this.labResolution = labResolution;
    }

    public void paint(Graphics g){

        Graphics g2D = (Graphics2D) g;

        // Choisir la taille des images
        String img = "";
        if(labResolution==125) img+="grand/";
        else if(labResolution==90) img+="moyen/";
        else if(labResolution==65) img+="petit/";

        // Choisir les images en fonction des positions des murs
        String nm = "mmmm";
        if(!laby.labyrinthe[x][y].murTop){
          nm = "s"+nm.substring(1,4);
        }
        if(!laby.labyrinthe[x][y].murRight){
          nm = nm.substring(0,1)+"s"+nm.substring(2,4);
        }
        if(!laby.labyrinthe[x][y].murBot){
          nm = nm.substring(0,2)+"s"+nm.substring(3,4);
        }
        if(!laby.labyrinthe[x][y].murLeft){
          nm = nm.substring(0,3)+"s";
        }

        image = new ImageIcon("Ressources/cases/" + img + nm + ".png").getImage();
        g2D.drawImage(image, 0, 0, null);
    }

}
