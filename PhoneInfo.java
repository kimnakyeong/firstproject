package phonebook;

import java.io.Serializable;

public class PhoneInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348665788805532442L;

	String name;
	String phoneNumber;

	public PhoneInfo(String name, String phone) { // 생성자는 원래 static 이니까 static 붙여주면 안됨
		this.name = name;
		phoneNumber = phone;
	}

	public void showPhoneInfo() {
		System.out.println("name: " + name);
		System.out.println("phone: " + phoneNumber);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) { // equals는 원래 hashCode값으로 비교하는 데 여기서는 오버라이딩 하고 있으니까 맞게 써 줘야 한다.
		PhoneInfo com =(PhoneInfo)obj;
		if (this.name.compareTo(com.name) == 0)
			// compareTo : 비교연산자
			// 같으면 0, A가 크면 양수, B가 크면 음수 맨 첫자리 부터 비교한다.
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "name: " + name + "/n phonenumber: " + phoneNumber;
	}

}
