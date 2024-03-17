package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.w3c.dom.Text;
import Model.*;


public class JoueurPartie extends JFrame {
	ImagePane choice = new ImagePane();
    JLabel instruction = new JLabel("");
    JButton retour2;
    String joueurSelect;
    LinkedList<String> listJoueurs;
    String[] choixJoueurs;
    int nbrJoueur;

	JoueurPartie(){
	Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    this.setSize(width, height);
    
    this.setLocationRelativeTo(null);
    this.setVisible(true);
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	instruction.setBounds(width/2-300,height/2-400,300,50);
	instruction.setFont(new Font("Arial",Font.BOLD, 25));

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
    createListeJoueur();
    JComboBox<String> liste = new JComboBox<>(choixJoueurs);
	LinkedList<String> playerName = new LinkedList<>();
	joueurSelect = "Melissa";
    liste.setBounds(width/2-300,height/2-100,100,30);
    liste.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		joueurSelect = (String) liste.getSelectedItem();
	    }
 	});

	Icon chdf = new ImageIcon("Ressources/Boutons/choixdiff.png");
	JButton diff=new JButton(chdf);
	diff.setEnabled(false);
	ajusteBtn(diff);
	diff.setBounds(width/2-125,height/2-200, 250, 100);
	diff.addActionListener((event)->{
		new DifficultySelect(playerName);
		this.setVisible(false);
	});

	Icon tj = new ImageIcon("Ressources/Boutons/testjoueur.png");
    JButton b = new JButton(tj);
	ajusteBtn(b);
	b.setBounds(width/2-125,height/2-50,250, 100);
    b.addActionListener((event) -> {
    	instruction.setText("Enregistrement en cours ...");
        choice.repaint();
        TestVocal test = new TestVocal(joueurSelect);
        test.startTest();
        
        
        TextPage textPage = new TextPage(this);
        textPage.setVisible(true);
        this.setVisible(false);
        
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(22000);
                this.setVisible(true);
                textPage.setVisible(false);
				System.out.println("le test est"+test.isRight);
                if(test.isRight){
        			playerName.add(joueurSelect);
        			liste.removeItem(joueurSelect);
        			nbrJoueur++;
        			instruction.setText("Joueur ajouté");
        			choice.repaint();
        		}
        		else{
					
        			instruction.setText("Joueur non reconnu");
        			choice.repaint();
        		}

        		if(nbrJoueur>=1){
        			diff.setEnabled(true);
        		}

        		if(nbrJoueur==4){
        			b.setEnabled(false);
        		}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    });

	choice.add(instruction);
	choice.add(retour2);
    choice.add(b);
    choice.add(diff);
    choice.add(liste);
	choice.repaint();
    this.add(choice);
  }

  public void ajusteBtn(JButton bouton){
	bouton.setBorderPainted(false);
    bouton.setOpaque(false);
    bouton.setContentAreaFilled(false);
    bouton.setFocusable(false);
  }
  
  public void createListeJoueur() {
	  	listJoueurs=new LinkedList<>();
		File file = new File("Model/ListePlayer.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("[^a-zA-Z]+");
			
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				listJoueurs.add(line);
			
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		choixJoueurs=new String[listJoueurs.size()];
		for(int i =0;i<choixJoueurs.length;i++) {
			choixJoueurs[i]=listJoueurs.get(i);
		}
		
  }


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
	    	    image = ImageIO.read(new File("Ressources/Fonds/fondjeu.png"));
	        }
	        catch(Exception e){
	        	System.out.println("Erreur de chemin du fichier");
	        }
	    }

	}
}
