<%--@page import="java.util.*"--%>
<%--@page import="com.model2.mvc.service.product.vo.*"--%>

<%--@page import="com.model2.mvc.common.*"--%>
<%--@page import="com.model2.mvc.common.Page"--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	System.out.println(searchVO);
	int total=0;
	ArrayList<ProductVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		System.out.println("total값: "+total);
		list=(ArrayList<ProductVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit();
		
		if(total%searchVO.getPageUnit()>0){
			totalPage += 1;
		}
	}

--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">


<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--

//검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
function fncGetProductList(currentPage){
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
-->



</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=manage" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						상품 관리
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%--
		if(searchVO.getSearchCondition() != null) {
	--%>
	
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px" value="">
				
			<%--
				if(searchVO.getSearchCondition().equals("0")){
			--%>
				
				<option value="0" ${! empty searchVO.searchCondition.equals("0") ? "selected" : "" }>상품번호</option>
				<option value="1" ${! empty searchVO.searchCondition.equals("1") ? "selected" : "" }>상품명</option>
				<option value="2" ${! empty searchVO.searchCondition.equals("2") ? "selected" : "" }>상품가격</option>
				
			<%--
				}else if(searchVO.getSearchCondition().equals("1")){
			--%>
				<!-- <option value="0">상품번호</option>
				<option value="1" selected>상품명</option>
				<option value="2">상품가격</option>
				 -->
			<%--
				}else{
			--%>
			
				<!-- <option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2" selected>상품가격</option>
				 -->
			<%--
				}
			--%>			
			</select>
			<input type="text" name="searchKeyword" value="${searchVO.searchKeyword ? searchVO.searchKeyword : ""}" class="ct_input_g" style="width:200px; height:19px" />
		</td>
		<%--
			}else{
		--%>
			<!--<td align="right">
				<select name="searchCondition" class="ct_input_g" style="width:80px">
					<option value="0">상품번호</option>
					<option value="1">상품명</option>
					<option value="2">상품가격</option>
				</select>
				<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
			</td>-->
		<%--
			}
		--%>	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${ resultPage.totalCount }건수, 현재 ${ resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		<%--
			int no=list.size();
			System.out.println(no);
			for(int i=0; i<list.size(); i++) {
				ProductVO vo = (ProductVO)list.get(i);
				System.out.println(vo);
			
		--%>
	<c:forEach var="vo" items="${map.list}">	
	<c:set var="i" value="${ i+1 }"/>	
	<tr class="ct_list_pop">
		<td align="center">${ i }</td>
		<td></td>
				
		<td align="left"><a href="/getProduct.do?prodNo=${ vo.prodNo }&menu=${ param.menu }&name=${ vo.prodName }">${ vo.prodName }</a></td>
		
		<td></td>
		<td align="left">${ vo.price }</td>
		<td></td>
		<td align="left">${ vo.manuDate }</td>
		<td></td>
		<td align="left">배송</td>
	</tr>
	</c:forEach>	
	<%-- } --%>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage"/>
		
			<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
			</c:if>
			<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
					<a href="javascript:fncGetProductList('${ resultPage.currentPage-1}')">◀ 이전</a>
			</c:if>
			
				<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
					<a href="javascript:fncGetProductList('${ i }');">${ i }</a>
				</c:forEach>
			
			<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
					이후 ▶
			</c:if>
			<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
					<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">이후 ▶</a>
			</c:if>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
