package com.model2.mvc.view.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListUserAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search searchVO = new Search();
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
						
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("currentPage" + currentPage);
		}
		//page=Integer.parseInt(request.getParameter("page"));
		
		String pageUnit = request.getServletContext().getInitParameter("pageUnit");
		String pageSize = request.getServletContext().getInitParameter("pageSize");
		
		
		searchVO.setCurrentPage(currentPage);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		
		
		UserService service = new UserServiceImpl();
		HashMap<String,Object> map=service.getUserList(searchVO);
		
		System.out.println("Controller :: "+searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/user/listUser.jsp";
	}
}