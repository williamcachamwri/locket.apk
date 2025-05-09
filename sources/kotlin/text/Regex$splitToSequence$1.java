package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {1, 1, 1}, l = {275, 283, 287}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "splitCount"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Regex.kt */
final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a4 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00a5
        L_0x0016:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001e:
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$1
            java.util.regex.Matcher r5 = (java.util.regex.Matcher) r5
            java.lang.Object r6 = r10.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r10
            r11 = r5
            goto L_0x0076
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00ba
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            kotlin.text.Regex r1 = r10.this$0
            java.util.regex.Pattern r1 = r1.nativePattern
            java.lang.CharSequence r5 = r10.$input
            java.util.regex.Matcher r1 = r1.matcher(r5)
            int r5 = r10.$limit
            if (r5 == r4) goto L_0x00a8
            boolean r5 = r1.find()
            if (r5 != 0) goto L_0x0051
            goto L_0x00a8
        L_0x0051:
            r5 = 0
            r7 = r10
            r6 = r11
            r11 = r1
            r1 = r5
        L_0x0056:
            java.lang.CharSequence r8 = r7.$input
            int r9 = r11.start()
            java.lang.CharSequence r5 = r8.subSequence(r5, r9)
            java.lang.String r5 = r5.toString()
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.L$0 = r6
            r7.L$1 = r11
            r7.I$0 = r1
            r7.label = r3
            java.lang.Object r5 = r6.yield(r5, r8)
            if (r5 != r0) goto L_0x0076
            return r0
        L_0x0076:
            int r5 = r11.end()
            int r1 = r1 + r4
            int r8 = r7.$limit
            int r8 = r8 - r4
            if (r1 == r8) goto L_0x0086
            boolean r8 = r11.find()
            if (r8 != 0) goto L_0x0056
        L_0x0086:
            java.lang.CharSequence r11 = r7.$input
            int r1 = r11.length()
            java.lang.CharSequence r11 = r11.subSequence(r5, r1)
            java.lang.String r11 = r11.toString()
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r3 = 0
            r7.L$0 = r3
            r7.L$1 = r3
            r7.label = r2
            java.lang.Object r11 = r6.yield(r11, r1)
            if (r11 != r0) goto L_0x00a5
            return r0
        L_0x00a5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00a8:
            java.lang.CharSequence r1 = r10.$input
            java.lang.String r1 = r1.toString()
            r2 = r10
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r10.label = r4
            java.lang.Object r11 = r11.yield(r1, r2)
            if (r11 != r0) goto L_0x00ba
            return r0
        L_0x00ba:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex$splitToSequence$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
