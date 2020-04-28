package abc.home.zarni.welcomevoting.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abc.home.zarni.welcomevoting.R;
import abc.home.zarni.welcomevoting.adapter.FragmentAdapter;
import abc.home.zarni.welcomevoting.model.Selection;
import abc.home.zarni.welcomevoting.ui.ViewPagerDesigner;
import abc.home.zarni.welcomevoting.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class KingFragment extends Fragment {
    private ViewPagerDesigner mPager;
    private TextView title;
    private View v;
    private SharedPreferences preferences;
    public KingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v =  inflater.inflate(R.layout.fragment_king, container, false);



         title = v.findViewById(R.id.title);
         title.setTypeface(Utils.getTypeface(getActivity()));
        initKKViewPager();



        return v;
    }

    private void initKKViewPager() {
        mPager = v.findViewById(R.id.kk_pager);

        int[] mipmap = new int[]{ R.mipmap.tmh_p,R.mipmap.mcma_p,R.mipmap.okk_p,R.mipmap.kw_p,R.mipmap.yyn_p,R.mipmap.kkh_p,R.mipmap.amt_p,R.mipmap.tho_p};

        int[] viewImg = new int[]{ R.mipmap.tmh,R.mipmap.mcma,R.mipmap.okk,R.mipmap.kw,R.mipmap.yyn,R.mipmap.kkh,R.mipmap.amt,R.mipmap.tho};
        List<Selection> ss = new ArrayList<>();
        Selection  selection = new Selection("စောရဲရင့်နောင်","Section B","https://www.facebook.com/profile.php?id=100029862067481",mipmap[4],viewImg[4]);
        ss.add(selection);

        selection = new Selection("မောင်ခန့်ဝင်း","Section B","https://www.facebook.com/profile.php?id=100016647462091",mipmap[3],viewImg[3]);
        ss.add(selection);


        selection = new Selection("မင်းချမ်းမွန်အောင်","Section A","https://www.facebook.com/min.cma",mipmap[1],viewImg[1]);
        ss.add(selection);


        selection = new Selection("မောင်ကျော်ကျော်ထက်","Section A","https://www.facebook.com/profile.php?id=100017850104904",mipmap[5],viewImg[5]);
        ss.add(selection);

        selection = new Selection("မောင်ဥက္ကာကျော်","Section B","https://www.facebook.com/oakar.kyaw.16718",mipmap[2],viewImg[2]);
        ss.add(selection);

        selection = new Selection("မောင်သီဟဦး","Section B","https://www.facebook.com/thiha.oo.16547",mipmap[7],viewImg[7]);
        ss.add(selection);

        selection = new Selection("မောင်သူမြတ်ထူး","Section A","https://www.facebook.com/thu.myathtoo.98",mipmap[0],viewImg[0]);
        ss.add(selection);

        selection = new Selection("မင်းအောင်မျိုးသူ","Section A","https://www.facebook.com/min.a.thu.752",mipmap[6],viewImg[6]);
        ss.add(selection);




        mPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),
                getActivity(), ss,"king") {
        });
        mPager.setAnimationEnabled(true);
        mPager.setFadeEnabled(true);
        mPager.setFadeFactor(0.6f);
    }

}
