package com.squareup.kotlinpoet;

import io.sentry.protocol.ViewHierarchyNode;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\u0000J\u0011\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0001H\u0002J\u001a\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0001H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/squareup/kotlinpoet/NameAllocator;", "", "()V", "allocatedNames", "", "", "tagToName", "", "(Ljava/util/Set;Ljava/util/Map;)V", "copy", "get", "tag", "newName", "suggestion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: NameAllocator.kt */
public final class NameAllocator {
    private final Set<String> allocatedNames;
    private final Map<Object, String> tagToName;

    public final String newName(String str) {
        Intrinsics.checkNotNullParameter(str, "suggestion");
        return newName$default(this, str, (Object) null, 2, (Object) null);
    }

    private NameAllocator(Set<String> set, Map<Object, String> map) {
        this.allocatedNames = set;
        this.tagToName = map;
    }

    public NameAllocator() {
        this(new LinkedHashSet(), new LinkedHashMap());
    }

    public static /* synthetic */ String newName$default(NameAllocator nameAllocator, String str, Object obj, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(obj, "randomUUID().toString()");
        }
        return nameAllocator.newName(str, obj);
    }

    public final String newName(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "suggestion");
        Intrinsics.checkNotNullParameter(obj, ViewHierarchyNode.JsonKeys.TAG);
        String access$toJavaIdentifier = NameAllocatorKt.toJavaIdentifier(str);
        while (true) {
            if (!UtilKt.isKeyword(access$toJavaIdentifier) && this.allocatedNames.add(access$toJavaIdentifier)) {
                break;
            }
            access$toJavaIdentifier = access$toJavaIdentifier + '_';
        }
        String put = this.tagToName.put(obj, access$toJavaIdentifier);
        if (put == null) {
            return access$toJavaIdentifier;
        }
        this.tagToName.put(obj, put);
        throw new IllegalArgumentException("tag " + obj + " cannot be used for both '" + put + "' and '" + access$toJavaIdentifier + '\'');
    }

    public final String get(Object obj) {
        Intrinsics.checkNotNullParameter(obj, ViewHierarchyNode.JsonKeys.TAG);
        String str = this.tagToName.get(obj);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException(("unknown tag: " + obj).toString());
    }

    public final NameAllocator copy() {
        return new NameAllocator(CollectionsKt.toMutableSet(this.allocatedNames), MapsKt.toMutableMap(this.tagToName));
    }
}
