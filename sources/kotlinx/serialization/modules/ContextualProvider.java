package kotlinx.serialization.modules;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006H¦\u0002\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/modules/ContextualProvider;", "", "()V", "invoke", "Lkotlinx/serialization/KSerializer;", "typeArgumentsSerializers", "", "Argless", "WithTypeArguments", "Lkotlinx/serialization/modules/ContextualProvider$Argless;", "Lkotlinx/serialization/modules/ContextualProvider$WithTypeArguments;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SerializersModule.kt */
public abstract class ContextualProvider {
    public /* synthetic */ ContextualProvider(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract KSerializer<?> invoke(List<? extends KSerializer<?>> list);

    private ContextualProvider() {
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u001f\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00032\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u000fH\u0002R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/modules/ContextualProvider$Argless;", "Lkotlinx/serialization/modules/ContextualProvider;", "serializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/KSerializer;)V", "getSerializer", "()Lkotlinx/serialization/KSerializer;", "equals", "", "other", "", "hashCode", "", "invoke", "typeArgumentsSerializers", "", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SerializersModule.kt */
    public static final class Argless extends ContextualProvider {
        private final KSerializer<?> serializer;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Argless(KSerializer<?> kSerializer) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(kSerializer, "serializer");
            this.serializer = kSerializer;
        }

        public final KSerializer<?> getSerializer() {
            return this.serializer;
        }

        public KSerializer<?> invoke(List<? extends KSerializer<?>> list) {
            Intrinsics.checkNotNullParameter(list, "typeArgumentsSerializers");
            return this.serializer;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Argless) && Intrinsics.areEqual((Object) ((Argless) obj).serializer, (Object) this.serializer);
        }

        public int hashCode() {
            return this.serializer.hashCode();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B6\u0012/\u0010\u0002\u001a+\u0012\u001d\u0012\u001b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\u0002\u0010\tJ\u001f\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u00052\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004H\u0002R:\u0010\u0002\u001a+\u0012\u001d\u0012\u001b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lkotlinx/serialization/modules/ContextualProvider$WithTypeArguments;", "Lkotlinx/serialization/modules/ContextualProvider;", "provider", "Lkotlin/Function1;", "", "Lkotlinx/serialization/KSerializer;", "Lkotlin/ParameterName;", "name", "typeArgumentsSerializers", "(Lkotlin/jvm/functions/Function1;)V", "getProvider", "()Lkotlin/jvm/functions/Function1;", "invoke", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SerializersModule.kt */
    public static final class WithTypeArguments extends ContextualProvider {
        private final Function1<List<? extends KSerializer<?>>, KSerializer<?>> provider;

        public final Function1<List<? extends KSerializer<?>>, KSerializer<?>> getProvider() {
            return this.provider;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WithTypeArguments(Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> function1) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(function1, "provider");
            this.provider = function1;
        }

        public KSerializer<?> invoke(List<? extends KSerializer<?>> list) {
            Intrinsics.checkNotNullParameter(list, "typeArgumentsSerializers");
            return this.provider.invoke(list);
        }
    }
}
