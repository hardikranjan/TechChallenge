package hardik.Util;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import java.sql.*;
public class DBConnection 
{
	public static Connection getConnection() throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechChallenge", "root", "password");
       
        return con;
    }
}
