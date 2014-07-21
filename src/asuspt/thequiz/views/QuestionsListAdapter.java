package asuspt.thequiz.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ClipData.Item;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import asuspt.thequiz.data.MCQ;
import asuspt.thequiz.data.Quiz;

public class QuestionsListAdapter extends ArrayAdapter<Item>
{
	/* member variables */
	private Context context;
	private Quiz quiz;
	private HashMap<Integer, RadioGroup> radioGroups;
	private HashMap<Integer, LinearLayout> previousLayouts;

	public QuestionsListAdapter(Context context)
	{
		super(context, 0);
		this.context = context;
		this.radioGroups = new HashMap<Integer, RadioGroup>();
		this.previousLayouts = new HashMap<Integer, LinearLayout>();
	}

	/**
	 * @param quiz sets the quiz data for this list adapter
	 */
	public void setQuiz(Quiz quiz)
	{
		this.quiz = quiz;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return quiz.getMcqs().size();
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// check if we already have that view
		if (previousLayouts.containsKey(position))
			return previousLayouts.get(position);

		MCQ mcq = quiz.getMcqs().get(position);

		// Create a linear layout consisting of this question and answers
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
				ListView.LayoutParams.WRAP_CONTENT);

		// the number of the question
		TextView questionNumber = new TextView(context);
		questionNumber.setText("" + (position + 1));
		questionNumber.setLayoutParams(layoutParams);
		questionNumber.setGravity(Gravity.CENTER);
		questionNumber.setTextColor(0xff2186AD);
		linearLayout.addView(questionNumber);

		// the question statement
		TextView questionStatement = new TextView(context);
		questionStatement.setText(mcq.getQuestion());
		linearLayout.addView(questionStatement);

		// The answers
		RadioGroup answersRadioGroup = new RadioGroup(context);
		for (int i = 0; i < mcq.getAnswers().size(); i++)
		{
			RadioButton radioButton = new RadioButton(context);
			radioButton.setText(mcq.getAnswers().get(i));
			answersRadioGroup.addView(radioButton);
		}
		linearLayout.addView(answersRadioGroup);

		// add this radio group if it's not there
		if (!radioGroups.containsKey(position))
			radioGroups.put(position, answersRadioGroup);
		previousLayouts.put(position, linearLayout);

		return linearLayout;
	}

	/**
	 * @return the total score from the correct answers of all the mcqs
	 */
	public int getScore()
	{
		int score = 0;
		// check the selection of the radio button
		for (Integer index : radioGroups.keySet())
		{
			int selectedId = radioGroups.get(index).getCheckedRadioButtonId();
			int correctIndex = quiz.getMcqs().get(index).getCorrectAnswerIndex();
			int correctId = radioGroups.get(index).getChildAt(correctIndex).getId();
			if (correctId == selectedId)
				score++;
		}

		return score;
	}

	/**
	 * Clears the selections from all the radio buttons
	 */
	public void clearAllSelections()
	{
		for (RadioGroup radioGroup : this.radioGroups.values())
			radioGroup.clearCheck();
	}

	
	/**
	 * @return indices of answers selected or -1 if none is selected
	 */
	public ArrayList<Integer> getAnswers()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		int nQuestions = quiz.getMcqs().size();

		for (int i = 0; i < nQuestions; i++)
		{
			if (radioGroups.containsKey(i))
			{
				RadioGroup radioGroup = radioGroups.get(i);
				int radioButtonID = radioGroup.getCheckedRadioButtonId();
				View radioButton = radioGroup.findViewById(radioButtonID);
				int idx = radioGroup.indexOfChild(radioButton);
				result.add(idx);
			}
			else
				result.add(-1);
		}
		
		return result;
	}
}
