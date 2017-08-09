package POS_final.DomainLayer;

public class SalesLineItem {
	private int quantity;
	private ProductDescription description;//ProductSpecification 
	
	public SalesLineItem(ProductDescription desc, int qty){
		this.description=desc;
		this.quantity=qty;
	}
	/*public void makeLineItem(String desc, int qty){
		//1.SalesLineItem생성
		//2.LineItems에 넣는다.
	}*/
	
	public Money getSubtotal(){
		return description.getPrice().times(quantity);
	}
}
