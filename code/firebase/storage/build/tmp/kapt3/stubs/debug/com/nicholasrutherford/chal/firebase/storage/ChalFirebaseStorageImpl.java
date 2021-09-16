package com.nicholasrutherford.chal.firebase.storage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/storage/ChalFirebaseStorageImpl;", "Lcom/nicholasrutherford/chal/firebase/storage/ChalFirebaseStorage;", "()V", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "getFirebaseApp", "()Lcom/google/firebase/FirebaseApp;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "getStorage", "()Lcom/google/firebase/storage/FirebaseStorage;", "profileImagesReference", "Lcom/google/firebase/storage/StorageReference;", "fileName", "", "profileUserImagesDirectory", "storage_debug"})
public final class ChalFirebaseStorageImpl implements com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorage {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.storage.FirebaseStorage storage = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.FirebaseApp firebaseApp = null;
    
    @javax.inject.Inject()
    public ChalFirebaseStorageImpl() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.storage.FirebaseStorage getStorage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.FirebaseApp getFirebaseApp() {
        return null;
    }
    
    private final java.lang.String profileUserImagesDirectory(java.lang.String fileName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.storage.StorageReference profileImagesReference(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return null;
    }
}