package com.nicholasrutherford.chal.firebase.storage.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ApplicationComponent.class})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/storage/di/ChalStorageModule;", "", "()V", "bindChalFirebaseStorage", "Lcom/nicholasrutherford/chal/firebase/storage/ChalFirebaseStorage;", "chalFirebaseStorageImpl", "Lcom/nicholasrutherford/chal/firebase/storage/ChalFirebaseStorageImpl;", "storage_debug"})
@dagger.Module()
public abstract class ChalStorageModule {
    
    public ChalStorageModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorage bindChalFirebaseStorage(@org.jetbrains.annotations.NotNull()
    com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorageImpl chalFirebaseStorageImpl);
}