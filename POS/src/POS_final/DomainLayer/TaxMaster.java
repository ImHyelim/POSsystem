package POS_final.DomainLayer;

public class TaxMaster {

	public Money calcTax(Sale s){
		//s.total�� ���� 10%�� �ΰ��� �հ踦 ��ȯ�ϵ��� �ۼ��Ѵ�.

		int m=(int) ((s.getTotal().getAmount())*1.1);
		s.getTaxLineItem().add(new TaxLineItem("Tax1",0.1,m));//���������߰�

		return new Money(m);
	}
}
