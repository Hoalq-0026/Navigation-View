package com.techdb.app.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.techdb.app.navigationdrawer.activities.BaseActivity;
import com.techdb.app.navigationdrawer.activities.adapters.MainPagerAdapter;
import com.techdb.app.navigationdrawer.widgets.AppViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  implements TabLayout.OnTabSelectedListener{
    @Bind(R.id.main_tabs)
    protected TabLayout mMainTabs;
    @Bind(R.id.main_pager)
    protected AppViewPager mMainPager;
    protected MainPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (mPagerAdapter == null){
            final int[] titleResIds = new int[]{R.string.string_orders_tab_content, R.string.string_menu_tab_content};
            mPagerAdapter = new MainPagerAdapter(MainActivity.this, getSupportFragmentManager());
            final MainPagerAdapter.MainFragment[] mFragments = MainPagerAdapter.MainFragment.values();
            for (int index = 0; index < mFragments.length; index ++){
                mPagerAdapter.add(mFragments[index].getFragmentClass(), null, titleResIds[index]);
            }
        }

        // Initialize the ViewPager
        mMainPager.setAdapter(mPagerAdapter);
        mMainPager.setOffscreenPageLimit(mPagerAdapter.getCount() - 1);
        mMainPager.setIsPagingEnabled(true);

        mMainTabs.setTabMode(TabLayout.MODE_FIXED);
        mMainTabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        mMainTabs.setupWithViewPager(mMainPager);
        for (int index = 0; index < mMainTabs.getTabCount(); index ++){
            final TabLayout.Tab tab = mMainTabs.getTabAt(index);
            final View tabView = mPagerAdapter.getTabView(index);

            if (tab != null){
                tab.setCustomView(tabView);
            }
        }


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

