package phonebook;

public class PhoneBook { // ���� �޼ҵ� �ִ� ��
	public static void main(String[] args) {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		// �̱��� ����

		int choice; // ���� �޾ƿ��°� 1.2.3.
		try {
			while (true) {
				MenuViewer.showMenu();
				choice = MenuViewer.keyboard.nextInt();
				MenuViewer.keyboard.nextLine(); // int �� ���� �޾ƿ�����

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
					manager.StoreToFile(); // �����ϰ�
					System.out.println("�����մϴ�.");
					return; 
					// �� return?
				}

			}
		} catch (MenuChoiceException e) {
			e.showWrongChoice();
			System.out.println("�����");
		}

	}
}
