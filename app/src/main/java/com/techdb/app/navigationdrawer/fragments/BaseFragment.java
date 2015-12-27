package com.techdb.app.navigationdrawer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by framgia on 12/18/15.
 */
public abstract class BaseFragment extends Fragment {
    protected Bundle mBundle = new Bundle();
    protected HashMap<String, Object> mTags;

    protected String mFragmentKey;
    protected ViewGroup mRootView;

    /**
     * Sets the Extras Bundle to an instance of {@link BaseFragment} class, Which will be assigned fy
     * something variables that instance used to handle something into this screen
     *
     * @param extras The Bundle Object.
     */
    public void setBundle(Bundle extras){
        this.mBundle = extras;
    }

    /**
     * Gets the Extras Bundle object which was integrated into the current fragment object.
     *
     * @return the Bundle Object.
     */
    public Bundle getBundle() {
        return mBundle;
    }

    /**
     * Set the FragmentKey which has interacted with key that <code>BaseActivity</code> has used to call the
     * BaseFragment instance.
     * @param fragmentKey The FragmentKey String type.
     */
    public void setFragmentKey(String fragmentKey){
        this.mFragmentKey = fragmentKey;
    }

    /**
     * Gets the FragmentKey
     * @return The Fragment's Key value.
     */
    public String getFragmentKey(){
        return mFragmentKey;
    }

    /**
     * Method used to display the keyboard onto the current screen.
     */
    protected void onShowKeyboard(){}

    /**
     * Method used to hide the keyboard onto the current screen.
     */
    protected void onHideKeyboard(){}


    public abstract void startProgress();
    public abstract void endProgress();


}
