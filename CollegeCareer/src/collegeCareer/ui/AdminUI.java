package collegeCareer.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import collegeCareer.vo.Course;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.Major;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Student;
import collegeCareer.manager.CCManager;

public class AdminUI {
	Scanner sc = new Scanner(System.in);
	CCManager mgr = new CCManager();
	
	public AdminUI() {
		boolean res = login();
		if(!res) return;
		while (true) {
			int menuNum = 0;
			printMainMenu();
			
			try {
				menuNum = sc.nextInt();
				switch (menuNum) {
					case 20: getCourse();		 break;
					case 1:  addCourse();		 break;
					case 2:  listCourse();	 	 break;
					case 3:  updateCourse();	 break;
					case 4:  deleteCourse();	 break;
					case 5:  insertStudent();	 break;
					case 6:  searchStudent();	 break;
					case 7:  listStudent();		 break;
					case 8:  updateStudent();	 break;
					case 9:  deleteStudent();	 break;
					case 10: insertGradReq(); 	 break;
					case 11: printGradReq();	 break;
					case 12: updateGradReq();	 break;
					case 13: deleteGradReq();	 break;
					case 0: 				   	 return;
					default:
						System.out.println("[오류] 다시 선택하세요.");
				}
			}
			catch (Exception e) {
				System.out.println("[오류] 입력형식이 잘못되었습니다.");
				sc.nextLine();
			}
		}		
	}
	
	public boolean login() {
		String studentID = null;
		String password = null;
		Student student = null;
		
		while(true) {
			System.out.println();
			System.out.println("[ 관리자 계정으로 로그인 ]");
			System.out.print("ID: ");
			studentID = sc.nextLine().toUpperCase();
			System.out.print("PW: ");
			password = sc.nextLine();
			
			student = mgr.getStudent(studentID);
					
			if(student == null || !student.getPassword().equals(password)) {
				System.out.println("[알림] 아이디 또는 패스워드가 잘못되었습니다.");
				return false;
			}else if(student.getStudentID().substring(0,5).equalsIgnoreCase("ADMIN")){
				System.out.println();
				System.out.println("======= "+ student.getStudentName() + "님 환영합니다. =======");
				return true;
			}else{
				System.out.println("[알림] 학생은 관리자 계정으로 접속할 수 없습니다.");
				return false;
			}
		}
	}
	
	public void printMainMenu() {
		System.out.println();
		System.out.println("[ 관리자 계정 ]");
		System.out.print("== 수업관리 ==");
		System.out.print("\t== 학생관리 ==");
		System.out.println("\t== 졸업관리 ==");
		System.out.print("1. 수업 등록");
		System.out.print("\t5. 학생 등록");
		System.out.println("\t10. 졸업요건 등록");
		//
		System.out.print("2. 수업 조회");
		System.out.print("\t6. 학생 검색");
		System.out.println("\t11. 졸업요건 조회");
		//
		System.out.print("3. 수업 수정");
		System.out.print("\t7. 학생 전체 조회");
		System.out.println("\t12. 졸업요건 수정");
		//
		System.out.print("4. 수업 삭제");
		System.out.print("\t8. 학생 수정");
		System.out.println("\t13. 졸업요건 삭제");
		//
		System.out.println("\t\t9. 학생 삭제");
		//
		System.out.println("0. 이전 메뉴로");
		System.out.print("** 번호 선택 > ");
	}
	
	public void getCourse() {
		System.out.println();
		System.out.println("[ 수업검색 ]");
		sc.nextLine();
		String courseNo = sc.nextLine().toUpperCase();
		Course course = mgr.getCourse(courseNo);
		if(course instanceof Major) {
			Major major = (Major)course;
			System.out.println(major);
		}else if(course instanceof LibArts) {
			LibArts libArts = (LibArts)course;
			System.out.println(libArts);
		}else {
			System.out.println("[알림]해당 과목 없음");
		}
	}
	
