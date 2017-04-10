import java.util.Comparator;


public class MyComparetor implements Comparator<Table> {

	String compare;
	
	public MyComparetor(String columnName){
		
		this.compare = columnName;
	}

	@Override
	public int compare(Table arg0, Table arg1) {
		if(this.compare.equals("CID")){
			if(arg0.getCustomerCID()>arg1.getCustomerCID()){
				return 1;
			}else
				return 0;
		}else if(this.compare.equals("FirstName")){
			int s1,s2;
			s1 = arg0.getName().length();
			s2 = arg1.getName().length();
			if(s1>s2) s1=s2;
			if(arg0.getName().substring(0,s1).toLowerCase().compareTo(arg1.getName().substring(0, s1).toLowerCase())>0){
				return 1;
			}else if(arg0.getName().substring(0,s1).toLowerCase().compareTo(arg1.getName().substring(0, s1).toLowerCase())<0){
				return -1;
			}
				return 0;
		}else if(this.compare.equals("LastName")){
			int s1,s2;
			s1 = arg0.getLastName().length();
			s2 = arg1.getLastName().length();
			if(s1>s2) s1=s2;
			if(arg0.getLastName().substring(0,s1).toLowerCase().compareTo(arg1.getLastName().substring(0,s1).toLowerCase())>0){
				return 1;
			}else if(arg0.getLastName().substring(0,s1).toLowerCase().compareTo(arg1.getLastName().substring(0,s1).toLowerCase())<0){
				return -1;
			}
				return 0;
		}else if(this.compare.equals("City")){
			int s1,s2;
			s1 = arg0.getCity().length();
			s2 = arg1.getCity().length();
			if(s1>s2) s1=s2;
			if(arg0.getCity().substring(0,s1).toLowerCase().compareTo(arg1.getCity().substring(0,s1).toLowerCase())>0){
				return 1;
			}else if(arg0.getCity().substring(0,s1).toLowerCase().compareTo(arg1.getCity().substring(0,s1).toLowerCase())<0){
				return -1;
			}
				return 0;
		}else if(this.compare.equals("AddressLine1")){
			int s1,s2;
			s1 = arg0.getAdderees().length();
			s2 = arg1.getAdderees().length();
			if(s1>s2) s1=s2;
			if(arg0.getAdderees().substring(0,s1).toLowerCase().compareTo(arg1.getAdderees().substring(0,s1).toLowerCase())>0){
				return 1;
			}else if(arg0.getAdderees().substring(0,s1).toLowerCase().compareTo(arg1.getAdderees().substring(0,s1).toLowerCase())<0){
				return -1;
			}else return 0;
		}else{
			if(arg0.getSsNo()>arg1.getSsNo()){
				return 1;
			}else return 0;
		}
		
	}
}
