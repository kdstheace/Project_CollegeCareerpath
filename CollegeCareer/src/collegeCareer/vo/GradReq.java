package collegeCareer.vo;

public class GradReq {
	private double version;
	private int gradGrossCredit;
	private int gradMajorCredit;
	private int gradLiberalCredit;
	private int toeic;
	private int hsk;
	private int jpt;

//»ý¼ºÀÚ
	public GradReq() {}

	public GradReq(double version, int gradGrossCredit, int gradMajorCredit, int gradLiberalCredit, int toeic,
			int hsk, int jpt) {
		super();
		this.version = version;
		this.gradGrossCredit = gradGrossCredit;
		this.gradMajorCredit = gradMajorCredit;
		this.gradLiberalCredit = gradLiberalCredit;
		this.toeic = toeic;
		this.hsk = hsk;
		this.jpt = jpt;
	}

//GETTER, SETTER
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public int getGradGrossCredit() {
		return gradGrossCredit;
	}

	public void setGradGrossCredit(int gradGrossCredit) {
		this.gradGrossCredit = gradGrossCredit;
	}

	public int getGradMajorCredit() {
		return gradMajorCredit;
	}

	public void setGradMajorCredit(int gradMajorCredit) {
		this.gradMajorCredit = gradMajorCredit;
	}

	public int getGradLiberalCredit() {
		return gradLiberalCredit;
	}

	public void setGradLiberalCredit(int gradLiberalCredit) {
		this.gradLiberalCredit = gradLiberalCredit;
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
	
	
	
}
