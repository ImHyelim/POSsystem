package POS_final.DomainLayer;

public class GoodAsGoldTaxPro {

	public GoodAsGoldTaxPro(){
		System.out.println("GoodAsGoldTaxPro");
	}
	
	public Money calculateTax(Sale s){
		//s.total�� ���� 20%�� �ΰ��� �հ踦 ��ȯ�ϵ��� �ۼ��Ѵ�.
				int m=(int) ((s.getTotal().getAmount())*1.2);
				s.getTaxLineItem().add(new TaxLineItem("Tax2",0.2,m));//String x, float p,int a
				
				return new Money(m);
	}
	
}
