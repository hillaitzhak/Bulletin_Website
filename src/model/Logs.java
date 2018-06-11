package model;

public class Logs {
	private int id;
	private String changedBy;
	private String date;
	private String change;
	
	public Logs (String changedBy, String date, String change){
		super();
		
		this.changedBy = changedBy;
		this.change = change;
		this.date = date;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
	
	 
}
