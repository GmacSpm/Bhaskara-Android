package com.GmacSpm.Bhaskara;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AjudaActivity extends Activity {
    private Animation deslize;

    @Override
    public void onCreate(Bundle bundle) {
        
        super.onCreate(bundle);
        setContentView(R.layout.ajuda_a);
        View findViewById = findViewById(R.id.ajudaaLinearLayout1);
        this.deslize = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.debaixo);
        findViewById.startAnimation(this.deslize);
    }
}
