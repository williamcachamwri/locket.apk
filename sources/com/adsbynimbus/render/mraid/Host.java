package com.adsbynimbus.render.mraid;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 '2\u00020\u0001:\u0002&'B¥\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010\u001cB\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u000b¢\u0006\u0002\u0010\u001dJ&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÁ\u0001¢\u0006\u0002\b%R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\r8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u00188\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/adsbynimbus/render/mraid/Host;", "", "seen1", "", "CurrentAppOrientation", "Lcom/adsbynimbus/render/mraid/AppOrientation;", "CurrentPosition", "Lcom/adsbynimbus/render/mraid/Position;", "isViewable", "", "PlacementType", "", "MaxSize", "Lcom/adsbynimbus/render/mraid/Size;", "ScreenSize", "OrientationProperties", "Lcom/adsbynimbus/render/mraid/OrientationProperties;", "ResizeProperties", "Lcom/adsbynimbus/render/mraid/ResizeProperties;", "DefaultPosition", "State", "ExpandProperties", "Lcom/adsbynimbus/render/mraid/ExpandProperties;", "supports", "", "Version", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/adsbynimbus/render/mraid/AppOrientation;Lcom/adsbynimbus/render/mraid/Position;ZLjava/lang/String;Lcom/adsbynimbus/render/mraid/Size;Lcom/adsbynimbus/render/mraid/Size;Lcom/adsbynimbus/render/mraid/OrientationProperties;Lcom/adsbynimbus/render/mraid/ResizeProperties;Lcom/adsbynimbus/render/mraid/Position;Ljava/lang/String;Lcom/adsbynimbus/render/mraid/ExpandProperties;Ljava/util/Map;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/adsbynimbus/render/mraid/AppOrientation;Lcom/adsbynimbus/render/mraid/Position;ZLjava/lang/String;Lcom/adsbynimbus/render/mraid/Size;Lcom/adsbynimbus/render/mraid/Size;Lcom/adsbynimbus/render/mraid/OrientationProperties;Lcom/adsbynimbus/render/mraid/ResizeProperties;Lcom/adsbynimbus/render/mraid/Position;Ljava/lang/String;Lcom/adsbynimbus/render/mraid/ExpandProperties;Ljava/util/Map;Ljava/lang/String;)V", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$static_release", "$serializer", "Companion", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* compiled from: Host.kt */
public final class Host {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, BooleanSerializer.INSTANCE), null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public AppOrientation CurrentAppOrientation;
    public Position CurrentPosition;
    public Position DefaultPosition;
    public ExpandProperties ExpandProperties;
    public final Size MaxSize;
    public OrientationProperties OrientationProperties;
    public final String PlacementType;
    public ResizeProperties ResizeProperties;
    public final Size ScreenSize;
    public String State;
    public final String Version;
    public boolean isViewable;
    public final Map<String, Boolean> supports;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/mraid/Host$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/render/mraid/Host;", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Host.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Host> serializer() {
            return Host$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Host(int i, AppOrientation appOrientation, Position position, boolean z, String str, Size size, Size size2, OrientationProperties orientationProperties, ResizeProperties resizeProperties, Position position2, String str2, ExpandProperties expandProperties, Map map, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        int i2 = i;
        if (7999 != (i2 & 7999)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7999, Host$$serializer.INSTANCE.getDescriptor());
        }
        this.CurrentAppOrientation = appOrientation;
        this.CurrentPosition = position;
        this.isViewable = z;
        this.PlacementType = str;
        this.MaxSize = size;
        this.ScreenSize = size2;
        if ((i2 & 64) == 0) {
            this.OrientationProperties = null;
        } else {
            this.OrientationProperties = orientationProperties;
        }
        if ((i2 & 128) == 0) {
            this.ResizeProperties = null;
        } else {
            this.ResizeProperties = resizeProperties;
        }
        this.DefaultPosition = position2;
        this.State = str2;
        this.ExpandProperties = expandProperties;
        this.supports = map;
        this.Version = str3;
    }

    public Host(AppOrientation appOrientation, Position position, boolean z, String str, Size size, Size size2, OrientationProperties orientationProperties, ResizeProperties resizeProperties, Position position2, String str2, ExpandProperties expandProperties, Map<String, Boolean> map, String str3) {
        Intrinsics.checkNotNullParameter(appOrientation, "CurrentAppOrientation");
        Intrinsics.checkNotNullParameter(position, "CurrentPosition");
        Intrinsics.checkNotNullParameter(str, "PlacementType");
        Intrinsics.checkNotNullParameter(size, "MaxSize");
        Intrinsics.checkNotNullParameter(size2, "ScreenSize");
        Intrinsics.checkNotNullParameter(position2, "DefaultPosition");
        Intrinsics.checkNotNullParameter(str2, "State");
        Intrinsics.checkNotNullParameter(expandProperties, "ExpandProperties");
        Intrinsics.checkNotNullParameter(map, "supports");
        Intrinsics.checkNotNullParameter(str3, "Version");
        this.CurrentAppOrientation = appOrientation;
        this.CurrentPosition = position;
        this.isViewable = z;
        this.PlacementType = str;
        this.MaxSize = size;
        this.ScreenSize = size2;
        this.OrientationProperties = orientationProperties;
        this.ResizeProperties = resizeProperties;
        this.DefaultPosition = position2;
        this.State = str2;
        this.ExpandProperties = expandProperties;
        this.supports = map;
        this.Version = str3;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$static_release(Host host, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z = false;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, AppOrientation$$serializer.INSTANCE, host.CurrentAppOrientation);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Position$$serializer.INSTANCE, host.CurrentPosition);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 2, host.isViewable);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, host.PlacementType);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 4, Size$$serializer.INSTANCE, host.MaxSize);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 5, Size$$serializer.INSTANCE, host.ScreenSize);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || host.OrientationProperties != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, OrientationProperties$$serializer.INSTANCE, host.OrientationProperties);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || host.ResizeProperties != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, ResizeProperties$$serializer.INSTANCE, host.ResizeProperties);
        }
        compositeEncoder.encodeSerializableElement(serialDescriptor, 8, Position$$serializer.INSTANCE, host.DefaultPosition);
        compositeEncoder.encodeStringElement(serialDescriptor, 9, host.State);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 10, ExpandProperties$$serializer.INSTANCE, host.ExpandProperties);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 11, kSerializerArr[11], host.supports);
        compositeEncoder.encodeStringElement(serialDescriptor, 12, host.Version);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Host(com.adsbynimbus.render.mraid.AppOrientation r18, com.adsbynimbus.render.mraid.Position r19, boolean r20, java.lang.String r21, com.adsbynimbus.render.mraid.Size r22, com.adsbynimbus.render.mraid.Size r23, com.adsbynimbus.render.mraid.OrientationProperties r24, com.adsbynimbus.render.mraid.ResizeProperties r25, com.adsbynimbus.render.mraid.Position r26, java.lang.String r27, com.adsbynimbus.render.mraid.ExpandProperties r28, java.util.Map r29, java.lang.String r30, int r31, kotlin.jvm.internal.DefaultConstructorMarker r32) {
        /*
            r17 = this;
            r0 = r31
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r10 = r2
            goto L_0x000b
        L_0x0009:
            r10 = r24
        L_0x000b:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0011
            r11 = r2
            goto L_0x0013
        L_0x0011:
            r11 = r25
        L_0x0013:
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r12 = r26
            r13 = r27
            r14 = r28
            r15 = r29
            r16 = r30
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.mraid.Host.<init>(com.adsbynimbus.render.mraid.AppOrientation, com.adsbynimbus.render.mraid.Position, boolean, java.lang.String, com.adsbynimbus.render.mraid.Size, com.adsbynimbus.render.mraid.Size, com.adsbynimbus.render.mraid.OrientationProperties, com.adsbynimbus.render.mraid.ResizeProperties, com.adsbynimbus.render.mraid.Position, java.lang.String, com.adsbynimbus.render.mraid.ExpandProperties, java.util.Map, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
