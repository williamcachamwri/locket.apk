package expo.modules.kotlin.objects;

import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.FunctionBuilder;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
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

@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0005J,\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0\u000fH\bø\u0001\u0000J+\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0004\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH\bø\u0001\u0000¢\u0006\u0002\b+JI\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u00012\u0006\u0010(\u001a\u00020\u00052#\b\u0004\u0010*\u001a\u001d\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002H)0-H\bø\u0001\u0000Jf\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u00012\u0006\u0010(\u001a\u00020\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H)01H\bø\u0001\u0000J\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u00012\u0006\u0010(\u001a\u00020\u00052M\b\u0004\u0010*\u001aG\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H)04H\bø\u0001\u0000J \u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u00012\u0006\u0010(\u001a\u00020\u00052b\b\u0004\u0010*\u001a\\\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H)07H\bø\u0001\u0000J½\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u00012\u0006\u0010(\u001a\u00020\u00052w\b\u0004\u0010*\u001aq\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002H)0:H\bø\u0001\u0000JÜ\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u0001\b\u0004\u0010*\u001a\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H)0=H\bø\u0001\u0000Jù\u0001\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u00012\u0006\u0010(\u001a\u00020\u00052¢\u0001\b\u0004\u0010*\u001a\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H)0@H\bø\u0001\u0000J\u0002\u0010'\u001a\u00020\b\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u0001\"\u0006\b\b\u0010B\u0018\u00012\u0006\u0010(\u001a\u00020\u00052·\u0001\b\u0004\u0010*\u001a°\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0013\u0012\u0011HB¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(D\u0012\u0004\u0012\u0002H)0CH\bø\u0001\u0000J\"\u0010E\u001a\u00020F2\u001a\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00100\u000fJ;\u0010E\u001a\u00020F2.\u0010G\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010I0H\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010I¢\u0006\u0002\u0010JJ\u001f\u0010K\u001a\u00020F\"\u0014\b\u0000\u0010L\u0018\u0001*\u00020M*\b\u0012\u0004\u0012\u0002HL0NH\bJ\u001d\u0010K\u001a\u00020F2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00050HH\u0007¢\u0006\u0004\bP\u0010QJ\u001f\u0010K\u001a\u00020F2\u0012\u0010O\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050H\"\u00020\u0005¢\u0006\u0002\u0010QJ\u000e\u0010R\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0005J,\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002H)0\u000fH\bø\u0001\u0000J+\u0010R\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00052\u0010\b\u0004\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH\bø\u0001\u0000¢\u0006\u0002\bSJI\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u00012\u0006\u0010(\u001a\u00020\u00052#\b\u0004\u0010*\u001a\u001d\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002H)0-H\bø\u0001\u0000Jf\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u00012\u0006\u0010(\u001a\u00020\u000528\b\u0004\u0010*\u001a2\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0004\u0012\u0002H)01H\bø\u0001\u0000J\u0001\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u00012\u0006\u0010(\u001a\u00020\u00052M\b\u0004\u0010*\u001aG\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0004\u0012\u0002H)04H\bø\u0001\u0000J \u0001\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u00012\u0006\u0010(\u001a\u00020\u00052b\b\u0004\u0010*\u001a\\\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0004\u0012\u0002H)07H\bø\u0001\u0000J½\u0001\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u00012\u0006\u0010(\u001a\u00020\u00052w\b\u0004\u0010*\u001aq\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0004\u0012\u0002H)0:H\bø\u0001\u0000JÜ\u0001\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u00012\u0006\u0010(\u001a\u00020\u00052\u0001\b\u0004\u0010*\u001a\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H)0=H\bø\u0001\u0000Jù\u0001\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u00012\u0006\u0010(\u001a\u00020\u00052¢\u0001\b\u0004\u0010*\u001a\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H)0@H\bø\u0001\u0000J\u0002\u0010R\u001a\u00020#\"\u0006\b\u0000\u0010)\u0018\u0001\"\u0006\b\u0001\u0010,\u0018\u0001\"\u0006\b\u0002\u00100\u0018\u0001\"\u0006\b\u0003\u00103\u0018\u0001\"\u0006\b\u0004\u00106\u0018\u0001\"\u0006\b\u0005\u00109\u0018\u0001\"\u0006\b\u0006\u0010<\u0018\u0001\"\u0006\b\u0007\u0010?\u0018\u0001\"\u0006\b\b\u0010B\u0018\u00012\u0006\u0010(\u001a\u00020\u00052·\u0001\b\u0004\u0010*\u001a°\u0001\u0012\u0013\u0012\u0011H,¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0013\u0012\u0011H0¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H3¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H6¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(8\u0012\u0013\u0012\u0011H9¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(;\u0012\u0013\u0012\u0011H<¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0013\u0012\u0011H?¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(A\u0012\u0013\u0012\u0011HB¢\u0006\f\b.\u0012\b\b(\u0012\u0004\b\b(D\u0012\u0004\u0012\u0002H)0CH\bø\u0001\u0000J\u001c\u0010T\u001a\u00020F2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020F0\u000fH\bø\u0001\u0000J\u001c\u0010U\u001a\u00020F2\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u00020F0\u000fH\bø\u0001\u0000J\u0010\u0010V\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0005H\u0016J*\u0010V\u001a\u00020\u0019\"\u0004\b\u0000\u0010L2\u0006\u0010(\u001a\u00020\u00052\u000e\b\u0004\u0010*\u001a\b\u0012\u0004\u0012\u0002HL0\u000fH\bø\u0001\u0000J\u0006\u0010W\u001a\u00020XJ\u0010\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u0005H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u000e¢\u0006\u0002\n\u0000R0\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00048\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00100\u000fX\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R0\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u00048\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR0\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001e0\u00048\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\u000b\"\u0004\b!\u0010\rR0\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020#0\u00048\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010\u0002\u001a\u0004\b%\u0010\u000b\"\u0004\b&\u0010\r\u0002\u0007\n\u0005\b20\u0001¨\u0006\\"}, d2 = {"Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "", "()V", "asyncFunctionBuilders", "", "", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "asyncFunctions", "Lexpo/modules/kotlin/functions/AsyncFunction;", "getAsyncFunctions$annotations", "getAsyncFunctions", "()Ljava/util/Map;", "setAsyncFunctions", "(Ljava/util/Map;)V", "constantsProvider", "Lkotlin/Function0;", "", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "getEventsDefinition$annotations", "getEventsDefinition", "()Lexpo/modules/kotlin/events/EventsDefinition;", "setEventsDefinition", "(Lexpo/modules/kotlin/events/EventsDefinition;)V", "properties", "Lexpo/modules/kotlin/objects/PropertyComponentBuilder;", "getProperties$annotations", "getProperties", "setProperties", "syncFunctionBuilder", "Lexpo/modules/kotlin/functions/FunctionBuilder;", "getSyncFunctionBuilder$annotations", "getSyncFunctionBuilder", "setSyncFunctionBuilder", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getSyncFunctions$annotations", "getSyncFunctions", "setSyncFunctions", "AsyncFunction", "name", "R", "body", "AsyncFunctionWithoutArgs", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Constants", "", "constants", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "Events", "T", "Lexpo/modules/kotlin/types/Enumerable;", "", "events", "EventsWithArray", "([Ljava/lang/String;)V", "Function", "FunctionWithoutArgs", "OnStartObserving", "OnStopObserving", "Property", "buildObject", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "containsFunction", "", "functionName", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public class ObjectDefinitionBuilder {
    private Map<String, AsyncFunctionBuilder> asyncFunctionBuilders = new LinkedHashMap();
    private Map<String, AsyncFunction> asyncFunctions = new LinkedHashMap();
    private Function0<? extends Map<String, ? extends Object>> constantsProvider = ObjectDefinitionBuilder$constantsProvider$1.INSTANCE;
    private EventsDefinition eventsDefinition;
    private Map<String, PropertyComponentBuilder> properties = new LinkedHashMap();
    private Map<String, FunctionBuilder> syncFunctionBuilder = new LinkedHashMap();
    private Map<String, SyncFunctionComponent> syncFunctions = new LinkedHashMap();

    public static /* synthetic */ void getAsyncFunctions$annotations() {
    }

    public static /* synthetic */ void getEventsDefinition$annotations() {
    }

    public static /* synthetic */ void getProperties$annotations() {
    }

    public static /* synthetic */ void getSyncFunctionBuilder$annotations() {
    }

    public static /* synthetic */ void getSyncFunctions$annotations() {
    }

    public final EventsDefinition getEventsDefinition() {
        return this.eventsDefinition;
    }

    public final void setEventsDefinition(EventsDefinition eventsDefinition2) {
        this.eventsDefinition = eventsDefinition2;
    }

    public final Map<String, SyncFunctionComponent> getSyncFunctions() {
        return this.syncFunctions;
    }

    public final void setSyncFunctions(Map<String, SyncFunctionComponent> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncFunctions = map;
    }

    public final Map<String, FunctionBuilder> getSyncFunctionBuilder() {
        return this.syncFunctionBuilder;
    }

    public final void setSyncFunctionBuilder(Map<String, FunctionBuilder> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncFunctionBuilder = map;
    }

    public final Map<String, AsyncFunction> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final void setAsyncFunctions(Map<String, AsyncFunction> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.asyncFunctions = map;
    }

    public final Map<String, PropertyComponentBuilder> getProperties() {
        return this.properties;
    }

    public final void setProperties(Map<String, PropertyComponentBuilder> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.properties = map;
    }

    public final ObjectDefinitionData buildObject() {
        if (this.eventsDefinition != null) {
            if (!containsFunction("addListener")) {
                getSyncFunctions().put("addListener", new SyncFunctionComponent("addListener", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ObjectDefinitionBuilder$buildObject$lambda$2$$inlined$Function$1.INSTANCE))}, new ObjectDefinitionBuilder$buildObject$lambda$2$$inlined$Function$2()));
            }
            if (!containsFunction("removeListeners")) {
                getSyncFunctions().put("removeListeners", new SyncFunctionComponent("removeListeners", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, ObjectDefinitionBuilder$buildObject$lambda$2$$inlined$Function$3.INSTANCE))}, new ObjectDefinitionBuilder$buildObject$lambda$2$$inlined$Function$4()));
            }
        }
        Function0<? extends Map<String, ? extends Object>> function0 = this.constantsProvider;
        Map<String, SyncFunctionComponent> map = this.syncFunctions;
        Map<String, FunctionBuilder> map2 = this.syncFunctionBuilder;
        Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
        for (Map.Entry entry : map2.entrySet()) {
            linkedHashMap.put(entry.getKey(), ((FunctionBuilder) entry.getValue()).build$expo_modules_core_release());
        }
        Map<String, SyncFunctionComponent> plus = MapsKt.plus(map, (Map<String, SyncFunctionComponent>) linkedHashMap);
        Map<String, AsyncFunction> map3 = this.asyncFunctions;
        Map<String, AsyncFunctionBuilder> map4 = this.asyncFunctionBuilders;
        Map linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map4.size()));
        for (Map.Entry entry2 : map4.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), ((AsyncFunctionBuilder) entry2.getValue()).build$expo_modules_core_release());
        }
        Map<String, AsyncFunction> plus2 = MapsKt.plus(map3, (Map<String, AsyncFunction>) linkedHashMap2);
        EventsDefinition eventsDefinition2 = this.eventsDefinition;
        Map<String, PropertyComponentBuilder> map5 = this.properties;
        Map linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(map5.size()));
        for (Map.Entry entry3 : map5.entrySet()) {
            linkedHashMap3.put(entry3.getKey(), ((PropertyComponentBuilder) entry3.getValue()).build());
        }
        return new ObjectDefinitionData(function0, plus, plus2, eventsDefinition2, linkedHashMap3);
    }

    private final boolean containsFunction(String str) {
        return this.syncFunctions.containsKey(str) || this.asyncFunctions.containsKey(str) || this.asyncFunctionBuilders.containsKey(str);
    }

    public final void Constants(Function0<? extends Map<String, ? extends Object>> function0) {
        Intrinsics.checkNotNullParameter(function0, "constantsProvider");
        this.constantsProvider = function0;
    }

    public final void Constants(Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "constants");
        this.constantsProvider = new ObjectDefinitionBuilder$Constants$1(pairArr);
    }

    public final FunctionBuilder Function(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        FunctionBuilder functionBuilder = new FunctionBuilder(str);
        this.syncFunctionBuilder.put(str, functionBuilder);
        return functionBuilder;
    }

    public final SyncFunctionComponent FunctionWithoutArgs(String str, Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, new AnyType[0], new ObjectDefinitionBuilder$Function$2(function0));
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R> SyncFunctionComponent Function(String str, Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, new AnyType[0], new ObjectDefinitionBuilder$Function$4(function0));
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SyncFunctionComponent Function(String str, Function1<? super P0, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$6.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$7(function1));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SyncFunctionComponent Function(String str, Function2<? super P0, ? super P1, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$9.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$10.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$11(function2));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SyncFunctionComponent Function(String str, Function3<? super P0, ? super P1, ? super P2, ? extends R> function3) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function3, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$13.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$14.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$15.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$16(function3));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SyncFunctionComponent Function(String str, Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> function4) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function4, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$18.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$19.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$20.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$Function$21.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$22(function4));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SyncFunctionComponent Function(String str, Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> function5) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function5, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$25.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$26.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$Function$27.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$Function$28.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$29(function5));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SyncFunctionComponent Function(String str, Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> function6) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function6, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$31.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$32.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$33.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$Function$34.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$Function$35.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$Function$36.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$37(function6));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SyncFunctionComponent Function(String str, Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> function7) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function7, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$Function$42.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$Function$43.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$Function$44.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$Function$45.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$46(function7));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SyncFunctionComponent Function(String str, Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> function8) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function8, TtmlNode.TAG_BODY);
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$Function$48.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$Function$49.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$Function$50.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$Function$51.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$Function$52.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$Function$53.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$Function$54.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ObjectDefinitionBuilder$Function$55.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$Function$56(function8));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        getSyncFunctions().put(str, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final AsyncFunction AsyncFunctionWithoutArgs(String str, Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(str, new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(function0));
        getAsyncFunctions().put(str, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R> AsyncFunction AsyncFunction(String str, Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(str, new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$3(function0));
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
            asyncFunction = new AsyncFunctionWithPromiseComponent(str, new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$5(function1));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$6.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$7(function1));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$9.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$10(function2));
        } else {
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$11.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$12.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$13(function2));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$16.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$17(function32));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$18.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$19.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$20.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$21(function32));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$23.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$25.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$26(function42));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$27.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$28.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$AsyncFunction$29.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$AsyncFunction$30.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$31(function42));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$33.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$34.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$35.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$36.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$37(function52));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$38.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$AsyncFunction$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$AsyncFunction$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ObjectDefinitionBuilder$AsyncFunction$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ObjectDefinitionBuilder$AsyncFunction$42.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$43(function52));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$45.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$46.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$47.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$48.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$49.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$50(function62));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$AsyncFunction$51.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$AsyncFunction$52.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ObjectDefinitionBuilder$AsyncFunction$53.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ObjectDefinitionBuilder$AsyncFunction$54.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ObjectDefinitionBuilder$AsyncFunction$55.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ObjectDefinitionBuilder$AsyncFunction$56.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$57(function62));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$59.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$60.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$61.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$62.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$63.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$AsyncFunction$64.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str2, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$65(function72));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$AsyncFunction$66.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, ObjectDefinitionBuilder$AsyncFunction$67.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ObjectDefinitionBuilder$AsyncFunction$68.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ObjectDefinitionBuilder$AsyncFunction$69.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ObjectDefinitionBuilder$AsyncFunction$70.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, ObjectDefinitionBuilder$AsyncFunction$71.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, ObjectDefinitionBuilder$AsyncFunction$72.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$73(function72));
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, ObjectDefinitionBuilder$AsyncFunction$75.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, ObjectDefinitionBuilder$AsyncFunction$76.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, ObjectDefinitionBuilder$AsyncFunction$77.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, ObjectDefinitionBuilder$AsyncFunction$78.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, ObjectDefinitionBuilder$AsyncFunction$79.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, ObjectDefinitionBuilder$AsyncFunction$80.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, ObjectDefinitionBuilder$AsyncFunction$81.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(str3, anyTypeArr, new ObjectDefinitionBuilder$AsyncFunction$82(function82));
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass8, false, ObjectDefinitionBuilder$AsyncFunction$83.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, ObjectDefinitionBuilder$AsyncFunction$84.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, ObjectDefinitionBuilder$AsyncFunction$85.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, ObjectDefinitionBuilder$AsyncFunction$86.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, ObjectDefinitionBuilder$AsyncFunction$87.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, ObjectDefinitionBuilder$AsyncFunction$88.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass14, false, ObjectDefinitionBuilder$AsyncFunction$89.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass15, false, ObjectDefinitionBuilder$AsyncFunction$90.INSTANCE))};
            Intrinsics.needClassReification();
            str2 = str;
            asyncFunction = new AsyncFunctionComponent(str2, anyTypeArr2, new ObjectDefinitionBuilder$AsyncFunction$91(function82));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        getAsyncFunctions().put(str2, asyncFunction);
        return asyncFunction;
    }

    public final AsyncFunctionBuilder AsyncFunction(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        AsyncFunctionBuilder asyncFunctionBuilder = new AsyncFunctionBuilder(str);
        this.asyncFunctionBuilders.put(str, asyncFunctionBuilder);
        return asyncFunctionBuilder;
    }

    public final void Events(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "events");
        this.eventsDefinition = new EventsDefinition(strArr);
    }

    public final void EventsWithArray(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "events");
        this.eventsDefinition = new EventsDefinition(strArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        if (r4.size() == 1) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ <T extends java.lang.Enum<T> & expo.modules.kotlin.types.Enumerable> void Events() {
        /*
            r7 = this;
            r0 = 4
            java.lang.String r1 = "T"
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r0, r1)
            java.lang.Class<java.lang.Enum> r2 = java.lang.Enum.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            kotlin.reflect.KFunction r2 = kotlin.reflect.full.KClasses.getPrimaryConstructor(r2)
            r3 = 0
            if (r2 == 0) goto L_0x0021
            java.util.List r4 = r2.getParameters()
            if (r4 == 0) goto L_0x0021
            int r4 = r4.size()
            r5 = 1
            if (r4 != r5) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r5 = r3
        L_0x0022:
            r4 = 5
            if (r5 == 0) goto L_0x00b1
            java.util.List r2 = r2.getParameters()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r2)
            kotlin.reflect.KParameter r2 = (kotlin.reflect.KParameter) r2
            java.lang.String r2 = r2.getName()
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r0, r1)
            java.lang.Class<java.lang.Enum> r0 = java.lang.Enum.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getDeclaredMemberProperties(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0046:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x005e
            java.lang.Object r5 = r0.next()
            r6 = r5
            kotlin.reflect.KProperty1 r6 = (kotlin.reflect.KProperty1) r6
            java.lang.String r6 = r6.getName()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r2)
            if (r6 == 0) goto L_0x0046
            goto L_0x005f
        L_0x005e:
            r5 = 0
        L_0x005f:
            kotlin.reflect.KProperty1 r5 = (kotlin.reflect.KProperty1) r5
            if (r5 == 0) goto L_0x0092
            kotlin.reflect.KType r0 = r5.getReturnType()
            kotlin.reflect.KClassifier r0 = r0.getClassifier()
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x0086
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r4, r1)
            java.lang.Enum[] r0 = new java.lang.Enum[r3]
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r3)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r0 = (java.util.List) r0
            goto L_0x00bf
        L_0x0086:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The enum parameter has to be a string."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0092:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot find a property for "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " parameter"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x00b1:
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r4, r1)
            java.lang.Enum[] r0 = new java.lang.Enum[r3]
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r3)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r0 = (java.util.List) r0
        L_0x00bf:
            expo.modules.kotlin.events.EventsDefinition r1 = new expo.modules.kotlin.events.EventsDefinition
            java.util.Collection r0 = (java.util.Collection) r0
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.String[] r2 = new java.lang.String[r3]
            java.lang.Object[] r0 = r0.toArray(r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r1.<init>(r0)
            r7.setEventsDefinition(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.objects.ObjectDefinitionBuilder.Events():void");
    }

    public PropertyComponentBuilder Property(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(str);
        this.properties.put(str, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final <T> PropertyComponentBuilder Property(String str, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(str);
        propertyComponentBuilder.setGetter(new SyncFunctionComponent("get", new AnyType[0], new PropertyComponentBuilder$get$1$1(function0)));
        getProperties().put(str, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final void OnStartObserving(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("startObserving", new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(function0));
        getAsyncFunctions().put("startObserving", asyncFunctionComponent);
        AsyncFunction asyncFunction = asyncFunctionComponent;
    }

    public final void OnStopObserving(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("stopObserving", new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(function0));
        getAsyncFunctions().put("stopObserving", asyncFunctionComponent);
        AsyncFunction asyncFunction = asyncFunctionComponent;
    }
}
