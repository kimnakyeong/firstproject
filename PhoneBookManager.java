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

public class PhoneBookManager {  // 싱글톤 디자인패턴
	// 객체가 프로세스상에서 유일하게 하나만 생성
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
		System.out.print("이름: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("전화번호: ");
		String num = MenuViewer.keyboard.nextLine();
		
		return new PhoneInfo(name,num);
	}
	private PhoneInfo readUnivFriendInfo() {
		System.out.print("이름: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("전화번호: ");
		String num = MenuViewer.keyboard.nextLine();	
		System.out.print("전공: ");
		String major = MenuViewer.keyboard.nextLine();
		System.out.print("학년: ");
		int year = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();
		return new PhoneUnivInfo(name,num,major,year);
			
	}
	private PhoneInfo readCompanyFriendInfo() {
		System.out.print("이름: ");
		String name = MenuViewer.keyboard.nextLine();
		System.out.print("전화번호: ");
		String num = MenuViewer.keyboard.nextLine();	
		System.out.print("회사: ");
		String company = MenuViewer.keyboard.nextLine();
		
		return new PhoneCompanyInfo(name,num,company);
		
	}
	
	public void inputData() throws MenuChoiceException {
		System.out.println("데이터 입력시작합니다...");
		System.out.println("1. 일반, 2. 대학, 3. 회사");
		System.out.print("선택>> ");
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
		// Set에 add할 때 주의사항, 중복불허
		if(isAdded)
			System.out.println("데이터입력 완료했습니다.");
		else
			System.out.println("이미 저장된 데이터가 있습니다.");	
	}
	
	
	public void searchData() {
		System.out.println("데이터 검색을 시작합니다.");
		System.out.print("이름: ");
		String name = MenuViewer.keyboard.nextLine();
		
		PhoneInfo info = search(name);
		if(info == null) {
			System.out.println("똑디 입력해라. 니가 찾는거 없다.");			
		}else {
			info.showPhoneInfo();
			//info가 일반친구인지...학교친구인지, 회사친구인지 파악해
			//출력시켜야 한다. 다형성의 개념이 없으면...
			//다형성 고민해 볼것.
			System.out.println("데이터 검색 완료했음.");
		}		
	}
	public void deleteData() {
		System.out.println("데이터 삭제를 시작합니다...");
		System.out.print("이름: ");
		String name = MenuViewer.keyboard.nextLine();
		
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if(name.compareTo(curInfo.name)==0) {
				itr.remove();
				System.out.println("데이터 삭제가 완료되었습니다.");
				return;
			}				
		}
		System.out.println("해당하는 데이터는 존재하지 않습니다.");		
	}
	
	
	public String searchData(String name) { //오버로딩
		PhoneInfo info = search(name);
		if(info == null) {
			return null;
		}else {
			return info.toString();  //다형성
		}		
	}
	
	public boolean deleteData(String name) {  //오버로딩
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
				in.close();//파일 읽기 종료되면 반드시 실행
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



