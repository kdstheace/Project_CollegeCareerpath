package collegeCareer.vo;

public abstract class Course {
	private String courseNo;
	private String courseName;
	private String profName;
	private int courseCredit;
	private int track;
	private int gradeRequired;
	private double score;
	
	
//생성자	
	public Course() {}
	
	public Course(String courseNo, String courseName, String profName, int courseCredit, int track,
			int gradeRequired, double score) {
		super();
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.profName = profName;
		this.courseCredit = courseCredit;
		this.track = track;
		this.gradeRequired = gradeRequired;
		this.score = score;
	}

//게터세터
	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public int getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public int getGradeRequired() {
		return gradeRequired;
	}

	public void setGradeRequired(int gradeRequired) {
		this.gradeRequired = gradeRequired;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "학정번호: " + courseNo + "\t 수업이름: " + courseName + "\t\t 교수명: " + profName
				+ "\t 학점: " + courseCredit + "\t 트랙: " + track + "\t 학년제한: " + gradeRequired
				+ "\t 별점: " + score;
	}
	
//출력
	
	
}