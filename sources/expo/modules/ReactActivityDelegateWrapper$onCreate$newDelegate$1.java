package expo.modules;

import com.facebook.react.ReactActivityDelegate;
import expo.modules.core.interfaces.ReactActivityHandler;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/facebook/react/ReactActivityDelegate;", "it", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactActivityDelegateWrapper.kt */
final class ReactActivityDelegateWrapper$onCreate$newDelegate$1 extends Lambda implements Function1<ReactActivityHandler, ReactActivityDelegate> {
    final /* synthetic */ ReactActivityDelegateWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReactActivityDelegateWrapper$onCreate$newDelegate$1(ReactActivityDelegateWrapper reactActivityDelegateWrapper) {
        super(1);
        this.this$0 = reactActivityDelegateWrapper;
    }

    public final ReactActivityDelegate invoke(ReactActivityHandler reactActivityHandler) {
        return reactActivityHandler.onDidCreateReactActivityDelegate(this.this$0.activity, this.this$0);
    }
}
