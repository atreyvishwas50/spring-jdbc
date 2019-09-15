package org.djwebpros.jdbcProject.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.djwebpros.jdbcProject.model.Entity;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDataSource implements Repoistories {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Entity> findAll() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		List<Entity> ans = new LinkedList<Entity>();
		try{
			
			con = this.dataSource.getConnection();
			stmt = con.createStatement();
			String sql = "SELECT * FROM entity";
			result = stmt.executeQuery(sql);
			while(result.next()) {
				
				Entity en = new Entity();
				en.setId(result.getLong("id"));
				en.setFirstName(result.getString("first_name"));
				en.setLastName(result.getString("last_name"));
				en.setEmail(result.getString("email"));
				en.setPassword(result.getString("password"));
				ans.add(en);
				
			}
			
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if(con != null) con.close(); 
				if(stmt != null) stmt.close();
				if(result != null) result.close();
				
			}
			
			catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
			
		}
		
		return ans;
		
	}
	
	@Override
	public Entity findById(long id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		Entity en = null;
		try {
			
			conn = this.dataSource.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM entity WHERE id = ?");
			stmt.setLong(1, id);
			result = stmt.executeQuery();
			en = new Entity();
			while(result.next()) {
				
				en.setId(result.getLong("id"));
				en.setFirstName(result.getString("first_name"));
				en.setLastName(result.getString("last_name"));
				en.setEmail(result.getString("email"));
				en.setPassword(result.getString("password"));
				
			}
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		finally {
			
			try {
				
				if(result != null) result.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			}
			
			catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return(en);
		
	}

	@Override
	public boolean insert(Entity en) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		int rowEffected = 0;
		try {
			
			con = this.dataSource.getConnection();
			String sql = "INSERT INTO entity(first_name, last_name, email, password) VALUES (?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, en.getFirstName());
			stmt.setString(2, en.getLastName());
			stmt.setString(3, en.getEmail());
			stmt.setString(4, en.getPassword());
			rowEffected = stmt.executeUpdate();
			
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally {
			
			try {
				
				if(stmt != null) stmt.close();
				if(con != null) con.close();
				
			}
			
			catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		if (rowEffected == 1)return(true);
		else  return(false);
		
	}
	
	@Override
	public boolean update(Entity en) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		int rowEffected = 0;
		try{
			
			con = this.dataSource.getConnection();
			String sql = "UPDATE entity SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, en.getFirstName());
			stmt.setString(2, en.getLastName());
			stmt.setString(3, en.getEmail());
			stmt.setString(4, en.getPassword());
			stmt.setLong(5, en.getId());
			rowEffected = stmt.executeUpdate();
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally{
			
			try{
				
				if (con != null) con.close();
				if (stmt != null) stmt.close();
				
			}
			
			catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		if (rowEffected == 0) return(false);
		else return(true);
		
	}
	
	@Override
	public boolean delete(long id) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		int rowEffected = 0;
		try{
			
			con = this.dataSource.getConnection();
			String sql = "DELETE FROM entity WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			rowEffected = stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally{
			
			try{
				
				if(con != null) con.close();
				if(stmt != null) stmt.close();
				
			}
			
			catch(SQLException e){
				
				e.printStackTrace();
				
			}
			
		}
		
		if (rowEffected == 0) return(false);
		else return(true);
		
	}
	
	@Override
	public boolean delete(Entity en) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		int rowEffected = 0;
		try{
			
			con = this.dataSource.getConnection();
			String sql = "DELETE FROM entity WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, en.getId());
			rowEffected = stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		finally{
			
			try{
				
				if(con != null) con.close();
				if(stmt != null) stmt.close();
				
			}
			
			catch(SQLException e){
				
				e.printStackTrace();
				
			}
			
		}
		
		if (rowEffected == 0) return(false);
		else return(true);
		
	}
	
}