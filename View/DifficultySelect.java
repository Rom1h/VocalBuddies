package View;
import java.awt.*;
import javax.swing.*;
import java.net.MalformedURLException;
import java.util.LinkedList; 
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;    

public class DifficultySelect extends JFrame {

    ImagePane choice = new ImagePane();
    JLabel instruction = new JLabel();
    JButton retour, retour2;
    int nbj=1;
    LinkedList<String> players;
    GameView gv;

    public DifficultySelect(LinkedList<String> players){

      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension screenSize = toolkit.getScreenSize();
      int height = screenSize.height;
      int width = screenSize.width;
      this.setSize(width, height);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);

      retour = new JButton("Menu"); 
      retour.setBounds(100,160,100,30);
      retour.addActionListener((event) -> { 
        this.dispose();
        new Menu();
      });

      Icon icone = new ImageIcon("Ressources/Boutons/retourmenu.png");
      retour2 = new JButton(icone);
      retour2.setBounds(1600,800,91,91);
      retour2.setBorderPainted(false);
      retour2.setOpaque(false);
      retour2.setContentAreaFilled(false);
      retour2.setFocusable(false);
      retour2.addActionListener((event) -> {
        this.dispose();
        new Menu();
      });

      this.nbj=players.size();
      this.players=players;
      JButton fac = bouton("Ressources/Boutons/facile.png",width/2-125,height/2-200, 1);
      JButton moy = bouton("Ressources/Boutons/moyen.png",width/2-125,height/2-100, 2);
      JButton dif = bouton("Ressources/Boutons/difficile.png",width/2-125,height/2, 3);

        this.add(choice);

        this.setVisible(true);
    }

    public void setNbj(int n){
      this.nbj = n;
    }

    public JButton bouton(String img, int largeur, int hauteur, int q){
      Icon i = new ImageIcon(img);
      JButton b = new JButton(i);
      b.setBounds(largeur, hauteur, 250, 75);
      b.setBorderPainted(false);
      b.setOpaque(false);
      b.setContentAreaFilled(false);
      b.addActionListener((event) -> {
      this.remove(choice);
      gv = new GameView(q,players);
      gv.retour = this.retour;
      gv.retour2 = this.retour2;
      gv.btnRetour();
        this.add(gv);
        this.setVisible(true);
      });
      choice.add(b);
      return b;
    }

    // classe pour image de fond pour le panneau
    public class ImagePane extends JPanel{
        BufferedImage image;

        @Override
        public void paintComponent(Graphics g){
          super.paintComponent(g);
          g.drawImage(image, 0, 0, this);
        }

        public ImagePane() {
          setImage();
          setLayout(null);
        }

        public void setImage(){
          try{
            image = ImageIO.read(new File("Ressources/Fonds/difficulte.png"));
          }
          catch(Exception e){
            System.out.println("Erreur de chemin du fichier");
          }
        }

    }

}
