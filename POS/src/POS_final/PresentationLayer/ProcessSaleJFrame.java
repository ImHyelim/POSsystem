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
	
	//�߰�
	private GridBagConstraints c;
	
	public Container contentPane=getContentPane();
	public JButton jButtonMakeNewSale=new JButton("1. Make New Sale");
	public JLabel jLabelItemID=new JLabel("Item ID:");
	
	public JLabel jLabelQuantity=new JLabel("Quantity:");
	public static JTextField jTextFieldQuantity=new JTextField(8);
	public static JButton jButtonEnterItem=new JButton("2. Enter Item(�ݺ�)");
	
	public static JButton jButtonEndSale=new JButton("3. End Sale()");
	public JLabel jLabelTotal=new JLabel("Total after Discount:");
	public static JTextField jTextFieldTotal=new JTextField(5);
	public JLabel jLabelAmount=new JLabel("Amount:");
	public static JTextField jTextFieldAmount=new JTextField(5);
	public static JButton jButtonMakePayment=new JButton("6. Make Payment");
	public JLabel jLabelBalance=new JLabel("Balance");
	public static JTextField jTextFieldBalance=new JTextField(5);
	
	//���� �߰��� GUI������Ʈ��...
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
		setTitle("POS System(�й�: 20131091   �̸�: ������)");
		setVisible(true);
	}
	
	private void initGUI(){
		//��ġ������ ����
		//contentPane.setLayout(new FlowLayout());
		
		//���ο� ��ġ������
		contentPane.setLayout(new GridBagLayout());
		c=new GridBagConstraints();
		c.weightx=1.0;
		c.weighty=1.0;
		c.fill=GridBagConstraints.BOTH;

		//radio �׷켳��
		taxgroup.add(Tax_Master);
		taxgroup.add(Tax_Good);
		
		totalgroup.add(total_Customer);
		totalgroup.add(total_Store);

		//GUI ������Ʈ �ޱ�2
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
		
		//desc �ʱⰪ
		jTextFieldDesc.setText("SaeWooKang");
		
		//������ ���
		jButtonMakeNewSale.addActionListener(this);
		jButtonEnterItem.addActionListener(this);
		jButtonEndSale.addActionListener(this);
		jButtonMakePayment.addActionListener(this);
		
		//�����ʵ��2
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
				if(jTextFieldAmount.getText().matches("^[0-9]*$")){//quantity�� ���ڸ� ���ԵǾ��ִٸ�
					//MakePayment Ȱ��ȭ
					jButtonMakePayment.setEnabled(true);
					}else{
						JOptionPane.showMessageDialog(null, "�߸��� �Է�","���",JOptionPane.WARNING_MESSAGE);
					jTextFieldAmount.setText("");
					jTextFieldAmount.requestFocus();
					jTextFieldContent.append("�߸��� ���ұݾ� �Է�\n");
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
	public void layout(Component obj,int x,int y,int width,int height,int type){//contentpane�� add�ϴ� �Լ�
		c.gridx=x;
		c.gridy=y;
		c.gridwidth=width;
		c.gridheight=height;
		
		if(obj.equals(jButtonMakeNewSale)||obj.equals(jScrollContent)){
			c.insets=new Insets(30,15,2,15);//�����Ͽ쿩��
		}else{//label �� ��Ÿ...
			c.insets=new Insets(2,15,2,15);//�����Ͽ쿩��
		}

		add(obj,c);
		
		if(type==0)//���� �� ��Ȱ��ȭ
		obj.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jButtonMakeNewSale){//MakeNewSale
			System.out.println("Make New Sale ��ư Ŭ�� -> register�� ����");
			jTextFieldContent.append("�� �ǸŰ� ���۵Ǿ����ϴ�.\n");
			register.makeNewSale();
			register.setActiveGUI(1);
			//observer
			initialize(register.getSale());
			count=0;
		}else if(e.getSource()==jButtonEnterItem){//enterItem
			System.out.println("Enter Item��ư Ŭ��->register�� ����");
			//quantity�� ���ڰ� ���ԵǾ��ִٸ� ���� �������� ���â
			if(jTextFieldQuantity.getText().matches("^[0-9]*$")&&!jTextFieldQuantity.getText().equals("")){//quantity�� ���ڸ� ���ԵǾ��ִٸ�
				//����ڰ� �Է��� id�� quantity���
				int id=Integer.parseInt(jComboBoxItemID.getSelectedItem().toString());
				//int id=Integer.parseInt(jTextFieldItemID.getText());
				int quantity=Integer.parseInt(jTextFieldQuantity.getText());
				String desc=jTextFieldDesc.getText();
				//��Ʈ�ѷ����� ����
				register.enterItem(new ItemID(id), quantity);
				//Text ���
				jTextFieldContent.append(desc+"/ id: "+id+" ����: "+quantity+"->"+register.getSale().getSubSale(count)+"��\n");
				//���ݱ��� �հ� ���
				count++;
				register.setActiveGUI(2);
			}else{
				JOptionPane.showMessageDialog(null, "�߸��� �Է�","���",JOptionPane.WARNING_MESSAGE);
				jTextFieldQuantity.setText("");
				jTextFieldQuantity.requestFocus();
				jTextFieldContent.append("�߸��� �����Է�\n");
			}
		}else if(e.getSource()==jButtonEndSale){
			System.out.println("EndSale��ư Ŭ��->register�� ����");
			//��Ʈ�ѷ����� ����
			register.endSale();
			//Sale��ü ����
			Sale sale=register.getSale();
			jTextFieldContent.append("���ž����� �հ�: "+sale.getTotal()+"\n");
			jTextFieldCurrentTotal.setText(""+sale.getTotal());//Total
			register.setActiveGUI(3);
		}else if(e.getSource()==jButtonCalculateTax){//Tax ���ù�ư
			jTextFieldContent.append("Tax�������: ");
			
			if(Tax_Master.isSelected()==true){
				jTextFieldContent.append("Tax_Master->");
			}
			else if(Tax_Good.isSelected()==true){
				jTextFieldContent.append("Tax_Good->");
			}else{//�Ѵ� �������� �ʾ��� ���				
				return;
			}			
			//����� ��ü���� ���� ����ϴ� ���� ��Ų��
			//����������...
			register.calculateTax();
			Sale sale=register.getSale();
			jTextFieldToal_Tax.setText(""+sale.getTaxTotal());
			jTextFieldContent.append(""+sale.getTaxTotal()+"��\n");
			register.setActiveGUI(4);
		}else if(e.getSource()==jButtonApplyDiscount){//ApplayDiscount
			jTextFieldContent.append("������������: ");
			if(total_Customer.isSelected()==true){
				jTextFieldContent.append("BestForCustomer->");
			}
			else if(total_Store.isSelected()==true){
				jTextFieldContent.append("BestForStore->");
			}else{//�Ѵ� �������� �ʾ��� ���				
				return;
			}
			//Ȱ��ȭ
			register.setActiveGUI(5);
			//register���� Discount
			register.applyDiscount();
			Sale sale=register.getSale();
			jTextFieldTotal.setText(""+sale.getDiscountTotal());
			jTextFieldContent.append(""+sale.getDiscountTotal()+"��\n");
		}else if(e.getSource()==jButtonMakePayment){
			System.out.println("jButtonMakePayment��ư Ŭ��->register�� ����");

			//����ڰ� �Է��� Amount���
			int amount=Integer.parseInt(jTextFieldAmount.getText());
			int total=Integer.parseInt(jTextFieldTotal.getText());
			if(amount<total)
				JOptionPane.showMessageDialog(null, "�ݾ� ����","���",JOptionPane.WARNING_MESSAGE);
			else{
				//��Ʈ�ѷ����� ����
				register.makePayment(new Money(amount));
				//Sale��ü ����
				Sale sale=register.getSale();
				jTextFieldContent.append("���� �ݾ�: "+amount);
				jTextFieldContent.append("\n�Ž�����"+sale.getBalance()+"\n");
				jTextFieldBalance.setText(""+sale.getBalance());//Balance
				register.setActiveGUI(6);
			}
		}
	}
	//����Ŭ���� ItmeListener
	class RadioItemListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {//������ ��
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
