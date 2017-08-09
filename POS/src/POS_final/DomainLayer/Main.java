package POS_final.DomainLayer;

import POS_final.PresentationLayer.ProcessSaleJFrame;

//// run: java ATM sun.jdbc.odbc.JdbcOdbcDriver jdbc:odbc:ATM

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//초기 도메인 객체 생성
		Store store=new Store();
		
		//컨트롤러 획득
		Register register=store.getRegister();
		new ProcessSaleJFrame(register);
		/*
		//1) makeNewSale()
		register.makeNewSale();
		
		//2) enteritem()
		//새우깡(2000원짜리) 5봉지
		register.enterItem(new ItemID(100), 5);
		
		//신라면(1000원짜리) 10봉지
		register.enterItem(new ItemID(200), 10);
		
		//3) endSale()
		register.endSale();
		
		//4) makePayment()
		register.makePayment(new Money(50000));
		
		//잔돈확인
		//50000-20000=30000원이 나와야 맞다
		
		//Sale객체를 얻음
		Sale sale=register.getSale();
		
		//Sale객체의 getBalance() 호출
		System.out.println("잔돈: "+sale.getBalance()+"원");*/
		
	}

}
