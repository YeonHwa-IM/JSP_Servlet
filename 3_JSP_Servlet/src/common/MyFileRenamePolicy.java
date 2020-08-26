package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
		
		//시간을 이용해서 들어오는 파일 이름 전부 바꿀것임. + 랜덤숫자도 이용
		
		//시간가져오기.
		long currentTime = System.currentTimeMillis();
		
		//시간가져와서 원하는 포멧으로 바꾸기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)Math.random() * 100000;
		//시간+랜덤숫자로 안겹치는 거까지 완성

		//확장자 가져오기
		String name = originFile.getName(); //파일이름가져오기
		int dot = name.lastIndexOf("."); //확장자앞의 .가져오기
//		file.jsp
//		file.jsp.txt
		
		String ext = null; // .이 없는 확장자가 있을 수 있으니까 적어주기
		if(dot != -1) { // .(dot)이 존재한다면
			ext =name.substring(dot);
		}else {
			ext ="";
		}
		
		String fileName = sdf.format(new Date(currentTime))+ ranNum + ext; //date는 utill
		File newFile = new File(originFile.getParent(), fileName); //오리지널파일의 부모경로를 가져와서, 파일이름을 넣어주겠다.
		
		return newFile; //마이리네임 폴리시 완성.
	}

}
