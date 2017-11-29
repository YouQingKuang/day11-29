package cn.com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Service;

import cn.com.example.domain.Actor;

@Service
public class ActorService {
	private static final Logger log = LoggerFactory.getLogger(ActorService.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void hello() {
		System.out.println("---------------------------");
		log.info("dataSource = {}", dataSource);
		log.info("jdbcTemplate = {}", jdbcTemplate);
		log.info("namedParameterJdbcTemplate = {}", namedParameterJdbcTemplate);
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birth
	 * @return primary key value
	 */
	public Integer insert(String firstName, String lastName, Date birth) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName("ACTOR").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("first_name", firstName);
		parameters.put("last_name", lastName);
		parameters.put("birth_date", birth);
		Number newId = insertActor.executeAndReturnKey(parameters);
		return newId.intValue();
	}

	/**
	 * @param firstNames
	 * @param lastNames
	 * @param birthes
	 * @return the array of number of rows affected 
	 */
	@SuppressWarnings("unchecked")
	public int[] insertBatch(String firstNames[], String lastNames[],
			Date birthes[]) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName("ACTOR").usingGeneratedKeyColumns("id");
		Map<String, Object>[] parameters = (Map<String, Object>[]) new Map[firstNames.length];
		for (int i = 0; i < firstNames.length; i++) {
			Map<String, Object> parameter = new HashMap<String, Object>(3);
			parameter.put("first_name", firstNames[i]);
			parameter.put("last_name", lastNames[i]);
			parameter.put("birth_date", birthes[i]);
			parameters[i] = parameter;
		}
		return insertActor.executeBatch(parameters);
	}

	public List<Actor> select() {
		return jdbcTemplate.query("select * from actor", new RowMapper<Actor>() {
			@Override
			public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
				log.debug("current row = {}", rowNum);
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setBirth(rs.getDate("birth_date"));
				return actor;
			}
		});
	}

	public List<Actor> selectSome() {
		Object[] ids = { 1 };
		log.info("{}",
				jdbcTemplate.queryForList("select * from actor where id = ?", ids));
		return jdbcTemplate.query("select * from actor where id = ?", ids,
				new RowMapper<Actor>() {
					@Override
					public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
						// log.debug("current row = {}", rowNum);
						Actor actor = new Actor();
						actor.setId(rs.getInt("id"));
						actor.setFirstName(rs.getString("first_name"));
						actor.setLastName(rs.getString("last_name"));
						actor.setBirth(rs.getDate("birth_date"));
						return actor;
					}
				});
	}

	public List<Actor> selectMore() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 3);
		log.info("use namedParameterJdbcTemplate = {}", namedParameterJdbcTemplate
				.queryForList("select * from actor where id = :id", paramMap));
		ActorMappingQuery query = new ActorMappingQuery(dataSource);
		return query.execute(2);
	}

	public class ActorMappingQuery extends MappingSqlQuery<Actor> {
		public ActorMappingQuery(DataSource ds) {
			super(ds,
					"select id, first_name, last_name, birth_date from actor where id = ?");
			super.declareParameter(new SqlParameter("id", Types.INTEGER));
			compile();
		}

		@Override
		protected Actor mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actor.setBirth(rs.getDate("birth_date"));
			return actor;
		}
	}
}
