package kr.yjc.jcls.pb;

public class PhoneUnivInfo extends PhoneInfo {
	private static final long serialVersionUID = 7789990556084313397L;
	String major;
	int year;

	public PhoneUnivInfo(String name, String num, String major, int year) {
		super(name, num);
		this.major=major;
		this.year = year;		
	}
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major: "+major);		
		System.out.println("year: "+year);
	}
	public String toString() {
		return super.toString()+"major: "+major+"year: "+year+"\n";
	}
}
