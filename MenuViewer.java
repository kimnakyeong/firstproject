package kr.yjc.jcls.pb;

import java.util.Scanner;

public class MenuViewer {
	public static Scanner keyboard = new Scanner(System.in);
	public static void showMenu() {
		System.out.println("선택을하시오...");
		System.out.println("1. 데이터 입력");		
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.println("선택: ");
	}
}
