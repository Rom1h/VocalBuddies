package View;
import java.awt.*;
import javax.swing.*;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import Model.*;

public class GameView extends JLayeredPane{

    Labyrinthe modele;
    int diff;
    JButton retour = new JButton();
    JButton retour2 = new JButton();
    JButton parler = new JButton();
    boolean canMove;
    Joueurs[] joueurs;
    Joueurs currentJoueur;
    int nbJoueurs;
    JLabel quiJoue, de, l; // de = chiffre tiré au dé
    JLabel nbTours=new JLabel(); // nombre de déplacements d'un joueur
    LinkedList<Joueurs> joueursFinis=new LinkedList<>();
    // les joueurs qui ont fini

    int resolution = 0;
    int labSize = 0;

    BufferedImage image;

    public GameView(int d, LinkedList<String> players){
      nbJoueurs=players.size();

      try{
        image = ImageIO.read(new File("Ressources/Fonds/fondjeu.png"));
      }
      catch(Exception e){
        System.out.println("Erreur de chemin du fichier");
      }

        //A METTRE DANS LABYRINTHEVIEW POUR LES FONCTIONNALITES!
        diff = d;

        // dimensions de l'écran
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int)tailleEcran.getHeight();
        int largeur = (int)tailleEcran.getWidth();

        int y = 0;
        switch(d){
            case 1: y = (hauteur-(625))/2; resolution = 125; labSize = 5; break;
            case 2: y = 50; resolution = 90; labSize = 10; break;
            case 3: y = 20; resolution = 65; labSize = 15; break;
        }

        int x = (largeur-(labSize*resolution))/2;

        //modele  =   new Labyrinthe(10);
        modele = new Labyrinthe(labSize, nbJoueurs);
        joueurs = modele.joueurs;
        modele.initialisation();
        modele.afficherLabyrinthe();
        for(int i=0;i<joueurs.length;i++) {
        	joueurs[i].setName(players.get(i));
        }
        for(int i = 0; i<nbJoueurs; i++){
          joueurs[i].panel.setBounds(x+(resolution/2)-10,y+(resolution/2)-10,20,20);
          this.add(joueurs[i].panel,JLayeredPane.PALETTE_LAYER);
        }
        for(int i=0;i<joueurs.length;i++) {
        	System.out.println(joueurs[i].getName()+" "+joueurs[i].getID());
        }
        currentJoueur = joueurs[0];
        currentJoueur.de = nbrAlea();

      //  this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

        int l = x;
        int h = y;
        for(int i=0; i<labSize; i++){
            for(int j=0; j<labSize; j++){
                CasePanel tmp = new CasePanel(i, j, modele, resolution);
                tmp.setBounds(l, h, resolution,resolution);
                l+=resolution;
                this.add(tmp,JLayeredPane.DEFAULT_LAYER);
            }
            l=x;
            h+=resolution;
        }

        JLabel jc = ajouteLabel("Joueur courant :", 150, 150, 400, 50, 30);
        JLabel dp = ajouteLabel("Déplacements permis :", 1500, 450, 400, 50, 25);
        JLabel pts = ajouteLabel("Nb de tours :", 1500, 150, 400, 50, 30);
        de = ajouteLabel(String.valueOf(currentJoueur.de), 1650, 500, 100, 50, 60);
        nbTours = ajouteLabel("", 1600, 175, 400, 100, 60);
        quiJoue = ajouteLabel("", 200, 200, 500, 50, 40);

        quiJoue(currentJoueur.id);

