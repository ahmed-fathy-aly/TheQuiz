package asuspt.thequiz.web;

import asuspt.thequiz.data.StudentInfo;


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

	/**
	 * @param studentInfo contains only the id and the password
	 * @return StudentInfo object containing name, quiz results and same id and password
	 */
	public static StudentInfo getCompleteStudentInfo(StudentInfo studentInfo)
	{
		StudentInfo result = new StudentInfo("619", "fsad");
		result.setName("Rey mysterio");
		result.setQuizResults("Quiz 1 : 5 / 12\nQuiz 2 : 5 1 / 12");
		return result;
	}
}
