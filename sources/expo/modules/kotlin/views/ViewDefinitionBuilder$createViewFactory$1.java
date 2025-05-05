package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import expo.modules.kotlin.AppContext;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Landroid/view/View;", "T", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewDefinitionBuilder.kt */
final class ViewDefinitionBuilder$createViewFactory$1 extends Lambda implements Function2<Context, AppContext, View> {
    final /* synthetic */ ViewDefinitionBuilder<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewDefinitionBuilder$createViewFactory$1(ViewDefinitionBuilder<T> viewDefinitionBuilder) {
        super(2);
        this.this$0 = viewDefinitionBuilder;
    }

    public final View invoke(Context context, AppContext appContext) {
        Constructor<T> constructor;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Constructor<T> constructor2 = null;
        try {
            constructor = JvmClassMappingKt.getJavaClass(this.this$0.getViewClass()).getConstructor(new Class[]{Context.class, AppContext.class});
        } catch (NoSuchMethodException unused) {
            constructor = null;
        }
        if (constructor != null) {
            ViewDefinitionBuilder<T> viewDefinitionBuilder = this.this$0;
            try {
                T newInstance = constructor.newInstance(new Object[]{context, appContext});
                Intrinsics.checkNotNull(newInstance);
                return (View) newInstance;
            } catch (Throwable th) {
                return viewDefinitionBuilder.handleFailureDuringViewCreation(context, appContext, th);
            }
        } else {
            try {
                constructor2 = JvmClassMappingKt.getJavaClass(this.this$0.getViewClass()).getConstructor(new Class[]{Context.class});
            } catch (NoSuchMethodException unused2) {
            }
            if (constructor2 != null) {
                ViewDefinitionBuilder<T> viewDefinitionBuilder2 = this.this$0;
                try {
                    T newInstance2 = constructor2.newInstance(new Object[]{context});
                    Intrinsics.checkNotNull(newInstance2);
                    return (View) newInstance2;
                } catch (Throwable th2) {
                    return viewDefinitionBuilder2.handleFailureDuringViewCreation(context, appContext, th2);
                }
            } else {
                throw new IllegalStateException("Didn't find a correct constructor for " + this.this$0.getViewClass());
            }
        }
    }
}
