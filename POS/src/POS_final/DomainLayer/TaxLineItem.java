package POS_final.DomainLayer;

public class TaxLineItem {//�ּ�, ���漼 �� ����...
	private String description;//�����̸�
	double percentage;//����
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
