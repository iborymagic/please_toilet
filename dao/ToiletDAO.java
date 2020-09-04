package com.pleasetoilet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pleasetoilet.vo.ToiletVO;

@Component
public class ToiletDAO {
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
	  /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
         
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
         
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        
        return (dist);
    }
     
 
    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
     
    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
 	
	public List<ToiletVO> findToilet(double latitude,double longitude, int dis){
		List<ToiletVO> list= new ArrayList<ToiletVO>();
		try {
			conn=dataSource.getConnection();
			String sql="select tno, latitude, longitude, bigName, smallName from toilet";
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()) {
				double dbLat=Double.parseDouble(rs.getString("latitude"));
				double dbLon=Double.parseDouble(rs.getString("longitude"));
				String bname=rs.getString("bigName");
				String sname=rs.getString("smallName");
				int getDis=(int)distance(latitude,longitude,dbLat,dbLon);
				/*, */
				if(getDis<=dis) {
					list.add(new ToiletVO(rs.getString("tno"), Double.toString(dbLat), Double.toString(dbLon), bname, sname, getDis));	
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}

}
