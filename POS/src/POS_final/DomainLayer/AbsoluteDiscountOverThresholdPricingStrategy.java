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
		
		if(s.getTaxTotal().getAmount()>threshold.getAmount())//가격이 threshold이상이라면
		{	
			return s.getTaxTotal().minus(discount);//할인
		}
		
		else
		{	
			return s.getTaxTotal();}
	}
	
}
