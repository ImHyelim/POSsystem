package POS_final.DomainLayer;

import java.util.List;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter {

	@Override
	public List<TaxLineItem> getTaxes(Sale s) {
		// TODO Auto-generated method stub
		//���⼭ GoodAsGoldTaxPro���� ������ ���ݰ��޼ҵ带 ȣ��
		
		GoodAsGoldTaxPro tm=new GoodAsGoldTaxPro();
		Money x=tm.calculateTax(s);
		
		
		return null;
	}

}
