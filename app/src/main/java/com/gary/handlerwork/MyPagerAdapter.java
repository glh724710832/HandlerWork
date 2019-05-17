package com.gary.handlerwork;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gary.handlerwork.Fragment.OneFragment;

import java.util.List;


public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<String> tableTitleList;
    private List<Fragment> fragmentList;

    public MyPagerAdapter(FragmentManager fm,List<String> tableTitleList, List<Fragment> fragmentList) {
        super(fm);
        this.tableTitleList = tableTitleList;
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int i) {

        return  fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitleList.get(position);
    }
}
