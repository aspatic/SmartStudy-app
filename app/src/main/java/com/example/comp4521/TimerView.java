//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class TimerView extends LinearLayout {
    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public TimerView(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                startTimer();
                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);

            }
        });
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                stopTimer();
                btnPause.setVisibility(GONE);
                btnResume.setVisibility(VISIBLE);

            }
        });
        btnResume = (Button) findViewById(R.id.btnResume);
        btnResume.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startTimer();
                btnResume.setVisibility(GONE);
                btnPause.setVisibility(VISIBLE);
            }
        });
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                stopTimer();
                etHour.setText("0");
                etMinute.setText("0");
                etSecond.setText("0");
                btnReset.setVisibility(GONE);
                btnResume.setVisibility(GONE);
                btnPause.setVisibility(GONE);
                btnStart.setVisibility(VISIBLE);

            }
        });

        etHour = (EditText) findViewById(R.id.etHour);
        etMinute = (EditText) findViewById(R.id.etMinute);
        etSecond = (EditText) findViewById(R.id.etSecond);

        etHour.setText("00");
        etMinute.setText("00");
        etSecond.setText("00");

        etHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)){
                    int value = Integer.parseInt(s.toString());
                    if(value>59){
                        etHour.setText("59");
                    }else if(value<0){
                        etHour.setText("0");
                    }

                }
                checkToEnableBtnStart();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMinute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());
                    if (value > 59) {
                        etMinute.setText("59");
                    } else if (value < 0) {
                        etMinute.setText("0");
                    }
                }
                checkToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etSecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());
                    if (value > 59) {
                        etSecond.setText("59");
                    } else if (value < 0) {
                        etSecond.setText("0");
                    }
                }
                checkToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnStart.setVisibility(View.VISIBLE);
        btnStart.setEnabled(false);
        btnPause.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);


    }


    private void checkToEnableBtnStart(){
        btnStart.setEnabled((!TextUtils.isEmpty(etHour.getText()) && Integer.parseInt(etHour.getText().toString())>0)||
                (!TextUtils.isEmpty(etMinute.getText()) && Integer.parseInt(etMinute.getText().toString())>0)||
                (!TextUtils.isEmpty(etSecond.getText()) && Integer.parseInt(etSecond.getText().toString())>0));

    }

    private void startTimer(){

        if(timerTask == null){
            allTimerCount = Integer.parseInt(etHour.getText().toString())*60*60 + Integer.parseInt(etMinute.getText().toString())*60+ Integer.parseInt(etSecond.getText().toString());
            timerTask = new TimerTask(){

                @Override
                public void run() {
                    allTimerCount--;
                    handler.sendEmptyMessage(MSG_WHAT_TIME_TICK);

                    if(allTimerCount<=0){
                        handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                        //TODO: pass message that task is finished.
                        stopTimer();
                    }
                }
            };

            timer.schedule(timerTask,1000,1000);


        }
    }

    private void stopTimer(){
        if (timerTask!=null){
            timerTask.cancel();
            timerTask = null;
        }

    }

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_WHAT_TIME_IS_UP:
                    new AlertDialog.Builder(getContext()).setTitle("Task Finished").setMessage("Time is up").setNegativeButton("Cancel", null).show();
                    btnReset.setVisibility(GONE);
                    btnResume.setVisibility(GONE);
                    btnPause.setVisibility(GONE);
                    btnStart.setVisibility(VISIBLE);
                    break;
                case MSG_WHAT_TIME_TICK:
                    int hour = allTimerCount/60/60;
                    int minute = (allTimerCount/60)%60;
                    int sec = allTimerCount%60;

                    etHour.setText(hour+"");
                    etMinute.setText(minute+"");
                    etSecond.setText(sec+"");


                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + msg.what);
            }
        };
    };



    private Button btnStart,btnPause,btnResume,btnReset;
    private EditText etHour,etMinute,etSecond;
    private Timer timer=new Timer();
    private int allTimerCount = 0;
    private TimerTask timerTask = null;
    private static final int MSG_WHAT_TIME_IS_UP = 1;
    private static final int MSG_WHAT_TIME_TICK = 2;
}
