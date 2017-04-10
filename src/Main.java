import java.io.IOException;
import java.util.ArrayList;


public class Main {

	/**
	 * thıs class is starter class 
	 * @param args : is taking files which will be used.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		ReadFile csv = new ReadFile(args[0]);
		ReadFile com = new ReadFile(args[1]);
		
		Operation ope = new Operation(csv, com);
		
	}

}
