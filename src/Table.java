/**
 * 
 * @author orcahm
 * this class is holding informations about customer which taken from data.csv file.
 *
 */
public class Table{

	private long customerCID;
	private String name;
	private String lastName;
	private String city;
	private String adderees;
	private long ssNo;

	public Table(String[] splitLine) {
		
		this.customerCID= Long.parseLong(splitLine[0]);
		this.name=splitLine[1];
		this.lastName=splitLine[2];
		this.city=splitLine[3];
		this.adderees=splitLine[4];
		this.ssNo=Long.parseLong(splitLine[5]);
	}
	
	// in compare method needs two table object
	// for where comparing in here we create objects
	public Table(String value,String comment){
		if(comment.equals("CID")){		
			this.customerCID = Long.parseLong(value);
		}else if(comment.equals("FirstName")){
			this.name = value;
		}else if(comment.equals("LastName")){
			this.lastName = value;
		}else if(comment.equals("City")){
			this.city = value;
		}else if(comment.equals("AddressLine1")){
			this.adderees = value;
		}else this.ssNo = Long.parseLong(value);
		
	}

	public long getCustomerCID() {
		return customerCID;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String getAdderees() {
		return adderees;
	}

	public long getSsNo() {
		return ssNo;
	}

}
