package ChatBot;

public class Faqs {
	
	private int faqId;
	private String faqTitle;
	private String faqQuery;
	private String faqAnswer;
	private String faqUserType;
	
	public Faqs(int faqId, String faqTitle, String faqQuery, String faqAnswer, String faqUserType) {
		this.faqId = faqId;
		this.faqTitle = faqTitle;
		this.faqQuery = faqQuery;
		this.faqAnswer = faqAnswer;
		this.faqUserType = faqUserType;
	}
	
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqQuery() {
		return faqQuery;
	}

	public void setFaqQuery(String faqQuery) {
		this.faqQuery = faqQuery;
	}

	public String getFaqAnswer() {
		return faqAnswer;
	}
	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}
	public String getFaqUserType() {
		return faqUserType;
	}
	public void setFaqUserType(String faqUserType) {
		this.faqUserType = faqUserType;
	}

}
