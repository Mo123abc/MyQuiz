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

public class q4f extends Fragment {

    q4f.updateScore4 updateScore4;
    int buttonclickcounter = 0;


    boolean hasCheated = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q4f, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final QuestionBank q = new QuestionBank(getActivity());


        TextView qFour = (TextView)getView().findViewById(R.id.tquestion4);
        qFour.setText(q.selectQuestion(4));
        final String answer = q.selectAnswer(4).replaceAll(" ","").toLowerCase();

        final EditText answerFour= (EditText)getView().findViewById(R.id.etAnswer4);

        Button confirm = (Button)getView().findViewById(R.id.bcheck4);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonclickcounter++;
                if (hasCheated && buttonclickcounter >=1){
                    Toast youCheated = Toast.makeText(getContext() , " You have cheated ", Toast.LENGTH_SHORT);
                    youCheated.show();


                }
                if (!hasCheated && buttonclickcounter == 1) {

                    if (answerFour.getText().toString().replaceAll(" ", "").toLowerCase().equals(answer)) {
                        Toast correct = Toast.makeText(getContext(), "Correct!!",
                                Toast.LENGTH_SHORT);
                        correct.show();
                        updateScore4.scoreChanged4(1);



                    } else {
                        Toast incorrect = Toast.makeText(getContext(), "Wrong!!",
                                Toast.LENGTH_SHORT);

                        incorrect.show();
                        updateScore4.scoreChanged4(0);


                    }

                }
                if (buttonclickcounter >=2 ){

                    Toast Answered = Toast.makeText(getContext(), " Already answered ", Toast.LENGTH_SHORT);
                    Answered.show();

                }



            }
        });



        Button cheat = (Button)getView().findViewById(R.id.bcheat4);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasCheated = true;

                Toast showAnswer = Toast.makeText(getContext(), q.selectAnswer(4).toString(), Toast.LENGTH_SHORT);
                showAnswer.show();
                updateScore4.scoreChanged4(0);

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
            updateScore4 = (q4f.updateScore4)context;
        } catch (Exception e) {}
    }

    public interface updateScore4
    {

        public void scoreChanged4 (int score);
    }
}
