package com.mrousavy.camera.core.types;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.InvalidTypeScriptUnionError;
import com.mrousavy.camera.core.types.CodeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/mrousavy/camera/core/types/CodeScannerOptions;", "", "codeTypes", "", "Lcom/mrousavy/camera/core/types/CodeType;", "(Ljava/util/List;)V", "getCodeTypes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodeScannerOptions.kt */
public final class CodeScannerOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<CodeType> codeTypes;

    public static /* synthetic */ CodeScannerOptions copy$default(CodeScannerOptions codeScannerOptions, List<CodeType> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = codeScannerOptions.codeTypes;
        }
        return codeScannerOptions.copy(list);
    }

    public final List<CodeType> component1() {
        return this.codeTypes;
    }

    public final CodeScannerOptions copy(List<? extends CodeType> list) {
        Intrinsics.checkNotNullParameter(list, "codeTypes");
        return new CodeScannerOptions(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CodeScannerOptions) && Intrinsics.areEqual((Object) this.codeTypes, (Object) ((CodeScannerOptions) obj).codeTypes);
    }

    public int hashCode() {
        return this.codeTypes.hashCode();
    }

    public String toString() {
        return "CodeScannerOptions(codeTypes=" + this.codeTypes + ")";
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/types/CodeScannerOptions$Companion;", "", "()V", "fromJSValue", "Lcom/mrousavy/camera/core/types/CodeScannerOptions;", "value", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CodeScannerOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CodeScannerOptions fromJSValue(ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(readableMap, "value");
            ReadableArray array = readableMap.getArray("codeTypes");
            if (array != null) {
                ArrayList<Object> arrayList = array.toArrayList();
                Intrinsics.checkNotNullExpressionValue(arrayList, "toArrayList(...)");
                Iterable iterable = arrayList;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Object next : iterable) {
                    CodeType.Companion companion = CodeType.Companion;
                    Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
                    arrayList2.add(companion.fromUnionValue((String) next));
                }
                return new CodeScannerOptions((List) arrayList2);
            }
            throw new InvalidTypeScriptUnionError("codeScanner", readableMap.toString());
        }
    }

    public CodeScannerOptions(List<? extends CodeType> list) {
        Intrinsics.checkNotNullParameter(list, "codeTypes");
        this.codeTypes = list;
    }

    public final List<CodeType> getCodeTypes() {
        return this.codeTypes;
    }
}
