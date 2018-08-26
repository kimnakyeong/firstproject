package kr.yjc.jcls.pb;

public class MenuChoiceException extends Exception {
	private static final long serialVersionUID = 1719617483991706593L;
	//Exception�� Serializable�������̽��� ������ Ŭ������
	int wrongChoice;
	
	public MenuChoiceException(int choice) {
		super("�߸��� ������ �߽��ϴ�.");
		wrongChoice = choice;		
	}
	public void showWrongChoice() {
		System.out.println(wrongChoice+"�� �ش��ϴ� ������ �����ϴ�. "
				+ "1~4�̳��� �Է��Ͻÿ�.");
	}
}
