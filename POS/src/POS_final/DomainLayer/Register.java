package POS_final.DomainLayer;

import POS_final.PresentationLayer.ProcessSaleJFrame;

public class Register {
	private ProductCatalog catalog;
	private Sale currentSale;
	
	//추가
	ITaxCalculatorAdapter taxCalculatorAdapter;
	static final int makeNewSaleNum=1;
	static final int enterItemNum=2;
	static final int endSaleNum=3;
	static final int calculateTaxNum=4;
	static final int applyDiscountNum=5;
	static final int makePaymentNum=6;
	private int state;
	
	public Register(ProductCatalog catalog){
		this.catalog=catalog;
	}
	public void endSale(){
		currentSale.becomeComplete();
	}
	public void enterItem(ItemID id, int quantity){
		ProductDescription desc=catalog.getProductDescription(id);
		currentSale.makeLineItem(desc, quantity);
		currentSale.setTotal(currentSale.getTotal());
	}
	public void makeNewSale(){
		currentSale=new Sale();
	}
	public void makePayment(Money cashTendered){
		currentSale.makePayment(cashTendered);
	}
	public Sale getSale(){
		return currentSale;
	}
	//추가
	public void calculateTax(){//세금 계산
		//세금 어댑터 생성
		taxCalculatorAdapter=ServicesFactory.getInstance().getTaxCalculatorAdapter();
		//내생각에는...: 일을 시킨다.
		taxCalculatorAdapter.getTaxes(currentSale);//return Money
	}
	public void setPricingStrategyType(int strategyType){//PricingStrategyFactory의 생성클래스 종류정하기
		PricingStrategyFactory.getInstance().setPricingStrategyType(strategyType);
	}
	public void applyDiscount(){//할인전략적용 계산
		currentSale.getDiscountTotal();
	}
	public void setActiveGUI(int num){
		state=num;
		if(state==makeNewSaleNum){
			//이전 계산내용
			ProcessSaleJFrame.jComboBoxItemID.setSelectedIndex(0);
			ProcessSaleJFrame.jTextFieldQuantity.setText("");
			ProcessSaleJFrame.total_Customer.setSelected(false);
			ProcessSaleJFrame.total_Store.setSelected(false);
			ProcessSaleJFrame.jTextFieldCurrentTotal.setText("");
			ProcessSaleJFrame.Tax_Master.setSelected(false);
			ProcessSaleJFrame.Tax_Good.setSelected(false);
			ProcessSaleJFrame.jTextFieldToal_Tax.setText("");
			ProcessSaleJFrame.jTextFieldDesc.setText("");
			ProcessSaleJFrame.jTextFieldTotal.setText("");
			ProcessSaleJFrame.jTextFieldDesc.setText("SaeWooKang");
			ProcessSaleJFrame.jTextFieldAmount.setText("");
			ProcessSaleJFrame.jTextFieldBalance.setText("");
			//활성화
			ProcessSaleJFrame.jButtonMakePayment.setEnabled(false);
			ProcessSaleJFrame.jButtonEnterItem.setEnabled(true);
			ProcessSaleJFrame.jComboBoxItemID.setEnabled(true);
			ProcessSaleJFrame.jTextFieldQuantity.setEnabled(true);
		}else if(state==enterItemNum){
			ProcessSaleJFrame.jButtonEndSale.setEnabled(true);
		}else if(state==endSaleNum){
			//활성화
			ProcessSaleJFrame.jButtonEnterItem.setEnabled(false);
			ProcessSaleJFrame.jComboBoxItemID.setEnabled(false);
			ProcessSaleJFrame.jTextFieldQuantity.setEnabled(false);
			ProcessSaleJFrame.Tax_Master.setEnabled(true);
			ProcessSaleJFrame.Tax_Good.setEnabled(true);
		}else if(state==calculateTaxNum){
			//활성화
			ProcessSaleJFrame.jButtonEndSale.setEnabled(false);
			ProcessSaleJFrame.Tax_Master.setEnabled(false);
			ProcessSaleJFrame.Tax_Good.setEnabled(false);
			ProcessSaleJFrame.total_Customer.setEnabled(true);
			ProcessSaleJFrame.total_Store.setEnabled(true);
		}else if(state==applyDiscountNum){
			ProcessSaleJFrame.jButtonApplyDiscount.setEnabled(false);
			ProcessSaleJFrame.jButtonCalculateTax.setEnabled(false);
			ProcessSaleJFrame.total_Customer.setEnabled(false);
			ProcessSaleJFrame.total_Store.setEnabled(false);
			ProcessSaleJFrame.jTextFieldAmount.setEnabled(true);
		}else if(state==makePaymentNum){
			//활성화
			ProcessSaleJFrame.jButtonApplyDiscount.setEnabled(false);
			ProcessSaleJFrame.jTextFieldAmount.setEnabled(false);
		}
	}
	public ProductDescription getDescription(ItemID x){
		return catalog.getProductDescription(x);
	}
}
