package com.techdb.app.navigationdrawer.activities;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.techdb.app.navigationdrawer.R;

public class BaseActivity extends AppCompatActivity {

    private ObjectAnimator mStatusBarColorAnimator;

    private NavigationView mNavigationView;
    private Toolbar mActionBarToolbar;
    protected DrawerLayout mDrawerLayout;

    private int mThemedStatusBarColor = 0;
    private int mNormalStatusBarColor = 0;
    private static final TypeEvaluator ARGB_EVALUATOR = new ArgbEvaluator();
    private boolean mActionBarShown = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDefaultDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowTitleEnabled(true);
        }

        mThemedStatusBarColor = getResources().getColor(R.color.color_primary_dark);
        mNormalStatusBarColor = mThemedStatusBarColor;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
            if (mActionBarToolbar != null) {
                mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
                mActionBarToolbar.setNavigationContentDescription(getResources().getString(R.string.string_navdrawer_description));
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    public void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDrawerLayout.setStatusBarBackgroundColor(mThemedStatusBarColor);
        } else {
            mDrawerLayout.setStatusBarBackgroundColor(mNormalStatusBarColor);
        }

        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        if (mNavigationView == null) {
            return;
        }

        if (mNavigationView.isShown()) {
            final Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.color_transparent));
            }
        }

        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            Toast.makeText(BaseActivity.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            final int menuItemId = menuItem.getItemId();
            switch (menuItemId) {
                case R.id.nav_drawer_exit: {
                    BaseActivity.this.finish();
                    return true;
                }
                case R.id.nav_drawer_logout: {
                    return true;
                }
                case R.id.nav_drawer_printer: {
                    return true;
                }
                case R.id.nav_drawer_register: {
                    return true;
                }
                case R.id.nav_drawer_setting: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        });

        if (mActionBarToolbar != null){
            mActionBarToolbar.setNavigationIcon(R.drawable.ic_menu);
            mActionBarToolbar.setNavigationOnClickListener( view -> {
                mDrawerLayout.openDrawer(GravityCompat.START);
            });
        }
    }

    public int getThemedStatusBarColor(){
        return mThemedStatusBarColor;
    }

    public void setNormalStatusBarColor(int color){
        mNormalStatusBarColor = color;
        if (mDrawerLayout != null){
            mDrawerLayout.setStatusBarBackgroundColor(mNormalStatusBarColor);
        }
    }

    protected void onActionBarAutoShoworHide(boolean shown){

    }

    protected void autoShowOrHideActionBar(boolean show){
        if (show == mActionBarShown){
            return;
        }
        mActionBarShown = show;
        onActionBarAutoShoworHide(show);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(BaseActivity.this, "Query Text Submit:" + query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(BaseActivity.this, "Query Text Submit:" + newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
