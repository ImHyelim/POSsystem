package POS_final.DomainLayer;

public class Money {
	private int amount;
	
	public Money(){
		//amount=0;
		this(0);//0을 인자로 하는 Money생성자 호출
	}
	public Money(int amount){
		this.amount=amount;
	}
	public void add(Money m){
		amount=amount+m.getAmount();
	}
	public Money minus(Money m){
		//amount=amount-m.getAmount();//현재돈을 빼는게 아니라
		return new Money(amount-m.getAmount());//차액을 리턴
	}
	public Money times(int m){
		//amount=amount*m.getAmount();
		return new Money(amount*m);
	}
	public int getAmount(){
		return amount;
	}
	public String toString(){
		return String.valueOf(amount);//amount가 String으로 바뀌어 return
	}
	//추가
	public Money min(Money m){//작은 쪽을 리턴한다.
		System.out.println(""+m.getAmount());
		
		if(amount<m.getAmount())
			return this;
		else
			return m;
	}
	public Money max(Money m){//작은 쪽을 리턴한다.
		if(amount>m.getAmount())
			return this;
		else
			return m;
	}
}
