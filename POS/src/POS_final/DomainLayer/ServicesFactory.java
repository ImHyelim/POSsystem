package POS_final.DomainLayer;

public class ServicesFactory {
	static ServicesFactory factory = new ServicesFactory();
	ITaxCalculatorAdapter taxCalculatorAdapter = null;

	public static ServicesFactory getInstance() {
		System.out.println("ServiceFactory");
		return factory;
	}

	public ITaxCalculatorAdapter getTaxCalculatorAdapter() {// �ش� ���ݰ��⸦ ����
			String className = System.getProperty("taxcalculator.class.name");
			System.out.println("getProperty: " + className);
			try {
				taxCalculatorAdapter = (ITaxCalculatorAdapter) Class.forName(className).newInstance();
				System.out.println("TaxAdapter����: " + className);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return taxCalculatorAdapter;
	}
}
