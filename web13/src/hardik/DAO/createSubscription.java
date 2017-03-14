package hardik.DAO;

import hardik.beans.MainClass;

public interface createSubscription {
	public void insert(MainClass mainClass) throws Exception;
	public boolean checkExist(MainClass mainClass) throws Exception;
}
