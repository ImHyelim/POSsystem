package POS_final.PresentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import POS_final.DomainLayer.ItemID;
import POS_final.DomainLayer.Money;
import POS_final.DomainLayer.PropertyListener;
import POS_final.DomainLayer.Register;
import POS_final.DomainLayer.Sale;


public class ProcessSaleJFrame extends JFrame implements ActionListener, PropertyListener{
	
	//추가
	private GridBagConstraints c;
	
	public Container contentPane=getContentPane();
	public JButton jButtonMakeNewSale=new JButton("1. Make New Sale");
	public JLabel jLabelItemID=new JLabel("Item ID:");
	
	public JLabel jLabelQuantity=new JLabel("Quantity:");
	public static JTextField jTextFieldQuantity=new JTextField(8);
	public static JButton jButtonEnterItem=new JButton("2. Enter Item(반복)");
	
	public static JButton jButtonEndSale=new JButton("3. End Sale()");
	public JLabel jLabelTotal=new JLabel("Total after Discount:");
	public static JTextField jTextFieldTotal=new JTextField(5);
	public JLabel jLabelAmount=new JLabel("Amount:");
	public static JTextField jTextFieldAmount=new JTextField(5);
	public static JButton jButtonMakePayment=new JButton("6. Make Payment");
	public JLabel jLabelBalance=new JLabel("Balance");
	public static JTextField jTextFieldBalance=new JTextField(5);
	
	//새로 추가된 GUI컴포넌트들...
	public JTextArea jTextFieldContent=new JTextArea(20,30);
	public JScrollPane jScrollContent=new JScrollPane(jTextFieldContent);
	public JLabel jLabelDesc=new JLabel("Description:");
	public static JTextField jTextFieldDesc=new JTextField(5);
	public JLabel jLabelCurrentTotal=new JLabel("CurrentTotal:");
	public static JTextField jTextFieldCurrentTotal=new JTextField(5);
	public ButtonGroup taxgroup=new ButtonGroup();
	public static JRadioButton Tax_Master=new JRadioButton("TaxMaster");
	public static JRadioButton Tax_Good=new JRadioButton("GoodAsGoldTaxPro");
	public static JButton jButtonCalculateTax=new JButton("4. calculateTax()");
	public JLabel jLabelToal_Tax=new JLabel("Total with Tax:");
	public static JTextField jTextFieldToal_Tax=new JTextField(5);
	public ButtonGroup totalgroup=new ButtonGroup();
	public static JRadioButton total_Customer=new JRadioButton("BestForCustomer");
	public static JRadioButton total_Store=new JRadioButton("BestForStore");
	public static JButton jButtonApplyDiscount=new JButton("5. applayDiscount()");
	public static JComboBox jComboBoxItemID=new JComboBox();
	
	private Register register;
	private int count;
	
	public ProcessSaleJFrame(Register register){
		this.register=register;
		initGUI();
		pack();
		setTitle("POS System(학번: 20131091   이름: 임혜림)");
		setVisible(true);
	}
	
