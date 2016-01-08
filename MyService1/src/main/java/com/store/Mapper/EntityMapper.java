package com.store.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.store.model.Entity;

public class EntityMapper implements RowMapper<Entity>{
	
	private static Logger logger = Logger
			.getLogger(PackMapper.class.getName());
	

	@Override
	public Entity mapRow(ResultSet rs, int num) throws SQLException {
		// TODO Auto-generated method stub
	
		logger.info("Creating entity object using Entity mapper: "+rs.getStatement());
		Entity entity= new Entity();
		entity.setCertifiedBy(rs.getString("certifiedBy"));
		entity.setDescription(rs.getString("description"));
		entity.setName(rs.getString("name"));
		entity.setUuid(rs.getString("uuid"));
		entity.setVersion(rs.getString("version"));
		return entity;
	}

}
