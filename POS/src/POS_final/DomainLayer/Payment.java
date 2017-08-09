package POS_final.DomainLayer;

public class Payment {
	private Money amount;
	public Payment(Money cashTendered){//»ı¼ºÀÚ
		this.amount=cashTendered;
	}
	public Money getAmount(){
		return amount;
	}

}
