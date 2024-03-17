package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Model {
	String user;
	public Model(String user){
		this.user=user;
	}
	public void createModel() {
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "ModelCreation/script_Model2.sh",user);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if(exitCode==0) {
            	addJoueur();
            }
            System.out.println("Script finished with exit code " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public void addJoueur() {
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "Model/script_addPlayer.sh",user);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Script finished with exit code " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
	}
}
