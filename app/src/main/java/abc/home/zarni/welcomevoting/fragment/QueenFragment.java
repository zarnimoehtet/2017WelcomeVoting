package abc.home.zarni.welcomevoting.fragment;


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
public class QueenFragment extends Fragment {

    private ViewPagerDesigner mPager;
    private TextView title;
    private View v;


    public QueenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v =   inflater.inflate(R.layout.fragment_queen, container, false);

        title = v.findViewById(R.id.title);
        title.setTypeface(Utils.getTypeface(getActivity()));
        initKKViewPager();

        return v;
    }

    private void initKKViewPager() {
        mPager = v.findViewById(R.id.kk_pager);

        int[] mipmap = new int[]{ R.mipmap.mtz_p,R.mipmap.ppps_p,R.mipmap.zpp_p,R.mipmap.hyw_p,R.mipmap.tshc_p,R.mipmap.wtzk_p,R.mipmap.nkz_p,R.mipmap.nca_p,R.mipmap.sdh_p};

        int[] viewImg = new int[]{ R.mipmap.mtz,R.mipmap.ppps,R.mipmap.zpp,R.mipmap.hyw,R.mipmap.tshc,R.mipmap.wtzk,R.mipmap.nkz,R.mipmap.nca,R.mipmap.sdh};
        List<Selection> ss = new ArrayList<>();
        Selection  selection = new Selection("မမိုးသူဇာ","Section A","https://www.facebook.com/profile.php?id=100025767187995",mipmap[0],viewImg[0]);
        ss.add(selection);

        selection = new Selection("မဖူးပြည့်ပြည့်စံ","Section A","https://www.facebook.com/phue.san.50",mipmap[1],viewImg[1]);
        ss.add(selection);


        selection = new Selection("မဇင်ပွင့်ဖြူ","Section A","https://www.facebook.com/zin.t.phyu.9",mipmap[2],viewImg[2]);
        ss.add(selection);


        selection = new Selection("မနှင်းယုဝေ","Section A","https://www.facebook.com/mee.xnin",mipmap[3],viewImg[3]);
        ss.add(selection);

        selection = new Selection("မသဲစုထွေးချစ်","Section B","https://www.facebook.com/lmsweswe.swe.9",mipmap[4],viewImg[4]);
        ss.add(selection);

        selection = new Selection("မဝင်းသဉ္ဇာခိုင်","Section A","https://www.facebook.com/winthinzar.khaing.1",mipmap[5],viewImg[5]);
        ss.add(selection);

        selection = new Selection("မနော်ခိုင်ကြည်စင်","Section A","https://www.facebook.com/naw.kawdohpaw",mipmap[6],viewImg[6]);
        ss.add(selection);

        selection = new Selection("မနေခြည်အောင်","Section B","https://www.facebook.com/profile.php?id=100010801115525",mipmap[7],viewImg[7]);
        ss.add(selection);

        selection = new Selection("မစန္ဒီလှ","Section B","https://www.facebook.com/go.hanna.50",mipmap[8],viewImg[8]);
        ss.add(selection);


        mPager.setAdapter(new FragmentAdapter(getChildFragmentManager(),
                getActivity(), ss,"queen") {
        });
        mPager.setAnimationEnabled(true);
        mPager.setFadeEnabled(true);
        mPager.setFadeFactor(0.6f);
    }

}
