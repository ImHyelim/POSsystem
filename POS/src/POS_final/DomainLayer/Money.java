package POS_final.DomainLayer;

public class Money {
	private int amount;
	
	public Money(){
		//amount=0;
		this(0);//0�� ���ڷ� �ϴ� Money������ ȣ��
	}
	public Money(int amount){
		this.amount=amount;
	}
	public void add(Money m){
		amount=amount+m.getAmount();
	}
	public Money minus(Money m){
		//amount=amount-m.getAmount();//���絷�� ���°� �ƴ϶�
		return new Money(amount-m.getAmount());//������ ����
	}
	public Money times(int m){
		//amount=amount*m.getAmount();
		return new Money(amount*m);
	}
	public int getAmount(){
		return amount;
	}
	public String toString(){
		return String.valueOf(amount);//amount�� String���� �ٲ�� return
	}
	//�߰�
	public Money min(Money m){//���� ���� �����Ѵ�.
		System.out.println(""+m.getAmount());
		
		if(amount<m.getAmount())
			return this;
		else
			return m;
	}
	public Money max(Money m){//���� ���� �����Ѵ�.
		if(amount>m.getAmount())
			return this;
		else
			return m;
	}
}
