package kotlin.reflect.jvm.internal.impl.util;

import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.CheckResult;
import kotlin.text.Regex;

/* compiled from: modifierChecks.kt */
public final class Checks {
    private final Function1<FunctionDescriptor, String> additionalCheck;
    private final Check[] checks;
    private final Name name;
    private final Collection<Name> nameList;
    private final Regex regex;

    private Checks(Name name2, Regex regex2, Collection<Name> collection, Function1<? super FunctionDescriptor, String> function1, Check... checkArr) {
        this.name = name2;
        this.regex = regex2;
        this.nameList = collection;
        this.additionalCheck = function1;
        this.checks = checkArr;
    }

    public final boolean isApplicable(FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        if (this.name != null && !Intrinsics.areEqual((Object) functionDescriptor.getName(), (Object) this.name)) {
            return false;
        }
        if (this.regex != null) {
            String asString = functionDescriptor.getName().asString();
            Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
            if (!this.regex.matches(asString)) {
                return false;
            }
        }
        Collection<Name> collection = this.nameList;
        if (collection == null || collection.contains(functionDescriptor.getName())) {
            return true;
        }
        return false;
    }

    public final CheckResult checkAll(FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        for (Check invoke : this.checks) {
            String invoke2 = invoke.invoke(functionDescriptor);
            if (invoke2 != null) {
                return new CheckResult.IllegalSignature(invoke2);
            }
        }
        String invoke3 = this.additionalCheck.invoke(functionDescriptor);
        if (invoke3 != null) {
            return new CheckResult.IllegalSignature(invoke3);
        }
        return CheckResult.SuccessCheck.INSTANCE;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Checks(Name name2, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name2, checkArr, (Function1<? super FunctionDescriptor, String>) (i & 4) != 0 ? AnonymousClass2.INSTANCE : function1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Checks(Name name2, Check[] checkArr, Function1<? super FunctionDescriptor, String> function1) {
        this(name2, (Regex) null, (Collection<Name>) null, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
        Intrinsics.checkNotNullParameter(name2, "name");
        Intrinsics.checkNotNullParameter(checkArr, "checks");
        Intrinsics.checkNotNullParameter(function1, "additionalChecks");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Checks(Regex regex2, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(regex2, checkArr, (Function1<? super FunctionDescriptor, String>) (i & 4) != 0 ? AnonymousClass3.INSTANCE : function1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Checks(Regex regex2, Check[] checkArr, Function1<? super FunctionDescriptor, String> function1) {
        this((Name) null, regex2, (Collection<Name>) null, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
        Intrinsics.checkNotNullParameter(regex2, "regex");
        Intrinsics.checkNotNullParameter(checkArr, "checks");
        Intrinsics.checkNotNullParameter(function1, "additionalChecks");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Checks(Collection collection, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((Collection<Name>) collection, checkArr, (Function1<? super FunctionDescriptor, String>) (i & 4) != 0 ? AnonymousClass4.INSTANCE : function1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Checks(Collection<Name> collection, Check[] checkArr, Function1<? super FunctionDescriptor, String> function1) {
        this((Name) null, (Regex) null, collection, function1, (Check[]) Arrays.copyOf(checkArr, checkArr.length));
        Intrinsics.checkNotNullParameter(collection, "nameList");
        Intrinsics.checkNotNullParameter(checkArr, "checks");
        Intrinsics.checkNotNullParameter(function1, "additionalChecks");
    }
}
