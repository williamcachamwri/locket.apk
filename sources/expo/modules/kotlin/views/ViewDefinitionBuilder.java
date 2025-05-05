package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.defaultmodules.ErrorManagerModule;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.modules.DefinitionMarker;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;

@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u000bJ,\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u00012\u0006\u00106\u001a\u00020\u000b2\u000e\b\u0004\u00108\u001a\b\u0012\u0004\u0012\u0002H709H\bø\u0001\u0000J+\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020\u000b2\u0010\b\u0004\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000309H\bø\u0001\u0000¢\u0006\u0002\b:JI\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u00012\u0006\u00106\u001a\u00020\u000b2#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0004\u0012\u0002H70\u0018H\bø\u0001\u0000Jf\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\u0004\u00108\u001a2\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002H70?H\bø\u0001\u0000J\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u00012\u0006\u00106\u001a\u00020\u000b2M\b\u0004\u00108\u001aG\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0004\u0012\u0002H70BH\bø\u0001\u0000J \u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u00012\u0006\u00106\u001a\u00020\u000b2b\b\u0004\u00108\u001a\\\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H70EH\bø\u0001\u0000J½\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u00012\u0006\u00106\u001a\u00020\u000b2w\b\u0004\u00108\u001aq\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0004\u0012\u0002H70HH\bø\u0001\u0000JÜ\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u00012\u0006\u00106\u001a\u00020\u000b2\u0001\b\u0004\u00108\u001a\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H70KH\bø\u0001\u0000Jù\u0001\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u0001\"\u0006\b\b\u0010M\u0018\u00012\u0006\u00106\u001a\u00020\u000b2¢\u0001\b\u0004\u00108\u001a\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u0011HM¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0004\u0012\u0002H70NH\bø\u0001\u0000J\u0002\u00105\u001a\u00020\f\"\u0006\b\u0001\u00107\u0018\u0001\"\u0006\b\u0002\u0010;\u0018\u0001\"\u0006\b\u0003\u0010>\u0018\u0001\"\u0006\b\u0004\u0010A\u0018\u0001\"\u0006\b\u0005\u0010D\u0018\u0001\"\u0006\b\u0006\u0010G\u0018\u0001\"\u0006\b\u0007\u0010J\u0018\u0001\"\u0006\b\b\u0010M\u0018\u0001\"\u0006\b\t\u0010P\u0018\u00012\u0006\u00106\u001a\u00020\u000b2·\u0001\b\u0004\u00108\u001a°\u0001\u0012\u0013\u0012\u0011H;¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(=\u0012\u0013\u0012\u0011H>¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(@\u0012\u0013\u0012\u0011HA¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011HD¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011HG¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(I\u0012\u0013\u0012\u0011HJ¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(L\u0012\u0013\u0012\u0011HM¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(O\u0012\u0013\u0012\u0011HP¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(R\u0012\u0004\u0012\u0002H70QH\bø\u0001\u0000J\u001d\u0010S\u001a\u00020\u00192\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u000b0UH\u0007¢\u0006\u0004\bV\u0010WJ\u001f\u0010S\u001a\u00020\u00192\u0012\u0010T\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0U\"\u00020\u000b¢\u0006\u0002\u0010WJ7\u0010X\u001a\u00020\u0019\"\n\b\u0001\u0010Y\u0018\u0001*\u00020Z2\u001d\u00108\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002HY0[\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\\H\bø\u0001\u0000J1\u0010]\u001a\u00020\u00192#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u00190\u0018H\bø\u0001\u0000JB\u0010]\u001a\u00020\u0019\"\n\b\u0001\u0010_\u0018\u0001*\u00028\u00002#\b\b\u00108\u001a\u001d\u0012\u0013\u0012\u0011H_¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u00190\u0018H\bø\u0001\u0000¢\u0006\u0002\b`J1\u0010a\u001a\u00020\u00192#\b\u0004\u00108\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u00190\u0018H\bø\u0001\u0000JB\u0010a\u001a\u00020\u0019\"\n\b\u0001\u0010_\u0018\u0001*\u00028\u00002#\b\b\u00108\u001a\u001d\u0012\u0013\u0012\u0011H_¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020\u00190\u0018H\bø\u0001\u0000¢\u0006\u0002\bbJV\u0010c\u001a\u00020\u0019\"\u0006\b\u0001\u0010d\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\b\u00108\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0013\u0012\u0011Hd¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(e\u0012\u0004\u0012\u00020\u00190?H\bø\u0001\u0000Jg\u0010c\u001a\u00020\u0019\"\n\b\u0001\u0010_\u0018\u0001*\u00020\u0002\"\u0006\b\u0002\u0010d\u0018\u00012\u0006\u00106\u001a\u00020\u000b28\b\b\u00108\u001a2\u0012\u0013\u0012\u0011H_¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(^\u0012\u0013\u0012\u0011Hd¢\u0006\f\b<\u0012\b\b6\u0012\u0004\b\b(e\u0012\u0004\u0012\u00020\u00190?H\bø\u0001\u0000¢\u0006\u0002\bfJ\u0006\u0010g\u001a\u00020hJ\u001a\u0010i\u001a\u0014\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020\u00020?H\u0002J\u0010\u0010l\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010mH\u0002J \u0010n\u001a\u00020\u00022\u0006\u0010o\u001a\u00020j2\u0006\u0010p\u001a\u00020k2\u0006\u0010q\u001a\u00020rH\u0002R0\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00160\nX\u000e¢\u0006\u0002\n\u0000R2\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u000e\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR2\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00188\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b \u0010\u000e\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR0\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020$0\n8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u000e\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010\u0012R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u000e\u001a\u0004\b)\u0010*R&\u0010+\u001a\u0004\u0018\u00010,8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b-\u0010\u000e\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u0006\u001a\u00020\u00078\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u000e\u001a\u0004\b3\u00104\u0002\u0007\n\u0005\b20\u0001¨\u0006s"}, d2 = {"Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "T", "Landroid/view/View;", "", "viewClass", "Lkotlin/reflect/KClass;", "viewType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KType;)V", "asyncFunctions", "", "", "Lexpo/modules/kotlin/functions/AsyncFunction;", "getAsyncFunctions$annotations", "()V", "getAsyncFunctions", "()Ljava/util/Map;", "setAsyncFunctions", "(Ljava/util/Map;)V", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "functionBuilders", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "onViewDestroys", "Lkotlin/Function1;", "", "getOnViewDestroys$annotations", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "setOnViewDestroys", "(Lkotlin/jvm/functions/Function1;)V", "onViewDidUpdateProps", "getOnViewDidUpdateProps$annotations", "getOnViewDidUpdateProps", "setOnViewDidUpdateProps", "props", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps$annotations", "getProps", "setProps", "getViewClass$annotations", "getViewClass", "()Lkotlin/reflect/KClass;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$annotations", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "setViewGroupDefinition", "(Lexpo/modules/kotlin/views/ViewGroupDefinition;)V", "getViewType$annotations", "getViewType", "()Lkotlin/reflect/KType;", "AsyncFunction", "name", "R", "body", "Lkotlin/Function0;", "AsyncFunctionWithoutArgs", "P0", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Events", "callbacks", "", "EventsWithArray", "([Ljava/lang/String;)V", "GroupView", "ParentType", "Landroid/view/ViewGroup;", "Lexpo/modules/kotlin/views/ViewGroupDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "OnViewDestroys", "view", "ViewType", "OnViewDestroysGeneric", "OnViewDidUpdateProps", "OnViewDidUpdatePropsGeneric", "Prop", "PropType", "prop", "PropGeneric", "build", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "createViewFactory", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "getPrimaryConstructor", "Lkotlin/reflect/KFunction;", "handleFailureDuringViewCreation", "context", "appContext", "e", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@DefinitionMarker
/* compiled from: ViewDefinitionBuilder.kt */
public final class ViewDefinitionBuilder<T extends View> {
    private Map<String, AsyncFunction> asyncFunctions = new LinkedHashMap();
    private CallbacksDefinition callbacksDefinition;
    private Map<String, AsyncFunctionBuilder> functionBuilders = new LinkedHashMap();
    private Function1<? super View, Unit> onViewDestroys;
    private Function1<? super View, Unit> onViewDidUpdateProps;
    private Map<String, AnyViewProp> props = new LinkedHashMap();
    private final KClass<T> viewClass;
    private ViewGroupDefinition viewGroupDefinition;
    private final KType viewType;

