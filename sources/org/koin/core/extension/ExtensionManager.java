package org.koin.core.extension;

import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u0002H\u0013\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\n2\u0006\u0010\u0014\u001a\u00020\tH\b¢\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u0004\u0018\u0001H\u0013\"\n\b\u0000\u0010\u0013\u0018\u0001*\u00020\n2\u0006\u0010\u0014\u001a\u00020\tH\b¢\u0006\u0002\u0010\u0015J%\u0010\u0017\u001a\u00020\u0011\"\b\b\u0000\u0010\u0013*\u00020\n2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0018\u001a\u0002H\u0013¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R8\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000b8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/koin/core/extension/ExtensionManager;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "get_koin$koin_core", "()Lorg/koin/core/Koin;", "extensions", "Ljava/util/HashMap;", "", "Lorg/koin/core/extension/KoinExtension;", "Lkotlin/collections/HashMap;", "getExtensions$annotations", "()V", "getExtensions", "()Ljava/util/HashMap;", "close", "", "getExtension", "T", "id", "(Ljava/lang/String;)Lorg/koin/core/extension/KoinExtension;", "getExtensionOrNull", "registerExtension", "extension", "(Ljava/lang/String;Lorg/koin/core/extension/KoinExtension;)V", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionManager.kt */
public final class ExtensionManager {
    private final Koin _koin;
    private final HashMap<String, KoinExtension> extensions = new HashMap<>();

    public static /* synthetic */ void getExtensions$annotations() {
    }

    public ExtensionManager(Koin koin) {
        Intrinsics.checkNotNullParameter(koin, "_koin");
        this._koin = koin;
    }

    public final Koin get_koin$koin_core() {
        return this._koin;
    }

    public final HashMap<String, KoinExtension> getExtensions() {
        return this.extensions;
    }

    public final /* synthetic */ <T extends KoinExtension> T getExtension(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        T t = getExtensions().get(str);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        T t2 = (KoinExtension) t;
        if (t2 != null) {
            KoinExtension koinExtension = (KoinExtension) t2;
            return t2;
        }
        throw new IllegalStateException(("Koin extension '" + str + "' not found.").toString());
    }

    public final /* synthetic */ <T extends KoinExtension> T getExtensionOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        T t = getExtensions().get(str);
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (KoinExtension) t;
    }

    public final <T extends KoinExtension> void registerExtension(String str, T t) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(t, "extension");
        this.extensions.put(str, t);
        t.setKoin(this._koin);
    }

    public final void close() {
        Collection<KoinExtension> values = this.extensions.values();
        Intrinsics.checkNotNullExpressionValue(values, "extensions.values");
        for (KoinExtension onClose : values) {
            onClose.onClose();
        }
    }
}
