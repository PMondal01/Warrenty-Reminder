package com.bits.warrentyguaranteed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashscreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        progressBar=findViewById(R.id.progressbar);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                startApp();

            }
        });
        thread.start();
    }

    public  void doWork()
    {
        for(progress=20;progress<=60;progress=progress+20)
        {
            try{
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void startApp()
    {
        Intent intent=new Intent(SplashscreenActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
