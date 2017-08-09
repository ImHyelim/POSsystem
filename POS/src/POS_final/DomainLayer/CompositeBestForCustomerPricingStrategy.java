package POS_final.DomainLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeBestForCustomerPricingStrategy extends CompositePricingStrategy{//List, add 메소드 상속
	//protected List<ISalePricingStrategy> strategies=new ArrayList<ISalePricingStrategy>();
	public CompositeBestForCustomerPricingStrategy(){//생성자구현
			//전략들을 add한다.
			add(new PercentDiscountPricingStrategy((float) 0.2));
			add(new PercentDiscountPricingStrategy((float) 0.1));
			add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(1000),new Money(20000)));
			add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(500),new Money(15000)));
		}
	@Override
	public Money getTotal(Sale sale) {
		// TODO Auto-generated method stub
		//가지고 있는 전략 중 최저가 리턴

		Money lowestTotal=new Money(Integer.MAX_VALUE);
		
		for(Iterator i=strategies.iterator();i.hasNext();){
			ISalePricingStrategy strategy=(ISalePricingStrategy)i.next();
			Money total=strategy.getTotal(sale);
			lowestTotal=total.min(lowestTotal);
		}
		
		return lowestTotal;
	}

}
