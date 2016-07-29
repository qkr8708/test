package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDAO {

		public ProductDAO(){
			
		}
		
		public void insertProduct(Product productVO) throws Exception{
			Connection con = DBUtil.getConnection();
			
			String sql = "insert into PRODUCT values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
//			stmt.setInt(1, productVO.getProdNo());
			stmt.setString(1, productVO.getProdName());
			stmt.setString(2, productVO.getProdDetail());
			stmt.setString(3, productVO.getManuDate());
			stmt.setInt(4, productVO.getPrice());
			stmt.setString(5, productVO.getFileName());
//			stmt.setDate(7, productVO.getRegDate());
			stmt.executeQuery();
			
			con.close();
		}
		
		public Product findProduct(int prodNo) throws Exception{
			Connection con = DBUtil.getConnection();
			
			String sql = "select * from PRODUCT WHERE PROD_NO = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodNo);
			
			ResultSet rs = stmt.executeQuery();
			
			Product productVO = null;
			while(rs.next()){
			productVO = new Product();
//			stmt.setInt(1, productVO.getProdNo());
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
//			stmt.setDate(7, productVO.getRegDate());
			stmt.executeQuery();
			}	
			con.close();
			return productVO;
		}
		
		public HashMap<String,Object> getProductList(Search searchVO) throws Exception {
			
			Connection con = DBUtil.getConnection();
			
			String sql = "select * from PRODUCT";
			if (searchVO.getSearchKeyword()!="" && searchVO.getSearchKeyword()!=null) {
				if (searchVO.getSearchCondition().equals("0")) {
					sql += " where PROD_NO='" + searchVO.getSearchKeyword()+"'";
							
				} else if (searchVO.getSearchCondition().equals("1")) {
					sql += " where PROD_NAME LIKE '" + searchVO.getSearchKeyword()+"%'";
							
				} else if (searchVO.getSearchCondition().equals("2")) {
					sql += " where PRICE='" + searchVO.getSearchKeyword()+"'";
					
				}	
			}
			sql += " order by PROD_NO";

			PreparedStatement stmt = 
				con.prepareStatement(	sql,
															ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();

			rs.last();
			int total = rs.getRow();
			System.out.println("로우의 수:" + total);

			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("count", new Integer(total));
			System.out.println("map"+map);
			
		
			
			rs.absolute(searchVO.getCurrentPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			
			
			System.out.println("searchVO.getCurrentPage():" + searchVO.getCurrentPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

			ArrayList<Product> list = new ArrayList<Product>();
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					Product vo = new Product();
					vo.setProdNo(rs.getInt("PROD_NO"));
					vo.setProdName(rs.getString("PROD_NAME"));
					vo.setProdDetail(rs.getString("PROD_DETAIL"));
					vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
					vo.setPrice(rs.getInt("PRICE"));
					vo.setFileName(rs.getString("IMAGE_FILE"));
					vo.setRegDate(rs.getDate("REG_DATE"));
//					vo.setPhone(rs.getString("CELL_PHONE"));
//					vo.setAddr(rs.getString("ADDR"));
//					vo.setEmail(rs.getString("EMAIL"));
//					vo.setRegDate(rs.getDate("REG_DATE"));

					list.add(vo);
					if (!rs.next())
						break;
				}
			}
			System.out.println("list.size() : "+ list.size());
			map.put("list", list);
			System.out.println("map().size() : "+ map.size());

			con.close();
				
			return map;
		}
		
		public void updateProduct(Product productVO) throws Exception {
			
			Connection con = DBUtil.getConnection();

			String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, productVO.getProdName());
			stmt.setString(2, productVO.getProdDetail());
			stmt.setString(3, productVO.getManuDate());
			stmt.setInt(4, productVO.getPrice());
			stmt.setString(5, productVO.getFileName());
			stmt.setInt(6, productVO.getProdNo());
			stmt.executeUpdate();
			
			con.close();
		}
}
