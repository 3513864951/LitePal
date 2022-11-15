package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
  //  private ImageView imageView;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //imageView=(ImageView) findViewById(R.id.)
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        Button register=(Button) findViewById(R.id.button_1);
        boolean isRemember = pref.getBoolean("remember_password", false);//初始值为false,不执行下方的if语句
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 如果账号是admin且密码是123456，就认为登录成功
                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) { // 检查复选框是否被选中
                        editor.putBoolean("remember_password", true);//变为true后，再次登陆后，就会自动读取密码并自动填充
                        editor.putString("account", account);
                        editor.putString("password", password);
                    }
                    else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.
                            class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "登陆成功！",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误！",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(LoginActivity.this,register.class);
                startActivity(intent_1);
            }
        });
    }
}





