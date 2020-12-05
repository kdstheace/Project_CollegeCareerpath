package collegeCareer.dao;

import java.util.ArrayList;

import collegeCareer.vo.GradReq;
import collegeCareer.vo.LibArts;
import collegeCareer.vo.Major;
import collegeCareer.vo.Student;

public interface CCMapper {

	//1-1. 전공등록
	public int insertMajor(Major major);
	//1-2. 교양등록
	public int insertLibArts(LibArts libArts);
	//0-1. 전공검색
	public Major getMajor(String courseNo);
	//0-2. 교양검색
	public LibArts getLibArts(String courseNo);
	//2-1. 전공과목 조회
	public ArrayList<Major> listMajor();
	//2-2. 교양과목 조회
	public ArrayList<LibArts> listLibArts();
	//3-1. 전공수업 수정
	public int updateMajor(Major major);
	//3-2. 교양수업 수정
	public int updateLibArts(LibArts libArts);
	//4-1. 전공수업 삭제
	public int deleteMajor(String courseNo);
	//4-2. 교양수업 삭제
	public int deleteLibArts(String courseNo);
	//5. 학생 등록
	public int insertStudent(Student student);
	//6. 특정학생 검색
	public Student getStudent(String studentID);
	//7. 학생 전체 조회
	public ArrayList<Student> listStudent();
	//8. 학생수정
	public int updateStudent(Student student);
	//9. 학생삭제
	public int deleteStudent(String studentID);
	//10. 졸업요건 추가
	public int insertGradReq(GradReq gradReq);
	//11. 졸업요건 전체조회
	public ArrayList<GradReq> listGradReq();
	//11-1. 특정 졸업요건만 가져오기
	public GradReq getGradReq(double version);
	//12. 졸업요건 수정
	public int updateGradReq(GradReq gradReq);
	//13. 졸업요건 삭제
	public int deleteGradReq(double version);
	//3.전공추천
	public ArrayList<Major> recommendMajor(Student student);

	

}
