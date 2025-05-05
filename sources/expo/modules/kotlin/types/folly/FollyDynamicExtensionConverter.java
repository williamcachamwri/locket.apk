package expo.modules.kotlin.types.folly;

import android.util.ArrayMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.bm.Languages;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lexpo/modules/kotlin/types/folly/FollyDynamicExtensionConverter;", "", "()V", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FollyDynamicExtensionConverter.kt */
public final class FollyDynamicExtensionConverter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ArrayMap<Integer, Object> instanceMap = new ArrayMap<>();
    /* access modifiers changed from: private */
    public static int nextId;

    @JvmStatic
    public static final synchronized Object get(String str) {
        Object obj;
        synchronized (FollyDynamicExtensionConverter.class) {
            obj = Companion.get(str);
        }
        return obj;
    }

    @JvmStatic
    public static final synchronized String put(Object obj) {
        String put;
        synchronized (FollyDynamicExtensionConverter.class) {
            put = Companion.put(obj);
        }
        return put;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0001H\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/kotlin/types/folly/FollyDynamicExtensionConverter$Companion;", "", "()V", "instanceMap", "Landroid/util/ArrayMap;", "", "nextId", "get", "payload", "", "put", "any", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FollyDynamicExtensionConverter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized String put(Object obj) {
            int access$getNextId$cp;
            Intrinsics.checkNotNullParameter(obj, Languages.ANY);
            access$getNextId$cp = FollyDynamicExtensionConverter.nextId;
            FollyDynamicExtensionConverter.nextId = access$getNextId$cp + 1;
            FollyDynamicExtensionConverter.instanceMap.put(Integer.valueOf(access$getNextId$cp), obj);
            return FollyDynamicExtensionConverterKt.DYNAMIC_EXTENSION_PREFIX + access$getNextId$cp;
        }

        @JvmStatic
        public final synchronized Object get(String str) {
            String substring;
            Intrinsics.checkNotNullParameter(str, "payload");
            if (StringsKt.startsWith$default(str, FollyDynamicExtensionConverterKt.DYNAMIC_EXTENSION_PREFIX, false, 2, (Object) null)) {
                substring = str.substring(27);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            } else {
                throw new InvalidDynamicExtensionFormatException();
            }
            return FollyDynamicExtensionConverter.instanceMap.remove(Integer.valueOf(Integer.parseInt(substring)));
        }
    }
}
