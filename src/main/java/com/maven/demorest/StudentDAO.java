package com.maven.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection con = null;

	public StudentDAO() {
		String url = "jdbc:mysql://localhost:3306/rest";
		String user = "root";
		String password = "system";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudents() throws SQLException {
		List<Student> students = new ArrayList<>();
		String sql = "select * from student";

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			Student s = new Student();
			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setPoints(rs.getInt(3));
			students.add(s);

		}
		return students;

	}

	public Student getStudent(int id) throws SQLException {
		String sql = "select * from student where id=" + id;
		Student s = new Student();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {

			s.setId(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setPoints(rs.getInt(3));

		}
		return s;

	}

	public void createStudent(Student s1) {
		String sql = "insert into student values(?,?,?)";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, s1.getId());
			st.setString(2, s1.getName());
			st.setInt(3, s1.getPoints());
			st.executeUpdate();
			System.out.println("creating");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
