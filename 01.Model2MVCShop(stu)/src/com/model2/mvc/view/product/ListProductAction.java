package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search searchVO = new Search();
		System.out.println("searchVO: "+searchVO);
		int currentPage =1;
		
		System.out.println("어어어==="+request.getParameter("currentPage"));
		
		
		
		if(request.getParameter("currentPage") != null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("currentPage"+currentPage);	
		}	
		
		
		searchVO.setCurrentPage(currentPage);
		System.out.println("어어어2==="+request.getParameter("currentPage"));
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		
		searchVO.setPageUnit(pageUnit);
		
//		searchVO.setPageUnit(searchVO.getPageUnit());
		System.out.println(searchVO);
		ProductService service = new ProductServiceImpl();
		HashMap<String,Object> map=service.getProductList(searchVO);
		
		System.out.println("[pageUnit] : "+ searchVO.getPageUnit());
		System.out.println("[pageSize] : "+ searchVO.getPageUnit());
//		System.out.println("[totalcount] : "+map.get("totalCount"));
		System.out.println("[currentPage] : "+ currentPage);
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("count")).intValue(), pageUnit, pageSize);
		
		System.out.println(resultPage);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("resultPage", resultPage);
		
		return "forward:/product/listProduct.jsp";
	}
	
	
	
}
