package View;

import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;
import java.net.MalformedURLException;  
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;
import Model.*;

public class Menu extends JFrame{
  ImagePane panneau;
  int hauteur, largeur;

    public Menu(){
      panneau = new ImagePane();
      this.add(panneau);
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension screenSize = toolkit.getScreenSize();
      this.setSize(screenSize.width, screenSize.height);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      boutonsMenu();
    }

    public void boutonsMenu(){
      ajoutBtn("Ressources/Boutons/jouer.png", largeur/2-125, hauteur/2-120, 250, 75, 1);
      ajoutBtn("Ressources/Boutons/senregistrer.png", largeur/2-125, hauteur/2-35, 250, 75, 5);
      ajoutBtn("Ressources/Boutons/instructions.png", largeur/2-125, hauteur/2+50, 250, 75, 2);
      ajoutBtn("Ressources/Boutons/quitter.png", largeur/2-125, hauteur/2+135, 250, 75, 3);
      panneau.repaint();
    }

    public void ajoutBtn(String img, int x, int y, int l, int h, int a){
      Icon icone = new ImageIcon(img);
      JButton bouton = new JButton(icone);
      bouton.setBorderPainted(false);
      bouton.setOpaque(false);
      bouton.setContentAreaFilled(false);
      bouton.setBounds(x,y,l,h);
      bouton.setFocusable(false);
      switch(a){
        case 1 :
          bouton.addActionListener(e ->{
            new JoueurPartie();
            this.dispose();
        }); break;
        case 2 :
          bouton.addActionListener(e ->{
            panneau.removeAll();
            panneau.setImage(2);
            ajoutBtn("Ressources/Boutons/retourmenu.png", largeur/2-500, hauteur/2-500, 91, 91, 4);
            panneau.repaint();
          }); break;
        case 3 :
          bouton.addActionListener(e ->{ this.dispose(); }); break;   
        case 4 :
          bouton.addActionListener(e ->{ 
            panneau.removeAll(); 
            panneau.setImage(1);
            boutonsMenu();
            this.setVisible(true);
          }); break;
          case 5 :
            bouton.addActionListener(e ->{
              panneau.removeAll();
              panneau.setImage(3);

              JButton debut = nvBouton("Ressources/Boutons/nvEnregistrement.png", largeur/2-200, hauteur/2-250, 183,50);
              debut.setEnabled(true);
              JButton pause = nvBouton("Ressources/Boutons/pause.png", largeur/2-180, hauteur/2-170, 150,50);
              JButton fin = nvBouton("Ressources/Boutons/arreter.png", largeur/2-180, hauteur/2-90, 150,50);
              JButton model = nvBouton("Ressources/Boutons/nvModel.png", largeur/2-200, hauteur/2+20, 250,100);

              JButton rename = nvBouton("Ressources/Boutons/croix.png", largeur/2+15,hauteur/2-20,30,30);
              rename.setEnabled(true);
              rename.setVisible(false);
              JTextField name = new JTextField("Entrez votre nom",10);
              String initialText=name.getText();
              name.setBounds(largeur/2-190,hauteur/2-20,200,30);

              name.addMouseListener(new MouseAdapter(){
                  public void mouseClicked(MouseEvent e){
                      name.setText("");
                  }
              });

              name.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                  char c = e.getKeyChar();
                     if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_DELETE || Character.isDigit(c))) {
                      e.consume();  // ignorer l'événement
                     }
                      if(c==KeyEvent.VK_ENTER && name.getText().length()>0) {
                        model.setEnabled(true);
                        name.setEnabled(false);
                        rename.setVisible(true);
                      }
                }});

              rename.addActionListener(e1->{
                name.setEnabled(true);
                model.setEnabled(false);
                rename.setVisible(false);
              });

              model.addActionListener(e1->{
              Model m=new Model(name.getText());
                m.createModel();
                model.setEnabled(false);
              });

              Icon iconPause = new ImageIcon("Ressources/Boutons/pause.png");
              Icon iconRepr = new ImageIcon("Ressources/Boutons/reprendre.png");

              Chrono timer = new Chrono();
              JProgressBar jauge = timer.jauge;
              jauge.setBounds(largeur/2+120, hauteur/2-250, 80,500);

              debut.addActionListener(e1 ->{
                timer.demarrer(0);
                pause.setEnabled(true);
                fin.setEnabled(true);
                debut.setEnabled(false);
              });

              pause.addActionListener(e1 ->{
                if(pause.getIcon() == iconPause){
                  timer.pause();
                  debut.setEnabled(false);
                  pause.setIcon(iconRepr);
                }
                else{
                  timer.demarrer(1);
                  pause.setIcon(iconPause);
                }
              });

              fin.addActionListener(e1 ->{
                timer.fin();
                pause.setEnabled(false);
                fin.setEnabled(false);
                debut.setEnabled(true);
                model.setEnabled(true);
              });

              model.addActionListener(e1->{
                name.setEnabled(true);
                model.setEnabled(false);
                rename.setVisible(false);
              });

              panneau.add(debut);
              panneau.add(pause);
              panneau.add(fin);
              panneau.add(jauge);
              panneau.add(model);
              panneau.add(name);
              panneau.add(rename);

              ajoutBtn("Ressources/Boutons/retourmenu.png", largeur/2-230, hauteur/2+200, 91, 91, 4);

              panneau.repaint();
            }); break;

        default:
          break;
      }
      panneau.add(bouton);
    }

    public JButton nvBouton(String image, int l, int h, int x, int y){
      Icon icone = new ImageIcon(image);
      JButton bouton = new JButton(icone);
      bouton.setBorderPainted(false);
      bouton.setOpaque(false);
      bouton.setContentAreaFilled(false);
      bouton.setBounds(l, h, x, y);
      bouton.setFocusable(false);
      bouton.setEnabled(false);
      return bouton;
    }

    public class ImagePane extends JPanel{
    		BufferedImage image;

    		@Override
    		public void paintComponent(Graphics g){
    			super.paintComponent(g);
    			g.drawImage(image, 0, 0, this); 
    		}

    		public ImagePane() {
          setImage(1);
    			setLayout(null);
          Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
          hauteur = (int)tailleEcran.getHeight();
          largeur = (int)tailleEcran.getWidth();
    			setPreferredSize(new Dimension(largeur, hauteur)); 
    		}

        public void setImage(int i){
          String img;
          switch(i){
            case 1 : img = "Ressources/Fonds/fondmenu.png"; break;
            case 2 : img = "Ressources/Fonds/pageinstructions.png"; break;
            case 3 : img = "Ressources/Fonds/fondjeu.png"; break;
            default : img = ""; break;
          }
          try{
            image = ImageIO.read(new File(img));
          }
          catch(Exception e){
            System.out.println("Erreur de chemin du fichier");
          }
        }

		}


    public static void main(String[] args){
        Menu v = new Menu();
        v.pack();
    }

}
