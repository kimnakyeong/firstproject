package phonebook;

public class MenuChoiceException extends Exception{ /**
	 * 
	 */
	private static final long serialVersionUID = 5997734733886890557L;
// Excepion은 serializable << 직렬화를 구현한 클래스이다.
	
	int wrongChoice;
	public MenuChoiceException(int choice) { 
		// Exception 생성자가 불러와졌다는 건 오류가 일어났다는 소리
		super("잘못된 선택입니다.");
		wrongChoice = choice;
		
	}
	public void showWrongChoice() {
		// 이 메소드는 무엇이 잘못되었는가 를 보여주는 메소드
		System.out.println(wrongChoice+" 선택지가 없습니다. 다시 고르세요");
	}
}
