package expo.modules.application;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ApplicationModule.kt */
final class ApplicationModule$definition$1$1 extends Lambda implements Function0<Map<String, ? extends Object>> {
    final /* synthetic */ ApplicationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ApplicationModule$definition$1$1(ApplicationModule applicationModule) {
        super(0);
        this.this$0 = applicationModule;
    }

    public final Map<String, Object> invoke() {
        return MapsKt.mapOf(TuplesKt.to("applicationName", this.this$0.getApplicationName()), TuplesKt.to("applicationId", this.this$0.getPackageName()), TuplesKt.to("nativeApplicationVersion", this.this$0.getVersionName()), TuplesKt.to("nativeBuildVersion", String.valueOf(this.this$0.getVersionCode())));
    }
}