	private void initGUI(){
		//배치관리자 설정
		//contentPane.setLayout(new FlowLayout());
		
		//새로운 배치관리자
		contentPane.setLayout(new GridBagLayout());
		c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.fill=GridBagConstraints.BOTH;

		//radio 그룹설정
		taxgroup.add(Tax_Master);
		taxgroup.add(Tax_Good);
		
		totalgroup.add(total_Customer);
		totalgroup.add(total_Store);

		//GUI 컴포넌트 달기2
		layout(jButtonMakeNewSale,0,1,2,1,1);
		layout(jLabelItemID,0,2,1,1,1);
		layout(jComboBoxItemID,1,2,1,1,0);	
		layout(jLabelQuantity,0,3,1,1,1);
		layout(jTextFieldQuantity,1,3,1,1,0);
		layout(jLabelDesc,0,4,1,1,1);
		layout(jTextFieldDesc,1,4,1,1,0);
		layout(jButtonEnterItem,0,5,2,1,0);
		layout(jLabelCurrentTotal,0,6,1,1,1);
		layout(jTextFieldCurrentTotal,1,6,1,1,0);
		layout(jButtonEndSale,0,7,2,1,0);
		layout(Tax_Master,0,8,1,1,0);
		layout(Tax_Good,1,8,1,1,0);
		layout(jButtonCalculateTax,0,9,2,1,0);
		layout(jLabelToal_Tax,0,10,1,1,1);
		layout(jTextFieldToal_Tax,1,10,1,1,0);
		layout(total_Customer,0,11,1,1,0);
		layout(total_Store,1,11,1,1,0);
		layout(jButtonApplyDiscount,0,12,2,1,0);	
		layout(jLabelTotal,0,13,1,1,1);
		layout(jTextFieldTotal,1,13,1,1,0);//Total after Discount
		layout(jLabelAmount,0,14,1,1,1);
		layout(jTextFieldAmount,1,14,1,1,0);
		layout(jButtonMakePayment,0,15,2,1,0);
		layout(jLabelBalance,0,16,1,1,1);
		layout(jTextFieldBalance,1,16,1,1,0);
		
		//TextArea
		layout(jScrollContent,3,1,30,20,0);	
		
		//desc 초기값
		jTextFieldDesc.setText("SaeWooKang");
		
		//리스너 등록
		jButtonMakeNewSale.addActionListener(this);
		jButtonEnterItem.addActionListener(this);
		jButtonEndSale.addActionListener(this);
		jButtonMakePayment.addActionListener(this);
		
		//리스너등록2
		jButtonCalculateTax.addActionListener(this);
		jButtonApplyDiscount.addActionListener(this);
		Tax_Master.addItemListener(new RadioItemListener());
		Tax_Good.addItemListener(new RadioItemListener());
		total_Customer.addItemListener(new RadioItemListener());
		total_Store.addItemListener(new RadioItemListener());
		
		jTextFieldAmount.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(jTextFieldAmount.getText().matches("^[0-9]*$")){//quantity에 숫자만 포함되어있다면
					//MakePayment 활성화
					jButtonMakePayment.setEnabled(true);
					}else{
						JOptionPane.showMessageDialog(null, "잘못된 입력","경고",JOptionPane.WARNING_MESSAGE);
					jTextFieldAmount.setText("");
					jTextFieldAmount.requestFocus();
					jTextFieldContent.append("잘못된 지불금액 입력\n");
					return;
					}
			}
			
		});
		
		jComboBoxItemID.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb=(JComboBox)e.getSource();
				ItemID x=(ItemID) cb.getSelectedItem();
				jTextFieldDesc.setText(""+register.getDescription(x).getDescription());
			}
			
		});
		
	}
	public void layout(Component obj,int x,int y,int width,int height,int type){//contentpane에 add하는 함수
		c.gridx=x;
		c.gridy=y;
		c.gridwidth=width;
		c.gridheight=height;
		
		if(obj.equals(jButtonMakeNewSale)||obj.equals(jScrollContent)){
			c.insets=new Insets(30,15,2,15);//상좌하우여백
		}else{//label 등 기타...
			c.insets=new Insets(2,15,2,15);//상좌하우여백
		}

		add(obj,c);
		
		if(type==0)//붙일 때 비활성화
		obj.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jButtonMakeNewSale){//MakeNewSale
			System.out.println("Make New Sale 버튼 클릭 -> register로 전달");
			jTextFieldContent.append("새 판매가 시작되었습니다.\n");
			register.makeNewSale();
			register.setActiveGUI(1);
			//observer
			initialize(register.getSale());
			count=0;
		}else if(e.getSource()==jButtonEnterItem){//enterItem
			System.out.println("Enter Item버튼 클릭->register로 전달");
			//quantity에 문자가 포함되어있다면 내용 지워지고 경고창
			if(jTextFieldQuantity.getText().matches("^[0-9]*$")&&!jTextFieldQuantity.getText().equals("")){//quantity에 숫자만 포함되어있다면
				//사용자가 입력한 id와 quantity얻기
				int id=Integer.parseInt(jComboBoxItemID.getSelectedItem().toString());
				//int id=Integer.parseInt(jTextFieldItemID.getText());
				int quantity=Integer.parseInt(jTextFieldQuantity.getText());
				String desc=jTextFieldDesc.getText();
				//컨트롤러에게 전달
				register.enterItem(new ItemID(id), quantity);
				//Text 출력
				jTextFieldContent.append(desc+"/ id: "+id+" 수량: "+quantity+"->"+register.getSale().getSubSale(count)+"원\n");
				//지금까지 합계 출력
				count++;
				register.setActiveGUI(2);
			}else{
				JOptionPane.showMessageDialog(null, "잘못된 입력","경고",JOptionPane.WARNING_MESSAGE);
				jTextFieldQuantity.setText("");
				jTextFieldQuantity.requestFocus();
				jTextFieldContent.append("잘못된 수량입력\n");
			}
		}else if(e.getSource()==jButtonEndSale){
			System.out.println("EndSale버튼 클릭->register로 전달");
			//컨트롤러에게 전달
			register.endSale();
			//Sale객체 생성
			Sale sale=register.getSale();
			jTextFieldContent.append("구매아이템 합계: "+sale.getTotal()+"\n");
			jTextFieldCurrentTotal.setText(""+sale.getTotal());//Total
			register.setActiveGUI(3);
		}else if(e.getSource()==jButtonCalculateTax){//Tax 선택버튼
			jTextFieldContent.append("Tax방법선택: ");
			
			if(Tax_Master.isSelected()==true){
				jTextFieldContent.append("Tax_Master->");
			}
			else if(Tax_Good.isSelected()==true){
				jTextFieldContent.append("Tax_Good->");
			}else{//둘다 선택하지 않았을 경우				
				return;
			}			
			//어댑터 객체에게 세금 계산하는 일을 시킨다
			//내생각에는...
			register.calculateTax();
			Sale sale=register.getSale();
			jTextFieldToal_Tax.setText(""+sale.getTaxTotal());
			jTextFieldContent.append(""+sale.getTaxTotal()+"원\n");
			register.setActiveGUI(4);
		}else if(e.getSource()==jButtonApplyDiscount){//ApplayDiscount
			jTextFieldContent.append("할인전략선택: ");
			if(total_Customer.isSelected()==true){
				jTextFieldContent.append("BestForCustomer->");
			}
			else if(total_Store.isSelected()==true){
				jTextFieldContent.append("BestForStore->");
			}else{//둘다 선택하지 않았을 경우				
				return;
			}
			//활성화
			register.setActiveGUI(5);
			//register에서 Discount
			register.applyDiscount();
			Sale sale=register.getSale();
			jTextFieldTotal.setText(""+sale.getDiscountTotal());
			jTextFieldContent.append(""+sale.getDiscountTotal()+"원\n");
		}else if(e.getSource()==jButtonMakePayment){
			System.out.println("jButtonMakePayment버튼 클릭->register로 전달");

			//사용자가 입력한 Amount얻기
			int amount=Integer.parseInt(jTextFieldAmount.getText());
			int total=Integer.parseInt(jTextFieldTotal.getText());
			if(amount<total)
				JOptionPane.showMessageDialog(null, "금액 부족","경고",JOptionPane.WARNING_MESSAGE);
			else{
				//컨트롤러에게 전달
				register.makePayment(new Money(amount));
				//Sale객체 생성
				Sale sale=register.getSale();
				jTextFieldContent.append("지불 금액: "+amount);
				jTextFieldContent.append("\n거스름돈"+sale.getBalance()+"\n");
				jTextFieldBalance.setText(""+sale.getBalance());//Balance
				register.setActiveGUI(6);
			}
		}
	}
	//내부클래스 ItmeListener
	class RadioItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {//눌렀을 때
			// TODO Auto-generated method stub
				if(Tax_Master.isSelected()){
					jButtonCalculateTax.setEnabled(true);
					System.setProperty("taxcalculator.class.name", "POS_final.DomainLayer.TaxMasterAdapter");
				}	
				else if(Tax_Good.isSelected()){
					jButtonCalculateTax.setEnabled(true);
					System.setProperty("taxcalculator.class.name", "POS_final.DomainLayer.GoodAsGoldTaxProAdapter");
				}
				
			if(total_Customer.isSelected()){
				jButtonApplyDiscount.setEnabled(true);
				register.setPricingStrategyType(1);
			}
			else if(total_Store.isSelected()){
				jButtonApplyDiscount.setEnabled(true);
				register.setPricingStrategyType(2);
			}
		}
		
	}
	@Override
	public void onPropertyEvent(Sale source, String name, Money value) {
		// TODO Auto-generated method stub
		if(name.equals("sale.total"))
			jTextFieldCurrentTotal.setText(value.toString());
	}
	public void initialize(Sale sale){
		sale.addPropertyListener(this);
	}
}
