package collegeCareer.ui;

import java.util.Scanner;

public class GeneralUI {
	Scanner input = new Scanner(System.in);
	
	public GeneralUI() {
		int menuNum = 0;
		while (true) {
			printMainMenu();
			
			try {
				menuNum = input.nextInt();
				switch (menuNum) {
					case 1: new AdminUI();	break;
					case 2: new StudentUI();	break;
					case 0: exit();				return;
					default:
						System.out.println("[오류] 다시 선택하세요.");
				}
			}
			catch (Exception e) {
				System.out.println("[오류] 입력형식이 잘못되었습니다.");
				input.nextLine();
			}
		}		
	}
	
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ CC를 시작합니다. ]");
		System.out.println("1. 관리자 계정");
		System.out.println("2. 학생 계정");
		System.out.println("0. 프로그램 종료");
		System.out.print("** 번호 선택 > ");
	}
	
	public void exit() {
		System.out.println("프로그램을 종료합니다.");
		return;
	}
	
}
