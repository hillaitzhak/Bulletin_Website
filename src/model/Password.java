package model;

public class Password {
	
	private String id;
	private int year;
	private int day;
	private int month;
	
	public Password(String id,  int year, int day, int month) {
		super();
		this.id = id;
		this.year = year;
		this.day = day;
		this.month = month;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
