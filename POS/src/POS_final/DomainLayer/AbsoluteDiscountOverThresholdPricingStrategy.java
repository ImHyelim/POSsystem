package POS_final.DomainLayer;

public class AbsoluteDiscountOverThresholdPricingStrategy implements ISalePricingStrategy{

	Money discount;
	Money threshold;
	
	AbsoluteDiscountOverThresholdPricingStrategy(Money discount, Money threshold){
		this.discount=discount;
		this.threshold=threshold;
		System.out.println("AbsoluteDiscount>"+discount);
	}
	
	@Override
	public Money getTotal(Sale s) {
		// TODO Auto-generated method stub
		
		if(s.getTaxTotal().getAmount()>threshold.getAmount())//������ threshold�̻��̶��
		{	
			return s.getTaxTotal().minus(discount);//����
		}
		
		else
		{	
			return s.getTaxTotal();}
	}
	
}
