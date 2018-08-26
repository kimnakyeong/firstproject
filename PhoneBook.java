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
					System.out.println("���α׷��� �����մϴ�.");
					return;
				}
				
			}catch(MenuChoiceException e) {
				e.showWrongChoice();
				System.out.println("�޴������� ó������ �ٽý����մϴ�.");
				
			}
		}

	}

}
