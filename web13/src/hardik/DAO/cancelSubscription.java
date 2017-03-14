package hardik.DAO;

import hardik.beans.MainClass;

public interface cancelSubscription {
	 void cancel(MainClass mainClass) throws Exception;
	 boolean checkExist(MainClass mainClass) throws Exception;
	 boolean getAccountIdentifier(MainClass mainClass) throws Exception;
}
