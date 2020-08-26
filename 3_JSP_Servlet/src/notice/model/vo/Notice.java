package notice.model.vo;

import java.sql.Date;

public class Notice {
	private int nNO;
	private String nTitle;
	private String nContent;
	private String nWriter;
	private int nCount;
	private Date nDate;
	private String status;
	
	public Notice() {}

	public Notice(String nTitle, String nContent, String nWriter, Date nDate) {
		super();
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nWriter = nWriter;
		this.nDate = nDate;
	}

	public Notice(int nNO, String nTitle, String nContent, String nWriter, int nCount, Date nDate) {
		super();
		this.nNO = nNO;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nWriter = nWriter;
		this.nCount = nCount;
		this.nDate = nDate;
	}

	public Notice(int nNO, String nTitle, String nContent, Date nDate) {
		super();
		this.nNO = nNO;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
	}

	public Notice(int nNO, String nTitle, String nContent, String nWriter, int nCount, Date nDate, String status) {
		super();
		this.nNO = nNO;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nWriter = nWriter;
		this.nCount = nCount;
		this.nDate = nDate;
		this.status = status;
	}

	public int getnNO() {
		return nNO;
	}

	public void setnNO(int nNO) {
		this.nNO = nNO;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nWriter) {
		this.nWriter = nWriter;
	}

	public int getnCount() {
		return nCount;
	}

	public void setnCount(int nCount) {
		this.nCount = nCount;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [nNO=" + nNO + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nWriter=" + nWriter
				+ ", nCount=" + nCount + ", nDate=" + nDate + ", status=" + status + "]";
	}
	
	

}