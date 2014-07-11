package asuspt.thequiz.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * models an mcq question with multiple answers and only one of them is correct
 * 
 * @author ahmed fathy aly
 * 
 */
public class MCQ
{

	/* member varibles */
	private String question;
	private ArrayList<String> answers;
	int correctAnswerIndex;

	/**
	 * Constructor
	 */
	public MCQ()
	{
		question = "";
		answers = new ArrayList<String>();
		correctAnswerIndex = -1;
	}

	/**
	 * @return string representation of the question
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * @param question
	 *            string representation of the question
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}

	/**
	 * @return list of the mcq's answers
	 */
	public ArrayList<String> getAnswers()
	{
		return answers;
	}

	/**
	 * @param answers
	 *            list of the mcq's answers
	 */
	public void setAnswers(ArrayList<String> answers)
	{
		this.answers = answers;
	}

	/**
	 * @return the index of the correct answer in the answers list
	 */
	public int getCorrectAnswerIndex()
	{
		return correctAnswerIndex;
	}

	/**
	 * @param correctAnswerIndex
	 *            the index of the correct answer in the answers list
	 */
	public void setCorrectAnswerIndex(int correctAnswerIndex)
	{
		this.correctAnswerIndex = correctAnswerIndex;
	}

	/**
	 * @param answer
	 *            a new answer to the mcq
	 */
	public void addAnswer(String answer)
	{
		answers.add(answer);
	}

	public String toString()
	{
		String str = "";

		str += "Question : " + this.question + "\n";
		for (String answer : this.answers)
			str += "Answer : " + answer + "\n";
		str += "correct answer index : " + correctAnswerIndex + "\n";

		return str;
	}

	/**
	 * The correct answer is random
	 * 
	 * @param mcqNumber
	 *            the number that'll appear in the question
	 * @param nAnswers
	 *            the number of answers
	 * @return
	 */
	public static MCQ generateTemplateMCQ(int mcqNumber, int nAnswers)
	{
		MCQ mcq = new MCQ();

		int correctAnswerIndex = new Random().nextInt(nAnswers);
		mcq.setQuestion("Question no."
				+ mcqNumber
				+ "..........This is a template for the question, the real question is usually longer as it mostly consists of useless stuff, so we added this useless sentence to the template to make it longer"
				+ "[answer no." + (correctAnswerIndex + 1) + " is correct]");
		for (int i = 1; i <= nAnswers; i++)
			mcq.addAnswer("Answer no." + i + ".......");
		mcq.setCorrectAnswerIndex(correctAnswerIndex);

		return mcq;

	}

	public static void main(String[] args)
	{
		MCQ mcq = MCQ.generateTemplateMCQ(3, 6);
		System.out.println(mcq);
	}

}
