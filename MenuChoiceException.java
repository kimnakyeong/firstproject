package kr.yjc.jcls.pb;

public class MenuChoiceException extends Exception {
	private static final long serialVersionUID = 1719617483991706593L;
	//Exception은 Serializable인터페이스를 구현한 클래스임
	int wrongChoice;
	
	public MenuChoiceException(int choice) {
		super("잘못된 선택을 했습니다.");
		wrongChoice = choice;		
	}
	public void showWrongChoice() {
		System.out.println(wrongChoice+"에 해당하는 선택은 없습니다. "
				+ "1~4이내로 입력하시오.");
	}
}
