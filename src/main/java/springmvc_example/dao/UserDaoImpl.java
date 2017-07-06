package springmvc_example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import springmvc_example.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<User> listAllUser() {
		String sql = "select id, firstname, lastname, address from users";

		List<User> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new UserMapper());

		return list;
	}

	private SqlParameterSource getSqlParameterByModel(User user) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		if (user != null) {
			paramSource.addValue("id", user.getId());
			paramSource.addValue("firstname", user.getFirstname());
			paramSource.addValue("lastname", user.getLastname());
			paramSource.addValue("address", user.getAddress());
		}
		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();

			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));
			return user;
		}

	}

	public void addUser(User user) {
		String sql = "insert into users (firstname,lastname,address) values(:firstname, :lastname, :address)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
	}

	public void updateUser(User user) {
		String sql = "update users set firstname = :firstname,lastname = :lastname, address = :address where id = :id";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
	}

	public void deleteUser(int id) {
		String sql = "delete from users where id = :id";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new User(id)));
	}

	public User findById(int id) {
		String sql = "select * from users where id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(new User(id)), new UserMapper());
	}

}
