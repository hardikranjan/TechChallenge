package hardik.Service;

import hardik.DAO.cancelSubscriptionDAO;
import hardik.DAO.createSubscriptionDAO;
import hardik.Model.ResponseSuccess;
import hardik.beans.MainClass;

public class cancelSubscriptionService implements cancelSubscription {
public ResponseSuccess cancelService(MainClass mainClass) throws Exception{
		ResponseSuccess responseObject;
		cancelSubscriptionDAO cancelSubscriptionDao = new cancelSubscriptionDAO();
		if(cancelSubscriptionDao.checkExist(mainClass)){
			 if(cancelSubscriptionDao.getAccountIdentifier(mainClass))
             {
				 cancelSubscriptionDao.cancel(mainClass);
                  responseObject = new ResponseSuccess(true,mainClass.getCreator().getFirstName()+":"+mainClass.getCreator().getUuid());
                  return responseObject;
             }
              responseObject = new ResponseSuccess(false,"");  
              return responseObject;
		}
		else{
			System.out.println("Donot User Exist");
			responseObject = new ResponseSuccess(false,"");
			return responseObject;
		}
			
	}
}
