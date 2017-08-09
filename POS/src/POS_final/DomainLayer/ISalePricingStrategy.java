package POS_final.DomainLayer;

public interface ISalePricingStrategy {
	//할인 가격을 계산하는 메소드: 할인 후 가격 리턴
	public Money getTotal(Sale s);
}
