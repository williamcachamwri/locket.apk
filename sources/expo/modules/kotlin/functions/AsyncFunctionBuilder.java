package expo.modules.kotlin.functions;

import expo.modules.kotlin.Promise;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
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

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u000e\b\u0004\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\bø\u0001\u0000J#\u0010\u0010\u001a\u00020\u00112\u0010\b\u0004\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014H\bø\u0001\u0000¢\u0006\u0002\b\u0015JA\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u00012#\b\u0004\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\u00120\u0017H\bø\u0001\u0000J^\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u000128\b\u0004\u0010\u0013\u001a2\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H\u00120\u001bH\bø\u0001\u0000J{\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u00012M\b\u0004\u0010\u0013\u001aG\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\u00120\u001eH\bø\u0001\u0000J\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u00012b\b\u0004\u0010\u0013\u001a\\\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u00120!H\bø\u0001\u0000Jµ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u00012w\b\u0004\u0010\u0013\u001aq\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u00120$H\bø\u0001\u0000JÔ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u00012\u0001\b\u0004\u0010\u0013\u001a\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u00120'H\bø\u0001\u0000Jñ\u0001\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u00012¢\u0001\b\u0004\u0010\u0013\u001a\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u00120*H\bø\u0001\u0000J\u0002\u0010\u0010\u001a\u00020\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u0001\"\u0006\b\b\u0010,\u0018\u00012·\u0001\b\u0004\u0010\u0013\u001a°\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(.\u0012\u0004\u0012\u0002H\u00120-H\bø\u0001\u0000J6\u0010/\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u001e\b\u0004\u00100\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\b¢\u0006\u0002\u00102JS\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u000123\b\u0004\u00100\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001bH\b¢\u0006\u0002\u00104Jp\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u00012H\b\u0004\u00100\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001eH\b¢\u0006\u0002\u00105J\u0001\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u00012]\b\u0004\u00100\u001aW\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010!H\b¢\u0006\u0002\u00106Jª\u0001\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u00012r\b\u0004\u00100\u001al\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010$H\b¢\u0006\u0002\u00107JÉ\u0001\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u00012\u0001\b\u0004\u00100\u001a\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010'H\b¢\u0006\u0002\u00108Jæ\u0001\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u00012\u0001\b\u0004\u00100\u001a\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010*H\b¢\u0006\u0002\u00109J\u0002\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u00012²\u0001\b\u0004\u00100\u001a«\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010-H\b¢\u0006\u0002\u0010:J \u0002\u0010/\u001a\u000203\"\u0006\b\u0000\u0010\u0012\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001a\u0018\u0001\"\u0006\b\u0003\u0010\u001d\u0018\u0001\"\u0006\b\u0004\u0010 \u0018\u0001\"\u0006\b\u0005\u0010#\u0018\u0001\"\u0006\b\u0006\u0010&\u0018\u0001\"\u0006\b\u0007\u0010)\u0018\u0001\"\u0006\b\b\u0010,\u0018\u00012Ç\u0001\b\u0004\u00100\u001aÀ\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u0011H\u001d¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001201\u0012\u0006\u0012\u0004\u0018\u00010\u00010;H\b¢\u0006\u0002\u0010<J\r\u0010=\u001a\u00020\u0006H\u0000¢\u0006\u0002\b>R&\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\u000f\u0002\u0007\n\u0005\b20\u0001¨\u0006?"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "", "name", "", "(Ljava/lang/String;)V", "asyncFunctionComponent", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "getAsyncFunctionComponent$annotations", "()V", "getAsyncFunctionComponent", "()Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "setAsyncFunctionComponent", "(Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;)V", "getName$annotations", "getName", "()Ljava/lang/String;", "AsyncBody", "Lexpo/modules/kotlin/functions/AsyncFunction;", "R", "body", "Lkotlin/Function0;", "AsyncBodyWithoutArgs", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "SuspendBody", "block", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "(Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "Lkotlin/Function9;", "(Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "build", "build$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AsyncFunctionBuilder.kt */
public final class AsyncFunctionBuilder {
    private BaseAsyncFunctionComponent asyncFunctionComponent;
    private final String name;

