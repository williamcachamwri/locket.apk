package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzij {
    static final CharSequence zza(@CheckForNull Object obj) {
        obj.getClass();
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public static final Appendable zzb(Appendable appendable, Iterator it, String str) throws IOException {
        if (it.hasNext()) {
            appendable.append(zza(it.next()));
            while (it.hasNext()) {
                appendable.append(",");
                appendable.append(zza(it.next()));
            }
        }
        return appendable;
    }
}
