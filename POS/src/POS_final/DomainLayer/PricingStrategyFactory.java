package POS_final.DomainLayer;

public class PricingStrategyFactory {
	static final int BestForCustomer = 1;
	static final int BestForStore = 2;
	
	private static PricingStrategyFactory instance=new PricingStrategyFactory();//적극적 초기화
	
	int currentStrategyType;
	
	public static PricingStrategyFactory getInstance(){//싱글턴
		return instance;
	}
	public void setPricingStrategyType(int strategyType){//라디오 버튼 선택시 호출
		//PricingStrategy 타입을 설정하는 메소드
		//currentStrategyType 속성에 strategyType을 담아둠
		currentStrategyType = strategyType;
		System.out.println("Strategy Factory>"+currentStrategyType);
	}
	public ISalePricingStrategy getSalePricingStrategy(){//객체 생성 후 리턴
		if(currentStrategyType==BestForCustomer){
			return new CompositeBestForCustomerPricingStrategy();
		}
		else{
			return new CompositeBestForStorePricingStrategy();
		}
			
	}
}
