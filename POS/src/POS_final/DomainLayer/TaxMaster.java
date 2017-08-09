package POS_final.DomainLayer;

public class TaxMaster {

	public Money calcTax(Sale s){
		//s.total에 세율 10%를 부과한 합계를 반환하도록 작성한다.

		int m=(int) ((s.getTotal().getAmount())*1.1);
		s.getTaxLineItem().add(new TaxLineItem("Tax1",0.1,m));//세금종류추가

		return new Money(m);
	}
}
