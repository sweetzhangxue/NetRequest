package com.example.sweet_xue.webviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btn_baidu, btn_cnds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_baidu = (Button) findViewById(R.id.btn_baidu);
        btn_cnds = (Button) findViewById(R.id.btn_cnds);

        btn_baidu.setOnClickListener(this);
        btn_cnds.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        switch (v.getId()) {
            case R.id.btn_baidu:
                intent.putExtra("url", "https://www.baidu.com");
                break;
            case R.id.btn_cnds:
                intent.putExtra("url", "http://www.csdn.net");
                break;
        }

        startActivity(intent);

    }
}
