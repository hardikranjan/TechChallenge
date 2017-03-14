package hardik.Service;
import hardik.DAO.*;
import hardik.Controller.CreateSubscription;
import hardik.beans.*;
import hardik.Model.*;

public class createSubscriptionService implements createSubscription{

	public boolean createService(MainClass mainClass) throws Exception{
		
		createSubscriptionDAO createSubscriptionDao = new createSubscriptionDAO();
		if(!createSubscriptionDao.checkExist(mainClass)){
			createSubscriptionDao.insert(mainClass);
			return true;
		}
		else
			System.out.println("Adready Exist");
			
		return false;
	}
}
