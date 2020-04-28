package abc.home.zarni.welcomevoting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import abc.home.zarni.welcomevoting.R;

public class SplashScreen extends Activity {

    public static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView image = (ImageView)findViewById(R.id.image);

        YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .playOn(findViewById(R.id.image));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               startActivity( new Intent(SplashScreen.this,MainActivity.class));
               finish();
            }
        },TIME_OUT);


    }
}
