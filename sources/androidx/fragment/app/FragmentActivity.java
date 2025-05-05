package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.app.SharedElementCallback;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.squareup.kotlinpoet.FileSpecKt;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends ComponentActivity implements ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    final LifecycleRegistry mFragmentLifecycleRegistry = new LifecycleRegistry(this);
    final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
    boolean mResumed;
    boolean mStopped = true;

    @Deprecated
    public void onAttachFragment(Fragment fragment) {
    }

    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i) {
    }

    public FragmentActivity() {
        init();
    }

    public FragmentActivity(int i) {
        super(i);
        init();
    }

    private void init() {
        getSavedStateRegistry().registerSavedStateProvider(LIFECYCLE_TAG, new FragmentActivity$$ExternalSyntheticLambda0(this));
        addOnConfigurationChangedListener(new FragmentActivity$$ExternalSyntheticLambda1(this));
        addOnNewIntentListener(new FragmentActivity$$ExternalSyntheticLambda2(this));
        addOnContextAvailableListener(new FragmentActivity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$init$0$androidx-fragment-app-FragmentActivity  reason: not valid java name */
    public /* synthetic */ Bundle m379lambda$init$0$androidxfragmentappFragmentActivity() {
        markFragmentsCreated();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        return new Bundle();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$init$1$androidx-fragment-app-FragmentActivity  reason: not valid java name */
    public /* synthetic */ void m380lambda$init$1$androidxfragmentappFragmentActivity(Configuration configuration) {
        this.mFragments.noteStateNotSaved();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$init$2$androidx-fragment-app-FragmentActivity  reason: not valid java name */
    public /* synthetic */ void m381lambda$init$2$androidxfragmentappFragmentActivity(Intent intent) {
        this.mFragments.noteStateNotSaved();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$init$3$androidx-fragment-app-FragmentActivity  reason: not valid java name */
    public /* synthetic */ void m382lambda$init$3$androidxfragmentappFragmentActivity(Context context) {
        this.mFragments.attachHost((Fragment) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.noteStateNotSaved();
        super.onActivityResult(i, i2, intent);
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        this.mFragments.dispatchCreate();
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView((View) null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    /* access modifiers changed from: package-private */
    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.onCreateView(view, str, context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.dispatchDestroy();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            return this.mFragments.dispatchContextItemSelected(menuItem);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.dispatchPause();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.mFragments.noteStateNotSaved();
        super.onResume();
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        this.mFragments.dispatchResume();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mFragments.noteStateNotSaved();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.execPendingActions();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        this.mFragments.dispatchStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.dispatchStop();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateMenu();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (shouldDumpInternalState(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + FileSpecKt.DEFAULT_INDENT;
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            if (getApplication() != null) {
                LoaderManager.getInstance(this).dump(str2, fileDescriptor, printWriter, strArr);
            }
            this.mFragments.getSupportFragmentManager().dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.noteStateNotSaved();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        startActivityFromFragment(fragment, intent, i, (Bundle) null);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (i == -1) {
            ActivityCompat.startActivityForResult(this, intent, -1, bundle);
        } else {
            fragment.startActivityForResult(intent, i, bundle);
        }
    }

    @Deprecated
    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i == -1) {
            ActivityCompat.startIntentSenderForResult(this, intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            fragment.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        }
    }

    class HostCallbacks extends FragmentHostCallback<FragmentActivity> implements OnConfigurationChangedProvider, OnTrimMemoryProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, SavedStateRegistryOwner, FragmentOnAttachListener, MenuHost {
        public HostCallbacks() {
            super(FragmentActivity.this);
        }

        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        public ViewModelStore getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        public void onDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        public boolean onShouldSaveFragmentState(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        public LayoutInflater onGetLayoutInflater() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        public FragmentActivity onGetHost() {
            return FragmentActivity.this;
        }

        public void onSupportInvalidateOptionsMenu() {
            invalidateMenu();
        }

        public boolean onShouldShowRequestPermissionRationale(String str) {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, str);
        }

        public boolean onHasWindowAnimations() {
            return FragmentActivity.this.getWindow() != null;
        }

        public int onGetWindowAnimations() {
            Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        public void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        public View onFindViewById(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        public boolean onHasView() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        public ActivityResultRegistry getActivityResultRegistry() {
            return FragmentActivity.this.getActivityResultRegistry();
        }

        public SavedStateRegistry getSavedStateRegistry() {
            return FragmentActivity.this.getSavedStateRegistry();
        }

        public void addOnConfigurationChangedListener(Consumer<Configuration> consumer) {
            FragmentActivity.this.addOnConfigurationChangedListener(consumer);
        }

        public void removeOnConfigurationChangedListener(Consumer<Configuration> consumer) {
            FragmentActivity.this.removeOnConfigurationChangedListener(consumer);
        }

        public void addOnTrimMemoryListener(Consumer<Integer> consumer) {
            FragmentActivity.this.addOnTrimMemoryListener(consumer);
        }

        public void removeOnTrimMemoryListener(Consumer<Integer> consumer) {
            FragmentActivity.this.removeOnTrimMemoryListener(consumer);
        }

        public void addOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> consumer) {
            FragmentActivity.this.addOnMultiWindowModeChangedListener(consumer);
        }

        public void removeOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> consumer) {
            FragmentActivity.this.removeOnMultiWindowModeChangedListener(consumer);
        }

        public void addOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> consumer) {
            FragmentActivity.this.addOnPictureInPictureModeChangedListener(consumer);
        }

        public void removeOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> consumer) {
            FragmentActivity.this.removeOnPictureInPictureModeChangedListener(consumer);
        }

        public void addMenuProvider(MenuProvider menuProvider) {
            FragmentActivity.this.addMenuProvider(menuProvider);
        }

        public void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner) {
            FragmentActivity.this.addMenuProvider(menuProvider, lifecycleOwner);
        }

        public void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.State state) {
            FragmentActivity.this.addMenuProvider(menuProvider, lifecycleOwner, state);
        }

        public void removeMenuProvider(MenuProvider menuProvider) {
            FragmentActivity.this.removeMenuProvider(menuProvider);
        }

        public void invalidateMenu() {
            FragmentActivity.this.invalidateMenu();
        }
    }

    /* access modifiers changed from: package-private */
    public void markFragmentsCreated() {
        do {
        } while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED));
    }

    private static boolean markState(FragmentManager fragmentManager, Lifecycle.State state) {
        boolean z = false;
        for (Fragment next : fragmentManager.getFragments()) {
            if (next != null) {
                if (next.getHost() != null) {
                    z |= markState(next.getChildFragmentManager(), state);
                }
                if (next.mViewLifecycleOwner != null && next.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    next.mViewLifecycleOwner.setCurrentState(state);
                    z = true;
                }
                if (next.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    next.mLifecycleRegistry.setCurrentState(state);
                    z = true;
                }
            }
        }
        return z;
    }
}
