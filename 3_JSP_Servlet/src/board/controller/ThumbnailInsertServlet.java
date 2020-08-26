package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.th")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
//		String title = request.getParameter("title");
//		System.out.println(title);
		
		//**겁나게 어려워짐
		//인코딩 타입이 멀티파트폼-데이터로 전송되었는지 확인해보는 요소
		//enctype이 multipart/form-date로 전송되었는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 *10; //10MByte로 전송파일 용량제한
			String root = request.getSession().getServletContext().getRealPath("/");//웹 서버 컨테이너 경로 추출
			
			//한번 뽑아보기...?
//			System.out.println(request.getSession());
//			System.out.println(request.getSession().getServletContext());
//			System.out.println(request.getSession().getServletContext().getRealPath("/"));
			String savePath = root + "thumbnail_uploadFiles/"; //파일 저장해둔곳
			
//			System.out.println(savePath); // 이걸 뽑아보면, 맨처음 서버 설정할때 체크했던 Server modules without publishing
			//퍼블리싱을 안한다고 해야 바로 루트 확인이 가능.
			
			/*
			= new MultipartRequest(request, savePath, maxSize, new DefaultFileRenamePolicy()); //plicy는 파일명 변경 방법을 제시해라
			DefaultFileRenamePolicy : 뒤에 숫자를 넣어서 자동 저장해준다.
			aaa.zip
			aaa1.zip
			aaa2.zip
			
			*/	
			
			MultipartRequest multiRequest
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy()); //plicy는 파일명 변경 방법을 제시해라
			//매개변수중, policy 부분은 우리가 파일을 보낼 때 리네임하는 게 필수인데, 그 파일명 변경방법을 기술하라는 뜻.
			//new DefaultFileRenamePolicy() 기본적으로 제공해주는 방식이 있지만, 우리가 연습을 위해 new MyFileRenamePolicy()으로 하는것.
			
			ArrayList<String> saveFiles = new ArrayList<String>();   //바뀐 파일의 이름저장
			ArrayList<String> originFiles = new ArrayList<String>(); //원본 파일의 이름저장
			
			Enumeration<String> files = multiRequest.getFileNames(); //폼에서 전송된 파일 리스트들의 name 반환
			while(files.hasMoreElements()) {
				String name= files.nextElement(); // 전송순서 역순으로 가져옴
//				System.out.println(name);// 사진넣었을때 넣은 순서의 역순으로 파일 이름을 가져옴 
				
//				System.out.println(multiRequest.getFilesystemName(name)); //리네임된 파일이름을 가져오고 있음
				if(multiRequest.getFilesystemName(name) != null) { //파일이 들어왔다면
				//	multiRequest.getFilesystemName() : 
				//	MyRenameRilePolicy의 rename메소드에서 작성한 대로 rename된 파일명을 가져옴
					
					saveFiles.add(multiRequest.getFilesystemName(name)); //바뀐이름은 saveFiles에 
					originFiles.add(multiRequest.getOriginalFileName(name)); //원래이름은 originFiles에 넣고있는것.
					//getOriginalFileName(): 실제 사용자가 업로드 할때의 파일명
				}
			} //여기까지 사진 다 받아온것.
			
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String bwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
			//여기까지 제목, 내용, 작성자까지 받다옴.
			
			//받아온거
//===================================================================================================================================
			//디비에 보낼것
			
			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setbWriter(bwriter);
			
			System.out.println(saveFiles);
			System.out.println(originFiles); //사진이 역순으로 들어오다보니 섬네일에 들어가야하는 사진이 제일 마지막으로 들어옴.
			
			//어레이리스트를 통해서 순서를 다시 바꾸어주기.
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size() -1; i >= 0; i--) {//-1해주는 이유는 제로인덱스이기때문임. //i가  0보다 클때까지,
				Attachment at = new Attachment();
				at.setFilePath(savePath); //어디에 저장할건지
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				//여기까지 거꾸로 들어가 있는것을 넣어주면된다고 함...예? ㅠㅠ 쌤..모르겠어요...
				
				//파일레벨
				if(i == originFiles.size() -1) { //내가가진 인덱스 i가 originFiles.size() -1과 같으면 = 맨처음꺼.
					at.setFileLevel(0);
				}else {
					at.setFileLevel(1);
				}
				fileList.add(at);
			 }
			
			int result = new BoardService().insertThumbnail(b, fileList);
				
			if(result > 0) {
				response.sendRedirect("list.th");
			}else {//인설트에 실패했다면? 파일이 들어갈 필요가 없기때문에 지워주기.
				for(int i =0; i< saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i)); //경로+파일이름이 객체로 만들어지고
					failedFile.delete(); //그 객체를 지워주겠다.
					
				}
				request.setAttribute("msg", "사진 게시판 등록에 실패하였습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
