package com.example.user.myquiz;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class q3f extends Fragment {
    q3f.updateScore3 updateScore3;
    int buttonclickcounter = 0;


    boolean hasCheated = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q3f, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final QuestionBank q = new QuestionBank(getActivity());


        TextView qThree = (TextView)getView().findViewById(R.id.tquestion3);
        qThree.setText(q.selectQuestion(3));
        final String answer = q.selectAnswer(3).replaceAll(" ","").toLowerCase();

        final EditText answerThree= (EditText)getView().findViewById(R.id.etAnswer3);

        Button confirm = (Button)getView().findViewById(R.id.bcheck3);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonclickcounter++;
                if (hasCheated && buttonclickcounter >=1){
                    Toast youCheated = Toast.makeText(getContext() , " You have cheated ", Toast.LENGTH_SHORT);
                    youCheated.show();


                }
                if (!hasCheated && buttonclickcounter == 1) {

                    if (answerThree.getText().toString().replaceAll(" ", "").toLowerCase().equals(answer)) {
                        Toast correct = Toast.makeText(getContext(), "Correct!!",
                                Toast.LENGTH_SHORT);
                        correct.show();
                        updateScore3.scoreChanged3(1);



                    } else {
                        Toast incorrect = Toast.makeText(getContext(), "Wrong!!",
                                Toast.LENGTH_SHORT);

                        incorrect.show();
                        updateScore3.scoreChanged3(0);


                    }

                }
                if (buttonclickcounter >=2 ){

                    Toast Answered = Toast.makeText(getContext(), " Already answered ", Toast.LENGTH_SHORT);
                    Answered.show();

                }



            }
        });



        Button cheat = (Button)getView().findViewById(R.id.bcheat3);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasCheated = true;

                Toast showAnswer = Toast.makeText(getContext(), q.selectAnswer(3).toString(), Toast.LENGTH_SHORT);
                showAnswer.show();
                updateScore3.scoreChanged3(0);

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity){
            a = (Activity)context;
        }

        try {
            updateScore3 = (q3f.updateScore3)context;
        } catch (Exception e) {}
    }

    public interface updateScore3
    {

        public void scoreChanged3 (int score);
    }
}
