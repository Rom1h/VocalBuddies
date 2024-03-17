package Model;
import javax.sound.sampled.*;
import javax.swing.JLayeredPane;
import javax.swing.SwingWorker;

import java.io.*;
import java.awt.event.*;

public class Recorder extends JLayeredPane{

    static long RECORD_TIME; // durée total de l'enregistrement en ms
    static long TIME_REMAIN;  //temps restant avant la fin de l'enregistrement
    long startTime,endTime;
    int nbr_Enregistrement=0;
    Thread record;
    Enregistrement current;
    boolean endRecorde;
    public Recorder(long times) {
    	RECORD_TIME=times;
    	TIME_REMAIN=times;
    	current=new Enregistrement(nbr_Enregistrement);
    	nbr_Enregistrement++;
    	record=new Thread();

    }
    //Fonction qui enregistre le son;
    void start() {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, current.format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }

            current.line = (TargetDataLine) AudioSystem.getLine(info);
            current.line.open(current.format);
            current.line.start();
            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(current.line);

            System.out.println("Start recording...");
            AudioSystem.write(ais, current.fileType, current.wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    //fonction qui met pause à l'enregistrement
    void stop() {
    	endTime=System.currentTimeMillis();
    	TIME_REMAIN=endTime-startTime;
    	record.interrupt();
    	finish();
    }
    //fonction qui lance l'enregistrement
    void enregistrement() {
        startTime = System.currentTimeMillis();
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Effectuer la tâche d'enregistrement en arrière-plan      
            	record = new Thread(new Runnable() {
                    public void run() {
            
                            try {
								Thread.sleep(TIME_REMAIN);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								System.out.println("enregistrement terminé");
							}
                        
                        System.out.println("fjq");
                        finish();
                    }
                });
         
                record.start();
                start();
                return null;
            }
        };
        worker.execute();
    }
    //fonction pour reprendre l'enregistrement;
    void reprendre() {
    	current=new Enregistrement(nbr_Enregistrement);
    	nbr_Enregistrement++;
    	enregistrement();
    }
    //fonction pour arreter completement l'enregistrement;
    void finish() {
    	
        current.line.stop();
        
        current.line.close();
        TIME_REMAIN=0;
        endRecorde=true;
        record.interrupt();
        System.out.println("finis");
    }
   
}
