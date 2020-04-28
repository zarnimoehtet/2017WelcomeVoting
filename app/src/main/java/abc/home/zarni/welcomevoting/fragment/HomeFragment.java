package abc.home.zarni.welcomevoting.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.util.HashMap;

import abc.home.zarni.welcomevoting.R;
import abc.home.zarni.welcomevoting.activity.MainActivity;
import abc.home.zarni.welcomevoting.model.Admin;
import abc.home.zarni.welcomevoting.utils.Utils;
import me.myatminsoe.mdetect.MDetect;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View v;
    private static final String BASE_URL = "http://voting.zarnimoehtet.ml/vote.php";
    private OkHttpClient client = new OkHttpClient();
    private SliderLayout mDemoSlider;
    TextView title, subtitle, selected, ktitle,qtitle, king , queen;
    Button vote, clear;

    private SharedPreferences preferences;
    String king_name,queen_name;
    Boolean isVoted_king = false;
    Boolean isVoted_queen = false;
    String vote_king,vote_queen,voted;
    String code,respone,login, resp="";
    private DatabaseReference db ;
    Admin admin = new Admin();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_home, container, false);


        preferences = getActivity().getSharedPreferences("Voting", MODE_PRIVATE);

        login = preferences.getString("login","");

//        String success = preferences.getString("loginSuccess","");
//
//        if (success.equals("1")){
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("loginSuccess","");
//            editor.commit();
//            TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Login ဝင်ခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
//        }else if (success.equals("0")){
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("loginSuccess","");
//            editor.commit();
//            TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Login ဝင်ခြင်းမအောင်မြင်ပါ။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
//        }




        code = preferences.getString("Code","");

        respone = preferences.getString("response","");

        king_name = preferences.getString("king","");

        queen_name = preferences.getString("queen","");

        vote_king = preferences.getString("isVoted_king","");

        vote_queen = preferences.getString("isVoted_queen","");

        voted = preferences.getString("Voted","");

        if (vote_king.equals("yes")){
            isVoted_king = true;
        }

        if (vote_queen.equals("yes")){
            isVoted_queen = true;
        }


        if (respone.equals("1")){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("response","");
            editor.commit();
            TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ပေးခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
        }else if (respone.equals("0")){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("response","");
            editor.commit();
            TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ပေးခြင်းမအောင်မြင်ပါ။ နောက်ထပ်ကြိုးစားကြည့်ပါ။"),TastyToast.LENGTH_SHORT,TastyToast.ERROR ).show();
        }else {

        }


        mDemoSlider = v.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("a",R.mipmap.one_min);
        file_maps.put("b",R.mipmap.two_min);
        file_maps.put("c",R.mipmap.three_min);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

        title = v.findViewById(R.id.title);
        subtitle = v.findViewById(R.id.sub_title);
        selected = v.findViewById(R.id.selected);
        ktitle = v.findViewById(R.id.ktitle);
        qtitle = v.findViewById(R.id.qtitle);
        king = v.findViewById(R.id.king);
        queen = v.findViewById(R.id.queen);
        vote = v.findViewById(R.id.vote);
        clear = v.findViewById(R.id.clear);

        if (isVoted_king){
            king.setTypeface(Utils.getMMTypeface(getActivity()));
            king.setText(MDetect.INSTANCE.getText(king_name));

        }

        if (isVoted_queen){
            queen.setTypeface(Utils.getMMTypeface(getActivity()));
            queen.setText(MDetect.INSTANCE.getText(queen_name));
        }

        title.setTypeface(Utils.getTypeface(getActivity()));
        subtitle.setTypeface(Utils.getTypeface(getActivity()));
        selected.setTypeface(Utils.getTypeface(getActivity()));
        ktitle.setTypeface(Utils.getTypeface(getActivity()));
        qtitle.setTypeface(Utils.getTypeface(getActivity()));
        king.setTypeface(Utils.getTypeface(getActivity()));
        queen.setTypeface(Utils.getTypeface(getActivity()));
        vote.setTypeface(Utils.getTypeface(getActivity()));
        clear.setTypeface(Utils.getTypeface(getActivity()));


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                        .setMessage(MDetect.INSTANCE.getText("ရွေးချယ်ထားတာတွေကို ပယ်ဖျက်မှာသေချာပြီးလား?"))
                        .setPositiveButton(MDetect.INSTANCE.getText("သေချာပါပြီ"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("king","");
                                editor.putString("queen","");
                                editor.putString("isVoted_king","");
                                editor.putString("isVoted_queen","");
                                editor.commit();

                                king.setText("King");
                                queen.setText("Queen");


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


        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                db = FirebaseDatabase.getInstance().getReference().child("Admin");
                db.child("1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                           // for (DataSnapshot ds :dataSnapshot.getChildren()){
                                 admin = dataSnapshot.getValue(Admin.class);
                           // }


                            if (!admin.getPermission().equals("true")) {

                                TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ပေးလို့မရသေးပါ"),TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();

                            }else {

                                if (!king.getText().toString().equals("King") && !queen.getText().toString().equals("Queen")) {

                                    if (login.equals("yes")) {

                                        if (voted.equals("yes")) {
                                            TastyToast.makeText(getActivity(), MDetect.INSTANCE.getText("Vote ပေးပြီးသားဖြစ်သောကြောင့် ထပ်ပေးလို့မရနိုင်ပါ။"), TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                            builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                                                    .setMessage(MDetect.INSTANCE.getText("ရွေးချယ်ထားတာတွေကို Vote မှာသေချာပြီလား?"))
                                                    .setPositiveButton(MDetect.INSTANCE.getText("သေချာပါပြီ"), new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            String kid = Decode_King(king_name);
                                                            String qid = Decode_Queen(queen_name);

                                                            UpdateData(code, kid, qid);


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
                                    } else {
                                        TastyToast.makeText(getActivity(), MDetect.INSTANCE.getText("Vote ပေးရန် Login ဝင်ပေးပါ။။"), TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                                    }
                                } else {
                                    TastyToast.makeText(getActivity(), MDetect.INSTANCE.getText("ရွေးချယ်မှုများပြုလုပ်ပေးပါ။"), TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });
        return v;
    }

    public String Decode_King(String name){
        String id = "";

        if (name.equals("စောရဲရင့်နောင်")){
            id = "1";
        }else  if (name.equals("မောင်ခန့်ဝင်း")){
            id = "2";
        }else  if (name.equals("မင်းချမ်းမွန်အောင်")){
            id = "3";
        }else  if (name.equals("မောင်ကျော်ကျော်ထက်")){
            id = "4";
        }else  if (name.equals("မောင်ဥက္ကာကျော်")){
            id = "5";
        }else  if (name.equals("မောင်သီဟဦး")){
            id = "6";
        }else  if (name.equals("မောင်သူမြတ်ထူး")){
            id = "7";
        }else  if (name.equals("မင်းအောင်မျိုးသူ")){
            id = "8";
        }

        return id;
    }

    public String Decode_Queen(String name){
        String id = "";

        if (name.equals("မမိုးသူဇာ")){
            id = "1";
        }else  if (name.equals("မဖူးပြည့်ပြည့်စံ")){
            id = "2";
        }else  if (name.equals("မဇင်ပွင့်ဖြူ")){
            id = "3";
        }else  if (name.equals("မနှင်းယုဝေ")){
            id = "4";
        }else  if (name.equals("မသဲစုထွေးချစ်")){
            id = "5";
        }else  if (name.equals("မဝင်းသဉ္ဇာခိုင်")){
            id = "6";
        }else  if (name.equals("မနော်ခိုင်ကြည်စင်")){
            id = "7";
        }else  if (name.equals("မနေခြည်အောင်")){
            id = "8";
        }else  if (name.equals("မစန္ဒီလှ")){
            id = "9";
        }

        return id;
    }



    public void UpdateData(final String name, final String king_name, final String queen_name) {

        RequestBody body = new FormBody.Builder()
                .add("name", name)
                .add("king", king_name)
                .add("queen", queen_name)
                .build();
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



        //Log.i("Response" ,resp);

//
//        if (resp.equals("vote success")) {
//            TastyToast.makeText(getActivity(), MDetect.INSTANCE.getText("Vote ခြင်းအောင်မြင်ပါတယ်။"), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();
//        }
//        else {
//            TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ပေးခြင်းမအောင်မြင်ပါ။ နောက်ထပ်ကြိုးစားကြည့်ပါ။"),TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
//        }
    }

    private void outAlert(String resp) {

        if (resp.equals("vote success")) {

         //   TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ခြင်းအောင်မြင်ပါတယ်။"),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("response","1");
            editor.putString("Voted","yes");
            editor.commit();
            Refresh();
        }
        else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("response","0");
            editor.putString("Voted","no");
            editor.commit();
            Refresh();
          // TastyToast.makeText(getActivity(),MDetect.INSTANCE.getText("Vote ပေးခြင်းမအောင်မြင်ပါ။ နောက်ထပ်ကြိုးစားကြည့်ပါ။"),TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
        }
    }

    public void Refresh(){
        MainActivity parentActivity = (MainActivity) getActivity();
        parentActivity.changeFragment(new HomeFragment());
        parentActivity.bottomBar.selectTabAtPosition(0);

    }
}
