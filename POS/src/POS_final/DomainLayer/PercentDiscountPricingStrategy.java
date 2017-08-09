package POS_final.DomainLayer;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy{
	float percent;
	
	public PercentDiscountPricingStrategy(float percent){
		this.percent=percent;//percent만큼 할인
		System.out.println("PercentDiscount>"+percent);
	}

	@Override
	public Money getTotal(Sale s) {
		// TODO Auto-generated method stub
		Money SalePrice=new Money((int)((s.getTaxTotal().getAmount())*(1-percent)));//percent가격
		return SalePrice;//percent할인
	}
}
