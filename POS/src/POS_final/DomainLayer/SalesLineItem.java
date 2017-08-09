package POS_final.DomainLayer;

public class SalesLineItem {
	private int quantity;
	private ProductDescription description;//ProductSpecification 
	
	public SalesLineItem(ProductDescription desc, int qty){
		this.description=desc;
		this.quantity=qty;
	}
	/*public void makeLineItem(String desc, int qty){
		//1.SalesLineItem����
		//2.LineItems�� �ִ´�.
	}*/
	
	public Money getSubtotal(){
		return description.getPrice().times(quantity);
	}
}
