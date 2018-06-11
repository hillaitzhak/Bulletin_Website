package model;

public class Bulletin {
	private int id;
	private String product;
	private String openDate;
	private String closeDate;
	private String link;
	private String contact;
	private String cve;
	private String status;
	
	public Bulletin (String product, String openDate, String closeDate, String link, String cve, String contact, String status){
		super();
		
		this.product = product;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.link = link;
		this.contact = contact;
		this.cve = cve;
		this.status = status;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setProduct(String p){
		this.product = p;
	}
	
	public String getProduct(){
		return product;
	}
	
	public void setOpenDate(String OD){
		this.openDate = OD;
	}
	
	public String getOpenDate(){
		return openDate;
	}
	
	public void setCloseDate(String CD){
		this.closeDate = CD;
	}
	
	public String getCloseDate(){
		return closeDate;
	}
	
	public void setLink(String l){
		this.link = l;
	}
	
	public String getLink(){
		return link;
	}
	
	public void setContact(String c){
		this.contact = c;
	}
	
	public String getContact(){
		return contact;
	}
	
	public void setCVE(String c){
		this.cve = c;
	}
	
	public String getCVE(){
		return cve;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public String getStatus(){
		return status;
	}
}
