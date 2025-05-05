package expo.modules.kotlin;

import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import kotlin.jvm.functions.Function0;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppContext$$ExternalSyntheticLambda0 implements UIBlock {
    public final /* synthetic */ Function0 f$0;

    public /* synthetic */ AppContext$$ExternalSyntheticLambda0(Function0 function0) {
        this.f$0 = function0;
    }

    public final void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
        AppContext.dispatchOnMainUsingUIManager$lambda$15(this.f$0, nativeViewHierarchyManager);
    }
}
