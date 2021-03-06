package com.lentimosystems.licio.futaa;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class MainActivity extends AppCompatActivity {
    private KenBurnsView kenburns;
    private boolean moving = true;
    KenBurnsView mKenburns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kenburns = findViewById(R.id.kenburns);

        Typeface openSansFont = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");

        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(3000, adi);
        kenburns.setTransitionGenerator(generator);

        kenburns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moving) {
                    kenburns.pause();
                    moving = false;
                } else {
                    kenburns.resume();
                    moving = true;
                }
            }
        });

        kenburns.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
