package phonebook;

public class PhoneCompanyInfo extends PhoneInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4884264668762168040L;
	String company;
	public PhoneCompanyInfo(String name, String phonenumber, String company) {
		super(name, phonenumber);
		this.company = company;
	}
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("company: "+company);
	}
	@Override
	public String toString() {
		return super.toString()+"/n company: "+company;
	}
	
}
