package expo.modules.kotlin;

import expo.modules.kotlin.exception.Exceptions;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0001H\b¨\u0006\u0002"}, d2 = {"toStrongReference", "Lexpo/modules/kotlin/AppContext;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt {
    public static final AppContext toStrongReference(AppContext appContext) {
        if (appContext != null) {
            return appContext;
        }
        throw new Exceptions.AppContextLost();
    }
}
