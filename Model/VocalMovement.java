package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VocalMovement {
	Recorder rec;
	public int deplacement=-1;
	boolean success;
	public VocalMovement() {
		rec=new Recorder(3000);
	}	
	public void start() {
         try {
          	rec.enregistrement();
            Thread.sleep(3000);
            getMovement();
           
            	try {
            		deplacement=analyseFile();
            	
            		System.out.println(deplacement);
				} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
           
             
            catch (InterruptedException ex) {
                 ex.printStackTrace();
                }
       
        }
	public void getMovement() {
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "Model/script_getAudioToText.sh");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            
            int exitCode = process.waitFor();
            System.out.println("Script finished with exit code " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
           
       
	}
	
	public int analyseFile() throws FileNotFoundException {
		File file = new File("Model/res.txt");
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter("[^a-zA-Z]+");
		
		
		while(scanner.hasNext()) {
			String line = scanner.next();
			int test=analyseString(line);
			if(test!=0) {
				System.out.println("reusie "+ line);
				return test;
			}
			
		
		}
		return 0;
		
	}
	public int analyseString(String str) {
		str=str.toLowerCase();
		System.out.println(str);
		if(isUp(str))return 1;
		if(isDown(str))return 2;
		if(isLeft(str))return 3;
		if(isRight(str))return 4;
		return 0;
	}
	public boolean isUp(String str) {
		return str.equals("up")||str.equals("oop")||str.equals("but")||str.equals("uh")||str.equals("haut")||str.equals("au")||str.equals("ouh")||str.equals("oh")||str.equals("où")||str.equals("ouf")||str.equals("oup");
	}
	public boolean isDown(String str) {
		return str.equals("down")||str.equals("don")||str.equals("dong")||str.equals("bas")||str.equals("bah");
	}
	public boolean isLeft(String str) {
		return str.equals("left")||str.equals("lift")||str.equals("gauche")||str.equals("couche")||str.equals("couch");
	}
	public boolean isRight(String str) {
		return str.equals("right")||str.equals("white")||str.equals("droite");
	}
	
}

