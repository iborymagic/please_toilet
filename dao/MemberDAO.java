package com.pleasetoilet.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pleasetoilet.vo.MemberVO;
import com.pleasetoilet.vo.useToiletVO;



@Component
public class MemberDAO {
	
	@Autowired
	private DataSource dataSource;
	@Autowired 
	private BCryptPasswordEncoder pwEncoder;
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
	
	public boolean checkId(String id) {
		boolean k = true;
		String sql = "select * from mem where id=?";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (rs.getString("id").equals(id)) {
					k = false;
					break;
				}
			}
			rs.close();
			rs = null;
			return k;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}

	public void signUp(MemberVO vo) {
		String sql = "insert into mem(id,pw,email) values(?,?,?)";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, vo.getId());
			st.setString(2,pwEncoder.encode(vo.getPw()));
			st.setString(3, vo.getEmail());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	public void changePW(int mno, String pw) {
		String sql="update mem set pw=? where mno=?";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1,pwEncoder.encode(pw));
			st.setInt(2, mno);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}	
	}
	public void changePW(String id, String pw) {
		String sql="update mem set pw=? where id=?";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1,pwEncoder.encode(pw));
			st.setString(2, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	public void deleteMember(int mno) {
		String sql="update mem set id=null where mno=?";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);			
			st.setInt(1, mno);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	public void deleteMember(String id) {
		String sql="update mem set id=null where id=?";
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	 
	public boolean loginCheck(String id, String pw) {
		String sql="select pw from mem where id=?";
		boolean pcheck=true;
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs=st.executeQuery();
			String getPW=null;
			while(rs.next()) {
				getPW=rs.getString("pw");
			}
			if(getPW==null)
				pcheck=false;
			else {
				if(pwEncoder.matches(pw, getPW)==false)
					pcheck=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return pcheck;
	}
	
	public boolean checkIDAndEmail(String id, String email) {
		String sql="select email from mem where id=?";
		boolean echeck=true;
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs=st.executeQuery();
			String getEmail=null;
			while(rs.next()) {
				getEmail=rs.getString("email");
			}
			if(getEmail==null)
				echeck=false;
			else {
				if(!email.equals(getEmail))
					echeck=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return echeck;
	}
	
	
	public List<String> findByEmail(String email) {
		String sql="select id from mem where email=? and id is not null";
		List<String> list=new ArrayList<String>();
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, email);
			rs=st.executeQuery();
			String getID=null;
			while(rs.next()) {
				String id=rs.getString("id");
				list.add(rs.getString("id"));
			}
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		if(list.size()==0)
			return null;
		else 
			return list;
	}
	
	public boolean checkIdAndPW(String id, String pw) {
		String sql="select pw from mem where id=?";
		String encodedPW=null;
		boolean key=true;
		try {
			conn=dataSource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs=st.executeQuery();
			while(rs.next()) {
				encodedPW=rs.getString("pw");
			}
			if(pwEncoder.matches(pw, encodedPW)==false)
				key=false;
			
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return key;
	}
	
	public ArrayList<useToiletVO> getUseList(String id){
		String sql="select uno,toilet.tno,usedate,smallName from mem, usetoilet, toilet where mem.id=? and mem.mno=usetoilet.mno and toilet.tno=usetoilet.tno";
		ArrayList<useToiletVO> list = new ArrayList<useToiletVO>();
		try {
			conn=dataSource.getConnection();
			st=conn.prepareStatement(sql);
			st.setString(1,id);
			rs=st.executeQuery();
			while(rs.next()) {
				list.add(new useToiletVO(rs.getInt("uno"), rs.getString("tno"), rs.getString("smallName"), rs.getString("usedate")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	
	public void saveUseList(String tno, String id) {
		try {
			String sql="insert into usetoilet(usedate,mno,tno) values(now(),(select mno from mem where id = ?),?)";
			conn=dataSource.getConnection();
			st=conn.prepareStatement(sql);
			st.setString(1,id);
			st.setString(2,tno);
			st.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
}
