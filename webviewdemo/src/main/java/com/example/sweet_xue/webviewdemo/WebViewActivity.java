package com.example.sweet_xue.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {


    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        initView();


        String url = getIntent().getStringExtra("url");
        loadShow(url);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        mWebView.onResume();
        //恢复pauseTimers状态
        mWebView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        mWebView.onPause();
        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
        //它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
        mWebView.pauseTimers();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new BackHandler(this), "back");


    }

    public static class BackHandler {

        private WebViewActivity mWebViewActivity;

        public BackHandler(WebViewActivity mWebViewActivity) {
            this.mWebViewActivity = mWebViewActivity;
        }

        @JavascriptInterface
        public void backUp() {
            mWebViewActivity.finish();
        }

    }


    private void loadShow(String url) {

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.setVisibility(View.INVISIBLE);
                Toast.makeText(WebViewActivity.this, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁Webview
//在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
//但是注意：webview调用destory时,webview仍绑定在Activity上
//这是由于自定义webview构建时传入了该Activity的context对象
//因此需要先从父容器中移除webview,然后再销毁webview:
//        rootLayout.removeView(webView);
        mWebView.destroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
