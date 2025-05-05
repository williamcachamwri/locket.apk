package io.grpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ChoiceChannelCredentials extends ChannelCredentials {
    private final List<ChannelCredentials> creds;

    public static ChannelCredentials create(ChannelCredentials... channelCredentialsArr) {
        if (channelCredentialsArr.length != 0) {
            for (ChannelCredentials channelCredentials : channelCredentialsArr) {
                channelCredentials.getClass();
            }
            return new ChoiceChannelCredentials(Collections.unmodifiableList(new ArrayList(Arrays.asList(channelCredentialsArr))));
        }
        throw new IllegalArgumentException("At least one credential is required");
    }

    private ChoiceChannelCredentials(List<ChannelCredentials> list) {
        this.creds = list;
    }

    public List<ChannelCredentials> getCredentialsList() {
        return this.creds;
    }

    public ChannelCredentials withoutBearerTokens() {
        ArrayList arrayList = new ArrayList();
        for (ChannelCredentials withoutBearerTokens : this.creds) {
            arrayList.add(withoutBearerTokens.withoutBearerTokens());
        }
        return new ChoiceChannelCredentials(Collections.unmodifiableList(arrayList));
    }
}
