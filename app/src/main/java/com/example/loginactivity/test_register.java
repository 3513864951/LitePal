package com.example.loginactivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.loginactivity.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerUsername;
    private EditText registerPassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_register);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        register = findViewById(R.id.btn_register);
        registerUsername = findViewById(R.id.et_register_username);
        registerPassword = findViewById(R.id.et_register_password);

        register.setOnClickListener(v -> {
            String username = registerUsername.getText().toString();
            String password = registerPassword.getText().toString();

            if (username.equals("") || password.equals("")) {
                Toast.makeText(RegisterActivity.this, "账号或密码为空", Toast.LENGTH_SHORT).show();
                return;
            }
            List<User> users = LitePal.findAll(User.class);
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    Toast.makeText(RegisterActivity.this, "账号不可以重复注册", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.save();
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent showintent = new Intent(RegisterActivity.this, ShowActivity.class);
            showintent.putExtra("username",username);
            showintent.putExtra("password",password);
            startActivity(showintent);
        });
    }
}
