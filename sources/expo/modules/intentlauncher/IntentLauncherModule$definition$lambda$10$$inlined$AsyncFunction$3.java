package expo.modules.intentlauncher;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.intentlauncher.exceptions.ActivityAlreadyStartedException;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$3 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ IntentLauncherModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$3(IntentLauncherModule intentLauncherModule) {
        super(2);
        this.this$0 = intentLauncherModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        CodedException codedException;
        ComponentName componentName;
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            IntentLauncherParams intentLauncherParams = objArr[1];
            if (intentLauncherParams != null) {
                IntentLauncherParams intentLauncherParams2 = intentLauncherParams;
                if (this.this$0.pendingPromise == null) {
                    Intent intent = new Intent(str2);
                    if (intentLauncherParams2.getClassName() != null) {
                        if (intentLauncherParams2.getPackageName() != null) {
                            componentName = new ComponentName(intentLauncherParams2.getPackageName(), intentLauncherParams2.getClassName());
                        } else {
                            componentName = new ComponentName(this.this$0.getContext(), intentLauncherParams2.getClassName());
                        }
                        intent.setComponent(componentName);
                    }
                    if (intentLauncherParams2.getData() != null && intentLauncherParams2.getType() != null) {
                        intent.setDataAndType(Uri.parse(intentLauncherParams2.getData()), intentLauncherParams2.getType());
                    } else if (intentLauncherParams2.getData() != null) {
                        intent.setData(Uri.parse(intentLauncherParams2.getData()));
                    } else if (intentLauncherParams2.getType() != null) {
                        intent.setType(intentLauncherParams2.getType());
                    }
                    Map<String, Object> extra = intentLauncherParams2.getExtra();
                    if (extra != null) {
                        intent.putExtras(IntentLauncherModuleKt.toBundle(extra));
                    }
                    Integer flags = intentLauncherParams2.getFlags();
                    if (flags != null) {
                        intent.addFlags(flags.intValue());
                    }
                    String category = intentLauncherParams2.getCategory();
                    if (category != null) {
                        intent.addCategory(category);
                    }
                    try {
                        this.this$0.getCurrentActivity().startActivityForResult(intent, 12);
                        this.this$0.pendingPromise = promise;
                    } catch (Throwable th) {
                        if (th instanceof CodedException) {
                            codedException = (CodedException) th;
                        } else if (th instanceof expo.modules.core.errors.CodedException) {
                            String code = th.getCode();
                            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                            codedException = new CodedException(code, th.getMessage(), th.getCause());
                        } else {
                            codedException = new UnexpectedException((Throwable) th);
                        }
                        promise.reject(codedException);
                    }
                } else {
                    throw new ActivityAlreadyStartedException();
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.intentlauncher.IntentLauncherParams");
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
