import java.io.IOException;


public class RunMedley {
public static void main(String[] args) {
	try {
		Process update = new ProcessBuilder(
				"java","-jar","Update.jar").start();
		update.waitFor();
		Process medley = new ProcessBuilder("java","-jar","Medley.jar").start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
}
}
