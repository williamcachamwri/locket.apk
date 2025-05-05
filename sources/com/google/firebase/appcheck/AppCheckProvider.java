package com.google.firebase.appcheck;

import com.google.android.gms.tasks.Task;

public interface AppCheckProvider {
    Task<AppCheckToken> getToken();
}
