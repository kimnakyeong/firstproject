package kr.yjc.jcls.pb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class PhoneBookManager {  // �̱��� ����������
	// ��ü�� ���μ����󿡼� �����ϰ� �ϳ��� ����
	private final File dataFile = new File("PhoneBook.dat");
	HashSet<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();
	//Generic
	
	static PhoneBookManager inst = null;
	
	private PhoneBookManager() {
		readFromFile();		
	}
	public static PhoneBookManager createManagerInst() {
		if(inst == null) 
			inst = new PhoneBookManager();		
		return inst;		
	}
	private PhoneInfo readFriendInfo() {
		System.out.print("�̸�: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("��ȭ��ȣ: ");
		String num = MenuViewer.keyboard.nextLine();
		
		return new PhoneInfo(name,num);
	}
	private PhoneInfo readUnivFriendInfo() {
		System.out.print("�̸�: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("��ȭ��ȣ: ");
		String num = MenuViewer.keyboard.nextLine();	
		System.out.print("����: ");
		String major = MenuViewer.keyboard.nextLine();
		System.out.print("�г�: ");
		int year = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();
		return new PhoneUnivInfo(name,num,major,year);
			
	}
	private PhoneInfo readCompanyFriendInfo() {
		System.out.print("�̸�: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("��ȭ��ȣ: ");
		String num = MenuViewer.keyboard.nextLine();	
		System.out.print("ȸ��: ");
		String company = MenuViewer.keyboard.nextLine();
		
		return new PhoneCompanyInfo(name,num,company);
		
	}
	
	public void inputData() throws MenuChoiceException {
		System.out.println("������ �Է½����մϴ�...");
		System.out.println("1. �Ϲ�, 2. ����, 3. ȸ��");
		System.out.print("����>> ");
		int choice = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();
		
		PhoneInfo info = null;
		if(choice<INPUT_SELECT.NORMAL || choice > INPUT_SELECT.COMPANY)
			throw new MenuChoiceException(choice);
		
		switch(choice) {
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
		boolean isAdded = infoStorage.add(info);
		// Set�� add�� �� ���ǻ���, �ߺ�����
		if(isAdded)
			System.out.println("�������Է� �Ϸ��߽��ϴ�.");
		else
			System.out.println("�̹� ����� �����Ͱ� �ֽ��ϴ�.");	
	}
	
	
	public void searchData() {
		System.out.println("������ �˻��� �����մϴ�.");
		System.out.print("�̸�: ");
		String name = MenuViewer.keyboard.nextLine();
		
		PhoneInfo info = search(name);
		if(info == null) {
			System.out.println("�ȵ� �Է��ض�. �ϰ� ã�°� ����.");			
		}else {
			info.showPhoneInfo();
			//info�� �Ϲ�ģ������...�б�ģ������, ȸ��ģ������ �ľ���
			//��½��Ѿ� �Ѵ�. �������� ������ ������...
			//������ ����� ����.
			System.out.println("������ �˻� �Ϸ�����.");
		}		
	}
	public void deleteData() {
		System.out.println("������ ������ �����մϴ�...");
		System.out.print("�̸�: ");
		String name = MenuViewer.keyboard.nextLine();
		
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if(name.compareTo(curInfo.name)==0) {
				itr.remove();
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
				return;
			}				
		}
		System.out.println("�ش��ϴ� �����ʹ� �������� �ʽ��ϴ�.");		
	}
	
	
	public String searchData(String name) { //�����ε�
		PhoneInfo info = search(name);
		if(info == null) {
			return null;
		}else {
			return info.toString();  //������
		}		
	}
	
	public boolean deleteData(String name) {  //�����ε�
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if(name.compareTo(curInfo.name)==0)
				return true;
		}
		return false;
	}
	
	private PhoneInfo search(String name) {
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if(name.compareTo(curInfo.name)==0)
				return curInfo;
		}

		return null;
	}
	public void readFromFile() {
		if(dataFile.exists()==false) return;
			FileInputStream file;
			ObjectInputStream in;
			PhoneInfo info;
			try {
				file = new FileInputStream(dataFile);
				in = new ObjectInputStream(file);
				while(true) {
				   info = (PhoneInfo) in.readObject();
				   if(info == null) break;
					infoStorage.add(info);				
				}
				in.close();//���� �б� ����Ǹ� �ݵ�� ����
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
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
			}		
	}
	public void storeToFile() {
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			Iterator<PhoneInfo> itr  = infoStorage.iterator();
			while(itr.hasNext()) {
				out.writeObject(itr.next());
			}
			out.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}



