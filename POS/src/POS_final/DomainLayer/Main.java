package POS_final.DomainLayer;

import POS_final.PresentationLayer.ProcessSaleJFrame;

//// run: java ATM sun.jdbc.odbc.JdbcOdbcDriver jdbc:odbc:ATM

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//�ʱ� ������ ��ü ����
		Store store=new Store();
		
		//��Ʈ�ѷ� ȹ��
		Register register=store.getRegister();
		new ProcessSaleJFrame(register);
		/*
		//1) makeNewSale()
		register.makeNewSale();
		
		//2) enteritem()
		//�����(2000��¥��) 5����
		register.enterItem(new ItemID(100), 5);
		
		//�Ŷ��(1000��¥��) 10����
		register.enterItem(new ItemID(200), 10);
		
		//3) endSale()
		register.endSale();
		
		//4) makePayment()
		register.makePayment(new Money(50000));
		
		//�ܵ�Ȯ��
		//50000-20000=30000���� ���;� �´�
		
		//Sale��ü�� ����
		Sale sale=register.getSale();
		
		//Sale��ü�� getBalance() ȣ��
		System.out.println("�ܵ�: "+sale.getBalance()+"��");*/
		
	}

}
