package abc.home.zarni.welcomevoting.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import abc.home.zarni.welcomevoting.R;
import abc.home.zarni.welcomevoting.fragment.HomeFragment;
import abc.home.zarni.welcomevoting.fragment.KingFragment;
import abc.home.zarni.welcomevoting.fragment.QueenFragment;
import abc.home.zarni.welcomevoting.fragment.SettingFragment;
import abc.home.zarni.welcomevoting.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 0;

public BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bundle extras = getIntent().getExtras();
//
//        if(extras != null) {
//            String message = extras.getString("refresh");
//            if (message.equals("1")){
//                changeFragment(new HomeFragment());
//                Log.i("Refresh ===>","yess");
//            }
//
//        }

        bottomBar = findViewById(R.id.bottomBar);

        bottomBar.getTabAtPosition(0).setTitleTypeface(Utils.getTypeface(MainActivity.this));
        bottomBar.getTabAtPosition(1).setTitleTypeface(Utils.getTypeface(MainActivity.this));
        bottomBar.getTabAtPosition(2).setTitleTypeface(Utils.getTypeface(MainActivity.this));
        bottomBar.getTabAtPosition(3).setTitleTypeface(Utils.getTypeface(MainActivity.this));

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_home: changeFragment(new HomeFragment());
                        break;

                    case R.id.tab_king: changeFragment(new KingFragment());
                        break;

                    case R.id.tab_queen: changeFragment(new QueenFragment());
                        break;

                    case R.id.tab_set: changeFragment(new SettingFragment());
                        break;

                    default: changeFragment(new HomeFragment());
                        break;
                }
            }
        });


        if (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_DENIED
                && ContextCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA, CALL_PHONE}, REQUEST_CAMERA);

        }


    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,fragment).commit();
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //   Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                //  Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                .setMessage(MDetect.INSTANCE.getText("Application မှ ထွက်လိုပါသလား ?"))
                .setPositiveButton(MDetect.INSTANCE.getText("ထွက်မယ်"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(MDetect.INSTANCE.getText("မထွက်ပါ"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

}

