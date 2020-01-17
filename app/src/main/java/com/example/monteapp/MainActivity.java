package com.example.monteapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    TextView view;
    EditText edit;
    Button calculate;
    Button cancel;
    int iteration ;
    AppViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        view = (TextView) findViewById(R.id.textView);
        edit = (EditText) findViewById(R.id.editText);
        calculate = (Button) findViewById(R.id.button2);
        cancel = (Button) findViewById(R.id.button);

        model = ViewModelProviders.of(this).get(AppViewModel.class);
        if (model.getP() == 0) {
            bar.setProgress(model.getProgress());
        } else {
            bar.setProgress(100);
            view.setText(Double.toString(model.getP()));
        }


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iteration = Integer.parseInt(edit.getText().toString());
                    if (iteration > 0) {
                        final AsyncTask calculations = new MonteCarlo(iteration, new Delegate() {

                            @Override
                            public void onPreExecute() {
                                bar.setProgress(0);
                                view.setText("Расчитываем");
                            }

                            @Override
                            public void onProgressChanged(int progress) {
                                model.setProgress(progress);
                                bar.setProgress(progress);
                            }

                            @Override
                            public void onPostExecute(double result) {
                                model.setP(result);
                                view.setText(String.valueOf(result));
                            }

                        }).execute();

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                calculations.cancel(true);
                                view.setText("Отменено!");
                                bar.setProgress(0);
                            }
                        });
                    } else {
                        view.setText("Введите положительное число");
                    }

                } catch (Exception e) {
                    view.setText("Введите число а не символ");
                }

            }
        });


    }

}

