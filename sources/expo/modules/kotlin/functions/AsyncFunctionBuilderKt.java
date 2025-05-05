package expo.modules.kotlin.functions;

import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u001e\b\u0004\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\f¢\u0006\u0002\u0010\b\u001aH\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001*\u00020\u00032$\b\u0004\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000bH\f¢\u0006\u0002\u0010\f\u001aV\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001*\u00020\u00032*\b\u0004\u0010\u0004\u001a$\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eH\f¢\u0006\u0002\u0010\u000f\u001ad\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001*\u00020\u000320\b\u0004\u0010\u0004\u001a*\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011H\f¢\u0006\u0002\u0010\u0012\u001ar\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001*\u00020\u000326\b\u0004\u0010\u0004\u001a0\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0014H\f¢\u0006\u0002\u0010\u0015\u001a\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001*\u00020\u00032<\b\u0004\u0010\u0004\u001a6\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0017H\f¢\u0006\u0002\u0010\u0018\u001a\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001*\u00020\u00032B\b\u0004\u0010\u0004\u001a<\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001aH\f¢\u0006\u0002\u0010\u001b\u001a\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001\"\u0006\b\u0007\u0010\u001c\u0018\u0001*\u00020\u00032H\b\u0004\u0010\u0004\u001aB\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001dH\f¢\u0006\u0002\u0010\u001e\u001aª\u0001\u0010\u0000\u001a\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\n\u0018\u0001\"\u0006\b\u0002\u0010\r\u0018\u0001\"\u0006\b\u0003\u0010\u0010\u0018\u0001\"\u0006\b\u0004\u0010\u0013\u0018\u0001\"\u0006\b\u0005\u0010\u0016\u0018\u0001\"\u0006\b\u0006\u0010\u0019\u0018\u0001\"\u0006\b\u0007\u0010\u001c\u0018\u0001\"\u0006\b\b\u0010\u001f\u0018\u0001*\u00020\u00032N\b\u0004\u0010\u0004\u001aH\b\u0001\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070 H\f¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"Coroutine", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "R", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P0", "Lkotlin/Function2;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P1", "Lkotlin/Function3;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P2", "Lkotlin/Function4;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P3", "Lkotlin/Function5;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P4", "Lkotlin/Function6;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P5", "Lkotlin/Function7;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P6", "Lkotlin/Function8;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P7", "Lkotlin/Function9;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: AsyncFunctionBuilder.kt */
public final class AsyncFunctionBuilderKt {
    public static final /* synthetic */ <R> BaseAsyncFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        BaseAsyncFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(asyncFunctionBuilder.getName(), new AnyType[0], new AsyncFunctionBuilder$SuspendBody$1(function1, (Continuation<? super AsyncFunctionBuilder$SuspendBody$1>) null));
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function2<? super P0, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function2, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2(function2, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function3, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5(function3, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function4, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9(function4, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> function5) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function5, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$10.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$11.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$12.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$13.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$14(function5, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> function6) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function6, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$15.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$16.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$17.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$18.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$19.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$20(function6, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> function7) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function7, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$21.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$22.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$23.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$25.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$26.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$27(function7, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> function8) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function8, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$28.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$29.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$30.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$31.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$32.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$33.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$34.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$35(function8, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> function9) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function9, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P2");
        KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P2");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P3");
        KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P3");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P4");
        KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P4");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P5");
        KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P5");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P6");
        KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P6");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P7");
        KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P7");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$36.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$37.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$38.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$42.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$43.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$44(function9, (Continuation) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        asyncFunctionBuilder.setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }
}
