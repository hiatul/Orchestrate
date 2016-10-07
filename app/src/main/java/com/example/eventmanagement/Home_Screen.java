package com.example.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class Home_Screen extends Activity implements View.OnClickListener {

    public static Typeface getFont(AssetManager assetManager){
        Typeface font = Typeface.createFromAsset(assetManager, "Comix Loud.ttf");
        return  font;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);


        TextView tv_event = (TextView) findViewById(R.id.tv_event);
        String text = "  ORCHESTRATE   ";
        SpannableString styledString = new SpannableString(text);
        styledString.setSpan(new BackgroundColorSpan(Color.BLACK), 0, 16, 0);
        tv_event.setText(styledString);
        tv_event.setTypeface(getFont(getAssets()));

        TextView tv_reg = (TextView) findViewById(R.id.tv_reg);
        String text1 = "  Register Here For Events  ";
        SpannableString styledString1 = new SpannableString(text1);
        styledString1.setSpan(new BackgroundColorSpan(Color.BLACK), 0, 27, 0);
        tv_reg.setText(styledString1);
        tv_reg.setTypeface(getFont(getAssets()));

        TextView tv_sign_in = (TextView) findViewById(R.id.tv_sign);
        String text2 = "  SIGN IN   ";
        SpannableString styledString2 = new SpannableString(text2);
        styledString2.setSpan(new BackgroundColorSpan(Color.BLACK), 0, 10, 0);
        tv_sign_in.setText(styledString2);
        tv_sign_in.setTypeface(getFont(getAssets()));

        findViewById(R.id.ll_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_get_started).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_get_started:
                    startActivity(new Intent(this, SignUp.class));
                    break;
                case R.id.ll_sign_in:
                    startActivity(new Intent(this, SignIn.class));
                    break;
            }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
