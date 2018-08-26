package kr.yjc.jcls.pb;

public class PhoneCompanyInfo extends PhoneInfo {
	private static final long serialVersionUID = -6304131392805246350L;
	String company;

	public PhoneCompanyInfo(String name, String num, String com) {
		super(name, num);
		this.company = com;
	}
	public void showPhoneInfo() {
		super.showPhoneInfo();
		//System.out.println("name: "+name);
		//System.out.println("phone: "+phoneNumber);
		System.out.println("company: "+company);		
	}
	public String toString() {
		return super.toString()+"company: "+company+"\n";
	}

}
