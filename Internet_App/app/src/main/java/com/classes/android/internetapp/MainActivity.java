package com.classes.android.internetapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.util.BitSet;

import java.util.logging.LogRecord;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Button b1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                downloadImage("https://s-media-cache-ak0.pinimg.com/736x/79/fe/4b/79fe4b7a9773b1e8b1213d58592efab9.jpg");
            }
        });


    }

    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;

        int cod = 2;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            cod = httpConn.getResponseCode();

            if (cod == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }

        } catch (MalformedInputException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private void downloadImage(String urlStr) {
        final String url = urlStr;

        //to unblock main application while downloading the image
        new Thread() {
            public void run() {
                InputStream in = null;// initialize it with image url
                Message msg = Message.obtain();
                msg.what = 1;

                try

                {
                    in = openHttpConnection(url);
                    bitmap = BitmapFactory.decodeStream(in);
                    Bundle b = new Bundle();//its for aggregation
                    b.putParcelable("bitmap", bitmap);
                    msg.setData(b);

                    //ImageView img = (ImageView) findViewById(R.id.imageView);
                    //img.setImageBitmap(bitmap);
                    in.close();

                } catch (IOException e1)

                {
                    e1.printStackTrace();
                }
                messageHandler.sendMessage(msg);
            }


        }.start();

    }

    private Handler messageHandler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));

        }



    };
}