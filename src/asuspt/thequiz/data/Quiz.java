package asuspt.thequiz.data;

import java.util.ArrayList;

/**
 * Models a quiz which consists of a list of questions
 * 
 * @author ahmed fathy aly
 * 
 */
public class Quiz
{
	/* member variables */
	String quizTitle;
	private ArrayList<MCQ> mcqList;
	private String webId;
	
 	public Quiz(String title)
	{
		this.quizTitle = title;
		this.mcqList = new ArrayList<MCQ>();
		this.webId = "";
	}

	/**
	 * @return the title of the whole quiz
	 */
	public String getQuizTitle()
	{
		return quizTitle;
	}

	/**
	 * @param quizTitle
	 *            the title of the whole quiz
	 */
	public void setQuizTitle(String quizTitle)
	{
		this.quizTitle = quizTitle;
	}

	/**
	 * @return a list of the mcqs
	 */
	public ArrayList<MCQ> getMcqs()
	{
		return mcqList;
	}

	/**
	 * @param mcqs
	 *            a list of the mcqs
	 */
	public void setMcqs(ArrayList<MCQ> mcqs)
	{
		this.mcqList = mcqs;
	}

	/**
	 * @return the id used to identify the quiz on the web
	 */
	public String getWebId()
	{
		return webId;
	}
	

	/**
	 * @param webId the id used to identify the quiz on the web
	 */
	public void setWebId(String webId)
	{
		this.webId = webId;
	}

	/**
	 * @param mcq
	 *            a new mcq to be added to the quiz
	 */
	public void addMcq(MCQ mcq)
	{
		this.mcqList.add(mcq);
	}

	public String toString()
	{
		String str = "";

		str += "Title : " + this.quizTitle + "\n";
		for (int i = 0; i < this.mcqList.size(); i++)
			str += mcqList.get(i).toString() + "\n";

		return str;
	}

	/**
	 * @param title
	 *            the title of the quiz
	 * @param nQuestions
	 *            the number of mcq questions in the quiz
	 * @param nMcqAnswers
	 *            the number of choices to each mcq
	 * @return a generated template of the quiz
	 */
	public static Quiz generateTemplateQuiz(String title, int nQuestions, int nMcqAnswers)
	{
		Quiz quiz = new Quiz(title);

		for (int i = 0; i < nQuestions; i++)
			quiz.addMcq(MCQ.generateTemplateMCQ(i + 1, nMcqAnswers));

		return quiz;
	}

	public static void main(String[] args)
	{
		Quiz quiz = Quiz.generateTemplateQuiz("My quiz", 5, 4);
		System.out.println(quiz);

	}

}
