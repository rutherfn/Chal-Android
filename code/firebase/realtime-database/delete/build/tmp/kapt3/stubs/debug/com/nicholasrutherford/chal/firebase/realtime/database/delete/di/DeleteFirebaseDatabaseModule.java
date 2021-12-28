package com.nicholasrutherford.chal.firebase.realtime.database.delete.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.android.components.ApplicationComponent.class})
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/di/DeleteFirebaseDatabaseModule;", "", "()V", "bindDeleteFirebaseDatabase", "Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/DeleteFirebaseDatabase;", "deleteFirebaseDatabaseImpl", "Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/DeleteFirebaseDatabaseImpl;", "delete_debug"})
@dagger.Module()
public abstract class DeleteFirebaseDatabaseModule {
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.nicholasrutherford.chal.firebase.realtime.database.delete.DeleteFirebaseDatabase bindDeleteFirebaseDatabase(@org.jetbrains.annotations.NotNull()
    com.nicholasrutherford.chal.firebase.realtime.database.delete.DeleteFirebaseDatabaseImpl deleteFirebaseDatabaseImpl);
    
    public DeleteFirebaseDatabaseModule() {
        super();
    }
}