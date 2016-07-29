package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService productService = new ProductServiceImpl();
		Product vo = productService.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		System.out.println("vo: "+vo);
		
		String forward = null;
		
		if(request.getParameter("menu").equals("manage")){
			forward = "forward:/product/getProductManage.jsp";
			System.out.println("forward : " + forward);
		}else{
			forward = "forward:/product/getProduct.jsp";
		}
		
		return forward;
		
	}
	
}