    public static /* synthetic */ void getAsyncFunctionComponent$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public AsyncFunctionBuilder(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public final BaseAsyncFunctionComponent getAsyncFunctionComponent() {
        return this.asyncFunctionComponent;
    }

    public final void setAsyncFunctionComponent(BaseAsyncFunctionComponent baseAsyncFunctionComponent) {
        this.asyncFunctionComponent = baseAsyncFunctionComponent;
    }

    public final /* synthetic */ <R> BaseAsyncFunctionComponent SuspendBody(Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        BaseAsyncFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$SuspendBody$1(function1, (Continuation<? super AsyncFunctionBuilder$SuspendBody$1>) null));
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SuspendFunctionComponent SuspendBody(Function2<? super P0, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(function2, "block");
        String name2 = getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$3.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$4(function2, (Continuation<? super AsyncFunctionBuilder$SuspendBody$4>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SuspendFunctionComponent SuspendBody(Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        String name2 = getName();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P0");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P0");
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, "P1");
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, "P1");
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$6.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$7.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$8(function3, (Continuation<? super AsyncFunctionBuilder$SuspendBody$8>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent SuspendBody(Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(function4, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$10.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$11.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$12.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$13(function4, (Continuation<? super AsyncFunctionBuilder$SuspendBody$13>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent SuspendBody(Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> function5) {
        Intrinsics.checkNotNullParameter(function5, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$15.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$16.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$17.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$SuspendBody$18.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$19(function5, (Continuation<? super AsyncFunctionBuilder$SuspendBody$19>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent SuspendBody(Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> function6) {
        Intrinsics.checkNotNullParameter(function6, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$21.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$22.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$23.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$SuspendBody$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$SuspendBody$25.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$26(function6, (Continuation<? super AsyncFunctionBuilder$SuspendBody$26>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent SuspendBody(Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> function7) {
        Intrinsics.checkNotNullParameter(function7, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$28.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$29.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$30.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$SuspendBody$31.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$SuspendBody$32.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$SuspendBody$33.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$34(function7, (Continuation<? super AsyncFunctionBuilder$SuspendBody$34>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent SuspendBody(Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> function8) {
        Intrinsics.checkNotNullParameter(function8, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$36.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$37.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$38.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$SuspendBody$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$SuspendBody$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$SuspendBody$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$SuspendBody$42.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$43(function8, (Continuation<? super AsyncFunctionBuilder$SuspendBody$43>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent SuspendBody(Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> function9) {
        Intrinsics.checkNotNullParameter(function9, "block");
        String name2 = getName();
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
        AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$SuspendBody$45.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$SuspendBody$46.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$SuspendBody$47.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$SuspendBody$48.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$SuspendBody$49.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$SuspendBody$50.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$SuspendBody$51.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilder$SuspendBody$52.INSTANCE))};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$53(function9, (Continuation<? super AsyncFunctionBuilder$SuspendBody$53>) null));
        SuspendFunctionComponent suspendFunctionComponent2 = suspendFunctionComponent;
        setAsyncFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final AsyncFunction AsyncBodyWithoutArgs(Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$AsyncBody$1(function0));
        setAsyncFunctionComponent(asyncFunctionComponent2);
        return asyncFunctionComponent2;
    }

    public final /* synthetic */ <R> AsyncFunction AsyncBody(Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$AsyncBody$3(function0));
        setAsyncFunctionComponent(asyncFunctionComponent2);
        return asyncFunctionComponent2;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncBody(Function1<? super P0, ? extends R> function1) {
        AsyncFunction asyncFunction;
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P0");
        if (Object.class == Promise.class) {
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$AsyncBody$5(function1));
        } else {
            String name2 = getName();
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$6.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$7(function1));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncBody(Function2<? super P0, ? super P1, ? extends R> function2) {
        AsyncFunction asyncFunction;
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P1");
        if (Object.class == Promise.class) {
            String name2 = getName();
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$9.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$10(function2));
        } else {
            String name3 = getName();
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$11.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$12.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$13(function2));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncBody(Function3<? super P0, ? super P1, ? super P2, ? extends R> function3) {
        AsyncFunction asyncFunction;
        Function3<? super P0, ? super P1, ? super P2, ? extends R> function32 = function3;
        Intrinsics.checkNotNullParameter(function32, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P2");
        if (Object.class == Promise.class) {
            String name2 = getName();
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P0");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P0");
            Intrinsics.needClassReification();
            Intrinsics.reifiedOperationMarker(4, "P1");
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Object.class);
            Intrinsics.reifiedOperationMarker(3, "P1");
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$15.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$16.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$17(function32));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$18.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$19.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$20.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$21(function32));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncBody(Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> function4) {
        AsyncFunction asyncFunction;
        Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> function42 = function4;
        Intrinsics.checkNotNullParameter(function42, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P3");
        if (Object.class == Promise.class) {
            String name2 = getName();
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$23.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$24.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$25.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$26(function42));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$27.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$28.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$AsyncBody$29.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$AsyncBody$30.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$31(function42));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncBody(Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> function5) {
        AsyncFunction asyncFunction;
        Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> function52 = function5;
        Intrinsics.checkNotNullParameter(function52, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P4");
        if (Object.class == Promise.class) {
            String name2 = getName();
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$33.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$34.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$35.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$36.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$37(function52));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$38.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$AsyncBody$39.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$AsyncBody$40.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilder$AsyncBody$41.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, AsyncFunctionBuilder$AsyncBody$42.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$43(function52));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncBody(Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> function6) {
        AsyncFunction asyncFunction;
        Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> function62 = function6;
        Intrinsics.checkNotNullParameter(function62, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P5");
        if (Object.class == Promise.class) {
            String name2 = getName();
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$45.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$46.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$47.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$48.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$49.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$50(function62));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$AsyncBody$51.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$AsyncBody$52.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilder$AsyncBody$53.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, AsyncFunctionBuilder$AsyncBody$54.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, AsyncFunctionBuilder$AsyncBody$55.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, AsyncFunctionBuilder$AsyncBody$56.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$57(function62));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncBody(Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> function7) {
        AsyncFunction asyncFunction;
        Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> function72 = function7;
        Intrinsics.checkNotNullParameter(function72, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P6");
        if (Object.class == Promise.class) {
            String name2 = getName();
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$59.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$60.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$61.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$62.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$63.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$AsyncBody$64.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$65(function72));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$AsyncBody$66.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilder$AsyncBody$67.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, AsyncFunctionBuilder$AsyncBody$68.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, AsyncFunctionBuilder$AsyncBody$69.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, AsyncFunctionBuilder$AsyncBody$70.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, AsyncFunctionBuilder$AsyncBody$71.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, AsyncFunctionBuilder$AsyncBody$72.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$73(function72));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> AsyncFunction AsyncBody(Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> function8) {
        AsyncFunction asyncFunction;
        Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> function82 = function8;
        Intrinsics.checkNotNullParameter(function82, TtmlNode.TAG_BODY);
        Intrinsics.reifiedOperationMarker(4, "P7");
        if (Object.class == Promise.class) {
            String name2 = getName();
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
            AnyType[] anyTypeArr = {new AnyType(new LazyKType(orCreateKotlinClass, false, AsyncFunctionBuilder$AsyncBody$75.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass2, false, AsyncFunctionBuilder$AsyncBody$76.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass3, false, AsyncFunctionBuilder$AsyncBody$77.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass4, false, AsyncFunctionBuilder$AsyncBody$78.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass5, false, AsyncFunctionBuilder$AsyncBody$79.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass6, false, AsyncFunctionBuilder$AsyncBody$80.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass7, false, AsyncFunctionBuilder$AsyncBody$81.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionWithPromiseComponent(name2, anyTypeArr, new AsyncFunctionBuilder$AsyncBody$82(function82));
        } else {
            String name3 = getName();
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
            AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(orCreateKotlinClass8, false, AsyncFunctionBuilder$AsyncBody$83.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass9, false, AsyncFunctionBuilder$AsyncBody$84.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass10, false, AsyncFunctionBuilder$AsyncBody$85.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass11, false, AsyncFunctionBuilder$AsyncBody$86.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass12, false, AsyncFunctionBuilder$AsyncBody$87.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass13, false, AsyncFunctionBuilder$AsyncBody$88.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass14, false, AsyncFunctionBuilder$AsyncBody$89.INSTANCE)), new AnyType(new LazyKType(orCreateKotlinClass15, false, AsyncFunctionBuilder$AsyncBody$90.INSTANCE))};
            Intrinsics.needClassReification();
            asyncFunction = new AsyncFunctionComponent(name3, anyTypeArr2, new AsyncFunctionBuilder$AsyncBody$91(function82));
        }
        AsyncFunction asyncFunction2 = asyncFunction;
        setAsyncFunctionComponent(asyncFunction);
        return asyncFunction;
    }

    public final BaseAsyncFunctionComponent build$expo_modules_core_release() {
        BaseAsyncFunctionComponent baseAsyncFunctionComponent = this.asyncFunctionComponent;
        if (baseAsyncFunctionComponent != null) {
            return baseAsyncFunctionComponent;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
