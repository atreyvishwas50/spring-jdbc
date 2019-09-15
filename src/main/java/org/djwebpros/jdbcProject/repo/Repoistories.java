package org.djwebpros.jdbcProject.repo;

import java.util.List;

import org.djwebpros.jdbcProject.model.Entity;
import org.springframework.stereotype.Repository;

@Repository
public interface Repoistories {
	
	public List<Entity> findAll();
	public Entity findById(long id);
	public boolean insert(Entity en);
	public boolean update(Entity en);
	public boolean delete(long id);
	public boolean delete(Entity en);
}
