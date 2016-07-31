package me.thenpush.iamessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import me.thenpush.R;

/**
 * Created by pappacena on 31/07/16.
 */
public class MessageViewer extends Activity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_viewer);


        this.webview = (WebView) findViewById(R.id.webview);
        this.initWebView();
    }

    private void initWebView() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }
}
