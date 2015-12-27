package com.techdb.app.navigationdrawer.activities.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.techdb.app.navigationdrawer.R;
import com.techdb.app.navigationdrawer.fragments.BaseFragment;
import com.techdb.app.navigationdrawer.fragments.MenuListFragment;
import com.techdb.app.navigationdrawer.fragments.OrdersFragment;
import com.techdb.app.navigationdrawer.widgets.OpenTextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 12/18/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private final SparseArray<WeakReference<BaseFragment>> mFragmentArray = new SparseArray<>();
    private final List<Holder> mHolders = new ArrayList<>();
    private final Context mContext;

    private int mCurrentPage;

    public void setCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }


    public MainPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
    }

    public void add(final Class<? extends BaseFragment> className, final Bundle params, final int titleResId) {
        final Holder holder = new Holder();
        holder.mClassName = className.getName();
        holder.mParams = params;
        holder.mTitleResId = titleResId;

        final int position = mHolders.size();
        mHolders.add(position, holder);
        notifyDataSetChanged();
    }

    @Override
    public BaseFragment getItem(int position) {
        final Holder currentHolder = mHolders.get(position);
        return (BaseFragment) BaseFragment.instantiate(mContext, currentHolder.mClassName, currentHolder.mParams);
    }

    public BaseFragment getFragment(final int position) {
        final WeakReference<BaseFragment> fragmentRef = mFragmentArray.get(position);
        if (fragmentRef != null && fragmentRef.get() != null) {
            return fragmentRef.get();
        }
        return getItem(position);
    }

    public View getTabView(final int position) {
        final View tabView = LayoutInflater.from(mContext).inflate(R.layout.layout_tabhost_item, null);
        final OpenTextView tabTextView = (OpenTextView) tabView.findViewById(R.id.tab_text_view);

        String title = mContext.getResources().getString(mHolders.get(position).mTitleResId);
        tabTextView.setText(title);
        if (position == getCurrentPage()) {
            tabTextView.setSelected(true);
        } else {
            tabTextView.setSelected(false);
        }
        return tabView;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        final BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        final WeakReference<BaseFragment> fragmentRef = mFragmentArray.get(position);
        if (fragmentRef != null) {
            fragmentRef.clear();
        }
        mFragmentArray.put(position, new WeakReference<BaseFragment>(fragment));
        return fragment;
    }

    @Override
    public int getCount() {
        return mHolders.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
        final WeakReference<BaseFragment> fragmentRef = mFragmentArray.get(position);
        if (fragmentRef != null) {
            fragmentRef.clear();
        }
    }

    public CharSequence getPageTitle(int position) {
        return null;
    }

    public enum MainFragment {
        ORDER_LIST(OrdersFragment.class),
        MENU_LIST(MenuListFragment.class);

        private Class<? extends BaseFragment> mFragmentClass;

        MainFragment(final Class<? extends BaseFragment> fragmentClass) {
            mFragmentClass = fragmentClass;
        }

        public Class<? extends BaseFragment> getFragmentClass() {
            return mFragmentClass;
        }
    }

    /**
     * The Holder container for initializing the <code>BaseFragment</code>'s instance for each of fragment
     * into the MainFragment's item.
     */
    public static final class Holder {
        public String mClassName;
        public Bundle mParams;
        public int mTitleResId;
    }
}
