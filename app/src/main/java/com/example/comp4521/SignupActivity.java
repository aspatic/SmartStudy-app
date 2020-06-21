//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp4521.LoginActivity;
import com.example.comp4521.R;
import com.example.comp4521.UserDatabaseHelper;

public class SignupActivity extends AppCompatActivity {

    UserDatabaseHelper userDatabaseHelper;

    EditText et_username;
    EditText et_email;
    EditText et_password;
    EditText et_password_confirm;
    TextView tv_name_error;
    TextView tv_email_error;
    TextView tv_password_error;
    TextView tv_password_confirm_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button btn_signup = findViewById(R.id.btn_signup);
        ImageButton btn_back = findViewById(R.id.btn_back);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_password_confirm = findViewById(R.id.et_password_confirm);
        final TextView tv_username = findViewById(R.id.tv_username);
        final TextView tv_email = findViewById(R.id.tv_email);
        final TextView tv_password = findViewById(R.id.tv_password);
        final TextView tv_password_confirm = findViewById(R.id.tv_password_confirm);
        final TextView tv_hasAccount = findViewById(R.id.tv_hasAccount);
        tv_name_error = findViewById(R.id.tv_name_error);
        tv_email_error = findViewById(R.id.tv_email_error);
        tv_password_error = findViewById(R.id.tv_password_error);
        tv_password_confirm_error = findViewById(R.id.tv_password_confirm_error);

        userDatabaseHelper = new UserDatabaseHelper(this);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_name_error.setVisibility(View.GONE);
                tv_email_error.setVisibility(View.GONE);
                tv_password_error.setVisibility(View.GONE);
                tv_password_confirm_error.setVisibility(View.GONE);
                signup();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });

        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_username.setVisibility(View.VISIBLE);
                    et_username.setHint("");
                } else {
                    tv_username.setVisibility(View.GONE);
                    et_username.setHint("Username");
                }
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

        et_password_confirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tv_password_confirm.setVisibility(View.VISIBLE);
                    et_password_confirm.setHint("");
                } else {
                    tv_password_confirm.setVisibility(View.GONE);
                    et_password_confirm.setHint("Password Confirmation");
                }
            }
        });

        tv_hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });



    }

    public void signup() {
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String password_confirm = et_password_confirm.getText().toString();
        if (!password.equals(password_confirm)) {
            et_password_confirm.setText(null);
            Toast.makeText(SignupActivity.this, "Sign Up failed. Password and Confirmed Password should be the same.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 8) {
            et_password.setText(null);
            et_password_confirm.setText(null);
            Toast.makeText(SignupActivity.this, "Sign Up failed. Password should have at least 8 characters.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userDatabaseHelper.emailExists(email)) {
            Toast.makeText(SignupActivity.this, "The email has been used before. Please try another email.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userDatabaseHelper.usernameExists(username)) {
            Toast.makeText(SignupActivity.this, "The username has been used before. Please try another username.", Toast.LENGTH_SHORT).show();
            return;
        }
        userDatabaseHelper.addUser(username, email, password);
        Toast.makeText(SignupActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }


}
