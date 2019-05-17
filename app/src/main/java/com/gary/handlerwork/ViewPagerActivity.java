package com.gary.handlerwork;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.gary.handlerwork.Fragment.FiveFragment;
import com.gary.handlerwork.Fragment.FourFragment;
import com.gary.handlerwork.Fragment.OneFragment;
import com.gary.handlerwork.Fragment.SevenFragment;
import com.gary.handlerwork.Fragment.SixFragment;
import com.gary.handlerwork.Fragment.ThreeFragment;
import com.gary.handlerwork.Fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
//TabLayout+ViewPager+Fragment实现分页滑动效果
    private ViewPager viewPager;
    private List<String> tableTitleList;   //存放TabLayout的标题集合
    private List<Fragment> fragmentList;   //存放Fragment的集合
    private TabLayout tabLayout;

    //要处理好  ViewPager    Fragment    TabLayout    pagerAdapter（采用FragmentPagerAdapter 或FragmentStatePagerAdapter）四个对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);




        fragmentList = new ArrayList<>();
        //添加实例
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());
        fragmentList.add(new FourFragment());
        fragmentList.add(new FiveFragment());
        fragmentList.add(new SixFragment());
        fragmentList.add(new SevenFragment());

        //标题集合放在下方是因为根据Fragment数量来添加标题，如果在上方会出现集合空指针异常，无法进行循环，程序报错
        tableTitleList =new ArrayList<>();
        for(int i=0;i<fragmentList.size();i++){
            int number=i+1;
            tableTitleList.add(String.format("第%d页%n",number));
        }

        //将集合中的标题内容添加给首页导航栏
        for(int i=0;i<tableTitleList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tableTitleList.get(i)));
        }

        //将TabLayout与Viewpager联动起来
        tabLayout.setupWithViewPager(viewPager);

        //viewPager设置适配器
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),tableTitleList,fragmentList));

    }
}
