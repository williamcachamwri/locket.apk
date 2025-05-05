package com.google.android.recaptcha;

import android.app.Application;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.internal.zzcs;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH@¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u0015"}, d2 = {"Lcom/google/android/recaptcha/Recaptcha;", "", "<init>", "()V", "getClient", "Lkotlin/Result;", "Lcom/google/android/recaptcha/RecaptchaClient;", "application", "Landroid/app/Application;", "siteKey", "", "timeout", "", "getClient-BWLJW6A", "(Landroid/app/Application;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksClient", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/recaptcha/RecaptchaTasksClient;", "fetchClient", "(Landroid/app/Application;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTaskClient", "java.com.google.android.libraries.abuse.recaptcha.enterprise_enterprise"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class Recaptcha {
    public static final Recaptcha INSTANCE = new Recaptcha();

    private Recaptcha() {
    }

    @JvmStatic
    public static final Task<RecaptchaTasksClient> fetchTaskClient(Application application, String str) {
        return zzcs.zze(application, str);
    }

    /* renamed from: getClient-BWLJW6A$default  reason: not valid java name */
    public static /* synthetic */ Object m2172getClientBWLJW6A$default(Recaptcha recaptcha, Application application, String str, long j, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 10000;
        }
        return recaptcha.m2173getClientBWLJW6A(application, str, j, continuation);
    }

    @JvmStatic
    @Deprecated(message = "Use fetchTaskClient(Application, siteKey) instead.", replaceWith = @ReplaceWith(expression = "fetchTaskClient", imports = {}))
    public static final Task<RecaptchaTasksClient> getTasksClient(Application application, String str) {
        return zzcs.zzc(application, str, 10000);
    }

    public final Object fetchClient(Application application, String str, Continuation<? super RecaptchaClient> continuation) {
        return zzcs.zzd(application, str, continuation);
    }

    @JvmStatic
    @Deprecated(message = "Use fetchTaskClient(Application, siteKey) instead", replaceWith = @ReplaceWith(expression = "fetchTaskClient", imports = {}))
    public static final Task<RecaptchaTasksClient> getTasksClient(Application application, String str, long j) {
        return zzcs.zzc(application, str, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @kotlin.Deprecated(message = "Use fetchClient(Application, siteKey) instead.", replaceWith = @kotlin.ReplaceWith(expression = "fetchClient", imports = {}))
    /* renamed from: getClient-BWLJW6A  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m2173getClientBWLJW6A(android.app.Application r5, java.lang.String r6, long r7, kotlin.coroutines.Continuation<? super kotlin.Result<? extends com.google.android.recaptcha.RecaptchaClient>> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof com.google.android.recaptcha.Recaptcha$getClient$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = (com.google.android.recaptcha.Recaptcha$getClient$1) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.Recaptcha$getClient$1 r0 = new com.google.android.recaptcha.Recaptcha$getClient$1
            r0.<init>(r4, r9)
        L_0x0018:
            java.lang.Object r9 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0029 }
            goto L_0x0044
        L_0x0029:
            r5 = move-exception
            goto L_0x004b
        L_0x002b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.Result$Companion r9 = kotlin.Result.Companion     // Catch:{ all -> 0x0029 }
            r9 = r4
            com.google.android.recaptcha.Recaptcha r9 = (com.google.android.recaptcha.Recaptcha) r9     // Catch:{ all -> 0x0029 }
            r0.zzc = r3     // Catch:{ all -> 0x0029 }
            java.lang.Object r9 = com.google.android.recaptcha.internal.zzcs.zzb(r5, r6, r7, r0)     // Catch:{ all -> 0x0029 }
            if (r9 != r1) goto L_0x0044
            return r1
        L_0x0044:
            com.google.android.recaptcha.internal.zzde r9 = (com.google.android.recaptcha.internal.zzde) r9     // Catch:{ all -> 0x0029 }
            java.lang.Object r5 = kotlin.Result.m2444constructorimpl(r9)     // Catch:{ all -> 0x0029 }
            goto L_0x0055
        L_0x004b:
            kotlin.Result$Companion r6 = kotlin.Result.Companion
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m2444constructorimpl(r5)
        L_0x0055:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.Recaptcha.m2173getClientBWLJW6A(android.app.Application, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
