package POS_final.DomainLayer;

import java.util.List;

public interface ITaxCalculatorAdapter {
	//5v¿¡ ÇØ´ç
	public List<TaxLineItem> getTaxes(Sale s);
}
