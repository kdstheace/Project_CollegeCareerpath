package collegeCareer.vo;

public class Major extends Course{
	private static final String DISTINCT = "Àü°ø";
	
	public Major(String courseNo, String courseName, String profName, int courseCredit, int track,
			int gradeRequired, double score) {
		super(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
	}
	
	public Major() {}

	@Override
	public String toString() {
		return  "[" + DISTINCT + "]" + super.toString();
	}

}
