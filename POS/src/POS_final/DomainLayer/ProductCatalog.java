package POS_final.DomainLayer;
import java.util.HashMap;
import java.util.Map;

//DB
import java.sql.*;

import POS_final.PresentationLayer.ProcessSaleJFrame;
public class ProductCatalog {
	private Map<String,ProductDescription> descriptions=new HashMap<String,ProductDescription>();//String은 같은지 비교하는 메소드가 이미 있다.
	
	//DB관련 속성들
	static private Connection myConnection;
	static private Statement myStatement;
	static private ResultSet myResultSet;
	
	public ProductCatalog(){//생성자
		
		String databaseDriver="sun.jdbc.odbc.JdbcOdbcDriver";
		String databaseURL="jdbc:odbc:POS";
		
		loadProdSpecs(databaseDriver,databaseURL);	
	}
	public ProductDescription getProductDescription(ItemID id){
		return descriptions.get(id.toString());
	}
	public void loadProdSpecs(String databaseDriver,String databaseURL){
		
		//DB(POS.mdb)로부터 제품 명세서 데이터를 읽어 들여
		// 데이터베이스 드라이버 로딩
				// Class.forName(패키지.드라이버클래스이름);
				// Class.forName(sun.jdbc.obdc.jdbcOdbcDriver);

				try {
					Class.forName(databaseDriver);// 인자를 이용하여 생성

					// 데이터베이스 연결
					// myConnection=DriverManager.getConnection(데이터베이스URL);
					// myConnection=DriverManager.getConnection(jdbc:odbc;ATM);
					myConnection = DriverManager.getConnection(databaseURL);

					// Statement 객체 생성(SQL 문 실행을 위해서)
					myStatement = myConnection.createStatement();
					
					//SQL문 시작
					myResultSet = myStatement.executeQuery("select * from ProductDescriptions");
					// ResultSet 처리
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
						//2. discriptions에 만든 객체를 넣는다.
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
