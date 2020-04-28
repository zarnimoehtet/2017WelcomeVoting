package abc.home.zarni.welcomevoting.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.nio.charset.Charset;

public class Utils {

    public static boolean isOnline(Context context){

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static final Charset ISO_8859_1 = Charset.forName("ISO_8859_1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static Typeface getTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"Quicksand-Bold.ttf");
    }

    public static Typeface getMMTypeface (Context context){
        return Typeface.createFromAsset(context.getAssets(),"MasterpieceUniRound.ttf");
    }
}
