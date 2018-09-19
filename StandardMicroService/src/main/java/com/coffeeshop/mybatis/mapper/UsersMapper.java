/**
 *
 * <p>Filename: UsersMapper.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */
package com.coffeeshop.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.coffeeshop.model.Addresses;
import com.coffeeshop.model.Roles;
import com.coffeeshop.model.Users;

@Mapper
public interface UsersMapper {

	@Select("select * from users")
	List<Users> findAll();

	@Select("Select id, name, salary from users where id=#{user_id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "name", column = "name"),
			@Result(property = "salary", column = "salary"),
			@Result(property = "addresses", javaType = List.class, column = "id", many = @Many(select = "getAddresses")) })
	public Users getUserById(Integer user_id); // Sample for One To Many

	@Select("select id, detail, user_id from addresses where user_id=#{user_id}")
	public Addresses getAddresses(Integer user_id); // Sample for One To Many

	// -----------------------------------------------------------------------------
	@Select("Select id, name, salary from users where id=#{user_id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "name", column = "name"),
			@Result(property = "salary", column = "salary"),
			@Result(property = "roles", javaType = List.class, column = "id", many = @Many(select = "getRoles")),
			@Result(property = "addresses", javaType = List.class, column = "id", many = @Many(select = "getAddresses"))})
	public Users getUserByIdWithRole(Integer user_id); // Sample for Many To Many

	@Select("select r.id as id, r.name as name\r\n" + "from users as u\r\n"
			+ "left join user_role as ur on u.id = ur.user_id\r\n" + "left join roles as r on r.id = ur.role_id\r\n"
			+ "where 1=1\r\n" + "AND u.id = 1;")
	public Roles getRoles(Integer user_id); // Sample for Many To Many

//    @Insert("insert into users(name,salary) values(#{name},#{salary})")
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
//            before = false, resultType = Integer.class)
//    void insert(Users users);
}
