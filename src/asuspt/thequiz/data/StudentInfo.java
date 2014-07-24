package asuspt.thequiz.data;

public class StudentInfo
{
	/* member variables */
	private String id;
	private String password;
	private String name;
	private String quizResults;

	public StudentInfo(String id, String password)
	{
		this.id = id;
		this.password = password;
		this.name = "";
	}

	public StudentInfo(String id, String password, String name)
	{
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getQuizResults()
	{
		return quizResults;
	}

	
	public void setQuizResults(String quizResults)
	{
		this.quizResults = quizResults;
	}

}
