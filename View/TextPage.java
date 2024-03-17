package View;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class TextPage extends JFrame {
	ImagePane choice = new ImagePane();
	private Timer timer;
    private JFrame previousFrame;

    public TextPage(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        timer = new Timer(20000, (ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TextPage.this.setVisible(false);
                previousFrame.setVisible(true);
                timer.stop();
            }
        });
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    this.setSize(width, height);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.add(choice);

	     this.setVisible(true);
	   
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
	            image = ImageIO.read(new File("Ressources/Fonds/fondlecture.png"));
	          }
	          catch(Exception e){
	            System.out.println("Erreur de chemin du fichier");
	          }
	        }

	    }
}
