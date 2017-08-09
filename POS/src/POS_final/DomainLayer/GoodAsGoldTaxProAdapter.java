package POS_final.DomainLayer;

import java.util.List;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter {

	@Override
	public List<TaxLineItem> getTaxes(Sale s) {
		// TODO Auto-generated method stub
		//여기서 GoodAsGoldTaxPro세금 계산기의 세금계산메소드를 호출
		
		GoodAsGoldTaxPro tm=new GoodAsGoldTaxPro();
		Money x=tm.calculateTax(s);
		
		
		return null;
	}

}
