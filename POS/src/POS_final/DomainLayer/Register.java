package POS_final.DomainLayer;

import POS_final.PresentationLayer.ProcessSaleJFrame;

public class Register {
	private ProductCatalog catalog;
	private Sale currentSale;
	
	//�߰�
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
	//�߰�
	public void calculateTax(){//���� ���
		//���� ����� ����
		taxCalculatorAdapter=ServicesFactory.getInstance().getTaxCalculatorAdapter();
		//����������...: ���� ��Ų��.
		taxCalculatorAdapter.getTaxes(currentSale);//return Money
	}
	public void setPricingStrategyType(int strategyType){//PricingStrategyFactory�� ����Ŭ���� �������ϱ�
		PricingStrategyFactory.getInstance().setPricingStrategyType(strategyType);
	}
	public void applyDiscount(){//������������ ���
		currentSale.getDiscountTotal();
	}
	public void setActiveGUI(int num){
		state=num;
		if(state==makeNewSaleNum){
			//���� ��곻��
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
			//Ȱ��ȭ
			ProcessSaleJFrame.jButtonMakePayment.setEnabled(false);
			ProcessSaleJFrame.jButtonEnterItem.setEnabled(true);
			ProcessSaleJFrame.jComboBoxItemID.setEnabled(true);
			ProcessSaleJFrame.jTextFieldQuantity.setEnabled(true);
		}else if(state==enterItemNum){
			ProcessSaleJFrame.jButtonEndSale.setEnabled(true);
		}else if(state==endSaleNum){
			//Ȱ��ȭ
			ProcessSaleJFrame.jButtonEnterItem.setEnabled(false);
			ProcessSaleJFrame.jComboBoxItemID.setEnabled(false);
			ProcessSaleJFrame.jTextFieldQuantity.setEnabled(false);
			ProcessSaleJFrame.Tax_Master.setEnabled(true);
			ProcessSaleJFrame.Tax_Good.setEnabled(true);
		}else if(state==calculateTaxNum){
			//Ȱ��ȭ
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
			//Ȱ��ȭ
			ProcessSaleJFrame.jButtonApplyDiscount.setEnabled(false);
			ProcessSaleJFrame.jTextFieldAmount.setEnabled(false);
		}
	}
	public ProductDescription getDescription(ItemID x){
		return catalog.getProductDescription(x);
	}
}
