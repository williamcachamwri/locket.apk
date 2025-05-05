package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.service.credentials.Action;
import android.service.credentials.BeginGetCredentialOption;
import android.service.credentials.BeginGetCredentialRequest;
import android.service.credentials.BeginGetCredentialResponse;
import androidx.credentials.provider.AuthenticationAction;
import androidx.credentials.provider.BeginGetCredentialOption;
import androidx.credentials.provider.BeginGetCredentialRequest;
import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.provider.CredentialEntry;
import androidx.credentials.provider.RemoteEntry;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialUtil.kt */
public final class BeginGetCredentialUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final BeginGetCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequest) {
        return Companion.convertToJetpackRequest$credentials_release(beginGetCredentialRequest);
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0010J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bJ\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u001e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0017H\u0002J\u001e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0017H\u0002J\u001a\u0010 \u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\"H\u0003¨\u0006#"}, d2 = {"Landroidx/credentials/provider/utils/BeginGetCredentialUtil$Companion;", "", "()V", "convertToFrameworkRequest", "Landroid/service/credentials/BeginGetCredentialRequest;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "convertToFrameworkResponse", "Landroid/service/credentials/BeginGetCredentialResponse;", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "convertToJetpackBeginOption", "Landroid/service/credentials/BeginGetCredentialOption;", "option", "Landroidx/credentials/provider/BeginGetCredentialOption;", "convertToJetpackRequest", "convertToJetpackRequest$credentials_release", "convertToJetpackResponse", "populateActionEntries", "", "builder", "Landroid/service/credentials/BeginGetCredentialResponse$Builder;", "actionEntries", "", "Landroidx/credentials/provider/Action;", "populateAuthenticationEntries", "frameworkBuilder", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "populateCredentialEntries", "credentialEntries", "Landroidx/credentials/provider/CredentialEntry;", "populateRemoteEntry", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialUtil.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginGetCredentialRequest convertToJetpackRequest$credentials_release(android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequest) {
            CallingAppInfo callingAppInfo;
            Intrinsics.checkNotNullParameter(beginGetCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            List arrayList = new ArrayList();
            List<BeginGetCredentialOption> beginGetCredentialOptions = beginGetCredentialRequest.getBeginGetCredentialOptions();
            Intrinsics.checkNotNullExpressionValue(beginGetCredentialOptions, "request.beginGetCredentialOptions");
            for (BeginGetCredentialOption beginGetCredentialOption : beginGetCredentialOptions) {
                BeginGetCredentialOption.Companion companion = androidx.credentials.provider.BeginGetCredentialOption.Companion;
                String id = beginGetCredentialOption.getId();
                Intrinsics.checkNotNullExpressionValue(id, "it.id");
                String type = beginGetCredentialOption.getType();
                Intrinsics.checkNotNullExpressionValue(type, "it.type");
                Bundle candidateQueryData = beginGetCredentialOption.getCandidateQueryData();
                Intrinsics.checkNotNullExpressionValue(candidateQueryData, "it.candidateQueryData");
                arrayList.add(companion.createFrom$credentials_release(id, type, candidateQueryData));
            }
            android.service.credentials.CallingAppInfo callingAppInfo2 = beginGetCredentialRequest.getCallingAppInfo();
            if (callingAppInfo2 != null) {
                String packageName = callingAppInfo2.getPackageName();
                Intrinsics.checkNotNullExpressionValue(packageName, "it.packageName");
                SigningInfo signingInfo = callingAppInfo2.getSigningInfo();
                Intrinsics.checkNotNullExpressionValue(signingInfo, "it.signingInfo");
                callingAppInfo = new CallingAppInfo(packageName, signingInfo, callingAppInfo2.getOrigin());
            } else {
                callingAppInfo = null;
            }
            return new BeginGetCredentialRequest(arrayList, callingAppInfo);
        }

        public final BeginGetCredentialResponse convertToFrameworkResponse(androidx.credentials.provider.BeginGetCredentialResponse beginGetCredentialResponse) {
            Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
            BeginGetCredentialResponse.Builder builder = new BeginGetCredentialResponse.Builder();
            populateCredentialEntries(builder, beginGetCredentialResponse.getCredentialEntries());
            populateActionEntries(builder, beginGetCredentialResponse.getActions());
            populateAuthenticationEntries(builder, beginGetCredentialResponse.getAuthenticationActions());
            populateRemoteEntry(builder, beginGetCredentialResponse.getRemoteEntry());
            BeginGetCredentialResponse build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "frameworkBuilder.build()");
            return build;
        }

        private final void populateRemoteEntry(BeginGetCredentialResponse.Builder builder, RemoteEntry remoteEntry) {
            if (remoteEntry != null) {
                builder.setRemoteCredentialEntry(new android.service.credentials.RemoteEntry(RemoteEntry.Companion.toSlice(remoteEntry)));
            }
        }

        private final void populateAuthenticationEntries(BeginGetCredentialResponse.Builder builder, List<AuthenticationAction> list) {
            for (AuthenticationAction slice : list) {
                builder.addAuthenticationAction(new Action(AuthenticationAction.Companion.toSlice(slice)));
            }
        }

        private final void populateActionEntries(BeginGetCredentialResponse.Builder builder, List<androidx.credentials.provider.Action> list) {
            for (androidx.credentials.provider.Action slice : list) {
                builder.addAction(new Action(androidx.credentials.provider.Action.Companion.toSlice(slice)));
            }
        }

        private final void populateCredentialEntries(BeginGetCredentialResponse.Builder builder, List<? extends CredentialEntry> list) {
            for (CredentialEntry credentialEntry : list) {
                Slice slice$credentials_release = CredentialEntry.Companion.toSlice$credentials_release(credentialEntry);
                if (slice$credentials_release != null) {
                    builder.addCredentialEntry(new android.service.credentials.CredentialEntry(new android.service.credentials.BeginGetCredentialOption(credentialEntry.getBeginGetCredentialOption().getId(), credentialEntry.getType(), Bundle.EMPTY), slice$credentials_release));
                }
            }
        }

        public final android.service.credentials.BeginGetCredentialRequest convertToFrameworkRequest(BeginGetCredentialRequest beginGetCredentialRequest) {
            Intrinsics.checkNotNullParameter(beginGetCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            BeginGetCredentialRequest.Builder builder = new BeginGetCredentialRequest.Builder();
            if (beginGetCredentialRequest.getCallingAppInfo() != null) {
                builder.setCallingAppInfo(new android.service.credentials.CallingAppInfo(beginGetCredentialRequest.getCallingAppInfo().getPackageName(), beginGetCredentialRequest.getCallingAppInfo().getSigningInfo(), beginGetCredentialRequest.getCallingAppInfo().getOrigin()));
            }
            android.service.credentials.BeginGetCredentialRequest build = builder.setBeginGetCredentialOptions((List) beginGetCredentialRequest.getBeginGetCredentialOptions().stream().map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda9(BeginGetCredentialUtil$Companion$convertToFrameworkRequest$1.INSTANCE)).collect(Collectors.toList())).build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.setBeginGetCrede…\n                .build()");
            return build;
        }

        /* access modifiers changed from: private */
        public static final android.service.credentials.BeginGetCredentialOption convertToFrameworkRequest$lambda$5(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (android.service.credentials.BeginGetCredentialOption) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public final android.service.credentials.BeginGetCredentialOption convertToJetpackBeginOption(androidx.credentials.provider.BeginGetCredentialOption beginGetCredentialOption) {
            return new android.service.credentials.BeginGetCredentialOption(beginGetCredentialOption.getId(), beginGetCredentialOption.getType(), beginGetCredentialOption.getCandidateQueryData());
        }

        public final androidx.credentials.provider.BeginGetCredentialResponse convertToJetpackResponse(BeginGetCredentialResponse beginGetCredentialResponse) {
            RemoteEntry remoteEntry;
            Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
            Object collect = beginGetCredentialResponse.getCredentialEntries().stream().map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda0(BeginGetCredentialUtil$Companion$convertToJetpackResponse$1.INSTANCE)).filter(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda1(BeginGetCredentialUtil$Companion$convertToJetpackResponse$2.INSTANCE)).map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda2(BeginGetCredentialUtil$Companion$convertToJetpackResponse$3.INSTANCE)).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(collect, "response.credentialEntri…lect(Collectors.toList())");
            List list = (List) collect;
            Object collect2 = beginGetCredentialResponse.getActions().stream().map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda3(BeginGetCredentialUtil$Companion$convertToJetpackResponse$4.INSTANCE)).filter(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda4(BeginGetCredentialUtil$Companion$convertToJetpackResponse$5.INSTANCE)).map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda5(BeginGetCredentialUtil$Companion$convertToJetpackResponse$6.INSTANCE)).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(collect2, "response.actions.stream(…lect(Collectors.toList())");
            List list2 = (List) collect2;
            Object collect3 = beginGetCredentialResponse.getAuthenticationActions().stream().map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda6(BeginGetCredentialUtil$Companion$convertToJetpackResponse$7.INSTANCE)).filter(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda7(BeginGetCredentialUtil$Companion$convertToJetpackResponse$8.INSTANCE)).map(new BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda8(BeginGetCredentialUtil$Companion$convertToJetpackResponse$9.INSTANCE)).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(collect3, "response.authenticationA…lect(Collectors.toList())");
            List list3 = (List) collect3;
            android.service.credentials.RemoteEntry remoteCredentialEntry = beginGetCredentialResponse.getRemoteCredentialEntry();
            if (remoteCredentialEntry != null) {
                RemoteEntry.Companion companion = RemoteEntry.Companion;
                Slice slice = remoteCredentialEntry.getSlice();
                Intrinsics.checkNotNullExpressionValue(slice, "it.slice");
                remoteEntry = companion.fromSlice(slice);
            } else {
                remoteEntry = null;
            }
            return new androidx.credentials.provider.BeginGetCredentialResponse(list, list2, list3, remoteEntry);
        }

        /* access modifiers changed from: private */
        public static final CredentialEntry convertToJetpackResponse$lambda$6(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (CredentialEntry) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$7(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final CredentialEntry convertToJetpackResponse$lambda$8(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (CredentialEntry) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final androidx.credentials.provider.Action convertToJetpackResponse$lambda$9(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (androidx.credentials.provider.Action) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$10(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final androidx.credentials.provider.Action convertToJetpackResponse$lambda$11(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (androidx.credentials.provider.Action) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final AuthenticationAction convertToJetpackResponse$lambda$12(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (AuthenticationAction) function1.invoke(obj);
        }

        /* access modifiers changed from: private */
        public static final boolean convertToJetpackResponse$lambda$13(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final AuthenticationAction convertToJetpackResponse$lambda$14(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (AuthenticationAction) function1.invoke(obj);
        }
    }
}
