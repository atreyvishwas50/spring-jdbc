package org.djwebpros.jdbcProject.repo;

import java.util.List;

import org.djwebpros.jdbcProject.model.Entity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateDataSource implements Repoistories {
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	@Override
	public List<Entity> findAll() {
		
		String sql = "SELECT * FROM entity";
		RowMapper<Entity> entityMapper = (resultSet, rowNum) -> {
			
			Entity en = new Entity();
			en.setId(resultSet.getLong(1));
			en.setFirstName(resultSet.getString(2));
			en.setLastName(resultSet.getString(3));
			en.setEmail(resultSet.getString(4));
			en.setPassword(resultSet.getString(5));
			return en;
			
		};
		return jdbcTemplate.query(sql, entityMapper);
		
	}
	
	@Override
	public Entity findById(long id) {
		
		Entity ans = null;
		try {
		
			String sql = "SELECT * FROM entity WHERE id = ?";
			RowMapper<Entity> entityMapper = (resultSet, rowNum) -> {
				
				Entity en = new Entity();
				en.setId(resultSet.getLong(1));
				en.setFirstName(resultSet.getString(2));
				en.setLastName(resultSet.getString(3));
				en.setEmail(resultSet.getString(4));
				en.setPassword(resultSet.getString(5));
				return en;
				
			};
			ans = jdbcTemplate.queryForObject(sql, entityMapper, id);
			
		}
		
		catch (DataAccessException e) {
			
			
			
		}
	
		return ans;
		
	}

	@Override
	public boolean insert(Entity en) {
		
		int rowEffected = 0;
		String sql = "INSERT INTO entity(first_name, last_name, email, password) VALUES (?,?,?,?)";
		rowEffected = jdbcTemplate.update(sql, en.getFirstName(), en.getLastName(), en.getEmail(), en.getPassword());
		if (rowEffected == 1) return true;
		else return false;
		
	}

	@Override
	public boolean update(Entity en) {
		
		int rowEffected = 0;
		String sql = "UPDATE entity SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";
		rowEffected = jdbcTemplate.update(sql, en.getFirstName(), en.getLastName(), en.getEmail(), en.getPassword(), en.getId());
		if(rowEffected == 0) return false;
		else return true;
		
	}

	@Override
	public boolean delete(long id) {
		
		int rowEffected = 0;
		String sql = "DELETE FROM entity WHERE id = ?";
		rowEffected = jdbcTemplate.update(sql, id);
		if(rowEffected == 0) return false;
		else return true;
		
	}

	@Override
	public boolean delete(Entity en) {
		
		int rowEffected = 0;
		String sql = "DELETE FROM entity WHERE id = ?";
		rowEffected = jdbcTemplate.update(sql, en.getId());
		if(rowEffected == 0) return false;
		else return true;
	
	}
	
}