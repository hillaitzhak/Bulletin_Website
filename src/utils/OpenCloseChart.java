package utils;

public class OpenCloseChart {
	private String product;
	private int close;
	private int open;
	
	public OpenCloseChart(String product, int open, int close) {
		super();
		this.product = product;
		this.close = close;
		this.open = open;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getClose() {
		return close;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}

}
