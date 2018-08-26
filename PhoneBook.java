package kr.yjc.jcls.pb;

public class PhoneBook {
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		
		int choice;
		
		while(true) {
			try {
				MenuViewer.showMenu();
				choice = MenuViewer.keyboard.nextInt();
				MenuViewer.keyboard.nextLine();
				
				if(choice<INIT_MENU.INPUT || choice > INIT_MENU.EXIT)
					throw new MenuChoiceException(choice);
				
				switch(choice) {
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
					manager.storeToFile();
					System.out.println("프로그램을 종료합니다.");
					return;
				}
				
			}catch(MenuChoiceException e) {
				e.showWrongChoice();
				System.out.println("메뉴선택을 처음부터 다시실행합니다.");
				
			}
		}

	}

}
