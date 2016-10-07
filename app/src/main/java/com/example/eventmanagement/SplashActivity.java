package com.example.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.widget.VideoView;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        VideoView splashVideo = (VideoView)findViewById(R.id.video_splash);

        splashVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie));
        splashVideo.setMediaController(null);
        splashVideo.setZOrderOnTop(true);

        splashVideo.start();

        splashVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                startActivity(new Intent(SplashActivity.this, Home_Screen.class));
                finish();
            }
        });
    }
}
