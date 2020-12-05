package collegeCareer.vo;

public class Student {
	private String studentID;
	private String password;
	private String studentName;
	private double version;
	private int track;
	private int grade;
	private int grossCredit;
	private int grossCreditMajor;
	private int grossCreditLiberal;
	private int toeic; 
	private int hsk;
	private int jpt;
	
	//생성자
	public Student() {}
	
	public Student(String studentID, String studentName, double version, int track, int grade, int grossCredit,
			int grossCreditMajor, int grossCreditLiberal, int toeic, int hsk, int jpt) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.version = version;
		this.track = track;
		this.grade = grade;
		this.grossCredit = grossCredit;
		this.grossCreditMajor = grossCreditMajor;
		this.grossCreditLiberal = grossCreditLiberal;
		this.toeic = toeic;
		this.hsk = hsk;
		this.jpt = jpt;
	}
	
// 게터, 세터

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrossCredit() {
		return grossCredit;
	}

	public void setGrossCredit(int grossCredit) {
		this.grossCredit = grossCredit;
	}

	public int getGrossCreditMajor() {
		return grossCreditMajor;
	}

	public void setGrossCreditMajor(int grossCreditMajor) {
		this.grossCreditMajor = grossCreditMajor;
	}

	public int getGrossCreditLiberal() {
		return grossCreditLiberal;
	}

	public void setGrossCreditLiberal(int grossCreditLiberal) {
		this.grossCreditLiberal = grossCreditLiberal;
	}

	public int getToeic() {
		return toeic;
	}

	public void setToeic(int toeic) {
		this.toeic = toeic;
	}

	public int getHsk() {
		return hsk;
	}

	public void setHsk(int hsk) {
		this.hsk = hsk;
	}

	public int getJpt() {
		return jpt;
	}

	public void setJpt(int jpt) {
		this.jpt = jpt;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", studentName=" + studentName + ", version=" + version + ", track="
				+ track + ", grade=" + grade + ", grossCredit=" + grossCredit + ", grossCreditMajor=" + grossCreditMajor
				+ ", grossCreditLiberal=" + grossCreditLiberal + ", toeic=" + toeic + ", hsk=" + hsk + ", jpt=" + jpt
				+ "]";
	}


	
}
