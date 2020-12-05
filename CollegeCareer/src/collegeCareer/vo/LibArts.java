package collegeCareer.vo;

public class LibArts extends Course{
	private static final String DISTINCT = "±³¾ç";
	
	public LibArts(String courseNo, String courseName, String profName, int courseCredit, int track,
			int gradeRequired, double score) {
		super(courseNo, courseName, profName, courseCredit, track, gradeRequired, score);
	}

	public LibArts() {}
	
	@Override
	public String toString() {
		return "[" + DISTINCT + "]" + super.toString();
	}
	
}
