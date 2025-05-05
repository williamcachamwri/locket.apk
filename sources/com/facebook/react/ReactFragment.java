package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

public class ReactFragment extends Fragment implements PermissionAwareActivity {
    protected static final String ARG_COMPONENT_NAME = "arg_component_name";
    protected static final String ARG_FABRIC_ENABLED = "arg_fabric_enabled";
    protected static final String ARG_LAUNCH_OPTIONS = "arg_launch_options";
    private PermissionListener mPermissionListener;
    private ReactDelegate mReactDelegate;

    /* access modifiers changed from: private */
    public static ReactFragment newInstance(String str, Bundle bundle, Boolean bool) {
        ReactFragment reactFragment = new ReactFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(ARG_COMPONENT_NAME, str);
        bundle2.putBundle(ARG_LAUNCH_OPTIONS, bundle);
        bundle2.putBoolean(ARG_FABRIC_ENABLED, bool.booleanValue());
        reactFragment.setArguments(bundle2);
        return reactFragment;
    }

    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        String str;
        Boolean bool;
        super.onCreate(bundle);
        if (getArguments() != null) {
            String string = getArguments().getString(ARG_COMPONENT_NAME);
            Bundle bundle3 = getArguments().getBundle(ARG_LAUNCH_OPTIONS);
            bool = Boolean.valueOf(getArguments().getBoolean(ARG_FABRIC_ENABLED));
            str = string;
            bundle2 = bundle3;
        } else {
            bool = null;
            str = null;
            bundle2 = null;
        }
        if (str != null) {
            this.mReactDelegate = new ReactDelegate(getActivity(), getReactNativeHost(), str, bundle2, bool.booleanValue());
            return;
        }
        throw new IllegalStateException("Cannot loadApp if component name is null");
    }

    /* access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getActivity().getApplication()).getReactNativeHost();
    }

    /* access modifiers changed from: protected */
    public ReactDelegate getReactDelegate() {
        return this.mReactDelegate;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mReactDelegate.loadApp();
        return this.mReactDelegate.getReactRootView();
    }

    public void onResume() {
        super.onResume();
        this.mReactDelegate.onHostResume();
    }

    public void onPause() {
        super.onPause();
        this.mReactDelegate.onHostPause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mReactDelegate.onHostDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mReactDelegate.onActivityResult(i, i2, intent, false);
    }

    public boolean onBackPressed() {
        return this.mReactDelegate.onBackPressed();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mReactDelegate.shouldShowDevMenuOrReload(i, keyEvent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener != null && permissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
            this.mPermissionListener = null;
        }
    }

    public int checkPermission(String str, int i, int i2) {
        return getActivity().checkPermission(str, i, i2);
    }

    public int checkSelfPermission(String str) {
        return getActivity().checkSelfPermission(str);
    }

    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        requestPermissions(strArr, i);
    }

    public static class Builder {
        String mComponentName = null;
        Boolean mFabricEnabled = null;
        Bundle mLaunchOptions = null;

        public Builder setComponentName(String str) {
            this.mComponentName = str;
            return this;
        }

        public Builder setLaunchOptions(Bundle bundle) {
            this.mLaunchOptions = bundle;
            return this;
        }

        public ReactFragment build() {
            return ReactFragment.newInstance(this.mComponentName, this.mLaunchOptions, this.mFabricEnabled);
        }

        public Builder setFabricEnabled(boolean z) {
            this.mFabricEnabled = Boolean.valueOf(z);
            return this;
        }
    }
}
