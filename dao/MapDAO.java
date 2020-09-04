package com.pleasetoilet.dao;

import java.lang.Math;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapDAO {
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
	
	public ArrayList<double[]> checkRange(double lat, double lng, int meter) {
		
		// 범위 안에 있는 화장실 위도 경도 저장할 배열
		ArrayList<double[]> checkedRange = new ArrayList<double[]>();
		
		String sql="select latitude, longitude from toilet";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			double clat, clng, temp;
			
			while(rs.next()) {
				clat = rs.getDouble("latitude");
				clng = rs.getDouble("longitude");
				
				temp = (((Math.acos(
						Math.sin(lat*Math.PI/180.0) *
						Math.sin(clat*Math.PI/180.0) +
						Math.cos(lat*Math.PI/180.0) *
						Math.cos(clat*Math.PI/180.0) *
						Math.cos((lng-clng)*Math.PI/180.0)))
						*180/Math.PI) * 60 * 1.1515) * 1609.344;
				
				if (temp <= meter) {
					checkedRange.add(new double[] {clat, clng});
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return checkedRange;
	
	}
}
