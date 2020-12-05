package collegeCareer.ui;

import java.util.ArrayList;
import java.util.Scanner;

import collegeCareer.manager.CCManager;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Major;
import collegeCareer.vo.Student;

public class StudentUI {
	Scanner sc = new Scanner(System.in);
	CCManager mgr = new CCManager();
	Student student = null;
	
	public StudentUI() {
		boolean res = login();
		if(!res) return;
		while(true) {
			int menuNum = 0;
			printMainMenu();
			
			try {
				menuNum = sc.nextInt();
				switch (menuNum) {
					case 1: updatePW(); 		break;
					case 2: getGradReq(); 		break;
					case 3: recommendMajor();	break;
					case 4: recommendLibArts();	break;
					case 0: 					return;
					default:
					System.out.println("[오류] 다시 선택하세요.");
				}
			} catch (Exception e) {
				System.out.println("[오류] 입력형식이 잘못되었습니다.");
				sc.nextLine();
			}
		}
	}
	/*
	 * 로그인 메뉴
	 */
	public boolean login() {
		String studentID = null;
		String password = null;
		
		while(true) {
			System.out.println();
			System.out.println("[ 학생 계정으로 로그인 ]");
			System.out.print("ID: ");
			studentID = sc.nextLine().toUpperCase();
			System.out.print("PW: ");
			password = sc.nextLine();
			
			student = mgr.getStudent(studentID);
					
			if(student == null || !student.getPassword().equals(password)) {
				System.out.println("[알림] 아이디 또는 패스워드가 잘못되었습니다.");
				return false;
			}else if(studentID.equalsIgnoreCase(student.getStudentID())){
				System.out.println();
				System.out.println("======= "+ student.getStudentName() + "님 환영합니다. =======");
				return true;
			}
		}
	}
	/*
	 * 메인메뉴
	 */
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ 학생 계정 ]");
		System.out.println("== 계정관리 ==");
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 졸업요건 비교");
		System.out.println("3. 전공설계");
		System.out.println("4. 교양설계");
		System.out.println("0. 이전 메뉴로");
		System.out.print("** 번호 선택 > ");
	}
	
	/*
	 * 1. 비밀번호 변경
	 */
	public void updatePW() {
		String password = null;
		String newPassword = null;
		String newPassword2 = null;
		while(true) {
			System.out.println();
			System.out.println("[ 비밀번호 변경 ]");
			System.out.print("기존 비밀번호 입력: ");
			password = sc.next();
			System.out.print("새로운 비밀번호 입력: ");
			newPassword = sc.next();
			System.out.print("새로운 비밀번호 다시 입력: ");
			newPassword2 = sc.next();
			
			if(!password.equals(student.getPassword())) {
				System.out.println("[오류] 기존 비밀번호가 잘못 입력되었습니다.");
				return;
			} else if(!newPassword.equals(newPassword2)) {
				System.out.println("[오류] 다시 입력한 비밀번호가 일치하지 않습니다.");
				return;
			} else {
				student.setPassword(newPassword);
				boolean res = mgr.updateStudent(student);
				if(res) {
					System.out.println("[알림] 비밀번호가 변경되었습니다.");
				} else {
					System.out.println("[알림] 비밀번호 변경을 실패했습니다.");
				}
				return;
			}
		}
	}
	/*
	 * 2. 졸업요건 비교
	 */
	public void getGradReq() {
		double version = student.getVersion();
		GradReq gradReq = mgr.getGradReq(version);
		System.out.println();
		System.out.println("[ 나의 졸업요건 : ver." + version + " ]");
		System.out.print("총 전공 학점: " + gradReq.getGradMajorCredit());
		if(gradReq.getGradMajorCredit() > student.getGrossCreditMajor()) 
				System.out.println("\t(" + (gradReq.getGradMajorCredit() - student.getGrossCreditMajor()) +"점 부족)");
		else	System.out.println("\t(달성!)");
		System.out.print("총 교양 학점: " + gradReq.getGradLiberalCredit());
		if(gradReq.getGradLiberalCredit() > student.getGrossCreditLiberal()) 
				System.out.println("\t(" + (gradReq.getGradLiberalCredit() - student.getGrossCreditLiberal()) +"점 부족)");
		else	System.out.println("\t(달성!)");
		System.out.print("총 이수 학점 : " + gradReq.getGradGrossCredit());
		if(gradReq.getGradGrossCredit() > student.getGrossCredit()) 
				System.out.println("\t(" + (gradReq.getGradGrossCredit() - student.getGrossCredit()) +"점 부족)");
		else	System.out.println("\t(달성!)");
		System.out.print("토익 요건: " + gradReq.getToeic());
		if(gradReq.getToeic() > student.getToeic()) 
				System.out.println("\t(" + (gradReq.getToeic() - student.getToeic()) +"점 부족)");
		else	System.out.println("\t(달성!)");
		System.out.print("HSK 요건: " + gradReq.getHsk());
		if(gradReq.getHsk() > student.getHsk()) 
				System.out.println("\t(미달성)");
		else	System.out.println("\t(달성!)");
		System.out.print("JPT 요건: " + gradReq.getJpt());
		if(gradReq.getJpt() > student.getJpt()) 
				System.out.println("\t(" + (gradReq.getJpt() - student.getJpt()) +"점 부족)");
		else	System.out.println("\t(달성!)");
	} 
	/*
	 * 전공추천
	 */
	public void recommendMajor() {
		System.out.println();
		System.out.println("[ 전공 설계 ]");
		
		ArrayList<Major> list = mgr.recommendMajor(student);
		if(list.size() == 0 || list == null) {
			System.out.println("[알림] 검색 결과가 없습니다.");
			return;
		}
		System.out.println("지금 " + student.getStudentName() + "님께 딱 맞는 전공 " + list.size() + "건을 추천합니다!.");
		System.out.println("학정번호\t과목명\t\t교수명\t단위학점\t트랙\t요구학년\t평점");
		System.out.println("=============================================================================");
		for(Major major : list) {
			System.out.print(major.getCourseNo() + "\t");
			System.out.print(major.getCourseName() + "\t\t");
			System.out.print(major.getProfName() + "\t");
			System.out.print(major.getCourseCredit() + "\t");
			System.out.print(major.getTrack() + "\t");
			System.out.print(major.getGradeRequired() + "\t");
			System.out.println(major.getScore());
		}
	}
	/*
	 * 교양추천
	 */
	public void recommendLibArts() {
		int menuNum = 0;
		
		System.out.println();
		System.out.println("[ 교양 설계 ]");
		System.out.println("어떤 알고리즘을 사용하시겠습니까?");
		System.out.println("1.외국어 요건 충족");
		System.out.println("2.꿀강");
		System.out.println("3.전공 기초세우기");
		System.out.print("** 번호 선택 > ");
		menuNum = sc.nextInt();
		try {
			switch (menuNum) {
//			case 1: recommendLang(); break;
//			case 2: recommendScore();break;
//			case 3: recommendBasic();break;
			default:
				System.out.println("[오류] 다시 선택하세요.");
			}
		} catch (Exception e) {
			System.out.println("[오류] 입력형식이 잘못되었습니다.");
			sc.nextLine();
		}
	}

}
