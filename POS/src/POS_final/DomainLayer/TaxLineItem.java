package POS_final.DomainLayer;

public class TaxLineItem {//林技, 楷规技 殿 技陛...
	private String description;//技陛捞抚
	double percentage;//技啦
	Money amount;
	public TaxLineItem(){
		
	}
	public TaxLineItem(String x, double p,int a){
		description=x;
		percentage=p;
		amount=new Money(a);
	}
	public Money getamount(){
		return amount;
	}
	
}
