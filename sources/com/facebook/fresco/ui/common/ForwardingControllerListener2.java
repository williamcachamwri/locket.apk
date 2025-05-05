package com.facebook.fresco.ui.common;

import com.facebook.fresco.ui.common.ControllerListener2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u0000 #*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006J+\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0018\u0010\r\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\b0\u000eH\bJ\u0012\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J$\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J)\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00018\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u001f\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J$\u0010 \u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010!\u001a\u00020\bJ\u0014\u0010\"\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/facebook/fresco/ui/common/ForwardingControllerListener2;", "I", "Lcom/facebook/fresco/ui/common/BaseControllerListener2;", "()V", "listeners", "", "Lcom/facebook/fresco/ui/common/ControllerListener2;", "addListener", "", "listener", "forEachListener", "methodName", "", "block", "Lkotlin/Function1;", "onEmptyEvent", "callerContext", "", "onFailure", "id", "throwable", "", "extras", "Lcom/facebook/fresco/ui/common/ControllerListener2$Extras;", "onFinalImageSet", "imageInfo", "extraData", "(Ljava/lang/String;Ljava/lang/Object;Lcom/facebook/fresco/ui/common/ControllerListener2$Extras;)V", "onIntermediateImageFailed", "onIntermediateImageSet", "(Ljava/lang/String;Ljava/lang/Object;)V", "onRelease", "onSubmit", "removeAllListeners", "removeListener", "Companion", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ForwardingControllerListener2.kt */
public class ForwardingControllerListener2<I> extends BaseControllerListener2<I> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "FwdControllerListener2";
    private final List<ControllerListener2<I>> listeners = new ArrayList(2);

    public final synchronized void addListener(ControllerListener2<I> controllerListener2) {
        Intrinsics.checkNotNullParameter(controllerListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.add(controllerListener2);
    }

    public final synchronized void removeListener(ControllerListener2<I> controllerListener2) {
        Intrinsics.checkNotNullParameter(controllerListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.remove(controllerListener2);
    }

    public final synchronized void removeAllListeners() {
        this.listeners.clear();
    }

    private final void forEachListener(String str, Function1<? super ControllerListener2<I>, Unit> function1) {
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    function1.invoke(this.listeners.get(i));
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in " + str, e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/fresco/ui/common/ForwardingControllerListener2$Companion;", "", "()V", "TAG", "", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ForwardingControllerListener2.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onSubmit(String str, Object obj, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    this.listeners.get(i).onSubmit(str, obj, extras);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onSubmit", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onFinalImageSet(String str, I i, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i2 = 0;
        while (i2 < size) {
            try {
                try {
                    this.listeners.get(i2).onFinalImageSet(str, i, extras);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onFinalImageSet", e);
                }
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onFailure(String str, Throwable th, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    this.listeners.get(i).onFailure(str, th, extras);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onFailure", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onRelease(String str, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    this.listeners.get(i).onRelease(str, extras);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onRelease", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onIntermediateImageSet(String str, I i) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i2 = 0;
        while (i2 < size) {
            try {
                try {
                    this.listeners.get(i2).onIntermediateImageSet(str, i);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onIntermediateImageSet", e);
                }
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onIntermediateImageFailed(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    this.listeners.get(i).onIntermediateImageFailed(str);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onIntermediateImageFailed", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onEmptyEvent(Object obj) {
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    this.listeners.get(i).onEmptyEvent(obj);
                } catch (Exception e) {
                    SentryLogcatAdapter.e(TAG, "InternalListener exception in onEmptyEvent", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }
}