        setBoutonParler();
        this.add(parler);
        this.repaint();

    }

    public void btnRetour(){
      this.add(retour2,JLayeredPane.PALETTE_LAYER);
    }

    public JLabel ajouteLabel(String txt, int x, int y, int l, int h, int taille){
      JLabel tmp = new JLabel(txt);
      tmp.setBounds(x,y,l,h);
      tmp.setFont(new Font("Arial",Font.BOLD, taille));
      this.add(tmp,JLayeredPane.PALETTE_LAYER);
      return tmp;
    }

    public void quiJoue(int i){
      nbTours.setText(String.valueOf(currentJoueur.nbrTours));
      de.setText(String.valueOf(currentJoueur.de));
      if(i==0){
        quiJoue.setText(joueurs[i].name);
        de.setForeground(Color.RED);
        quiJoue.setForeground(Color.RED);
        nbTours.setForeground(Color.RED);
      }
      else if(i==1){
        quiJoue.setText(joueurs[i].name);
        de.setForeground(Color.BLUE);
        quiJoue.setForeground(Color.BLUE);
        nbTours.setForeground(Color.BLUE);
      }
      else if(i==2){
        quiJoue.setText(joueurs[i].name);
        de.setForeground(Color.GREEN);
        quiJoue.setForeground(Color.GREEN);
        nbTours.setForeground(Color.GREEN);
      }
      else if(i==3){
        quiJoue.setText(joueurs[i].name);
        de.setForeground(Color.ORANGE);
        quiJoue.setForeground(Color.ORANGE);
        nbTours.setForeground(Color.ORANGE);
      }
    }

  public void setBoutonParler(){
    Icon icone = new ImageIcon("Ressources/Boutons/microOff.png");
    Icon icone2 = new ImageIcon("Ressources/Boutons/microOn.png");
    parler = new JButton(icone);
    parler.setBounds(100,800,80,100);
    parler.setBorderPainted(false);
    parler.setOpaque(false);
    parler.setContentAreaFilled(false);
    parler.setFocusable(false);
    // Mettre à jour l'attribut CurrentJoueur pour les tours.
    // Chaque mouvement appelle les methodes arriveJoueur()
    // et partJoueur() dans le modele de la classe Labyrinthe,
    // et le compteur de mouvements s'incrémente pour chaque joueur.
    parler.addActionListener((event) -> {
      parler.setEnabled(false);   
      parler.setIcon(icone2);
      VocalMovement v=new VocalMovement();
    	v.start();
    	switch(v.deplacement){
		    case 0:
			    positionner("erreur");
          parler.setEnabled(true);
          parler.setIcon(icone);
			    break;
		    case 1:
			    if(!modele.labyrinthe[currentJoueur.getX()][currentJoueur.getY()].murTop){
		        positionner("up");
            parler.setEnabled(true);
            parler.setIcon(icone); 
		      }
			    break;
		    case 2:
			    if(!modele.labyrinthe[currentJoueur.getX()][currentJoueur.getY()].murBot){
		        positionner("bot");
            parler.setEnabled(true);
            parler.setIcon(icone);
		      }
			    break;
		    case 3:
			    if(!modele.labyrinthe[currentJoueur.getX()][currentJoueur.getY()].murLeft){
			      positionner("left");
            parler.setEnabled(true);
            parler.setIcon(icone);
			    }
			    break;
		    case 4:
			    if(!modele.labyrinthe[currentJoueur.getX()][currentJoueur.getY()].murRight){
			      positionner("right");
            parler.setEnabled(true);
            parler.setIcon(icone);
			    }
          break;
		    default:
			    break;
	  	}
    });
  }


  public int nbrAlea(){
    // Entier entre 1 et 6
    int r = (int)(Math.random()*10)%6+1;
    return r;
  }

  public void positionner(String s){
    int x = 0;
    int y = 0;
    int resX = 0;
    int resY = 0;

    l = new JLabel("Bravo vous êtes dehors !");
    l.setFont(new Font("Arial",Font.BOLD, 30));
    l.setBounds(100,100,1000,50);

	if(s.equals("bot")){ ++x; resY = resolution; }
	else if(s.equals("up")){ --x; resY = -resolution; }
    else if(s.equals("left")){ --y; resX = -resolution; }
    else if(s.equals("right")){ ++y; resX = resolution; }
    else {currentJoueur.de--;return;}
    currentJoueur.panel.setLocation(currentJoueur.panel.getX()+resX,currentJoueur.panel.getY()+resY);
    currentJoueur.setPos(currentJoueur.getX()+x,currentJoueur.getY()+y);
    de.setText(String.valueOf(currentJoueur.de-1));

    if(currentJoueur.getX() == labSize || currentJoueur.getX() < 0
      || currentJoueur.getY() < 0 || currentJoueur.getY() == labSize){
      finPartie();
    }

    if(currentJoueur.de==1 || currentJoueur.afini){
    if(!currentJoueur.afini) currentJoueur.nbrTours++;
    if(currentJoueur.id<nbJoueurs-1) currentJoueur = joueurs[currentJoueur.id+1];
    else currentJoueur = joueurs[0];
    while(currentJoueur.afini && joueursFinis.size()!=nbJoueurs){
    	if(currentJoueur.id<nbJoueurs-1) currentJoueur = joueurs[currentJoueur.id+1];
	    else currentJoueur = joueurs[0];
	      }
	      //quiJoue(currentJoueur.id);
	      currentJoueur.de = nbrAlea();
	      quiJoue(currentJoueur.id);
	    }
	    else{
	      currentJoueur.de--;
	    }

  }

  public void finPartie(){
    currentJoueur.nbrTours++;
    joueursFinis.add(currentJoueur);
    currentJoueur.afini=true;
    if(joueursFinis.size() == nbJoueurs){
      System.out.println("!!!   BRAVO VOUS ETES DEHORS   !!!");
      this.removeAll();
      this.add(l,JLayeredPane.PALETTE_LAYER);
      this.add(retour,JLayeredPane.PALETTE_LAYER);
      this.repaint();

      int c = 200;
      for(int i = 0; i<nbJoueurs; i++){
        String s="";
        if(i==0) s = "ROUGE";
        if(i==1) s = "BLEU";
        if(i==2) s = "VERT";
        if(i==3) s = "ORANGE";
        JLabel tmp = new JLabel(s+" a fini en "+joueursFinis.get(i).nbrTours+" tours");
        tmp.setFont(new Font("Arial",Font.BOLD, 30));
        tmp.setBounds(200,c+100,1000,50);
        this.add(tmp,JLayeredPane.PALETTE_LAYER);
        c+=100;
      }
      this.repaint();

    }
  }


  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(image, 0, 0, this);
  }

}
