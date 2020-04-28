package abc.home.zarni.welcomevoting.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.Result;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import abc.home.zarni.welcomevoting.R;
import abc.home.zarni.welcomevoting.model.User;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import me.myatminsoe.mdetect.MDetect;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private Button scan;
    private ImageView qr;

    private String year = "";
    String key = "";
    private SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        scan = (Button) findViewById(R.id.btn_scan);
        qr = (ImageView) findViewById(R.id.icon);

        scannerView = new ZXingScannerView(getApplicationContext());


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });

        sp = getSharedPreferences("Voting",MODE_PRIVATE);
        // setupWindowAnimations();

    }


    private void scanQR() {
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }



    @Override
    public void handleResult(Result result) {
        key = result.toString();



        AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... movieIds) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://voting.zarnimoehtet.ml/login.php?name=" + key)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());



                        JSONObject object = array.getJSONObject(0);

                   final      User user = new User(object.getString("name"),object.getString("user_name"),object.getString("isvoted"),object.getString("king"),object.getString("queen"),object.getString("roll_no"));



                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SetupActivity(user);
                        }
                    });

                        Log.i("Server Response", "Name = " + user.getName());




                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        };

        asyncTask.execute(key);


      Intent i = new Intent(LoginActivity.this,MainActivity.class);
      i.putExtra("refresh","1");
      startActivity(i);
        scannerView.resumeCameraPreview(this);
        Log.i("Finish","yes");
        finish();


    }

    private void SetupActivity(User user) {
        if(user.getIsVoted().equals("yes")) {

            String kname = King_Encode(user.getKing());
            String qname = Queen_Encode(user.getQueen());

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("king", kname);
            editor.putString("queen", qname);
            editor.putString("user_name",user.getUser_name());
            editor.putString("roll_no",user.getRoll());
            editor.putString("isVoted_king","yes");
            editor.putString("isVoted_queen","yes");
            editor.putString("Voted", user.getIsVoted());
            editor.putString("Code", user.getName());
            editor.putString("login", "yes");


           TastyToast.makeText(LoginActivity.this, MDetect.INSTANCE.getText("Login ဝင်ခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
           // editor.putString("loginSuccess","1");
            editor.commit();
        }else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("king", "");
            editor.putString("queen","");
            editor.putString("user_name",user.getUser_name());
            editor.putString("roll_no",user.getRoll());
            editor.putString("isVoted_king","no");
            editor.putString("isVoted_queen","no");
            editor.putString("Voted", user.getIsVoted());
            editor.putString("Code", user.getName());
            editor.putString("login", "yes");
            TastyToast.makeText(LoginActivity.this, MDetect.INSTANCE.getText("Login ဝင်ခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
          //  editor.putString("loginSuccess","1");
            editor.commit();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }


    public String King_Encode(String kid){
        String name = "";
        if (kid.equals("1")){
            name = "စောရဲရင့်နောင်";
        }else if (kid.equals("2")){
            name = "မောင်ခန့်ဝင်း";
        }else if (kid.equals("3")){
            name = "မင်းချမ်းမွန်အောင်";
        }else if (kid.equals("4")){
            name = "မောင်ကျော်ကျော်ထက်";
        }else if (kid.equals("5")){
            name = "မောင်ဥက္ကာကျော်";
        }else if (kid.equals("6")){
            name = "မောင်သီဟဦး";
        }else if (kid.equals("7")){
            name = "မောင်သူမြတ်ထူး";
        }else if (kid.equals("8")){
            name = "မင်းအောင်မျိုးသူ";
        }
        return name;
    }

    public String Queen_Encode(String kid){
        String name = "";
        if (kid.equals("1")){
            name = "မမိုးသူဇာ";
        }else if (kid.equals("2")){
            name = "မဖူးပြည့်ပြည့်စံ";
        }else if (kid.equals("3")){
            name = "မဇင်ပွင့်ဖြူ";
        }else if (kid.equals("4")){
            name = "မနှင်းယုဝေ";
        }else if (kid.equals("5")){
            name = "မသဲစုထွေးချစ်";
        }else if (kid.equals("6")){
            name = "မဝင်းသဉ္ဇာခိုင်";
        }else if (kid.equals("7")){
            name = "မနော်ခိုင်ကြည်စင်";
        }else if (kid.equals("8")){
            name = "မနေခြည်အောင်";
        }else if (kid.equals("8")){
            name = "မစန္ဒီလှ";
        }
        return name;
    }
}

