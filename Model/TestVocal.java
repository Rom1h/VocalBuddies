package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestVocal {
	String user;
	Recorder rec;
	public boolean isRight;
	public TestVocal(String user) {
		this.user=user;
		rec=new Recorder(20000);
	}

	public void startTest() {
		Thread record = new Thread(new Runnable() {
            public void run() {
                try {
                	rec.enregistrement();
                    Thread.sleep(20000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                ComputeTest();
                try {  
                	System.out.println(verify());
					isRight=verify();
					System.out.println(isRight);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
        });
		record.start();


	}
	public void ComputeTest() {
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "ModelCreation/script_testVoc.sh",user);
            pb.redirectErrorStream(true);
            System.out.println("oui test :" +(pb==null));
            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("Script finished with exit code " + exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	public boolean verify() throws FileNotFoundException {
		File file = new File("ModelCreation/TestClients.res");
		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine();
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?\\b$");
		Pattern pattern2 = Pattern.compile("^\\s*\\S+\\s+(\\S+)");

		Matcher matcher = pattern.matcher(line);
		Matcher matcher2=pattern2.matcher(line);
		double value = 0;
		String name="";
		if (matcher.find()&&matcher2.find()) {
		    value = Double.parseDouble(matcher.group());
			name=matcher2.group(1);
		
		}

		
		while(scanner.hasNextLine()) {
			line=scanner.nextLine();
			matcher = pattern.matcher(line);
			matcher2= pattern2.matcher(line);

			if(matcher.find() && matcher2.find()) {
				if(value<Double.parseDouble(matcher.group())) {
					value=Double.parseDouble(matcher.group());
					name=matcher2.group(1);
				}          
				}
				
			}
				
			

		scanner.close();
		return name.equals(user);
	}
	

}
