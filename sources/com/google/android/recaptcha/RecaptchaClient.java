package com.google.android.recaptcha;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH¦@¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0004\b\u000b\u0010\fø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaClient;", "", "execute", "Lkotlin/Result;", "", "recaptchaAction", "Lcom/google/android/recaptcha/RecaptchaAction;", "timeout", "", "execute-0E7RQCE", "(Lcom/google/android/recaptcha/RecaptchaAction;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute-gIAlu-s", "(Lcom/google/android/recaptcha/RecaptchaAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public interface RecaptchaClient {
    /* renamed from: execute-0E7RQCE  reason: not valid java name */
    Object m2174execute0E7RQCE(RecaptchaAction recaptchaAction, long j, Continuation<? super Result<String>> continuation);

    /* renamed from: execute-gIAlu-s  reason: not valid java name */
    Object m2175executegIAlus(RecaptchaAction recaptchaAction, Continuation<? super Result<String>> continuation);
}
