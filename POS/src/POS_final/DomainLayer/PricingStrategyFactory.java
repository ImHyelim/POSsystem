package POS_final.DomainLayer;

public class PricingStrategyFactory {
	static final int BestForCustomer = 1;
	static final int BestForStore = 2;
	
	private static PricingStrategyFactory instance=new PricingStrategyFactory();//������ �ʱ�ȭ
	
	int currentStrategyType;
	
	public static PricingStrategyFactory getInstance(){//�̱���
		return instance;
	}
	public void setPricingStrategyType(int strategyType){//���� ��ư ���ý� ȣ��
		//PricingStrategy Ÿ���� �����ϴ� �޼ҵ�
		//currentStrategyType �Ӽ��� strategyType�� ��Ƶ�
		currentStrategyType = strategyType;
		System.out.println("Strategy Factory>"+currentStrategyType);
	}
	public ISalePricingStrategy getSalePricingStrategy(){//��ü ���� �� ����
		if(currentStrategyType==BestForCustomer){
			return new CompositeBestForCustomerPricingStrategy();
		}
		else{
			return new CompositeBestForStorePricingStrategy();
		}
			
	}
}
