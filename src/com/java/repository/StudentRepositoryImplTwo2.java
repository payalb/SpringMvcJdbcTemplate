package com.java.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.java.database.util.StudentRowMapper;
//java.sql package: jdbc classes
import com.java.dto.Student;
import com.java.exception.DatabaseException;

@Repository("rep2")
public class StudentRepositoryImplTwo2 implements StudentRepository {

	@Autowired NamedParameterJdbcTemplate template;
	@Autowired StudentRowMapper mapper;

	@Override
	public int addStudent(Student student) throws DatabaseException {
		/*Map<String, String> map= Map.of("name", student.getName(), "password", student.getPassword());
		SqlParameterSource sps= new MapSqlParameterSource(map);
		*/	
		SqlParameterSource sps= new BeanPropertySqlParameterSource
				(student);
		template.update("insert into student values (idGenerator.nextval , :name , :password)", sps);
		SqlParameterSource sps1=new EmptySqlParameterSource();
		Integer id=template.queryForObject("select idGenerator.currval from dual", sps1,Integer.class);
		return id;
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student getStudentById(int id) {
		SqlParameterSource sps= new MapSqlParameterSource("id", id);
		return template.queryForObject("select * from student where id =:id", sps,mapper);
	}

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}
