package util;

import java.sql.*;

public class Conn
{
Connection c;
public Statement s;
    public Conn()
      {
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		c=DriverManager.getConnection("jdbc:mysql:///techshop","root","2179@Manoj");
    		s=c.createStatement();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
	
      }
}

