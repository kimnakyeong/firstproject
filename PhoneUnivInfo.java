package phonebook;

public class PhoneUnivInfo extends PhoneInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1912100469096497435L;
	String major;
	int year; // «–≥‚¿∫ year
	public PhoneUnivInfo (String name ,String phonenumber ,String major ,int year) {
		super(name, phonenumber);
		this.major = major;
		this.year = year;
	}
	@Override
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major: "+major);
		System.out.println("year: "+year);
	}
	@Override
	public String toString() {
		return super.toString()+"/n major: "+major+"/n year: "+year;
	}

}
