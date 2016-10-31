package com.example.javah5;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private WebView webview;
	
    @SuppressLint("JavascriptInterface") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(MainActivity.this, "android");
        
        webview.loadUrl("file:///android_asset/web.html");
    
    
        findViewById(R.id.btn_android_to_js_).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(MainActivity.this, "点击了：java调用JS 按钮", Toast.LENGTH_SHORT).show();
				webview.loadUrl("javascript:javacalljs();");
			}
		});
        
        findViewById(R.id.btn_android_to_js_args_).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(MainActivity.this, "点击了：java调用JS有参函数 按钮", Toast.LENGTH_SHORT).show();
				webview.loadUrl("javascript:javacalljswith(" + "'http://www.baidu.com'" + ");");
			}
		});
    }
    
    @JavascriptInterface
    public void h5calljava() {
    	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "来自H5的函数回调:h5calljava", Toast.LENGTH_SHORT).show();
				findViewById(R.id.btn_android_to_js_).setVisibility(View.GONE);
			}
		});
    }
    
    @JavascriptInterface
    public void h5calljavawithargs(final String str) {
    	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "来自H5的带参数函数回调，参数="+str, Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    
}
