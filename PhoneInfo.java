package phonebook;

import java.io.Serializable;

public class PhoneInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348665788805532442L;

	String name;
	String phoneNumber;

	public PhoneInfo(String name, String phone) { // �����ڴ� ���� static �̴ϱ� static �ٿ��ָ� �ȵ�
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
	public boolean equals(Object obj) { // equals�� ���� hashCode������ ���ϴ� �� ���⼭�� �������̵� �ϰ� �����ϱ� �°� �� ��� �Ѵ�.
		PhoneInfo com =(PhoneInfo)obj;
		if (this.name.compareTo(com.name) == 0)
			// compareTo : �񱳿�����
			// ������ 0, A�� ũ�� ���, B�� ũ�� ���� �� ù�ڸ� ���� ���Ѵ�.
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "name: " + name + "/n phonenumber: " + phoneNumber;
	}

}
