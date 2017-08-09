package POS_final.DomainLayer;

public interface PropertyListener {
	public void onPropertyEvent(Sale source, String name, Money value);
}
