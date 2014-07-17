package asuspt.thequiz.web;


public class LoginRegisterServer
{

	/**
	 * @return true if the id is registered and the password is correct
	 */
	public static boolean isCorrectIdAndPassword(String id, String password)
	{
		// TODO @fathy 2otta do your web stuff here 
		
		// temp code
		if (id.equals("23046") && password.equals("1"))
			return true;
		else
			return false;		
	}

	
	/**
	 * adds an account with that id and password to the database
	 * @return true if the account is adde succesfylu
	 */
	public static Boolean registerAccount(String id, String password, String name)
	{
		if (id.equals("nope"))
			return false;
		else
			return true;
	}
}
