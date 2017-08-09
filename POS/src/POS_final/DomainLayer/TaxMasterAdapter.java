package POS_final.DomainLayer;

import java.util.ArrayList;
import java.util.List;

public class TaxMasterAdapter implements ITaxCalculatorAdapter {
	//List<TaxLineItem> TaxlineItems=new ArrayList<TaxLineItem>();
	
	public TaxMasterAdapter(){
		System.out.println("Tax_Master");
	}

	@Override
	public List<TaxLineItem> getTaxes(Sale s) {
		// TODO Auto-generated method stub
		
		//TaxList
		//List<TaxLineItem> TaxlineItems=new ArrayList<TaxLineItem>();
		
		//여기서 TaxMaster 세금계산기의 calcTax(s)호출
		TaxMaster tm=new TaxMaster();
		Money x=tm.calcTax(s);
		
		//TaxlineItems.add(new TaxLineItem());
		
		return s.getTaxLineItem();
	}

}
