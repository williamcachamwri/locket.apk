package kotlinx.serialization.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;

@Target({})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.PROPERTY})
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0014\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004R\u0017\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/JsonNames;", "", "names", "", "", "()[Ljava/lang/String;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
@ExperimentalSerializationApi
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: JsonAnnotations.kt */
public @interface JsonNames {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: JsonAnnotations.kt */
    public /* synthetic */ class Impl implements JsonNames {
        private final /* synthetic */ String[] names;

        public Impl(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "names");
            this.names = strArr;
        }

        public final /* synthetic */ String[] names() {
            return this.names;
        }
    }

    String[] names();
}
