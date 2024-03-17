package Model;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;

public class Enregistrement {
	 File wavFile;
	 // format of audio file
	 AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	 // the line from which audio data is captured
	 TargetDataLine line;
	 // Audio Format
	 AudioFormat format= new AudioFormat(16000,16,1,true, false);

	 Enregistrement(int nbr){
		 wavFile = new File("ModelCreation/wav/RecordAudio_"+nbr+".wav");
	 }
}
