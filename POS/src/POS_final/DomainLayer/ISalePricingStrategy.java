package POS_final.DomainLayer;

public interface ISalePricingStrategy {
	//���� ������ ����ϴ� �޼ҵ�: ���� �� ���� ����
	public Money getTotal(Sale s);
}