	/**
	 * 1. 수업등록
	 */
	public void addCourse() {
		int num = 0;
		Major major = null;
		LibArts libArts = null;
		String courseNo;
		String courseName;
		String profName;
		int courseCredit;
		int track;
		int gradeRequired;
		double score;
		
		//수업구분 선택
		try {
			System.out.println();
			System.out.println("[ 수업 등록 ]");
			System.out.println("1. 전공과목");
			System.out.println("2. 교양과목");
			System.out.print("** 과목 구분 선택 > ");
			num = sc.nextInt();
			 
			if(num < 1 || num > 2) {
				System.out.println("[오류] 잘못 선택했습니다.");
				return;
			}
			while(true) {
				System.out.print("학정번호 > ");
				sc.nextLine();
				courseNo = sc.nextLine().toUpperCase();
				if(mgr.getCourse(courseNo) == null) break; 
				System.out.println("[오류] 이미 존재하는 학정번호입니다.");
			}
			
			System.out.print("과목명 > ");
			courseName = sc.nextLine().toUpperCase();
			System.out.print("교수명 > ");
			profName = sc.nextLine().toUpperCase();
			System.out.print("학점 > ");
			courseCredit = sc.nextInt();
			System.out.print("트랙 > ");
			track = sc.nextInt();
			System.out.print("학년 제한 > ");
			gradeRequired = sc.nextInt();
			System.out.print("별점 > ");
			score = sc.nextDouble();
			boolean res;
			switch(num) {
				case 1: major = new Major(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
						res = mgr.insertMajor(major);
						if(res) {
							System.out.println("[정보] 저장 완료");
						} else {
							System.out.println("[정보] 저장 실패");
						}
						break;
				case 2: libArts = new LibArts(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
						res = mgr.insertLibArts(libArts);
						if(res) {
							System.out.println("[정보] 저장 완료");
						} else {
							System.out.println("[정보] 저장 실패");
						}
						break;
			}
		} catch (InputMismatchException e){
			System.out.println("[오류] 입력형식이 잘못되었습니다.");
			sc.nextLine();
			return;
		}
	}

	/*
	 * 2. 수업조회
	 * */
	public void listCourse() {
		int num = 0;

		try {
			System.out.println();
			System.out.println("[ 수업 조회  ]");
			System.out.println("1. 전공 조회");
			System.out.println("2. 교양 조회");
			System.out.print("** 번호 선택 > ");
			num = sc.nextInt();
			
			switch(num) {
				case 1: ArrayList<Major> majorList = mgr.listMajor();
						if(majorList.size() == 0 || majorList == null) {
							System.out.println("[알림] 검색 결과가 없습니다.");
							return;
						}
						System.out.println("총" + majorList.size() + "건의 검색 결과가 있습니다.");
						System.out.println("학정번호\t과목명\t\t교수명\t단위학점\t트랙\t요구학년\t평점");
						System.out.println("=============================================================================");
						for(Major major : majorList) {
							System.out.print(major.getCourseNo() + "\t");
							System.out.print(major.getCourseName() + "\t\t");
							System.out.print(major.getProfName() + "\t");
							System.out.print(major.getCourseCredit() + "\t");
							System.out.print(major.getTrack() + "\t");
							System.out.print(major.getGradeRequired() + "\t");
							System.out.println(major.getScore());
						}
						break;
				case 2: ArrayList<LibArts> libList = mgr.listLibArts();
						if(libList.size() == 0 || libList == null) {
							System.out.println("[알림] 검색 결과가 없습니다.");
							return;
						}
						System.out.println("총" + libList.size() + "건의 검색 결과가 있습니다.");
						System.out.println("학정번호\t과목명\t\t교수명\t단위학점\t트랙\t요구학년\t평점");
						System.out.println("=============================================================================");
						for(LibArts libArts : libList) {
							System.out.print(libArts.getCourseNo() + "\t");
							System.out.print(libArts.getCourseName() + "\t\t");
							System.out.print(libArts.getProfName() + "\t");
							System.out.print(libArts.getCourseCredit() + "\t");
							System.out.print(libArts.getTrack() + "\t");
							System.out.print(libArts.getGradeRequired() + "\t");
							System.out.println(libArts.getScore());
						}
						break;
				default:
					System.out.println("[오류] 잘못 선택했습니다.");
					return;
			}
		} catch (InputMismatchException ex) {
			System.out.println("[오류] 입력형식이 잘못되었습니다.");
			sc.nextLine();
		}
	}
	
	/*
	 * 2-1 수업리스트 출력
	 * */
	public void printCourse(ArrayList<Object> list) {
		
		if(list.size() == 0 || list == null) {
			System.out.println("[알림] 검색 결과가 없습니다.");
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("총" + list.size() + "건의 검색 결과가 있습니다.");
	}

	/*
	 * 3.과목수정
	 * */
	public void updateCourse() {
		String courseNo = null;
		
		System.out.println();
		System.out.println("[ 수업 수정 ]");
		sc.nextLine();
		System.out.print("** 수정할 과목의 학정번호 > ");
		courseNo = sc.nextLine().toUpperCase();
		
		Course course = mgr.getCourse(courseNo);
		if(course == null) {
			System.out.println("[오류] 해당 학정번호의 수업 정보가 존재하지 않습니다.");
			return;
		}else {
			System.out.println();
			System.out.println("* 기존 정보");
			System.out.println("학정번호: " + course.getCourseNo());
			System.out.println("수업명: " + course.getCourseName());
			System.out.println("교수명: " + course.getProfName());
			System.out.println("단위학점: " + course.getCourseCredit());
			System.out.println("트랙: " + course.getTrack());
			System.out.println("요구학년: " + course.getGradeRequired());
			System.out.println("평점: " + course.getScore());
			System.out.println("=======================");
			System.out.println("* 수정할 정보");
			System.out.print("수업명: ");
			course.setCourseName(sc.nextLine().toUpperCase());
			System.out.print("교수명: ");
			course.setProfName(sc.nextLine().toUpperCase());
			System.out.print("단위학점: ");
			course.setCourseCredit(sc.nextInt());
			System.out.print("트랙: ");
			course.setTrack(sc.nextInt());
			System.out.print("요구학년: ");
			course.setGradeRequired(sc.nextInt());
			System.out.print("평점: ");
			course.setScore(sc.nextDouble());
			
			boolean res = mgr.updateCourse(course);
			if(res) {
				System.out.println("[정보] 수정되었습니다.");
			} else {
				System.out.println("[오류] 수정 실패했습니다.");
			}
		}
	}

	/*
	 * 4.수업삭제
	 * */
	public void deleteCourse() {
		String courseNo = null;
		System.out.println();
		System.out.println("[ 수업 삭제 ]");
		sc.nextLine();
		System.out.print("** 삭제할 수업의 학정번호 > ");
		courseNo = sc.nextLine().toUpperCase();
		
		boolean res = mgr.deleteCourse(courseNo);
		if(res) {
			System.out.println("[정보] 삭제되었습니다.");
		} else {
			System.out.println("[정보] 삭제 대상이 존재하지 않습니다.");
		}	
	}
	
	/*
	 * 5.학생등록
	 * */
	public void insertStudent() {
		Student student = new Student();
		String studentID = null;
		System.out.println();
		try {
			System.out.println("[ 학생 등록 ]");
			sc.nextLine();
			System.out.print("학번 입력 > ");
			studentID = sc.nextLine().toUpperCase();
			if(mgr.getStudent(studentID) != null) {
				System.out.println("[오류] 이미 존재하는 학번입니다.");
				return;
			}else {
				student.setStudentID(studentID);
				System.out.print("이름 입력 > ");
				student.setStudentName(sc.nextLine().toUpperCase());
				System.out.print("졸업요건 버전 > ");
				student.setVersion(sc.nextDouble());
				System.out.println("**1.경영일반 2.회계 3.매니지먼트 4.금융 5.마케팅 6.창업 7.애널리틱스");
				System.out.print("트랙 입력 > ");
				student.setTrack(sc.nextInt());
				System.out.print("학년 입력 > ");
				student.setGrade(sc.nextInt()); 
				System.out.print("총 취득학점 > ");
				student.setGrossCredit(sc.nextInt());
				System.out.print("전공 취득학점 > ");
				student.setGrossCreditMajor(sc.nextInt());
				System.out.print("교양 취득학점 > ");
				student.setGrossCreditLiberal(sc.nextInt());
				System.out.print("토익 (없을 시 0)> ");
				student.setToeic(sc.nextInt());
				System.out.print("hsk (없을 시 0)> ");
				student.setHsk(sc.nextInt());
				System.out.print("jpt (없을 시 0)> ");
				student.setJpt(sc.nextInt());
			}
		} catch (InputMismatchException e) {
			System.out.println("[오류] 입력형식이 잘못되었습니다.");
			sc.nextLine();
			return;
		}
		boolean res = mgr.insertStudent(student);
		if(res) {
			System.out.println("[정보] 저장되었습니다.");
		}else {
			System.out.println("[오류] 저장 실패했습니다.");
		}
	}
	/*
	 * 6.학생검색
	 * */
	public void searchStudent() {
		String studentID = null;
		
		System.out.println();
		System.out.println("[ 학생 검색 ]");
		sc.nextLine();
		System.out.print("학번 입력 > ");
		studentID = sc.nextLine();
		Student student = mgr.getStudent(studentID);
		if(student != null) {
			System.out.println(student);
		}else {
			System.out.println("[알림] 해당 학번의 학생이 없습니다.");
		}
		
	}
	/*
	 * 7.학생 전체 조회
	 */
	public void listStudent(){
		System.out.println();
		System.out.println("[ 전체 학생 조회 ]");
		ArrayList<Student> list = mgr.listStudent();
		if(list != null && list.size() != 0) {
			System.out.println("학번\t\t이름\t졸업요건\t트랙\t학년\t총 취득학점\t전공학점\t교양학점\t토익\thsk\tjpt");
			System.out.println("===================================================================================================");
			for(Student student : list) {
				System.out.print(student.getStudentID() + "\t");
				System.out.print(student.getStudentName() + "\t");
				System.out.print(student.getVersion() + "\t");
				System.out.print(student.getTrack() + "\t");
				System.out.print(student.getGrade() + "\t");
				System.out.print(student.getGrossCredit() + "\t\t");
				System.out.print(student.getGrossCreditMajor() + "\t");
				System.out.print(student.getGrossCreditLiberal() + "\t");
				System.out.print(student.getToeic() + "\t");
				System.out.print(student.getHsk() + "\t");
				System.out.println(student.getJpt());
			}
		}else {
			System.out.println("[정보] 저장된 학생이 없습니다.");
		}
		
	}
	
	/*
	 * 8.학생수정
	 * */
	public void updateStudent() {
		String studentID = null;
		System.out.println();
		System.out.println("[ 학생정보 수정 ]");
		sc.nextLine();
		System.out.print("수정할 학생 번호: ");
		studentID = sc.nextLine().toUpperCase();
		Student student = mgr.getStudent(studentID);
		if(student == null) {
			System.out.println("[알림] 수정할 데이터가 없습니다.");
			return;
		}else {
			System.out.println();
			System.out.println("* 기존 정보");
			System.out.println("학번: " + studentID);
			System.out.println("이름: " + student.getStudentName());
			System.out.println("버전: " + student.getVersion());
			System.out.println("트랙: " + student.getTrack());
			System.out.println("학년: " + student.getGrade());
			System.out.println("총 전공 학점: " + student.getGrossCreditMajor());
			System.out.println("총 교양 학점: " + student.getGrossCreditLiberal());
			System.out.println("토익: " + student.getToeic());
			System.out.println("HSK: " + student.getHsk());
			System.out.println("JPT: " + student.getJpt());
			System.out.println("=======================");
			System.out.println("* 수정할 정보");
			System.out.print("이름: ");
			student.setStudentName(sc.nextLine().toUpperCase());
			System.out.print("버전: ");
			student.setVersion(sc.nextDouble());
			System.out.print("트랙: ");
			student.setTrack(sc.nextInt());
			System.out.print("학년: ");
			student.setGrade(sc.nextInt());
			System.out.print("총 전공 학점: ");
			student.setGrossCreditMajor(sc.nextInt());
			System.out.print("총 교양 학점: ");
			student.setGrossCreditLiberal(sc.nextInt());
			System.out.print("총 이수 학점: ");
			student.setGrossCredit(mgr.sumCredit(student.getGrossCreditMajor(), student.getGrossCreditLiberal()));
			System.out.println(student.getGrossCredit());
			System.out.print("토익: ");
			student.setToeic(sc.nextInt());
			System.out.print("HSK: ");
			student.setHsk(sc.nextInt());
			System.out.print("JPT: ");
			student.setJpt(sc.nextInt());
		}
		
		boolean res = mgr.updateStudent(student);
		if(res) {
			System.out.println("[알림] 수정되었습니다.");
		} else {
			System.out.println("[알림] 수정 실패하였습니다.");
		}
		
	}
	
	/*
	 * 9.학생삭제
	 * */
	public void deleteStudent() {
		String studentID = null;
		System.out.println();
		System.out.println("[ 학생 정보 삭제 ]");
		sc.nextLine();
		System.out.print("학번 입력 > ");
		studentID = sc.nextLine().toUpperCase();
		boolean res = mgr.deleteStudent(studentID);
		if(res) {
			System.out.println("[알림] 삭제되었습니다.");
		}else {
			System.out.println("[알림] 삭제할 데이터가 없습니다.");
		}
		
	}
	
	/*
	 * 10.졸업요건 등록
	 * */
	public void insertGradReq() {
		GradReq gradReq = new GradReq();
		System.out.println();
		System.out.println("[ 졸업요건 등록 ]");
		sc.nextLine();
		System.out.print("졸업 버전 > ");
		gradReq.setVersion(sc.nextDouble());
		System.out.print("총 요구 학점 > ");
		gradReq.setGradGrossCredit(sc.nextInt());
		System.out.print("총 전공 학점  > ");
		gradReq.setGradMajorCredit(sc.nextInt());
		System.out.print("총 교양 학점 > ");
		gradReq.setGradLiberalCredit(sc.nextInt());
		System.out.print("토익 점수 > ");
		gradReq.setToeic(sc.nextInt());
		System.out.print("hsk 점수 > ");
		gradReq.setHsk(sc.nextInt());
		System.out.print("jpt 점수 > ");
		gradReq.setJpt(sc.nextInt());
		
		boolean res = mgr.insertGradReq(gradReq);
		if(res) System.out.println("[정보] 저장되었습니다.");
		else System.out.println("[알림] 저장 실패했습니다.");
	}
	
	/*
	 * 11.졸업요건 조회
	 * */
	public void printGradReq() {
		ArrayList<GradReq> list = null;
		System.out.println();
		System.out.println("[ 졸업요건 출력 ]");
		list = mgr.listGradReq();
		if(list != null && list.size() != 0) {
			System.out.println("버전\t총 요구 학점\t총 전공 학점\t총 교양 학점\t토익\thsk\tjpt");
			System.out.println("===========================================================================");
			for(GradReq grad : list) {
				System.out.print(grad.getVersion() + "\t");
				System.out.print(grad.getGradGrossCredit() + "\t\t");
				System.out.print(grad.getGradMajorCredit() + "\t\t");
				System.out.print(grad.getGradLiberalCredit() + "\t\t");
				System.out.print(grad.getToeic() + "\t");
				System.out.print(grad.getHsk() + "\t");
				System.out.println(grad.getJpt());
			}
		} else {
			System.out.println("[정보] 저장된 졸업요건이 없습니다.");
		}
	}
	
	/*
	 * 12.졸업요건 수정
	 * */
	public void updateGradReq() {
		double version = 0;
		System.out.println();
		System.out.println("[ 졸업요건 수정 ]");
		sc.nextLine();
		System.out.print("수정할 졸업요건 버전: ");
		version = sc.nextDouble();
		
		GradReq gradReq = mgr.getGradReq(version);
		if(gradReq == null) {
			System.out.println("[알림] 수정할 데이터가 없습니다.");
			return;
		}else {
			System.out.println();
			System.out.println("* 기존 정보");
			System.out.println("버전: " + version);
			System.out.println("총 전공 학점: " + gradReq.getGradMajorCredit());
			System.out.println("총 교양 학점: " + gradReq.getGradLiberalCredit());
			System.out.println("총 이수 학점 : " + gradReq.getGradGrossCredit());
			System.out.println("토익 요건: " + gradReq.getToeic());
			System.out.println("HSK 요건: " + gradReq.getHsk());
			System.out.println("JPT 요건: " + gradReq.getJpt());
			System.out.println("=======================");
			System.out.println("* 수정할 정보");
			sc.nextLine();
			System.out.print("총 전공 학점: ");
			gradReq.setGradMajorCredit(sc.nextInt());
			System.out.print("총 교양 학점: ");
			gradReq.setGradLiberalCredit(sc.nextInt());
			System.out.print("총 이수 학점: ");
			gradReq.setGradGrossCredit(mgr.sumCredit(gradReq.getGradMajorCredit(), gradReq.getGradLiberalCredit()));
			System.out.println(gradReq.getGradGrossCredit());
			System.out.print("토익 요건: ");
			gradReq.setToeic(sc.nextInt());
			System.out.print("HSK 요건: ");
			gradReq.setHsk(sc.nextInt());
			System.out.print("JPT 요건: ");
			gradReq.setJpt(sc.nextInt());
		}
		
		boolean res = mgr.updateGradReq(gradReq);
		if(res) {
			System.out.println("[알림] 수정되었습니다.");
		} else {
			System.out.println("[알림] 수정 실패하였습니다.");
		}
	}
	
	/*
	 * 13.졸업요건 삭제
	 * */
	public void deleteGradReq() {
		double version = 0;
	
		System.out.println();
		System.out.println("[ 졸업요건 삭제 ]");
		sc.nextLine();
		System.out.print("삭제할 졸업요건 버전: ");
		version = sc.nextDouble();
		
		boolean res = mgr.deleteGradReq(version);
		if(res) {
			System.out.println("[알림] 삭제되었습니다.");
		}else {
			System.out.println("[알림] 삭제할 데이터가 없습니다.");
		}
	}
}
