package phonebook;

public class MenuChoiceException extends Exception{ /**
	 * 
	 */
	private static final long serialVersionUID = 5997734733886890557L;
// Excepion�� serializable << ����ȭ�� ������ Ŭ�����̴�.
	
	int wrongChoice;
	public MenuChoiceException(int choice) { 
		// Exception �����ڰ� �ҷ������ٴ� �� ������ �Ͼ�ٴ� �Ҹ�
		super("�߸��� �����Դϴ�.");
		wrongChoice = choice;
		
	}
	public void showWrongChoice() {
		// �� �޼ҵ�� ������ �߸��Ǿ��°� �� �����ִ� �޼ҵ�
		System.out.println(wrongChoice+" �������� �����ϴ�. �ٽ� ������");
	}
}
