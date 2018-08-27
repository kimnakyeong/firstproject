package phonebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class PhoneBookManager {
	private final File dataFile = new File("DataFile.dat"); // �� ���ⲯ ��ġ�� �ȴ�.

	HashSet<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();
	// HashSet�� 1. hashCode �޼ҵ��� ��ȯ ���� �ؽ� ������ Ȱ���ϰ�
	// 2. Object Ŭ������ equals �޼ҵ��� ��ȯ���� �̿��ؼ� ������ ���Ѵ�.

	static PhoneBookManager inst = null;

	public static PhoneBookManager createManagerInst() { // ������ ����� �̱��� ������ �޼ҵ�
		if (inst == null) {
			return new PhoneBookManager();
		} else
			return inst;
		// new~ ��ſ� PhoneBookManager.createManagerInst(); �ؼ� �� �Ŵϱ�
		// new PhoneBookManager(); ���� �� ����� �Ѵ�.
		// null �̸� ������ְ� �ƴϸ� ������ return
	}

	private PhoneBookManager() { // ������ �ٵ� ���⼭�� ���� �ʱ�ȭ �� �� ���µ�..?
		readFromFile();
	}

	private PhoneInfo readFriendInfo() { //
		System.out.println("�̸�: "); // �Է��϶�� �� �� �ߴ� ��
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("��ȭ��ȣ: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		return new PhoneInfo(name, phoneNumber);
		// ��� �޾� �ͼ� PhoneInfo�����ڿ� �־ PhoneInfo�� �����ϱ�
	}

	private PhoneInfo readUnivFriendInfo() {
		System.out.println("�̸�: "); // �Է��϶�� �� �� �ߴ� ��
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("��ȭ��ȣ: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		System.out.println("�а�: ");
		String major = MenuViewer.keyboard.nextLine();
		System.out.println("�г�: ");
		int year = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();
		return new PhoneUnivInfo(name, phoneNumber, major, year); // ������
	}

	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("�̸�: "); // �Է��϶�� �� �� �ߴ� ��
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("��ȭ��ȣ: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		System.out.println("ȸ���: ");
		String company = MenuViewer.keyboard.nextLine();
		return new PhoneCompanyInfo(name, phoneNumber, company);
	}

	// ��״� readFromFile���� �ڼ�.read~(); ���� �� ������ ������
	// inputData���� univeFriendInfo�� read�ؿͼ� �ű⿡ ���ٴ� ����.
	// ã�� ���� PhoneInfo ���� show~�޼ҵ尡 �ֱ� ������
	// �Է¹��� ���� �긦 �����ؾ� ��
	// ��¹��� ���;� ��.
	public void inputData() throws MenuChoiceException { // ������ �Է��ϴ� �� �Է� ��ư ������ �� = 1�϶� �ҷ������� �޼ҵ�
		System.out.println("������ �Է��� �����մϴ�...\n1. �Ϲ�, 2. ����, 3. ȸ��\\n����>>");
		int choice = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();

		PhoneInfo info = null;
		// PhoneInfo�� ���� �׸��� �غ��ؾ� �Ѵ�.
		// ���ܺ��� ������ switch case �� ����..
		if (choice < INIT_MENU.INPUT || choice > INIT_MENU.DELETE)
			throw new MenuChoiceException(choice); // throw �� ���� �� ���ܿ� ��Ƽ� �׷��� ���� �������� �� �� ����.

		switch (choice) {
		case INPUT_SELECT.NORMAL:
			info = readFriendInfo();
			break;
		case INPUT_SELECT.UNIV:
			info = readUnivFriendInfo();
			break;
		case INPUT_SELECT.COMPANY:
			info = readCompanyFriendInfo();
			break;
		}
		boolean isAdded = infoStorage.add(info); // infoStorage HashSet �ڷ� ����ִ� ��.
		if (isAdded) {
			System.out.println("������ �Է� �Ϸ��߽��ϴ�.");
		} else
			System.out.println("�����Ͱ� �ߺ��˴ϴ�.");
		// add(); << ������ true ������ false
		// �ߺ��� ����ϸ� �ȵǴϱ�
	}

	public void searchData() { //
		System.out.println("������ �˻��� �����մϴ�..\n�̸�: ");
		String name = MenuViewer.keyboard.nextLine();

		PhoneInfo info = search(name);
		// name ���� ã�Ƽ� PhoneInfo�� �ǵ��� ��..
		if (info == null) {
			System.out.println("�߸��� �Է��Դϴ�.");
		} else {
			info.showPhoneInfo();// �������̶� �ϳ��� ��� ��.
			System.out.println("������ �˻� �Ϸ� ����.");
		}

	}

	public void deleteData() { // INIT_MENU�� �޾ƿ��� �� 3.
		System.out.println("������ ������ �����մϴ�. \n�̸�: ");
		String name = MenuViewer.keyboard.nextLine();

		// Iterator�� HashSet ��밡��
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if (name.compareTo(curInfo.name) == 0) {
				itr.remove();
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
				return;
			}
		}
	}

	private PhoneInfo search(String name) {
		// �Է¹��� name�̶� ��ϵ� name�̶� ������ �� ������
		// ���ؼ� ������ PhoneInfo�� �ǵ��� �ִ� ��
		// HashSet�� Iterator�� ��
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) { // �������� �����ϴ���.
			PhoneInfo curInfo = itr.next(); // ������ �ҷ��ͼ� ���ؼ� ������ ��ȯ
			if (name.compareTo(curInfo.name) == 0)
				// compareTo << ������ 0
				// �տ� ���� ũ�� +, �ڿ� ���� ũ�� -, String�� char�� ó������ �� ����.
				return curInfo;
		}
		return null;
	}

	public void StoreToFile() {
		
			try {
				FileOutputStream file = new FileOutputStream(dataFile);
				ObjectOutputStream out = new ObjectOutputStream(file);
				
				Iterator<PhoneInfo> itr = infoStorage.iterator();
				while(itr.hasNext()) {
				out.writeObject(itr.next());
				}
				out.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@SuppressWarnings("resource")// ��� ����..?
	public void readFromFile() {
		// �����ڷ� ���� ���� �ҷ��� ���� �޼ҵ�
		// ���� ���� �ҷ��� ���Ƿ� dataFile�� �ִ��� ���� �� ���� ���캻��.
		if (dataFile.exists() == false)
			return;
		// exists �޼ҵ� ���� true, �ƴϸ� false
		FileInputStream file;
		ObjectInputStream in;
		PhoneInfo info;
		try {
			file = new FileInputStream(dataFile);
			// FileInputStream���� File��ü�� dataFile�� �о���� .. Stream ��°�� ������ �´�.
			in = new ObjectInputStream(file);
			// �װ��� ���� ObjectInputStream�� �־ ���� ���
			while (true) {
				info = (PhoneInfo) in.readObject();
				if (info == null)
					break;
				infoStorage.add(info);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
			// return; ���ָ� ȣ���ߴ� ������ �ٽ� ���ư��� �ȴ�.
		}

	}

// ���������� searchData�� deleteData �����ε� �ϴ� �� �ִµ� �׳� ���� �ʹ� ������.. 
}
