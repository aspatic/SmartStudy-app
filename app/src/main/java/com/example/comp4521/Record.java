//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

/*THIS IS THE PAST OVERALL RECORDS ACTIVITY*/
/*USERS CAN TAB THE IMAGE IN THIS ACTIVITY TO FIND THE RECORDS THEY WANT ie. PAST COMPLETION/ACHIEVEMENT*/

public class Record extends AppCompatActivity {
    private TextView tv;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tv = findViewById(R.id.record_title);
        String title = "Click the images & look for your INCREDIBLE RECORDS !";
        SpannableString ss = new SpannableString(title);
        StyleSpan bold = new StyleSpan(Typeface.BOLD);
        ss.setSpan(bold,33,53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ss);

    }

    public void showPastAcRecord(View view) {
        intent = new Intent(this,acRecord.class);
        startActivity(intent);
    }

    public void showPastCalendarRecord(View view) {
        intent = new Intent(this,completionRecord.class);
        startActivity(intent);
    }

    public void showPastGraphRecord(View view) {
        intent = new Intent(this,completionGraph.class);
        startActivity(intent);
    }
}

