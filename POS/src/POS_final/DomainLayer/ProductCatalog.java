package POS_final.DomainLayer;
import java.util.HashMap;
import java.util.Map;

//DB
import java.sql.*;

import POS_final.PresentationLayer.ProcessSaleJFrame;
public class ProductCatalog {
	private Map<String,ProductDescription> descriptions=new HashMap<String,ProductDescription>();//String�� ������ ���ϴ� �޼ҵ尡 �̹� �ִ�.
	
	//DB���� �Ӽ���
	static private Connection myConnection;
	static private Statement myStatement;
	static private ResultSet myResultSet;
	
	public ProductCatalog(){//������
		
		String databaseDriver="sun.jdbc.odbc.JdbcOdbcDriver";
		String databaseURL="jdbc:odbc:POS";
		
		loadProdSpecs(databaseDriver,databaseURL);	
	}
	public ProductDescription getProductDescription(ItemID id){
		return descriptions.get(id.toString());
	}
	public void loadProdSpecs(String databaseDriver,String databaseURL){
		
		//DB(POS.mdb)�κ��� ��ǰ ���� �����͸� �о� �鿩
		// �����ͺ��̽� ����̹� �ε�
				// Class.forName(��Ű��.����̹�Ŭ�����̸�);
				// Class.forName(sun.jdbc.obdc.jdbcOdbcDriver);

				try {
					Class.forName(databaseDriver);// ���ڸ� �̿��Ͽ� ����

					// �����ͺ��̽� ����
					// myConnection=DriverManager.getConnection(�����ͺ��̽�URL);
					// myConnection=DriverManager.getConnection(jdbc:odbc;ATM);
					myConnection = DriverManager.getConnection(databaseURL);

					// Statement ��ü ����(SQL �� ������ ���ؼ�)
					myStatement = myConnection.createStatement();
					
					//SQL�� ����
					myResultSet = myStatement.executeQuery("select * from ProductDescriptions");
					// ResultSet ó��
					while (myResultSet.next()) {
						
						ItemID id=new ItemID(myResultSet.getInt("itemId"));
						String description=myResultSet.getString("description");
						Money price=new Money(myResultSet.getInt("price"));
						
						System.out.println("des: "+id);
						
						//ComboBox
						ProcessSaleJFrame.jComboBoxItemID.addItem(id);
						
						//Desc
						ProductDescription desc;
						desc=new ProductDescription(id,price,description);
						//2. discriptions�� ���� ��ü�� �ִ´�.
						descriptions.put(id.toString(), desc);
					}
				} catch (ClassNotFoundException e) {// Class.forName
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {// myConnection
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
