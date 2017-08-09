package POS_final.DomainLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//���� ���� ����
public abstract class CompositePricingStrategy implements ISalePricingStrategy {

	// ������ ���� ������ ��Ƶ� �÷��� �ʿ�
	protected List strategies = new ArrayList();

	//���� ������ �߰��ϴ� �޼ҵ�
	public void add(ISalePricingStrategy s){
		strategies.add(s);
		System.out.println("add "+strategies.size());
	}
	
	//���� ���� ������ ���ϴ� �޼ҵ�: �ְ�/������ ������ �ٸ��Ƿ� �������� ��������
	public abstract Money getTotal(Sale s);

}
