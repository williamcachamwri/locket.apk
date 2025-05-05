package kotlinx.coroutines.selects;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J0\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007H¦\u0002¢\u0006\u0002\u0010\tJB\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b2\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fH¦\u0002¢\u0006\u0002\u0010\rJV\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\n*\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\n0\u000f2\u0006\u0010\u0010\u001a\u0002H\u000e2\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fH¦\u0002¢\u0006\u0002\u0010\u0011JP\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\n*\u0010\u0012\u0006\u0012\u0004\u0018\u0001H\u000e\u0012\u0004\u0012\u0002H\n0\u000f2\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fH\u0002¢\u0006\u0002\u0010\u0012J3\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007H\u0017¢\u0006\u0002\u0010\u0016\u0001\u0001\u0017¨\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilder;", "R", "", "invoke", "", "Lkotlinx/coroutines/selects/SelectClause0;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "(Lkotlinx/coroutines/selects/SelectClause2;Lkotlin/jvm/functions/Function2;)V", "onTimeout", "timeMillis", "", "(JLkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/selects/SelectImplementation;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Select.kt */
public interface SelectBuilder<R> {
    void invoke(SelectClause0 selectClause0, Function1<? super Continuation<? super R>, ? extends Object> function1);

    <Q> void invoke(SelectClause1<? extends Q> selectClause1, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);

    <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, P p, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);

    <P, Q> void invoke(SelectClause2<? super P, ? extends Q> selectClause2, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
    void onTimeout(long j, Function1<? super Continuation<? super R>, ? extends Object> function1);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: Select.kt */
    public static final class DefaultImpls {
        public static <R, P, Q> void invoke(SelectBuilder<? super R> selectBuilder, SelectClause2<? super P, ? extends Q> selectClause2, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
            selectBuilder.invoke(selectClause2, null, function2);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = {"kotlinx.coroutines.selects.onTimeout"}))
        public static <R> void onTimeout(SelectBuilder<? super R> selectBuilder, long j, Function1<? super Continuation<? super R>, ? extends Object> function1) {
            OnTimeoutKt.onTimeout(selectBuilder, j, function1);
        }
    }
}
