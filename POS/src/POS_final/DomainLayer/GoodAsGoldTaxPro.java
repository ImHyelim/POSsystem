package POS_final.DomainLayer;

public class GoodAsGoldTaxPro {

	public GoodAsGoldTaxPro(){
		System.out.println("GoodAsGoldTaxPro");
	}
	
	public Money calculateTax(Sale s){
		//s.total에 세율 20%를 부과한 합계를 반환하도록 작성한다.
				int m=(int) ((s.getTotal().getAmount())*1.2);
				s.getTaxLineItem().add(new TaxLineItem("Tax2",0.2,m));//String x, float p,int a
				
				return new Money(m);
	}
	
}
