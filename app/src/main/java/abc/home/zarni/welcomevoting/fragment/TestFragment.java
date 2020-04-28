package abc.home.zarni.welcomevoting.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sdsmdg.tastytoast.TastyToast;

import abc.home.zarni.welcomevoting.R;
import abc.home.zarni.welcomevoting.activity.MainActivity;
import abc.home.zarni.welcomevoting.utils.Utils;
import me.myatminsoe.mdetect.MDetect;

public class TestFragment extends Fragment {

    private TextView tname,tsection;
    private String name,section,fb;
    private int view,profile;
    private ImageView viewImg , goFb , Vote,Profile;
    private Dialog dialog;

    private Context c;
    private Boolean isVoted_King = false;
    private Boolean isVoted_Queen = false;
    private SharedPreferences preferences;
    private String TAG,king_name,queen_name;

    public static TestFragment newInstance(String name,String section,String fb,int profile,int view, Context c,String TAG) {

        TestFragment fragment = new TestFragment();

        fragment.name = name;
        fragment.section = section;
        fragment.fb = fb;
        fragment.profile= profile;
        fragment.view = view;
        fragment.c = c;
        fragment.TAG = TAG;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup Rootview = (ViewGroup) inflater.inflate(R.layout.item,
                container, false);

        preferences = getActivity().getSharedPreferences("Voting", Context.MODE_PRIVATE);



        tname = Rootview.findViewById(R.id.name);
        tsection = Rootview.findViewById(R.id.section);
        viewImg = Rootview.findViewById(R.id.view);
        goFb = Rootview.findViewById(R.id.fb);
        Vote = Rootview.findViewById(R.id.vote);
        Profile = Rootview.findViewById(R.id.image);

        if (TAG.equals("king")){
            king_name = preferences.getString("king","");

            if (king_name.equals(name)){
                Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark_g));
            }else {
                Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark));
            }
            String voted_king = preferences.getString("isVoted_king","");
            if(voted_king.equals("yes")){
                isVoted_King = true;
            }else {
                isVoted_King = false;
            }

        }else {

            queen_name = preferences.getString("queen", "");

            if (queen_name.equals(name)){
                Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark_g));
            }else {
                Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark));
            }

            String voted_queen = preferences.getString("isVoted_queen","");
            if(voted_queen.equals("yes")){
                isVoted_Queen = true;
            }else {
                isVoted_Queen = false;
            }

        }



        tname.setText(name);
        tsection.setText(section);

        tname.setTypeface(Utils.getMMTypeface(getActivity()));
        tsection.setTypeface(Utils.getTypeface(getActivity()));

        Glide.with(getActivity()).load(profile).into(Profile);

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.img_dialog);
        dialog.setCancelable(false);

        ImageView img = dialog.findViewById(R.id.dialog_image);
        Glide.with(getActivity()).load(view).into(img);

        Button close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



        goFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = getOpenFacebookIntent(getActivity(),fb);
                startActivity(facebookIntent);
            }
        });

        viewImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),"Viewing "+ name + "'s Photo",Toast.LENGTH_SHORT).show();

              dialog.show();
            }
        });


        Vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

          if (TAG.equals("king")) {

              if (isVoted_King) {

                  TastyToast.makeText(getContext(), MDetect.INSTANCE.getText(name + " ကို ထပ်ရွေးချယ် လို့မရနိုင်ပါ။"), Toast.LENGTH_LONG, TastyToast.INFO).show();
              } else {

                  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                  builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                          .setMessage(MDetect.INSTANCE.getText(name) + MDetect.INSTANCE.getText(" ကိုရွေးချယ် မှာသေချာပြီလား?"))
                          .setPositiveButton(MDetect.INSTANCE.getText("သေချာပါပြီ"), new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {


                                      SharedPreferences.Editor editor = preferences.edit();
                                      editor.putString("king", name);
                                      editor.putString("isVoted_king", "yes");
                                      editor.commit();
                                      //isVoted_King= true;
                                      Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark_g));


                                  TastyToast.makeText(getContext(), MDetect.INSTANCE.getText(name + " ကို ရွေးချယ်ပြီးပါပြီ။"), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();
                                  Refresh("k");

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
          }else {
              if (isVoted_Queen) {

                  TastyToast.makeText(getContext(), MDetect.INSTANCE.getText(name + " ကို ထပ်ရွေးချယ် လို့မရနိုင်ပါ။"), Toast.LENGTH_LONG, TastyToast.INFO).show();
              } else if (name.equals("မနော်ခိုင်ကြည်စင်")){
                  TastyToast.makeText(getContext(), MDetect.INSTANCE.getText(name + " ကို Vote ပေးလို့မရပါ။"), Toast.LENGTH_LONG, TastyToast.INFO).show();
              }else {

                  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                  builder.setTitle(MDetect.INSTANCE.getText("သတိပေးချက်"))
                          .setMessage(MDetect.INSTANCE.getText(name) + MDetect.INSTANCE.getText(" ကိုရွေးချယ် မှာသေချာပြီလား?"))
                          .setPositiveButton(MDetect.INSTANCE.getText("သေချာပါပြီ"), new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {


                                  SharedPreferences.Editor editor = preferences.edit();
                                  editor.putString("queen", name);
                                  editor.putString("isVoted_queen", "yes");
                                  editor.commit();
                                  //  isVoted_Queen = true;
                                  Vote.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_mark_g));


                                  TastyToast.makeText(getContext(), MDetect.INSTANCE.getText(name + " ကို ရွေးချယ်ပြီးပါပြီ။"), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();
                                  Refresh("q");

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
          }

            }
        });


        return Rootview;
    }

    public void Refresh(String tag){
        MainActivity parentActivity = (MainActivity) getActivity();
        if (tag.equals("k")) {
            parentActivity.changeFragment(new QueenFragment());
            parentActivity.bottomBar.selectTabAtPosition(2);
        }else {
            parentActivity.changeFragment(new HomeFragment());
            parentActivity.bottomBar.selectTabAtPosition(0);
        }
    }


    public Intent getOpenFacebookIntent(Context context, String uri) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(uri)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(uri)); //catches and opens a url to the desired page
        }
    }

}