package Model;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import javax.swing.*;

public class Chrono{
  Timer timer;
  long temps;
  int cpt=0;
  public JProgressBar jauge;
  Recorder enrg;

  public Chrono(){
    jauge = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
    jauge.setValue(0);
    enrg = new Recorder(300000);//30min
    temps = enrg.RECORD_TIME;
  }

  public void demarrer(int action){
    if(timer==null){
      timer = new Timer(true);
      if(action==0) {
    	  enrg.enregistrement();;  
      }
      else {
    	  enrg.reprendre();
      }
      timer.scheduleAtFixedRate(new TimerTask(){
        @Override
        public void run(){
            cpt++;
            if(cpt>=temps){
              timer.cancel();
              cpt=0;
            }
            jauge.setValue(cpt);
          }
      }, 0, temps/100);
    }
  }
  
  public void pause(){
    enrg.stop();
    if(timer!=null){
      timer.cancel();
      timer = null;
    }
    jauge.setValue(cpt);
  }

  public void fin(){
    enrg.finish();
    if(timer!=null){
      timer.cancel();
      timer = null;
    }
    cpt=0;
    jauge.setValue(cpt);
  }

}
