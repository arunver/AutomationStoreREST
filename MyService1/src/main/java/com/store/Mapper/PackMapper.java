package com.store.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.store.model.Entity;
import com.store.model.Pack;

public class PackMapper implements RowMapper<Pack>{
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger
			.getLogger(PackMapper.class.getName());
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public Pack mapRow(ResultSet rs, int num) throws SQLException {
		
		//System.out.println(rs.getStatement());
		logger.info("Creating pack object using PackMapper : "+rs.getStatement());
		int id= rs.getInt(1);
		Pack pack = new Pack();
		pack.setId((rs.getInt(1)));
		pack.setAuthor(rs.getString(5));
		pack.setName(rs.getString(3));
		pack.setDescription(rs.getString(4));
		pack.setUuid(rs.getString(2));
		pack.setVersion(rs.getString(7));
		pack.setPackFilePath(rs.getString(11));
		pack.setCertifiedBy(rs.getString(6));
		pack.setDownloadUrl("automationstore.netapp.com/packUrl");
		
		return pack;
	}
	

}
