package com.nicholasrutherford.chal.firebase.storage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/storage/ChalFirebaseStorage;", "", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "getFirebaseApp", "()Lcom/google/firebase/FirebaseApp;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "getStorage", "()Lcom/google/firebase/storage/FirebaseStorage;", "profileImagesReference", "Lcom/google/firebase/storage/StorageReference;", "fileName", "", "storage_debug"})
public abstract interface ChalFirebaseStorage {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.firebase.storage.FirebaseStorage getStorage();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.firebase.FirebaseApp getFirebaseApp();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.firebase.storage.StorageReference profileImagesReference(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName);
}