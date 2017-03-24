package com.example.sweet_xue.netrequest;

import java.io.IOException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sweet_xue.netrequest.util.HttpCallbackListener;
import com.example.sweet_xue.netrequest.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private String address = "https://www.baidu.com";

    private Button btn_request, btn_request_okhttp;

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_request = (Button) findViewById(R.id.btn_request_http);
        btn_request_okhttp = (Button) findViewById(R.id.btn_request_okhttp);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_request.setOnClickListener(this);
        btn_request_okhttp.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_http:
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {

                        System.out.println("====response===http==" + response);
//                        tv_show.setText(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("====response==http===" + e);
                    }
                });
                break;
            case R.id.btn_request_okhttp:
                HttpUtil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        System.out.println("====response===okhttp==" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        System.out.println("====response===okhttp==" + responseData);
                    }
                });
                break;
        }
    }
}
