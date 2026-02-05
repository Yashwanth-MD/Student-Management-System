package in.ps.studentapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connector {
	public static Connection requestConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Student_db","root","tiger");
		}
		catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return con;
	}

}
