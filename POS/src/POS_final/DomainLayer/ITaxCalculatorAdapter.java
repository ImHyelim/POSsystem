package POS_final.DomainLayer;

import java.util.List;

public interface ITaxCalculatorAdapter {
	//5v�� �ش�
	public List<TaxLineItem> getTaxes(Sale s);
}
