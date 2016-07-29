package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String prodNo=(String)request.getParameter("prodNo");
		
		
		Product productVO = new Product();
		
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(productVO);
		
//		HttpSession session = request.getSession();
//		session.setAttribute("prodNo", prodNo);
		
		
		request.setAttribute("productVO", productVO);
		
		System.out.println("prodNo: "+prodNo);
		

		return "forward:/getProduct.do?menu=search";
		
	}
	
}
