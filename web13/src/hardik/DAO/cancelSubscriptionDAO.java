package hardik.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import hardik.Util.DBConnection;
import hardik.beans.MainClass;

public class cancelSubscriptionDAO {
	public boolean getAccountIdentifier(MainClass mainClass) throws Exception {
	       Connection conn = DBConnection.getConnection();
	       Statement s1 = conn.createStatement();
	       ResultSet rst = s1.executeQuery("select * from MarketPlace where uuid = '"+ mainClass.payload.account.getAccountIdentifier()+"'");
	       if(rst.next())
	       {
	           conn.close();
	           return false;
	       }
	       conn.close();
	       return true;
	   }
	public void cancel(MainClass mainClass) throws Exception{
		
		Connection connection = DBConnection.getConnection();
			
		String uuid = mainClass.creator.getUuid();		
		String query = "delete from MarketPlace where uuid=?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, uuid);	
		int resultSet = prepareStatement.executeUpdate();
		System.out.println("User removed Successfully");	
	}
	
	public boolean checkExist(MainClass mainClass) throws Exception{

		Connection connection = DBConnection.getConnection();
		String uuid="";	
		uuid = mainClass.creator.getUuid();	
		String query = "select * from MarketPlace where uuid = ?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1,uuid);
		ResultSet resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			return true;
		}
		return false;
		
	}
	
}
