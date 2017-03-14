package hardik.DAO;
import hardik.beans.*;
import hardik.Util.*;
import hardik.beans.*;
import java.sql.*;
public class createSubscriptionDAO implements createSubscription{
	public void insert(MainClass mainClass) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String uuid,baseUrl, partner, fullName,firstName,lastName,email;
		
		uuid = mainClass.creator.getUuid();
		baseUrl = mainClass.marketplace.getBaseUrl();
		partner = mainClass.marketplace.getPartner();
		firstName = mainClass.creator.getFirstName();
		lastName = mainClass.creator.getLastName();
		fullName = firstName + " " + lastName;
		email = mainClass.getFlag();
				
		String query = "insert into MarketPlace values(?,?,?,?,?)";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, uuid);
		prepareStatement.setString(2, baseUrl);
		prepareStatement.setString(3, partner);
		prepareStatement.setString(4, fullName);
		prepareStatement.setString(5, email);
		
		int resultSet = prepareStatement.executeUpdate();
		
	}
	
	public boolean checkExist(MainClass mainClass) throws Exception{

		Connection connection = DBConnection.getConnection();
		String uuid;	
		uuid = mainClass.creator.getUuid();
		
		String query = "select * from MarketPlace where uuid = ?";
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, uuid);
		ResultSet resultSet = prepareStatement.executeQuery();
		while(resultSet.next()){
			return true;
		}
		return false;
		
	}
}
