package abc.home.zarni.welcomevoting;

import android.app.Application;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import me.myatminsoe.mdetect.MDetect;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        MDetect.INSTANCE.init(this);
        FirebaseApp.getApps(this);
        FirebaseApp.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }


}
