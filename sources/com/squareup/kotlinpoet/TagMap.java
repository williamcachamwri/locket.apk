package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.Taggable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J'\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u0004H\u0016¢\u0006\u0002\u0010\fR$\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/squareup/kotlinpoet/TagMap;", "Lcom/squareup/kotlinpoet/Taggable;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Ljava/util/Map;)V", "getTags", "()Ljava/util/Map;", "tag", "T", "type", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Taggable.kt */
public final class TagMap implements Taggable {
    private final Map<KClass<?>, Object> tags;

    public TagMap(Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "tags");
        this.tags = UtilKt.toImmutableMap(map);
    }

    public <T> T tag(Class<T> cls) {
        return Taggable.DefaultImpls.tag(this, cls);
    }

    public Map<KClass<?>, Object> getTags() {
        return this.tags;
    }

    public <T> T tag(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        return getTags().get(kClass);
    }
}
