package collegeCareer.manager;

import java.util.ArrayList;

import collegeCareer.dao.CCDAO;
import collegeCareer.vo.Course;
import collegeCareer.vo.GradReq;
import collegeCareer.vo.Major;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Student;

public class CCManager {
	private CCDAO dao = new CCDAO();
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;
	
	/*
	 * 생성자
	 * */
	public CCManager() {
		studentList = new ArrayList<>();
		courseList = new ArrayList<>();
	}
	
	/*
	 * 1-1.전공과목 등록
	 */
	public boolean insertMajor(Major major) {
		int count = dao.insertMajor(major);
		return count > 0 ? true : false;
	}
	
	/*
	 * 1-2.교양과목 등록
	 */
	public boolean insertLibArts(LibArts libArts) {
		int count = dao.insertLibArts(libArts);
		return count > 0 ? true : false;
	}
	
	
	/*
	 * 00 과목검색
	 */
	public Course getCourse(String courseNo) {
		Course course = dao.getCourse(courseNo);
		return course;
	}
	
	/*
	 * 2-1.전공과목 조회
	 */
	public ArrayList<Major> listMajor(){
		ArrayList<Major> list = dao.listMajor();
		return list;
	}
	
	/*
	 * 2-2. 교양과목 조회
	 */
	public ArrayList<LibArts> listLibArts(){
		ArrayList<LibArts> list = dao.listLibArts();
		return list;
	}
	/*
	 * 3. 과목수정
	 */
	public boolean updateCourse(Course course) {
		int count = dao.updateCourse(course);
		return count > 0 ? true : false;
	}
	/*
	 * 4. 과목삭제
	 */
	public boolean deleteCourse(String courseNo) {
		int count = dao.deleteCourse(courseNo);
		return count > 0 ? true : false;
	}
	
	/*
	 * 5.학생등록
	 * */
	public boolean insertStudent(Student student) {
		int count = dao.insertStudent(student);
		return count > 0 ? true : false;
	}
	
	/*
	 * 5-1. 총 학점 계산
	 */
	public int sumCredit(int creditMajor, int creditLiberal) {
		int grossCredit = creditMajor + creditLiberal;
		return grossCredit;
	}
	
	/*
	 * 6. 특정학생 검색
	 */
	public Student getStudent(String studentID) {
		Student student = dao.getStudent(studentID);
		return student;
	}
	/*
	 * 7.학생 전체 조회
	 */
	public ArrayList<Student> listStudent(){
		ArrayList<Student> list = dao.listStudent();
		return list;
	}
	/*
	 * 8.학생 수정
	 */
	public boolean updateStudent(Student student) {
		int count = dao.updateStudent(student);
		return count > 0 ? true : false;
	}
	
	/*
	 * 9.학생 삭제
	 */
	public boolean deleteStudent(String studentID) {
		int count = dao.deleteStudent(studentID);
		return count > 0 ? true : false;
	}
	/*
	 * 10.졸업요건 등록
	 */
	public boolean insertGradReq(GradReq gradReq) {
		int count = dao.insertGradReq(gradReq);
		if(count > 0) return true;
		return false;
	}
	/*
	 * 11.졸업요건 전체조회
	 */
	public ArrayList<GradReq> listGradReq(){
		ArrayList<GradReq> list = dao.listGradReq();
		return list;
	}
	
	/*
	 * 12.특정 졸업요건만 가져오기
	 */
	public GradReq getGradReq(double version) {
		GradReq gradReq = dao.getGradReq(version);
		return gradReq;
	}
	/*
	 * 졸업요건 수정
	 * */
	public boolean updateGradReq(GradReq gradReq) {
		int count = dao.updateGradReq(gradReq);
		if(count > 0) {
			return  true;
		}
		return false;
	}
	/*
	 * 졸업요건 삭제
	 */
	public boolean deleteGradReq(double version) {
		int count = dao.deleteGradReq(version);
		return count > 0 ? true : false;
	}
	/*
	 * 전공추천
	 */
	public ArrayList<Major> recommendMajor(Student student){
		ArrayList<Major> list = dao.recommendMajor(student);
		return list;
	}
}
