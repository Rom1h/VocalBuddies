package View;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import Model.*;
public class LabyrintheView extends JPanel{

    Labyrinthe modele;

    Color randColor(){
        Random r = new Random();
        float red = r.nextFloat();
        float green = r.nextFloat();
        float blue = r.nextFloat();
        return new Color(red,green,blue);
    }

    LabyrintheView(){
        modele  =   new Labyrinthe(5);
        this.setLayout(new GridLayout(5,5));
        JPanel panelTest = new JPanel();

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                panelTest.setBackground(randColor());
                this.add(panelTest);
                //this.add(new CasePanel(i, j, modele));
            }
        }
    }

}
