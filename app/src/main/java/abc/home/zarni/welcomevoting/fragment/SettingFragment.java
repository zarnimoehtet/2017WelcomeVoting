package abc.home.zarni.welcomevoting.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;

import abc.home.zarni.welcomevoting.R;

import abc.home.zarni.welcomevoting.activity.LoginActivity;
import abc.home.zarni.welcomevoting.activity.MainActivity;
import abc.home.zarni.welcomevoting.utils.Utils;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import me.myatminsoe.mdetect.MDetect;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment  {

    View v;
    TextView setting,login_title,user_code,code,alert,reg_title,name_title,name_,roll_title,roll,reg;
    ImageView logout,login;
    SharedPreferences preferences;
    EditText input_name,input_roll;
    Button save,cancel;
    ImageView edit;
    String name_input,roll_input;
    Boolean isLogin = false;
    CardView regcard;
    private ZXingScannerView scannerView;
    private static final String BASE_URL = "http://voting.zarnimoehtet.ml/AddUserName.php";
    private OkHttpClient client = new OkHttpClient();
    String key = "";
    String name = "";

    String resp = "";
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_setting, container, false);

        preferences = getActivity().getSharedPreferences("Voting",Context.MODE_PRIVATE);

        String a = preferences.getString("login","");
        if(a.equals("yes")){
            isLogin = true;
        }else {
            isLogin = false;
        }


        setting = v.findViewById(R.id.setting_title);
        login_title = v.findViewById(R.id.login_title);
        alert = v.findViewById(R.id.alert);
        user_code = v.findViewById(R.id.user_code);
        code = v.findViewById(R.id.code);
        logout = v.findViewById(R.id.logout);
        login = v.findViewById(R.id.login);
        reg_title = v.findViewById(R.id.reg_title);
        name_title = v.findViewById(R.id.name_title);
        name_ = v.findViewById(R.id.name);
        roll = v.findViewById(R.id.roll);
        roll_title  = v.findViewById(R.id.roll_title);
        input_name = v.findViewById(R.id.username);
        input_roll = v.findViewById(R.id.roll_input);
        save = v.findViewById(R.id.save);
        cancel = v.findViewById(R.id.cancel);
        regcard = v.findViewById(R.id.logoView);
        edit = v.findViewById(R.id.edit);
        reg = v.findViewById(R.id.loginTitle);


        name_title.setTypeface(Utils.getTypeface(getActivity()));
        reg.setTypeface(Utils.getTypeface(getActivity()));
        name_.setTypeface(Utils.getTypeface(getActivity()));
        roll_title.setTypeface(Utils.getTypeface(getActivity()));
        roll.setTypeface(Utils.getTypeface(getActivity()));
        setting.setTypeface(Utils.getTypeface(getActivity()));
        login_title.setTypeface(Utils.getTypeface(getActivity()));
        alert.setTypeface(Utils.getTypeface(getActivity()));
        user_code.setTypeface(Utils.getTypeface(getActivity()));
        code.setTypeface(Utils.getTypeface(getActivity()));
        reg_title.setTypeface(Utils.getTypeface(getActivity()));
        save.setTypeface(Utils.getTypeface(getActivity()));
        cancel.setTypeface(Utils.getTypeface(getActivity()));

        scannerView = new ZXingScannerView(getActivity());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regcard.setVisibility(View.VISIBLE);
            }
        });



        if(isLogin){
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            String b = preferences.getString("Code","");
            String c = preferences.getString("user_name","");
            String d = preferences.getString("roll_no","");
            alert.setVisibility(View.GONE);
            if (!b.equals(null) && !c.equals(null) && !d.equals(null) ){
                code.setText(b);
                input_roll.setText(c);
                input_name.setText(d);
                name_.setText(c);
                roll.setText(d);
            //    edit.setClickable(false);
            }
        }else {
            logout.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            alert.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regcard.setVisibility(View.GONE);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_input = input_name.getText().toString().trim();
                roll_input = input_roll.getText().toString().trim();

                // Toast.makeText(getActivity(),name_input + "   " + roll_input,Toast.LENGTH_SHORT).show();

                if(!name.equals(null) && !roll.equals(null)){
                    UpdateData(code.getText().toString(),name_input,roll_input);

                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                        .setMessage(MDetect.INSTANCE.getText("Account  မှထွက်မှာသေချာပြီလား?"))
                        .setPositiveButton(MDetect.INSTANCE.getText("သေချာပါပြီ"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("login","no");
                                editor.putString("king","");
                                editor.putString("queen","");
                                editor.putString("isVoted_king","");
                                editor.putString("isVoted_queen","");
                                editor.putString("user_name","");
                                editor.putString("roll_no","");
                                editor.putString("Voted","");
                                editor.putString("Code","");
                                editor.putString("response","");
                                editor.commit();
                                Refresh();


                            }
                        })
                        .setNegativeButton(MDetect.INSTANCE.getText("မသေချာပါ"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getActivity(), LoginActivity.class));
                 getActivity().finish();
            }
        });
        return v;
    }



    public void UpdateData(final String code, final String user_name,final String roll) {

        RequestBody body = new FormBody.Builder()
                .add("name", code)
                .add("user_name", user_name)
                .add("roll_no",roll)
                .build();

        Toast.makeText(getActivity(),user_name + "    " + roll,Toast.LENGTH_SHORT).show();
        Request request = new Request.Builder().url(BASE_URL).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    resp = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            outAlert(resp);
                        }
                    });

                } catch (IOException e) {
                    // Log.e(TAG_REGISTER, "Exception caught: ", e);
                    System.out.println("Exception caught" + e.getMessage());
                }
            }

        });
    }


        private void outAlert(String resp) {

            if (resp.equals("add success")) {
                regcard.setVisibility(View.GONE);
                TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Name ခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user_name",name_input);
                editor.putString("roll_no",roll_input);
               // editor.putString("Voted","yes");
                editor.commit();
                Refresh();
            }else {

                TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Name ပေးခြင်းမအောင်မြင်ပါ။ နောက်ထပ်ကြိုးစားကြည့်ပါ။"),TastyToast.LENGTH_SHORT, TastyToast.ERROR).show();
            }
        }



    public void Refresh(){
        MainActivity parentActivity = (MainActivity) getActivity();

            parentActivity.changeFragment(new SettingFragment());
            parentActivity.bottomBar.selectTabAtPosition(3);

    }

}


