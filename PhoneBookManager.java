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
	private final File dataFile = new File("DataFile.dat"); // 내 취향껏 고치면 된다.

	HashSet<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();
	// HashSet은 1. hashCode 메소드의 반환 값을 해시 값으로 활용하고
	// 2. Object 클래스의 equals 메소드의 반환값을 이용해서 내용을 비교한다.

	static PhoneBookManager inst = null;

	public static PhoneBookManager createManagerInst() { // 생성자 만드는 싱글톤 패턴의 메소드
		if (inst == null) {
			return new PhoneBookManager();
		} else
			return inst;
		// new~ 대신에 PhoneBookManager.createManagerInst(); 해서 쓸 거니까
		// new PhoneBookManager(); 까지 다 써줘야 한다.
		// null 이면 만들어주고 아니면 기존거 return
	}

	private PhoneBookManager() { // 생성자 근데 여기서는 딱히 초기화 할 게 없는데..?
		readFromFile();
	}

	private PhoneInfo readFriendInfo() { //
		System.out.println("이름: "); // 입력하라고 할 때 뜨는 거
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("전화번호: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		return new PhoneInfo(name, phoneNumber);
		// 모두 받아 와서 PhoneInfo생성자에 넣어서 PhoneInfo로 리턴하기
	}

	private PhoneInfo readUnivFriendInfo() {
		System.out.println("이름: "); // 입력하라고 할 때 뜨는 거
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("전화번호: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		System.out.println("학과: ");
		String major = MenuViewer.keyboard.nextLine();
		System.out.println("학년: ");
		int year = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();
		return new PhoneUnivInfo(name, phoneNumber, major, year); // 다형성
	}

	private PhoneInfo readCompanyFriendInfo() {
		System.out.println("이름: "); // 입력하라고 할 때 뜨는 거
		String name = MenuViewer.keyboard.nextLine();
		System.out.println("전화번호: ");
		String phoneNumber = MenuViewer.keyboard.nextLine();
		System.out.println("회사명: ");
		String company = MenuViewer.keyboard.nextLine();
		return new PhoneCompanyInfo(name, phoneNumber, company);
	}

	// 얘네는 readFromFile에서 자손.read~(); 했을 때 무엇이 나오냐
	// inputData에서 univeFriendInfo를 read해와서 거기에 쓴다는 뜻임.
	// 찾을 때는 PhoneInfo 안의 show~메소드가 있기 때문에
	// 입력받을 때는 얘를 실행해야 됨
	// 출력문이 나와야 함.
	public void inputData() throws MenuChoiceException { // 데이터 입력하는 곳 입력 버튼 눌렀을 때 = 1일때 불러와지는 메소드
		System.out.println("데이터 입력을 시작합니다...\n1. 일반, 2. 대학, 3. 회사\\n선택>>");
		int choice = MenuViewer.keyboard.nextInt();
		MenuViewer.keyboard.nextLine();

		PhoneInfo info = null;
		// PhoneInfo를 담을 그릇을 준비해야 한다.
		// 예외부터 던지고 switch case 로 들어가기..
		if (choice < INIT_MENU.INPUT || choice > INIT_MENU.DELETE)
			throw new MenuChoiceException(choice); // throw 할 때는 그 예외에 담아서 그래야 무슨 문제인지 알 수 있음.

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
		boolean isAdded = infoStorage.add(info); // infoStorage HashSet 자료 들어있는 곳.
		if (isAdded) {
			System.out.println("데이터 입력 완료했습니다.");
		} else
			System.out.println("데이터가 중복됩니다.");
		// add(); << 있으면 true 없으면 false
		// 중복을 허용하면 안되니까
	}

	public void searchData() { //
		System.out.println("데이터 검색을 시작합니다..\n이름: ");
		String name = MenuViewer.keyboard.nextLine();

		PhoneInfo info = search(name);
		// name 으로 찾아서 PhoneInfo로 되돌려 줌..
		if (info == null) {
			System.out.println("잘못된 입력입니다.");
		} else {
			info.showPhoneInfo();// 다형성이라서 하나만 적어도 됨.
			System.out.println("데이터 검색 완료 했음.");
		}

	}

	public void deleteData() { // INIT_MENU로 받아왔을 때 3.
		System.out.println("데이터 삭제를 시작합니다. \n이름: ");
		String name = MenuViewer.keyboard.nextLine();

		// Iterator로 HashSet 사용가능
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) {
			PhoneInfo curInfo = itr.next();
			if (name.compareTo(curInfo.name) == 0) {
				itr.remove();
				System.out.println("데이터 삭제가 완료되었습니다.");
				return;
			}
		}
	}

	private PhoneInfo search(String name) {
		// 입력받은 name이랑 등록된 name이랑 비교했을 때 같으면
		// 비교해서 같으면 PhoneInfo로 되돌려 주는 거
		// HashSet은 Iterator로 비교
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while (itr.hasNext()) { // 다음값이 존재하느냐.
			PhoneInfo curInfo = itr.next(); // 다음값 불러와서 비교해서 같으면 반환
			if (name.compareTo(curInfo.name) == 0)
				// compareTo << 같으면 0
				// 앞에 것이 크면 +, 뒤에 것이 크면 -, String은 char로 처음부터 비교 가능.
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

	@SuppressWarnings("resource")// 얘는 뭐지..?
	public void readFromFile() {
		// 생성자로 가장 먼저 불러와 지는 메소드
		// 가장 먼저 불러와 지므로 dataFile이 있는지 없는 지 부터 살펴본다.
		if (dataFile.exists() == false)
			return;
		// exists 메소드 존재 true, 아니면 false
		FileInputStream file;
		ObjectInputStream in;
		PhoneInfo info;
		try {
			file = new FileInputStream(dataFile);
			// FileInputStream으로 File객체인 dataFile을 읽어오기 .. Stream 통째로 가지고 온다.
			in = new ObjectInputStream(file);
			// 그것을 이제 ObjectInputStream에 넣어서 성능 향상
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
			// return; 해주면 호출했던 곳으로 다시 돌아가게 된다.
		}

	}

// 다형성으로 searchData랑 deleteData 오버로딩 하는 거 있는데 그냥 생략 너무 복잡행.. 
}
