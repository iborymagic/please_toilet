package com.pleasetoilet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pleasetoilet.vo.ReviewVO;

@Component
public class ReviewDAO {
	
	@Autowired
	private DataSource dataSource;
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	public void closeDB() {
		if(rs!=null) {
			try {
				rs.close();
			} catch(Exception e1) {
				e1.printStackTrace();
			} finally {
				rs=null;
			}
		}
		if(st!=null) {
			try {
				st.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			} finally {
				st=null;
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch(Exception e3) {
				e3.printStackTrace();
			} finally {
				conn=null;
			}
		}
	}

	public List<ReviewVO> getReview(String tno, String id){
		List<ReviewVO> list = new ArrayList<ReviewVO>();
		try {
			conn = dataSource.getConnection();			
			String sql = "select review.uno, contents, mem.id, toilet.smallName, usedate "
					+ "from mem, review, usetoilet, toilet "
					+ "where review.uno = usetoilet.uno "
					+ "and toilet.tno = usetoilet.tno "
					+ "and toilet.tno = ? "
					+ "and mem.mno = usetoilet.mno";
			st = conn.prepareStatement(sql);
			st.setString(1, tno);
			rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new ReviewVO(rs.getInt("uno"), rs.getString("contents"), rs.getString("id"), rs.getString("smallName"), rs.getString("usedate")));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		if(list.size() == 0)
			return null;
		else return list;
	}
	
	public void saveReview(String uno, String contents) {
		try { 
			conn=dataSource.getConnection();			
			String sql="insert into review values(?, ?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(uno));
			st.setString(2, contents);
			st.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
}



