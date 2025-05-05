package com.google.firebase.appcheck.playintegrity.internal;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.internal.AppCheckTokenResponse;
import com.google.firebase.appcheck.internal.NetworkClient;
import com.google.firebase.appcheck.internal.RetryManager;
import java.util.concurrent.Executor;

public class PlayIntegrityAppCheckProvider implements AppCheckProvider {
    private static final String UTF_8 = "UTF-8";
    private final Executor blockingExecutor;
    private final IntegrityManager integrityManager;
    private final Executor liteExecutor;
    private final NetworkClient networkClient;
    private final String projectNumber;
    private final RetryManager retryManager;

    public PlayIntegrityAppCheckProvider(FirebaseApp firebaseApp, Executor executor, Executor executor2) {
        this(firebaseApp.getOptions().getGcmSenderId(), IntegrityManagerFactory.create(firebaseApp.getApplicationContext()), new NetworkClient(firebaseApp), executor, executor2, new RetryManager());
    }

    PlayIntegrityAppCheckProvider(String str, IntegrityManager integrityManager2, NetworkClient networkClient2, Executor executor, Executor executor2, RetryManager retryManager2) {
        this.projectNumber = str;
        this.integrityManager = integrityManager2;
        this.networkClient = networkClient2;
        this.liteExecutor = executor;
        this.blockingExecutor = executor2;
        this.retryManager = retryManager2;
    }

    public Task<AppCheckToken> getToken() {
        return getPlayIntegrityAttestation().onSuccessTask(this.liteExecutor, new PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda3(this)).onSuccessTask(this.liteExecutor, new PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$1$com-google-firebase-appcheck-playintegrity-internal-PlayIntegrityAppCheckProvider  reason: not valid java name */
    public /* synthetic */ Task m581lambda$getToken$1$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider(IntegrityTokenResponse integrityTokenResponse) throws Exception {
        return Tasks.call(this.blockingExecutor, new PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda2(this, new ExchangePlayIntegrityTokenRequest(integrityTokenResponse.token())));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$0$com-google-firebase-appcheck-playintegrity-internal-PlayIntegrityAppCheckProvider  reason: not valid java name */
    public /* synthetic */ AppCheckTokenResponse m580lambda$getToken$0$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider(ExchangePlayIntegrityTokenRequest exchangePlayIntegrityTokenRequest) throws Exception {
        return this.networkClient.exchangeAttestationForAppCheckToken(exchangePlayIntegrityTokenRequest.toJsonString().getBytes("UTF-8"), 3, this.retryManager);
    }

    private Task<IntegrityTokenResponse> getPlayIntegrityAttestation() {
        return Tasks.call(this.blockingExecutor, new PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda0(this, new GeneratePlayIntegrityChallengeRequest())).onSuccessTask(this.liteExecutor, new PlayIntegrityAppCheckProvider$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPlayIntegrityAttestation$3$com-google-firebase-appcheck-playintegrity-internal-PlayIntegrityAppCheckProvider  reason: not valid java name */
    public /* synthetic */ GeneratePlayIntegrityChallengeResponse m578lambda$getPlayIntegrityAttestation$3$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider(GeneratePlayIntegrityChallengeRequest generatePlayIntegrityChallengeRequest) throws Exception {
        return GeneratePlayIntegrityChallengeResponse.fromJsonString(this.networkClient.generatePlayIntegrityChallenge(generatePlayIntegrityChallengeRequest.toJsonString().getBytes("UTF-8"), this.retryManager));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPlayIntegrityAttestation$4$com-google-firebase-appcheck-playintegrity-internal-PlayIntegrityAppCheckProvider  reason: not valid java name */
    public /* synthetic */ Task m579lambda$getPlayIntegrityAttestation$4$comgooglefirebaseappcheckplayintegrityinternalPlayIntegrityAppCheckProvider(GeneratePlayIntegrityChallengeResponse generatePlayIntegrityChallengeResponse) throws Exception {
        return this.integrityManager.requestIntegrityToken(IntegrityTokenRequest.builder().setCloudProjectNumber(Long.parseLong(this.projectNumber)).setNonce(generatePlayIntegrityChallengeResponse.getChallenge()).build());
    }
}
