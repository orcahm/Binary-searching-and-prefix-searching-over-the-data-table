import java.util.ArrayList;
/**
 * 
 * @author orcahm
 * it makes object from comments file for comparing and stuff
 * and split line which we read from file and make them meaningfull elements for using.
 *
 */

public class Comment {

	private String line;
	private ArrayList<String> select;
	private ArrayList<ArrayList<String>> where;
	
	public Comment(String line){
	
		this.line = line;
		this.select = new ArrayList<String>();
		this.where = new  ArrayList<ArrayList<String>>();
		String [] splitLine = line.split(" ");
		splitSelect(splitLine);
		splitWhere(splitLine);
	}
	
	public String getLine() {
		return line;
	}
	
	public ArrayList<String> getSelect() {
		return select;
	}
	
	public ArrayList<ArrayList<String>> getWhere() {
		return where;
	}
	
	private void splitSelect(String [] select){
		
		String [] splitSelect = select[1].split(",");
		for(int i=0;i<splitSelect.length;i++){
			this.select.add(splitSelect[i]);
		}
	}
	
	private void splitWhere(String [] where){
		String []str;
		int index = 0;
		for(int i=3; i<where.length;i++){
			if(where[i].equals("AND")){
				continue;
			}else{
				this.where.add(new ArrayList<String>());
				if((str=where[i].split("<")).length==2){
					this.where.get(index).add(str[0]);
					this.where.get(index).add(str[1]);
					this.where.get(index).add("<");
					index++;
				}else if((str=where[i].split(">")).length==2){
					this.where.get(index).add(str[0]);
					this.where.get(index).add(str[1]);
					this.where.get(index).add(">");
					index++;
				}else if((str=where[i].split("=")).length==2){
					this.where.get(index).add(str[0]);
					this.where.get(index).add(str[1]);
					this.where.get(index).add("=");
					index++;
				}else if((str=where[i].split("~")).length==2){
					this.where.get(index).add(str[0]);
					this.where.get(index).add(str[1]);
					this.where.get(index).add("~");
					index++;
				}
					
					
				
					
				
				
			}
	}
	
	}
}