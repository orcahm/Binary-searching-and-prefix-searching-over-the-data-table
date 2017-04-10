import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WriteFile {
	
	public WriteFile(BufferedWriter bw,ArrayList<Table> cus,Comment com) throws IOException{
		
		bw.write("CommendText: \""+com.getLine()+"\"\n\n");
		bw.write("Result:\n");
		StringBuilder strb = new StringBuilder();
		for(int i=0;i<com.getSelect().size();i++){
			strb.append(String.format("%-25s", com.getSelect().get(i)));
		}
		bw.write(strb.toString()+"\n");
		strb.setLength(0);
		for(int i = 0;i<cus.size();i++){
			for(int j=0;j<com.getSelect().size();j++){
				if(com.getSelect().get(j).equals("CID")){
					strb.append(String.format("%-25s", cus.get(i).getCustomerCID()));
				}else if(com.getSelect().get(j).equals("FirstName")){
					strb.append(String.format("%-25s", cus.get(i).getName()));
				}else if(com.getSelect().get(j).equals("LastName")){
					strb.append(String.format("%-25s", cus.get(i).getLastName()));
				}else if(com.getSelect().get(j).equals("City")){
					strb.append(String.format("%-25s", cus.get(i).getCity()));
				}else if(com.getSelect().get(j).equals("AddressLine1")){
					strb.append(String.format("%-25s", cus.get(i).getAdderees()));
				}else strb.append(String.format("%-25s", cus.get(i).getSsNo()));
			}
			bw.write(strb.toString()+"\n");
			strb.setLength(0);
		}
		bw.write("---------------------------\n\n");
		
	}
	public WriteFile(BufferedWriter bw,long dif) throws IOException{
		bw.write("Process time: " + dif + " milliseconds\n\n");
	}
	public WriteFile(BufferedWriter bw,String err,Comment com) throws IOException{
		bw.write("CommendText: \""+com.getLine()+"\"\n\n");
		bw.write("Result:\n");
		bw.write(err + "\n");
		bw.write("---------------------------\n\n");
		
	}
}
