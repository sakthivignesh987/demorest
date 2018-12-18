package com.maven.demorest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "students")
public class StudentResource {

	StudentDAO repo = new StudentDAO();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Student> getStudents() throws SQLException {
		System.out.println("getting students");
		return repo.getStudents();
	}

	@GET
	@Path(value = "student/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student getStudent(@PathParam(value = "id") int id) throws SQLException {
		System.out.println("getting student");
		return repo.getStudent(id);
	}

	@POST
	@Path(value = "student")
	public Student createStudent(Student s1) throws SQLException {
		System.out.println("student creation started");
		System.out.println(s1);
		repo.createStudent(s1);
		System.out.println("creation over");
		return s1;

	}
}
