package abc.home.zarni.welcomevoting.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import abc.home.zarni.welcomevoting.fragment.TestFragment;
import abc.home.zarni.welcomevoting.model.Selection;

public class FragmentAdapter  extends FragmentStatePagerAdapter {

    private Context context;
    private List<Selection> selections;
    private String TAG;

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (object != null) {
            return ((Fragment) object).getView() == view;
        } else {
            return false;
        }
    }

    public FragmentAdapter(FragmentManager fm,
                               Context context, List<Selection> selection,String tag) {
        super(fm);
        this.context = context;
        selections = selection;
        TAG = tag;
    }

    @Override
    public int getItemPosition(Object object) {
        // Causes adapter to reload all Fragments when
        // notifyDataSetChanged is called
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(selections.get(position).getName(),selections.get(position).getSection(),selections.get(position).getFb(),selections.get(position).getProfile(),selections.get(position).getView(),
                context,TAG);
    }

    @Override
    public int getCount() {
        return selections == null ? 0 : selections.size();
    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return selections.get(position);
//    }

}