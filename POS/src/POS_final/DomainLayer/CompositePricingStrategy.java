package POS_final.DomainLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//복합 할인 전략
public abstract class CompositePricingStrategy implements ISalePricingStrategy {

	// 각각의 할인 전략을 모아둘 컬렉션 필요
	protected List strategies = new ArrayList();

	//할인 전략을 추가하는 메소드
	public void add(ISalePricingStrategy s){
		strategies.add(s);
		System.out.println("add "+strategies.size());
	}
	
	//할인 후의 가격을 구하는 메소드: 최고가/최저가 선택이 다르므로 하위에서 구현하자
	public abstract Money getTotal(Sale s);

}
