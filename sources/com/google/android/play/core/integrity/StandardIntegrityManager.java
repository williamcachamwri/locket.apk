package com.google.android.play.core.integrity;

import android.app.Activity;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public interface StandardIntegrityManager {

    /* compiled from: com.google.android.play:integrity@@1.3.0 */
    public static abstract class PrepareIntegrityTokenRequest {

        /* compiled from: com.google.android.play:integrity@@1.3.0 */
        public static abstract class Builder {
            public abstract PrepareIntegrityTokenRequest build();

            public abstract Builder setCloudProjectNumber(long j);
        }

        public static Builder builder() {
            c cVar = new c();
            cVar.a(0);
            return cVar;
        }

        /* access modifiers changed from: package-private */
        public abstract int a();

        public abstract long b();
    }

    /* compiled from: com.google.android.play:integrity@@1.3.0 */
    public static abstract class StandardIntegrityToken {
        public abstract Task<Integer> showDialog(Activity activity, int i);

        public abstract String token();
    }

    /* compiled from: com.google.android.play:integrity@@1.3.0 */
    public interface StandardIntegrityTokenProvider {
        Task<StandardIntegrityToken> request(StandardIntegrityTokenRequest standardIntegrityTokenRequest);
    }

    /* compiled from: com.google.android.play:integrity@@1.3.0 */
    public static abstract class StandardIntegrityTokenRequest {

        /* compiled from: com.google.android.play:integrity@@1.3.0 */
        public static abstract class Builder {
            public abstract StandardIntegrityTokenRequest build();

            public abstract Builder setRequestHash(String str);
        }

        public static Builder builder() {
            return new f();
        }

        public abstract String a();
    }

    Task<StandardIntegrityTokenProvider> prepareIntegrityToken(PrepareIntegrityTokenRequest prepareIntegrityTokenRequest);
}
