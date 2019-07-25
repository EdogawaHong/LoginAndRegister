package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginandregister.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        db=new DatabaseHelper(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.etUsername.getText().toString();
                String pass=binding.etPassword.getText().toString();
                String cpass=binding.etConfirmPass.getText().toString();
                if(name.equals("")||pass.equals("")||cpass.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (chkpass(pass) == false) {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    } else {
                        if (pass.equals(cpass)) {
                            Boolean chkname = db.chkname(name);
                            if (chkname == true) {
                                Boolean insert = db.insert(name, pass);
                                if (insert == true) {
                                    Toast.makeText(getApplicationContext(), "Registered Successfull", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Username Already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public Boolean chkpass(String pass){
        long a=0;
        if(pass.length()>=6) {
            for (int i=0;i<=pass.length();i++) {
                if ((pass.charAt(i) >= 32 && pass.charAt(i) <= 47) || (pass.charAt(i) >= 58 && pass.charAt(i) <= 64) ||
                        (pass.charAt(i) >= 91 && pass.charAt(i) <= 96) || pass.charAt(i) >= 123 && pass.charAt(i) <= 126){
                    a++;
                    break;
                 }
            }
            for (int i=0;i<=pass.length();i++) {
                if (pass.charAt(i) >= 48 && pass.charAt(i) <= 57){
                    a++;
                    break;
                }

            }
            for (int i=0;i<=pass.length();i++) {
                if (pass.charAt(i) >= 65 && pass.charAt(i) <= 90){
                    a++;
                    break;
                }
            }
            for (int i=0;i<=pass.length();i++)
                if(pass.charAt(i)>=97&&pass.charAt(i)<=122) {
                    a++;
                    break;
                }
            }
        if(a==4) return true;
        else return false;
    }
}
