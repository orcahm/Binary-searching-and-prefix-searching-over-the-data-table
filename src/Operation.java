import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *  in here we have main methods for find boundries which wanted in command file and write it out to output file which we created.
 * @author orcahm
 *
 */
public class Operation{

	ArrayList<Table> cus ;
	ArrayList<Comment> commentList;
	ArrayList<Table> copyCus;
	
	public Operation(ReadFile csv,ReadFile com) throws IOException{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		Table temp ;
		MyComparetor comp;
		cus = new ArrayList<Table>();
		copyCus = new ArrayList<Table>();
		commentList = new ArrayList<Comment>();
		
		String [] splitLine = csv.getLine().get(0).split("\\|");
		
		for(int i=1;i<csv.getLine().size();i++){
			splitLine = csv.getLine().get(i).split("\\|");
			cus.add(new Table(splitLine));
		}
		
		for(int i=0;i<com.getLine().size();i++){
			commentList.add(new Comment(com.getLine().get(i)));
		}
		
		for(int i = 0; i<commentList.size();i++){
			long lStartTime = System.currentTimeMillis();
			if(commentList.get(i).getWhere().size()>5){
				new WriteFile(bw,"Comment error: More than 5 conditions are not acceptable!!!",commentList.get(i));
				continue;
			}
			if (trueComment(i)==0){
				new WriteFile(bw,"Comment error: Wrong comment mark!!!",commentList.get(i));
				continue;
			}
			copyCus.addAll(cus);
			for(int j = 0; j<commentList.get(i).getWhere().size();j++){
				temp = new Table(commentList.get(i).getWhere().get(j).get(1) , commentList.get(i).getWhere().get(j).get(0));
				comp = new MyComparetor(commentList.get(i).getWhere().get(j).get(0));
				this.quickSort(0, copyCus.size()-1, comp);
				this.binarySearc(temp, comp, commentList.get(i).getWhere().get(j).get(2));
			}
			
			if(copyCus.size()==0){
				new WriteFile(bw,"Empty rowset",commentList.get(i));
			}else{
				new WriteFile(bw, copyCus, commentList.get(i));
			}
			long lEndTime = System.currentTimeMillis();
			long difference = lEndTime - lStartTime;
			new WriteFile(bw,difference);
			
			copyCus.clear();
		}
		
		bw.close();

	}
	
	public int trueComment(int i){
		for(int j = 0 ; j<commentList.get(i).getWhere().size(); j++){
		if(commentList.get(i).getWhere().get(j).get(2).equals("<") || commentList.get(i).getWhere().get(j).get(2).equals(">") || commentList.get(i).getWhere().get(j).get(2).equals("=")){
			if(commentList.get(i).getWhere().get(j).get(0).equals("FirstName") ||
					commentList.get(i).getWhere().get(j).get(0).equals("LastName") ||
					commentList.get(i).getWhere().get(j).get(0).equals("City") ||
					commentList.get(i).getWhere().get(j).get(0).equals("AddressLine1")){
				return 0;
			}
		}else if(commentList.get(i).getWhere().get(j).get(2).equals("~")){
			if(commentList.get(i).getWhere().get(j).get(0).equals("CID") || commentList.get(i).getWhere().get(j).get(0).equals("SocialSecurityNumber")){
				return 0;
			}
		}
		}
		return 1;
		
	}
	
	public void quickSort(int lo,int hi,MyComparetor comp){
		if(hi<=lo){
			return;
		}
		int pivotIndex = sort(lo,hi,comp);
		this.quickSort(lo, pivotIndex-1,comp);
		this.quickSort(pivotIndex+1, hi,comp);
		
	}
	
	public int sort(int lo,int hi,MyComparetor comp){
		   int i = lo, j = hi+1; 
		   
		   while (true) 
		   { 
		      while (comp.compare(this.copyCus.get(lo),this.copyCus.get(++i))==1) 
		         if (i == hi) break; 
		      while (comp.compare( this.copyCus.get(--j),this.copyCus.get(lo))==1) 
		         if (j == lo) break; 
		      if (i >= j) break; 
		      this.swap(i, j); 
		   } 
		   this.swap(lo, j); 
		   return j; 
		} 
	
	public void swap(int arg1,int arg2){
		
		Table temp = copyCus.get(arg1);
		copyCus.set(arg1, copyCus.get(arg2));
		copyCus.set(arg2, temp);
		
	}

	public void binarySearc(Table value,MyComparetor comp,String ope){
		  
		     int lo = 0, hi = copyCus.size()-1;
		     int listSize = hi;
		     int mid = 0 ;
		     while (lo <= hi) 
		     { 
		         mid = lo + (hi - lo) / 2; 
		         if      (comp.compare(copyCus.get(mid),value )==1) hi = mid - 1; 
		         else if (comp.compare(value, copyCus.get(mid))==1) lo = mid + 1; 
		         else break;
		    }
			    if(ope.equals("<")){
			    	while(true){
			    		if(mid<listSize){
				    		if(comp.compare(value , copyCus.get(mid))==1){
				    			mid += 1;
				    		}
				    		if(comp.compare(value , copyCus.get(mid) )==0){
				 				int size = copyCus.size();
				    			for(int i = mid;i<size;i++){
			    					copyCus.remove(mid);
			    				}
				    			break; 
				    		}
			    		}
			    		break; 
			    	}
			    	if(mid>=listSize){
		    			copyCus.clear();
		    		}
			    }else if(ope.equals(">") ){
			    		while(true){
			    			if(mid<listSize){
				    			if(comp.compare(copyCus.get(mid) , value)==0){
				    				mid += 1;
				    			}
				    			if(comp.compare(copyCus.get(mid) , value)==1){
				    				for(int i = 0;i<mid;i++){
				    					copyCus.remove(0);
				    				}
				    				break;
				    			}
				    		}
			    			break; 
			    		}
			    		if(mid>=listSize){
			    			copyCus.clear();
			    		}
			    }else if(ope.equals("=") || ope.equals("~")){
			    	int min =mid, max = mid;
				    	while(true){
				    		if(max<listSize){
					    		if(comp.compare(copyCus.get(max) , value)==0){
					    			max += 1;
					    			continue;
					    		}
					    		if(comp.compare(copyCus.get(max) , value)==1){
					 				break;
					    		}
					    	}
				    		break;
				    	}
					    while(true){
					    	if(min>=0){
					    		if(comp.compare(value , copyCus.get(min))==0){
					    				min -= 1;
					    				continue;
					    			}
					    		if(comp.compare(value , copyCus.get(min))==1){
					    			min += 1;
					    			break;
					    		}
					    	}
						   	break;
					    }
					    if(ope.equals("~")){
					    	if(min==max) max += 1;
						   	for(int i = 0;i<min;i++){
			    				copyCus.remove(0);
			    				max -=1;
			    			}
					    }else{
						   	for(int i = 0;i<min;i++){
			    				copyCus.remove(0);
			    				max -=1;
			    			}
					    }
			    		int size = copyCus.size();
		    			for(int i = max;i<size;i++){
    						copyCus.remove(max);
    					}
			    }
			    
	}
}
