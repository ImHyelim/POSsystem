package POS_final.DomainLayer;

public class ProductDescription {
	private String description;
	private Money price;
	private ItemID itemId;
	
	public ProductDescription(ItemID id, Money price, String discription){//»ý¼ºÀÚ
		this.itemId=id;
		this.price=price;
		this.description=discription;
	}
	public ItemID getItemID(){
		return itemId;
	}
	public Money getPrice(){
		return price;
	}
	public String getDescription(){
		return description;
	}
	
}
