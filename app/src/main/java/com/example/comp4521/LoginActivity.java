//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {

    UserDatabaseHelper userDatabaseHelper;

    EditText et_email;
    EditText et_password;
    TextView tv_email_error;
    TextView tv_password_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_login = findViewById(R.id.btn_login);
        ImageButton btn_back = findViewById(R.id.btn_back);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        final TextView tv_email = findViewById(R.id.tv_email);
        final TextView tv_password = findViewById(R.id.tv_password);
        final TextView tv_noAccount = findViewById(R.id.tv_noAccount);
        tv_email_error = findViewById(R.id.tv_email_error);
        tv_password_error = findViewById(R.id.tv_password_error);

        userDatabaseHelper = new UserDatabaseHelper(this);

        btn_login.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv_email_error.setVisibility(View.GONE);
                tv_password_error.setVisibility(View.GONE);
                login();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_email.setVisibility(View.VISIBLE);
                    et_email.setHint("");
                } else {
                    tv_email.setVisibility(View.GONE);
                    et_email.setHint("Email");
                }
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_password.setVisibility(View.VISIBLE);
                    et_password.setHint("");
                } else {
                    tv_password.setVisibility(View.GONE);
                    et_password.setHint("Password");
                }
            }
        });

        tv_noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

    }

    public void login() {
        boolean loginSuccessful = userDatabaseHelper.loginSuccessful(et_email.getText().toString(), et_password.getText().toString());

        if(loginSuccessful){
            Intent intent = new Intent(LoginActivity.this, CalendarActivity.class);
            intent.putExtra("username", userDatabaseHelper.getUsername(et_email.getText().toString()));
            startActivity(intent);
        } else {
            et_password.setText(null);
            Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
        }

    }


}