    public static /* synthetic */ void getAsyncFunctions$annotations() {
    }

    public static /* synthetic */ void getOnViewDestroys$annotations() {
    }

    public static /* synthetic */ void getOnViewDidUpdateProps$annotations() {
    }

    public static /* synthetic */ void getProps$annotations() {
    }

    public static /* synthetic */ void getViewClass$annotations() {
    }

    public static /* synthetic */ void getViewGroupDefinition$annotations() {
    }

    public static /* synthetic */ void getViewType$annotations() {
    }

    public ViewDefinitionBuilder(KClass<T> kClass, KType kType) {
        Intrinsics.checkNotNullParameter(kClass, "viewClass");
        Intrinsics.checkNotNullParameter(kType, "viewType");
        this.viewClass = kClass;
        this.viewType = kType;
    }

    public final KClass<T> getViewClass() {
        return this.viewClass;
    }

    public final KType getViewType() {
        return this.viewType;
    }

    public final Map<String, AnyViewProp> getProps() {
        return this.props;
    }

    public final void setProps(Map<String, AnyViewProp> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.props = map;
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final void setOnViewDestroys(Function1<? super View, Unit> function1) {
        this.onViewDestroys = function1;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    public final void setOnViewDidUpdateProps(Function1<? super View, Unit> function1) {
        this.onViewDidUpdateProps = function1;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final void setViewGroupDefinition(ViewGroupDefinition viewGroupDefinition2) {
        this.viewGroupDefinition = viewGroupDefinition2;
    }

    public final Map<String, AsyncFunction> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final void setAsyncFunctions(Map<String, AsyncFunction> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.asyncFunctions = map;
    }

    public final ViewManagerDefinition build() {
        Map<String, AsyncFunction> map = this.asyncFunctions;
        Map<String, AsyncFunctionBuilder> map2 = this.functionBuilders;
        Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
        for (Map.Entry entry : map2.entrySet()) {
            linkedHashMap.put(entry.getKey(), ((AsyncFunctionBuilder) entry.getValue()).build$expo_modules_core_release());
        }
        Map<String, AsyncFunction> plus = MapsKt.plus(map, (Map<String, AsyncFunction>) linkedHashMap);
        for (Map.Entry<String, AsyncFunction> value : plus.entrySet()) {
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = (BaseAsyncFunctionComponent) value.getValue();
            baseAsyncFunctionComponent.runOnQueue(Queues.MAIN);
            baseAsyncFunctionComponent.setOwnerType(this.viewType);
            baseAsyncFunctionComponent.setCanTakeOwner(true);
        }
        return new ViewManagerDefinition(createViewFactory(), JvmClassMappingKt.getJavaClass(this.viewClass), this.props, this.onViewDestroys, this.callbacksDefinition, this.viewGroupDefinition, this.onViewDidUpdateProps, CollectionsKt.toList(plus.values()));
    }

    public final void OnViewDestroys(Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        setOnViewDestroys(new ViewDefinitionBuilder$OnViewDestroys$1(function1));
    }

    public final /* synthetic */ <ViewType extends T> void OnViewDestroysGeneric(Function1<? super ViewType, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        Intrinsics.needClassReification();
        setOnViewDestroys(new ViewDefinitionBuilder$OnViewDestroys$2(function1));
    }

    public final void OnViewDidUpdateProps(Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        setOnViewDidUpdateProps(new ViewDefinitionBuilder$OnViewDidUpdateProps$1(function1));
    }

    public final /* synthetic */ <ViewType extends T> void OnViewDidUpdatePropsGeneric(Function1<? super ViewType, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        Intrinsics.needClassReification();
        setOnViewDidUpdateProps(new ViewDefinitionBuilder$OnViewDidUpdateProps$2(function1));
    }

    public final /* synthetic */ <PropType> void Prop(String str, Function2<? super T, ? super PropType, Unit> function2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        Map<String, AnyViewProp> props2 = getProps();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "PropType");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "PropType");
        props2.put(str, new ConcreteViewProp(str, new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$Prop$1.INSTANCE)), function2));
    }

    public final /* synthetic */ <ViewType extends View, PropType> void PropGeneric(String str, Function2<? super ViewType, ? super PropType, Unit> function2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        Map<String, AnyViewProp> props2 = getProps();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "PropType");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "PropType");
        props2.put(str, new ConcreteViewProp(str, new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$Prop$2.INSTANCE)), function2));
    }

    public final void Events(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(strArr);
    }

    public final void EventsWithArray(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "callbacks");
        this.callbacksDefinition = new CallbacksDefinition(strArr);
    }

    public final /* synthetic */ <ParentType extends ViewGroup> void GroupView(Function1<? super ViewGroupDefinitionBuilder<ParentType>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        KClass viewClass2 = getViewClass();
        Intrinsics.reifiedOperationMarker(4, "ParentType");
        Intrinsics.areEqual((Object) viewClass2, (Object) Reflection.getOrCreateKotlinClass(ViewGroup.class));
        if (getViewGroupDefinition() == null) {
            ViewGroupDefinitionBuilder viewGroupDefinitionBuilder = new ViewGroupDefinitionBuilder();
            function1.invoke(viewGroupDefinitionBuilder);
            setViewGroupDefinition(viewGroupDefinitionBuilder.build());
            return;
        }
        throw new IllegalArgumentException("The viewManager definition may have exported only one groupView definition.".toString());
    }

    public final AsyncFunction AsyncFunctionWithoutArgs(String str, Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(str, new AnyType[0], new ViewDefinitionBuilder$AsyncFunction$1(function0));
        getAsyncFunctions().put(str, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R> AsyncFunction AsyncFunction(String str, Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(str, new AnyType[0], new ViewDefinitionBuilder$AsyncFunction$3(function0));
        getAsyncFunctions().put(str, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncFunction(String str, Function1<? super P0, ? extends R> function1) {
        AsyncFunction asyncFunction;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P0");
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str, new AnyType[0], new ViewDefinitionBuilder$AsyncFunction$5(function1));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$6.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$7(function1));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncFunction(String str, Function2<? super P0, ? super P1, ? extends R> function2) {
        AsyncFunction asyncFunction;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P1");
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$9.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$10(function2));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$11.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$12.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$13(function2));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncFunction(String str, Function3<? super P0, ? super P1, ? super P2, ? extends R> function3) {
        AsyncFunction asyncFunction;
        String str2 = str;
        Function3<? super P0, ? super P1, ? super P2, ? extends R> function32 = function3;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(function32, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P2");
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$16.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$17(function32));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$18.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$19.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$20.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$21(function32));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncFunction(String str, Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> function4) {
        AsyncFunction asyncFunction;
        String str2 = str;
        Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> function42 = function4;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(function42, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P3");
        if (Object.class == Promise.class) {
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$23.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$25.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$26(function42));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P3");
            KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P3");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$27.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$28.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ViewDefinitionBuilder$AsyncFunction$29.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ViewDefinitionBuilder$AsyncFunction$30.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$31(function42));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncFunction(String str, Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> function5) {
        AsyncFunction asyncFunction;
        String str2 = str;
        Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> function52 = function5;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(function52, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P4");
        if (Object.class == Promise.class) {
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$33.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$34.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$35.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$36.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$37(function52));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P3");
            KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P3");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P4");
            KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P4");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$38.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ViewDefinitionBuilder$AsyncFunction$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ViewDefinitionBuilder$AsyncFunction$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ViewDefinitionBuilder$AsyncFunction$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ViewDefinitionBuilder$AsyncFunction$42.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$43(function52));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncFunction(String str, Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> function6) {
        AsyncFunction asyncFunction;
        String str2 = str;
        Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> function62 = function6;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(function62, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P5");
        if (Object.class == Promise.class) {
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$45.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$46.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$47.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$48.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$49.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$50(function62));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P3");
            KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P3");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P4");
            KClass orCreateKotlinClass10 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P4");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P5");
            KClass orCreateKotlinClass11 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P5");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass6, false, ViewDefinitionBuilder$AsyncFunction$51.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ViewDefinitionBuilder$AsyncFunction$52.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ViewDefinitionBuilder$AsyncFunction$53.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ViewDefinitionBuilder$AsyncFunction$54.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ViewDefinitionBuilder$AsyncFunction$55.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ViewDefinitionBuilder$AsyncFunction$56.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$57(function62));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncFunction(String str, Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> function7) {
        AsyncFunction asyncFunction;
        String str2 = str;
        Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> function72 = function7;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(function72, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P6");
        if (Object.class == Promise.class) {
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$59.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$60.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$61.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$62.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$63.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ViewDefinitionBuilder$AsyncFunction$64.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$65(function72));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P3");
            KClass orCreateKotlinClass10 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P3");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P4");
            KClass orCreateKotlinClass11 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P4");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P5");
            KClass orCreateKotlinClass12 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P5");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P6");
            KClass orCreateKotlinClass13 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P6");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass7, false, ViewDefinitionBuilder$AsyncFunction$66.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ViewDefinitionBuilder$AsyncFunction$67.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ViewDefinitionBuilder$AsyncFunction$68.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ViewDefinitionBuilder$AsyncFunction$69.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ViewDefinitionBuilder$AsyncFunction$70.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, ViewDefinitionBuilder$AsyncFunction$71.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, ViewDefinitionBuilder$AsyncFunction$72.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$73(function72));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> AsyncFunction AsyncFunction(String str, Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> function8) {
        AsyncFunction asyncFunction;
        String str2;
        String str3 = str;
        Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> function82 = function8;
        Intrinsics.checkNotNullParameter(str3, "name");
        Intrinsics.checkNotNullParameter(function82, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P7");
        if (Object.class == Promise.class) {
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ViewDefinitionBuilder$AsyncFunction$75.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ViewDefinitionBuilder$AsyncFunction$76.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ViewDefinitionBuilder$AsyncFunction$77.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ViewDefinitionBuilder$AsyncFunction$78.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ViewDefinitionBuilder$AsyncFunction$79.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ViewDefinitionBuilder$AsyncFunction$80.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ViewDefinitionBuilder$AsyncFunction$81.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str3, anyTypeArr, new ViewDefinitionBuilder$AsyncFunction$82(function82));
            str2 = str3;
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass8 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass9 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P2");
            KClass orCreateKotlinClass10 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P2");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P3");
            KClass orCreateKotlinClass11 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P3");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P4");
            KClass orCreateKotlinClass12 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P4");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P5");
            KClass orCreateKotlinClass13 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P5");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P6");
            KClass orCreateKotlinClass14 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P6");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P7");
            KClass orCreateKotlinClass15 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P7");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass8, false, ViewDefinitionBuilder$AsyncFunction$83.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ViewDefinitionBuilder$AsyncFunction$84.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ViewDefinitionBuilder$AsyncFunction$85.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ViewDefinitionBuilder$AsyncFunction$86.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, ViewDefinitionBuilder$AsyncFunction$87.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, ViewDefinitionBuilder$AsyncFunction$88.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass14, false, ViewDefinitionBuilder$AsyncFunction$89.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass15, false, ViewDefinitionBuilder$AsyncFunction$90.INSTANCE))};
            Intrinsics.needClassReification();
            str2 = str;
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ViewDefinitionBuilder$AsyncFunction$91(function82));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final AsyncFunctionBuilder AsyncFunction(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        AsyncFunctionBuilder asyncFunctionBuilder = new AsyncFunctionBuilder(str);
        this.functionBuilders.put(str, asyncFunctionBuilder);
        return asyncFunctionBuilder;
    }

    private final Function2<Context, AppContext, View> createViewFactory() {
        return new ViewDefinitionBuilder$createViewFactory$1(this);
    }

    /* access modifiers changed from: private */
    public final View handleFailureDuringViewCreation(Context context, AppContext appContext, Throwable th) {
        CodedException codedException;
        SentryLogcatAdapter.e("ExpoModulesCore", "Couldn't create view of type " + this.viewClass, th);
        ErrorManagerModule errorManager = appContext.getErrorManager();
        if (errorManager != null) {
            if (th instanceof CodedException) {
                codedException = (CodedException) th;
            } else {
                codedException = new UnexpectedException(th);
            }
            errorManager.reportExceptionToLogBox(codedException);
        }
        if (ViewGroup.class.isAssignableFrom(JvmClassMappingKt.getJavaClass(this.viewClass))) {
            return new ErrorGroupView(context);
        }
        return new ErrorView(context);
    }

    private final KFunction<T> getPrimaryConstructor() {
        KFunction<T> primaryConstructor = KClasses.getPrimaryConstructor(this.viewClass);
        if (primaryConstructor != null) {
            return primaryConstructor;
        }
        return (KFunction) CollectionsKt.firstOrNull(this.viewClass.getConstructors());
    }
}
