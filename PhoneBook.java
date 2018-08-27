package phonebook;

public class PhoneBook { // 메인 메소드 있는 곳
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		// 싱글톤 패턴

		int choice; // 숫자 받아오는거 1.2.3.
		try {
			while (true) {
				MenuViewer.showMenu();
				choice = MenuViewer.keyboard.nextInt();
				MenuViewer.keyboard.nextLine(); // int 라서 엔터 받아오려고

				if (choice < INIT_MENU.INPUT || choice > INIT_MENU.EXIT)
					throw new MenuChoiceException(choice);
				switch (choice) {
				case INIT_MENU.INPUT:
					manager.inputData();
					break;
				case INIT_MENU.SEARCH:
					manager.searchData();
					break;
				case INIT_MENU.DELETE:
					manager.deleteData();
					break;
				case INIT_MENU.EXIT:
					manager.StoreToFile(); // 저장하고
					System.out.println("종료합니다.");
					return; 
					// 왜 return?
				}

			}
		} catch (MenuChoiceException e) {
			e.showWrongChoice();
			System.out.println("재시작");
		}

	}
}
