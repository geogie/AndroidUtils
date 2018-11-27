package com.example.george.androidutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.george.androidutils.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private EditText num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

    }

    public void toast(View view) {
        int n1 = Integer.parseInt(num1.getText().toString().trim());
        int n2 = Integer.parseInt(num2.getText().toString().trim());
//        ToastUtils.showLocationToast(n1,n2);
        ToastUtils.showCustom(this);
    }
}
