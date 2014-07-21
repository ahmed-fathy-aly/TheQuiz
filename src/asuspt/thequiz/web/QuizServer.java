package asuspt.thequiz.web;

import java.util.ArrayList;

import asuspt.thequiz.data.Quiz;
import asuspt.thequiz.data.StudentInfo;

public class QuizServer
{

	/**
	 * @return a single quiz
	 */
	public static Quiz loadQuiz(StudentInfo studentInfo)
	{
		// TODO @fathy 2otta : add web stuff here....if you can ignore this and
		// make a method
		// that returns a list of quizzes names and a tag so i can make a list
		// where the user chooses the quiz, it'll be great

		return Quiz.generateTemplateQuiz("Downloaded quiz", 5, 4);
	}

	/**
	 * @param studentInfo
	 * @param quiz
	 * @return MyUtils.GRADING_FAILED if you failed to grade the quiz or the
	 *         quiz result if you could
	 * @param answers
	 *            the indices of selected answers or -1 if none is selected
	 * @return
	 */
	public static String gradeQuiz(Quiz quiz, ArrayList<Integer> answers,
			StudentInfo loadLoginInfoFromPreferences)
	{
		return "4.0 / 13";

	}

}
