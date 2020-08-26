package board.model.vo;

import java.sql.Date;

public class Attachment {
	private int fId; //파일에 대한 아이디 (PK)
	private int bId; //보드연결 되어 있는 ID
	private String originName; //파일에 대한 원제, 설계에따라 다름 "내려받는 쪽"에서 받고자 하는 이름을 원제로 받겠다가 될수도 있고...라라라랄
	private String changeName; //처음에 파일을 넣을 때 날짜/시간등 변경된 이름으로 넣어야함(파일을 찾을 수있도록) 
	private String filePath; // 파일을 저장할 경로
	private Date uploadDate; // 언제올렸는지
	private int fileLevel; // 파일에 대한 레벨을 넣어서, 섬네일레벨은 0, 상세보기 사진은 1 이런식으로..
	private int downloadCount; 
	private String status;

	
	public Attachment() {}


	public Attachment(int bId, String changeName) {
		super();
		this.bId = bId;
		this.changeName = changeName;
	}


	public Attachment(int fId, int bId, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status) {
		super();
		this.fId = fId;
		this.bId = bId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
	}


	public int getfId() {
		return fId;
	}


	public void setfId(int fId) {
		this.fId = fId;
	}


	public int getbId() {
		return bId;
	}


	public void setbId(int bId) {
		this.bId = bId;
	}


	public String getOriginName() {
		return originName;
	}


	public void setOriginName(String originName) {
		this.originName = originName;
	}


	public String getChangeName() {
		return changeName;
	}


	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public int getFileLevel() {
		return fileLevel;
	}


	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	public int getDownloadCount() {
		return downloadCount;
	}


	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Attachment [fId=" + fId + ", bId=" + bId + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", downloadCount=" + downloadCount + ", status=" + status + "]";
	}
	
	 
}
