package asuspt.thequiz.web;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;
import asuspt.thequiz.data.StudentInfo;

public class LoginRegisterServer
{

	/**
	 * @return true if the id is registered and the password is correct
	 */
	public static boolean isCorrectIdAndPassword(String id, String password)
	{

		// @fathy 2otta : eb2a dos ctrl-shift-f lama t5ls 
		String urlString = "http://quiz-creator.herokuapp.com/users/api_login";
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlString);

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("data[User][username]", new StringBody(id));
			reqEntity.addPart("data[User][password]", new StringBody(password));
			post.setEntity(reqEntity);
			HttpResponse response = client.execute(post);
			HttpEntity resEntity = response.getEntity();
			final String response_str = EntityUtils.toString(resEntity);
			if (resEntity != null)
			{
				Log.i("RESPONSE", response_str);
				JSONObject res = new JSONObject(response_str);
				return res.getBoolean("successful");
			}
		} catch (Exception ex)
		{
			Log.e("Debug", "error: " + ex.getMessage(), ex);
		}

		return false;
		/*
		 * // temp code if (id.equals("23046") && password.equals("1")) return
		 * true; else return false;
		 */
	}

	/**
	 * adds an account with that id and password to the database
	 * 
	 * @param department
	 * @param email
	 * 
	 * @return true if the account is adde succesfylu
	 */
	public static boolean registerAccount(String id, String password, String name, String email,
			String department)
	{
		if (id.equals("nope"))
			return false;
		else
			return true;
	}

	/**
	 * @param studentInfo
	 *            contains only the id and the password
	 * @return StudentInfo object containing name, quiz results, department,
	 *         mail amd same id and password
	 */
	public static StudentInfo getCompleteStudentInfo(StudentInfo studentInfo)
	{
		StudentInfo result = new StudentInfo("619", "fsad");
		result.setName("Rey mysterio");
		result.setQuizResults("Quiz 1 : 5 / 12\nQuiz 2 : 5 1 / 12");
		result.setDepartment("Sorcery");
		result.setEmail("AnaAslnCrazy@gmail.com");
		return result;
	}
}
