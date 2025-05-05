package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.service.credentials.BeginCreateCredentialResponse;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.provider.BeginCreateCredentialRequest;
import androidx.credentials.provider.BeginCreateCustomCredentialRequest;
import androidx.credentials.provider.BeginCreatePasswordCredentialRequest;
import androidx.credentials.provider.BeginCreatePublicKeyCredentialRequest;
import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.provider.CreateEntry;
import androidx.credentials.provider.RemoteEntry;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginCreateCredentialUtil.kt */
public final class BeginCreateCredentialUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final BeginCreateCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginCreateCredentialRequest beginCreateCredentialRequest) {
        return Companion.convertToJetpackRequest$credentials_release(beginCreateCredentialRequest);
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\bJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u001a\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0003¨\u0006\u0019"}, d2 = {"Landroidx/credentials/provider/utils/BeginCreateCredentialUtil$Companion;", "", "()V", "convertToFrameworkRequest", "Landroid/service/credentials/BeginCreateCredentialRequest;", "request", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "convertToFrameworkResponse", "Landroid/service/credentials/BeginCreateCredentialResponse;", "response", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "convertToJetpackRequest", "convertToJetpackRequest$credentials_release", "convertToJetpackResponse", "frameworkResponse", "populateCreateEntries", "", "frameworkBuilder", "Landroid/service/credentials/BeginCreateCredentialResponse$Builder;", "createEntries", "", "Landroidx/credentials/provider/CreateEntry;", "populateRemoteEntry", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginCreateCredentialUtil.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginCreateCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginCreateCredentialRequest beginCreateCredentialRequest) {
            CallingAppInfo callingAppInfo;
            CallingAppInfo callingAppInfo2;
            CallingAppInfo callingAppInfo3;
            Intrinsics.checkNotNullParameter(beginCreateCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            CallingAppInfo callingAppInfo4 = null;
            try {
                String type = beginCreateCredentialRequest.getType();
                int hashCode = type.hashCode();
                if (hashCode != -543568185) {
                    if (hashCode == -95037569) {
                        if (type.equals(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL)) {
                            BeginCreatePublicKeyCredentialRequest.Companion companion = BeginCreatePublicKeyCredentialRequest.Companion;
                            Bundle data = beginCreateCredentialRequest.getData();
                            Intrinsics.checkNotNullExpressionValue(data, "request.data");
                            android.service.credentials.CallingAppInfo callingAppInfo5 = beginCreateCredentialRequest.getCallingAppInfo();
                            if (callingAppInfo5 != null) {
                                String packageName = callingAppInfo5.getPackageName();
                                Intrinsics.checkNotNullExpressionValue(packageName, "it.packageName");
                                SigningInfo signingInfo = callingAppInfo5.getSigningInfo();
                                Intrinsics.checkNotNullExpressionValue(signingInfo, "it.signingInfo");
                                callingAppInfo3 = new CallingAppInfo(packageName, signingInfo, callingAppInfo5.getOrigin());
                            } else {
                                callingAppInfo3 = null;
                            }
                            return companion.createFrom$credentials_release(data, callingAppInfo3);
                        }
                    }
                } else if (type.equals(PasswordCredential.TYPE_PASSWORD_CREDENTIAL)) {
                    BeginCreatePasswordCredentialRequest.Companion companion2 = BeginCreatePasswordCredentialRequest.Companion;
                    Bundle data2 = beginCreateCredentialRequest.getData();
                    Intrinsics.checkNotNullExpressionValue(data2, "request.data");
                    android.service.credentials.CallingAppInfo callingAppInfo6 = beginCreateCredentialRequest.getCallingAppInfo();
                    if (callingAppInfo6 != null) {
                        String packageName2 = callingAppInfo6.getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName2, "it.packageName");
                        SigningInfo signingInfo2 = callingAppInfo6.getSigningInfo();
                        Intrinsics.checkNotNullExpressionValue(signingInfo2, "it.signingInfo");
                        callingAppInfo2 = new CallingAppInfo(packageName2, signingInfo2, callingAppInfo6.getOrigin());
                    } else {
                        callingAppInfo2 = null;
                    }
                    return companion2.createFrom$credentials_release(data2, callingAppInfo2);
                }
                String type2 = beginCreateCredentialRequest.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "request.type");
                Bundle data3 = beginCreateCredentialRequest.getData();
                Intrinsics.checkNotNullExpressionValue(data3, "request.data");
                android.service.credentials.CallingAppInfo callingAppInfo7 = beginCreateCredentialRequest.getCallingAppInfo();
                if (callingAppInfo7 != null) {
                    String packageName3 = callingAppInfo7.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName3, "it.packageName");
                    SigningInfo signingInfo3 = callingAppInfo7.getSigningInfo();
                    Intrinsics.checkNotNullExpressionValue(signingInfo3, "it.signingInfo");
                    callingAppInfo = new CallingAppInfo(packageName3, signingInfo3, callingAppInfo7.getOrigin());
                } else {
                    callingAppInfo = null;
                }
                return new BeginCreateCustomCredentialRequest(type2, data3, callingAppInfo);
            } catch (FrameworkClassParsingException unused) {
                String type3 = beginCreateCredentialRequest.getType();
                Intrinsics.checkNotNullExpressionValue(type3, "request.type");
                Bundle data4 = beginCreateCredentialRequest.getData();
                Intrinsics.checkNotNullExpressionValue(data4, "request.data");
                android.service.credentials.CallingAppInfo callingAppInfo8 = beginCreateCredentialRequest.getCallingAppInfo();
                if (callingAppInfo8 != null) {
                    String packageName4 = callingAppInfo8.getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName4, "it.packageName");
                    SigningInfo signingInfo4 = callingAppInfo8.getSigningInfo();
                    Intrinsics.checkNotNullExpressionValue(signingInfo4, "it.signingInfo");
                    callingAppInfo4 = new CallingAppInfo(packageName4, signingInfo4, callingAppInfo8.getOrigin());
                }
                return new BeginCreateCustomCredentialRequest(type3, data4, callingAppInfo4);
            }
        }

        public final BeginCreateCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginCreateCredentialResponse beginCreateCredentialResponse) {
            Intrinsics.checkNotNullParameter(beginCreateCredentialResponse, Response.TYPE);
            BeginCreateCredentialResponse.Builder builder = new BeginCreateCredentialResponse.Builder();
            populateCreateEntries(builder, beginCreateCredentialResponse.getCreateEntries());
            populateRemoteEntry(builder, beginCreateCredentialResponse.getRemoteEntry());
            BeginCreateCredentialResponse build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "frameworkBuilder.build()");
            return build;
        }

        private final void populateRemoteEntry(BeginCreateCredentialResponse.Builder builder, RemoteEntry remoteEntry) {
            if (remoteEntry != null) {
                builder.setRemoteCreateEntry(new android.service.credentials.RemoteEntry(RemoteEntry.Companion.toSlice(remoteEntry)));
            }
        }

        private final void populateCreateEntries(BeginCreateCredentialResponse.Builder builder, List<CreateEntry> list) {
            for (CreateEntry slice : list) {
                Slice slice2 = CreateEntry.Companion.toSlice(slice);
                if (slice2 != null) {
                    builder.addCreateEntry(new android.service.credentials.CreateEntry(slice2));
                }
            }
        }

        public final android.service.credentials.BeginCreateCredentialRequest convertToFrameworkRequest(BeginCreateCredentialRequest beginCreateCredentialRequest) {
            Intrinsics.checkNotNullParameter(beginCreateCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            return new android.service.credentials.BeginCreateCredentialRequest(beginCreateCredentialRequest.getType(), beginCreateCredentialRequest.getCandidateQueryData(), beginCreateCredentialRequest.getCallingAppInfo() != null ? new android.service.credentials.CallingAppInfo(beginCreateCredentialRequest.getCallingAppInfo().getPackageName(), beginCreateCredentialRequest.getCallingAppInfo().getSigningInfo(), beginCreateCredentialRequest.getCallingAppInfo().getOrigin()) : null);
        }

        public final androidx.credentials.provider.BeginCreateCredentialResponse convertToJetpackResponse(BeginCreateCredentialResponse beginCreateCredentialResponse) {
            RemoteEntry remoteEntry;
            Intrinsics.checkNotNullParameter(beginCreateCredentialResponse, "frameworkResponse");
            Object collect = beginCreateCredentialResponse.getCreateEntries().stream().map(new BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda0(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$1.INSTANCE)).filter(new BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda1(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$2.INSTANCE)).map(new BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda2(BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3.INSTANCE)).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(collect, "frameworkResponse.create…lect(Collectors.toList())");
            List list = (List) collect;
            android.service.credentials.RemoteEntry remoteCreateEntry = beginCreateCredentialResponse.getRemoteCreateEntry();
            if (remoteCreateEntry != null) {
                RemoteEntry.Companion companion = RemoteEntry.Companion;
                Slice slice = remoteCreateEntry.getSlice();
                Intrinsics.checkNotNullExpressionValue(slice, "it.slice");
                remoteEntry = companion.fromSlice(slice);
            } else {
                remoteEntry = null;
            }
            return new androidx.credentials.provider.BeginCreateCredentialResponse(list, remoteEntry);
        }

        /* access modifiers changed from: private */
        public static final CreateEntry convertToJetpackResponse$lambda$5(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (CreateEntry) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$6(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final CreateEntry convertToJetpackResponse$lambda$7(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (CreateEntry) function1.invoke(obj);
        }
    }
}
