package com.model2.mvc.view.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		Product productVO = new Product();
//		//productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
//		productVO.setProdName(request.getParameter("prodName"));
//		//System.out.println("뭐냐1"+request.getParameter("prodName"));
//		productVO.setProdDetail(request.getParameter("prodDetail"));
//		//System.out.println("뭐냐2"+request.getParameter("prodDetail"));
//		productVO.setManuDate(request.getParameter("manuDate"));
//		
//		System.out.println(productVO);
//		//System.out.println("뭐냐3"+request.getParameter("manuDate"));
//		System.out.println("request.getParameter('price')"+request.getParameter("price"));
//		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
//		//System.out.println("뭐냐4"+request.getParameter("price"));
//		productVO.setFileName(request.getParameter("fileName"));
//		
//		//System.out.println("뭐냐5"+request.getParameter("fileName"));
//		//productVO.setRegDate(date.valueOf(request.getParameter("regDate")));				
//		//productVO.setProTranCode(request.getParameter("proTranCode"));
//		//System.out.println((ProductVO)productVO);
//		HttpSession session = request.getSession(true);
//		
//		if(productVO != null){
//			session.setAttribute("productVO", productVO);
//			
//		}
//		
		if(FileUpload.isMultipartContent(request)){
//			//==> Eclipse workspace / Project 변경시 변경할 것
//			//String temDir = "D:\\workspace03(Projcet&analysis)//9941.1.1.Model2MVCShop(ins)\\WebContent\\images\\uploadFiles\\"
			String temDir = "c:\\workspace\\01.Model2MVCShop(stu)\\WebContent\\images\\uploadFiles\\";
//		
//		
//		}
		
		DiskFileUpload fileUpload = new DiskFileUpload();
		fileUpload.setRepositoryPath(temDir);
		fileUpload.setSizeMax(1024*1024*10);
		fileUpload.setSizeThreshold(1024*100);
		
		if(request.getContentLength() < fileUpload.getSizeMax()){
			Product product = new Product();
			
			StringTokenizer token = null;
			
			//parseRequest()는 FileItem 을 포함하고 있는 List타입을 리턴한다.
			
			List fileItemList = fileUpload.parseRequest(request);
			int Size = fileItemList.size();
			for (int i=0; i< Size; i++){
				FileItem fileItem = (FileItem) fileItemList.get(i);
				
				if(fileItem.isFormField()){
					if(fileItem.getFieldName().equals("manuDate")){
						token = new StringTokenizer(fileItem.getString("euc-kr"), "-");
						String manuDate = token.nextToken() + token.nextToken()+token.nextToken();
						product.setManuDate(manuDate);
						
					}else if(fileItem.getFieldName().equals("prodName")){
						product.setProdName(fileItem.getString("euc-kr"));
					}else if(fileItem.getFieldName().equals("prodDetail")){
						product.setProdDetail(fileItem.getString("euc-kr"));
					}else if(fileItem.getFieldName().equals("price")){
						product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
					}	
				}else{//파일 형식이면....
					//out.prinnt("파일: " + fileItem.getFieldName() + "=" + 
					//fileItem.getName());

					if(fileItem.getSize()>0){
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1){
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1 );
						product.setFileName(fileName);
						
						try{
							File uploadedFile = new File(temDir, fileName);
							fileItem.write(uploadedFile);
						}catch(IOException e){
							System.out.println(e);
						}
						
					}else{
						product.setFileName("../../images.empty.GIF");
					}

				}
			}
			
			ProductServiceImpl service = new ProductServiceImpl();
			service.addProduct(product);
			
			request.setAttribute("product", product);
		}else{//업로드하는 파일이 setSizeMax보다 큰 경우
			
			int overSize = (request.getContentLength() / 1000000);
			System.out.println("<script>alert('파일크기는 1MB까지입니다 올리신 파일 용량은" +overSize + "MB입니다')");
			
			System.out.println("history.back();</script>");
		}
		
		
	}else{
		System.out.println("인코딩 타입이 multipart/form-data가 아닙니다..");
	}
		
//		ProductService productService = new ProductServiceImpl();
//		productService.addProduct(product);
//		
//		request.setAttribute("productVO", product);
//		
		
		
		return "forward:/product/addProductViewResult.jsp";
	}
	
}
