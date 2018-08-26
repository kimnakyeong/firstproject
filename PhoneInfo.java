package kr.yjc.jcls.pb;

import java.io.Serializable;

public class PhoneInfo implements Serializable {
	private static final long serialVersionUID = 130768460580571481L;
	// 객체 직렬화
	String name;
	String phoneNumber;
	//int GRID;
	
	public PhoneInfo(String name, String num) {
		this.name = name;
		phoneNumber = num;
	}
	public void showPhoneInfo() {
		System.out.println("name: "+name);
		System.out.println("phone: "+phoneNumber);
		//출력코드추가
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object obj) {
		PhoneInfo com = (PhoneInfo)obj;
		if(name.compareTo(com.name)==0) {
			return true;			
		}else 
			return false;
		
	}
	public String toString() {
		return "name: "+name+"\n phone: "+phoneNumber+"\n";
	}
}
