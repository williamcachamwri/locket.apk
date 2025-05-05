package org.koin.core.module.dsl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000Â\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a9\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00010\u0007H\bø\u0001\u0000¢\u0006\u0002\u0010\b\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001*\u00020\u00022\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00010\nH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aU\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001*\u00020\u00022\u001e\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00010\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ac\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001*\u00020\u00022$\u0010\u0003\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00010\u0010H\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001aq\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001*\u00020\u00022*\u0010\u0003\u001a&\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00010\u0013H\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001*\u00020\u000220\u0010\u0003\u001a,\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00010\u0016H\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001*\u00020\u000226\u0010\u0003\u001a2\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00010\u0019H\bø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001*\u00020\u00022<\u0010\u0003\u001a8\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u00010\u001cH\bø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a©\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001*\u00020\u00022B\u0010\u0003\u001a>\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u00010\u001fH\bø\u0001\u0000¢\u0006\u0002\u0010 \u001a·\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001*\u00020\u00022H\u0010\u0003\u001aD\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\u00010\"H\bø\u0001\u0000¢\u0006\u0002\u0010#\u001aÅ\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001*\u00020\u00022N\u0010\u0003\u001aJ\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H\u00010%H\bø\u0001\u0000¢\u0006\u0002\u0010&\u001aÓ\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001*\u00020\u00022T\u0010\u0003\u001aP\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H\u00010(H\bø\u0001\u0000¢\u0006\u0002\u0010)\u001aá\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001*\u00020\u00022Z\u0010\u0003\u001aV\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H\u00010+H\bø\u0001\u0000¢\u0006\u0002\u0010,\u001aï\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001*\u00020\u00022`\u0010\u0003\u001a\\\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H\u00010.H\bø\u0001\u0000¢\u0006\u0002\u0010/\u001aý\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001*\u00020\u00022f\u0010\u0003\u001ab\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H\u000101H\bø\u0001\u0000¢\u0006\u0002\u00102\u001a\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001*\u00020\u00022l\u0010\u0003\u001ah\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H\u000104H\bø\u0001\u0000¢\u0006\u0002\u00105\u001a\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001*\u00020\u00022r\u0010\u0003\u001an\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H\u000107H\bø\u0001\u0000¢\u0006\u0002\u00108\u001a§\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001\"\u0006\b\u0012\u00109\u0018\u0001*\u00020\u00022x\u0010\u0003\u001at\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H\u00010:H\bø\u0001\u0000¢\u0006\u0002\u0010;\u001aµ\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001\"\u0006\b\u0012\u00109\u0018\u0001\"\u0006\b\u0013\u0010<\u0018\u0001*\u00020\u00022~\u0010\u0003\u001az\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H\u00010=H\bø\u0001\u0000¢\u0006\u0002\u0010>\u001aÅ\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001\"\u0006\b\u0012\u00109\u0018\u0001\"\u0006\b\u0013\u0010<\u0018\u0001\"\u0006\b\u0014\u0010?\u0018\u0001*\u00020\u00022\u0001\u0010\u0003\u001a\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H?\u0012\u0004\u0012\u0002H\u00010@H\bø\u0001\u0000¢\u0006\u0002\u0010A\u001aÓ\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001\"\u0006\b\u0012\u00109\u0018\u0001\"\u0006\b\u0013\u0010<\u0018\u0001\"\u0006\b\u0014\u0010?\u0018\u0001\"\u0006\b\u0015\u0010B\u0018\u0001*\u00020\u00022\u0001\u0010\u0003\u001a\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H?\u0012\u0004\u0012\u0002HB\u0012\u0004\u0012\u0002H\u00010CH\bø\u0001\u0000¢\u0006\u0002\u0010D\u001aá\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0006\u0018\u0001\"\u0006\b\u0002\u0010\t\u0018\u0001\"\u0006\b\u0003\u0010\f\u0018\u0001\"\u0006\b\u0004\u0010\u000f\u0018\u0001\"\u0006\b\u0005\u0010\u0012\u0018\u0001\"\u0006\b\u0006\u0010\u0015\u0018\u0001\"\u0006\b\u0007\u0010\u0018\u0018\u0001\"\u0006\b\b\u0010\u001b\u0018\u0001\"\u0006\b\t\u0010\u001e\u0018\u0001\"\u0006\b\n\u0010!\u0018\u0001\"\u0006\b\u000b\u0010$\u0018\u0001\"\u0006\b\f\u0010'\u0018\u0001\"\u0006\b\r\u0010*\u0018\u0001\"\u0006\b\u000e\u0010-\u0018\u0001\"\u0006\b\u000f\u00100\u0018\u0001\"\u0006\b\u0010\u00103\u0018\u0001\"\u0006\b\u0011\u00106\u0018\u0001\"\u0006\b\u0012\u00109\u0018\u0001\"\u0006\b\u0013\u0010<\u0018\u0001\"\u0006\b\u0014\u0010?\u0018\u0001\"\u0006\b\u0015\u0010B\u0018\u0001\"\u0006\b\u0016\u0010E\u0018\u0001*\u00020\u00022\u0001\u0010\u0003\u001a\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u0002H<\u0012\u0004\u0012\u0002H?\u0012\u0004\u0012\u0002HB\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u0002H\u00010FH\bø\u0001\u0000¢\u0006\u0002\u0010G\u0002\u0007\n\u0005\b20\u0001¨\u0006H"}, d2 = {"new", "R", "Lorg/koin/core/scope/Scope;", "constructor", "Lkotlin/Function0;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "T1", "Lkotlin/Function1;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "T2", "Lkotlin/Function2;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "T3", "Lkotlin/Function3;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "T4", "Lkotlin/Function4;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "T5", "Lkotlin/Function5;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "T6", "Lkotlin/Function6;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function6;)Ljava/lang/Object;", "T7", "Lkotlin/Function7;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function7;)Ljava/lang/Object;", "T8", "Lkotlin/Function8;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function8;)Ljava/lang/Object;", "T9", "Lkotlin/Function9;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function9;)Ljava/lang/Object;", "T10", "Lkotlin/Function10;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function10;)Ljava/lang/Object;", "T11", "Lkotlin/Function11;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function11;)Ljava/lang/Object;", "T12", "Lkotlin/Function12;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function12;)Ljava/lang/Object;", "T13", "Lkotlin/Function13;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function13;)Ljava/lang/Object;", "T14", "Lkotlin/Function14;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function14;)Ljava/lang/Object;", "T15", "Lkotlin/Function15;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function15;)Ljava/lang/Object;", "T16", "Lkotlin/Function16;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function16;)Ljava/lang/Object;", "T17", "Lkotlin/Function17;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function17;)Ljava/lang/Object;", "T18", "Lkotlin/Function18;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function18;)Ljava/lang/Object;", "T19", "Lkotlin/Function19;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function19;)Ljava/lang/Object;", "T20", "Lkotlin/Function20;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function20;)Ljava/lang/Object;", "T21", "Lkotlin/Function21;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function21;)Ljava/lang/Object;", "T22", "Lkotlin/Function22;", "(Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function22;)Ljava/lang/Object;", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: New.kt */
public final class NewKt {
    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R> R m2071new(Scope scope, Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function0, "constructor");
        return function0.invoke();
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1> R m2082new(Scope scope, Function1<? super T1, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function1, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        return function1.invoke(scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2> R m2086new(Scope scope, Function2<? super T1, ? super T2, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function2, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        return function2.invoke(obj, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3> R m2087new(Scope scope, Function3<? super T1, ? super T2, ? super T3, ? extends R> function3) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function3, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        return function3.invoke(obj, obj2, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4> R m2088new(Scope scope, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> function4) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function4, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        return function4.invoke(obj, obj2, obj3, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5> R m2089new(Scope scope, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> function5) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function5, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        return function5.invoke(obj, obj2, obj3, obj4, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6> R m2090new(Scope scope, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> function6) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function6, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        return function6.invoke(obj, obj2, obj3, obj4, obj5, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7> R m2091new(Scope scope, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> function7) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function7, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        return function7.invoke(obj, obj2, obj3, obj4, obj5, obj6, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8> R m2092new(Scope scope, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> function8) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function8, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        return function8.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9> R m2093new(Scope scope, Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> function9) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function9, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        return function9.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> R m2072new(Scope scope, Function10<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? extends R> function10) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(function10, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        return function10.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> R m2073new(Scope scope, Function11<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? extends R> function11) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Function11<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? extends R> function112 = function11;
        Intrinsics.checkNotNullParameter(function112, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        return function112.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, scope.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> R m2074new(Scope scope, Function12<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? extends R> function12) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Function12<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? extends R> function122 = function12;
        Intrinsics.checkNotNullParameter(function122, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        return function122.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> R m2075new(Scope scope, Function13<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? extends R> function13) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Function13<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? extends R> function132 = function13;
        Intrinsics.checkNotNullParameter(function132, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        return function132.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> R m2076new(Scope scope, Function14<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? extends R> function14) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Function14<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? extends R> function142 = function14;
        Intrinsics.checkNotNullParameter(function142, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T14");
        return function142.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> R m2077new(Scope scope, Function15<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? extends R> function15) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Function15<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? extends R> function152 = function15;
        Intrinsics.checkNotNullParameter(function152, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T15");
        return function152.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> R m2078new(Scope scope, Function16<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? extends R> function16) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function16, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function16<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? extends R> function162 = function16;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        return function162.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> R m2079new(Scope scope, Function17<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? extends R> function17) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function17, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function17<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? extends R> function172 = function17;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        return function172.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> R m2080new(Scope scope, Function18<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? extends R> function18) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function18, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function18<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? extends R> function182 = function18;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        Object obj18 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T18");
        return function182.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, obj18, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> R m2081new(Scope scope, Function19<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? extends R> function19) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function19, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function19<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? extends R> function192 = function19;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        Object obj18 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T18");
        Object obj19 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T19");
        return function192.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, obj18, obj19, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> R m2083new(Scope scope, Function20<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? extends R> function20) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function20, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function20<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? extends R> function202 = function20;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        Object obj18 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T18");
        Object obj19 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T19");
        Object obj20 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T20");
        return function202.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, obj18, obj19, obj20, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> R m2084new(Scope scope, Function21<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? extends R> function21) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function21, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function21<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? extends R> function212 = function21;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        Object obj18 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T18");
        Object obj19 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T19");
        Object obj20 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T20");
        Object obj21 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T21");
        return function212.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, obj18, obj19, obj20, obj21, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }

    /* renamed from: new  reason: not valid java name */
    public static final /* synthetic */ <R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> R m2085new(Scope scope, Function22<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? super T22, ? extends R> function22) {
        Scope scope2 = scope;
        Intrinsics.checkNotNullParameter(scope2, "<this>");
        Intrinsics.checkNotNullParameter(function22, "constructor");
        Intrinsics.reifiedOperationMarker(4, "T1");
        Object obj = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T2");
        Object obj2 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T3");
        Object obj3 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T4");
        Object obj4 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T5");
        Object obj5 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T6");
        Object obj6 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T7");
        Object obj7 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T8");
        Object obj8 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T9");
        Object obj9 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T10");
        Object obj10 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T11");
        Object obj11 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T12");
        Object obj12 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T13");
        Object obj13 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Function22<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? super T22, ? extends R> function222 = function22;
        Intrinsics.reifiedOperationMarker(4, "T14");
        Object obj14 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Object obj15 = obj;
        Intrinsics.reifiedOperationMarker(4, "T15");
        Object obj16 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T16");
        Object obj17 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T17");
        Object obj18 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T18");
        Object obj19 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T19");
        Object obj20 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T20");
        Object obj21 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T21");
        Object obj22 = scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
        Intrinsics.reifiedOperationMarker(4, "T22");
        return function222.invoke(obj15, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj16, obj17, obj18, obj19, obj20, obj21, obj22, scope2.get(Reflection.getOrCreateKotlinClass(Object.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null));
    }
}
