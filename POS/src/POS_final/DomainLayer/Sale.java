package POS_final.DomainLayer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Sale {
	private List<SalesLineItem> lineItems=new ArrayList<SalesLineItem>();
	private Boolean isComplete=false;
	private Date date=new Date();
	private ProductCatalog catalog;
	private Register register;
	private Payment payment;
	
	//추가
	private List<TaxLineItem> TaxlineItems=new ArrayList<TaxLineItem>();
	private ISalePricingStrategy pricingStrategy;
	private List<PropertyListener> propertyListeners=new ArrayList<PropertyListener>();
	public Sale(){
		//1.lineItems를 만든다.
	}
	public Money getBalance(){
		//1. payment에서 getAmount
		//2. getTotal호출
		return payment.getAmount().minus(getDiscountTotal());
	}
	public void becomeComplete(){
		isComplete=true;
	}
	public boolean isComplete(){
		return isComplete;
	}
	public void makeLineItem(ProductDescription desc, int quantity){
		lineItems.add(new SalesLineItem(desc,quantity));
	}
	public Money getTotal(){//currentTotal
		Money total=new Money();
		Money subtotal=null;
		
		for(SalesLineItem lineItem:lineItems){
			subtotal=lineItem.getSubtotal();
			total.add(subtotal);
		}
		return total;
	}
	public void makePayment(Money cashTendred){
		payment=new Payment(cashTendred);
	}
	public void SalesLineItem(){
		
	}
	public Register getRegister(){
		return register;
	}
	//추가
	public List<TaxLineItem> getTaxLineItem(){
		return TaxlineItems;
	}
	public Money getTaxTotal(){//TaxTotal
		Money total=new Money();
		Money subtotal=null;
		for(TaxLineItem lineItem:TaxlineItems){
			subtotal=lineItem.getamount();
			total.add(subtotal);
		}
		return total;
	}
	public Money getDiscountTotal(){
		pricingStrategy=PricingStrategyFactory.getInstance().getSalePricingStrategy();
		return pricingStrategy.getTotal(this);
	}
	public void addPropertyListener(PropertyListener lis){
		propertyListeners.add(lis);
	}
	public void setTotal(Money newTotal){
		//total=newTotal;
		publishPropertyEvent("sale.total",newTotal);
	}
	public void publishPropertyEvent(String name, Money value){//propertyListeners에 통보
		
		for(Iterator i=propertyListeners.iterator();i.hasNext();){
			PropertyListener strategy=(PropertyListener)i.next();
			strategy.onPropertyEvent(this, name, value);
		}
	}
	public Money getSubSale(int index){
		return lineItems.get(index).getSubtotal();
	}
}
