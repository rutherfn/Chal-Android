package com.nicholasrutherford.chal.firebase.realtime.database.delete;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0016\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/DeleteFirebaseDatabaseImpl;", "Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/DeleteFirebaseDatabase;", "firebaseAuth", "Lcom/nicholasrutherford/chal/firebase/auth/ChalFirebaseAuth;", "(Lcom/nicholasrutherford/chal/firebase/auth/ChalFirebaseAuth;)V", "databaseAllActiveChallengesReference", "Lcom/google/firebase/database/DatabaseReference;", "getDatabaseAllActiveChallengesReference", "()Lcom/google/firebase/database/DatabaseReference;", "databasePostsReference", "getDatabasePostsReference", "deleteActiveChallenge", "", "challengeTitle", "", "username", "deletePosts", "listOfSelectedPosts", "", "", "delete_debug"})
public final class DeleteFirebaseDatabaseImpl implements com.nicholasrutherford.chal.firebase.realtime.database.delete.DeleteFirebaseDatabase {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference databaseAllActiveChallengesReference = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference databasePostsReference = null;
    private final com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth firebaseAuth = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.database.DatabaseReference getDatabaseAllActiveChallengesReference() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.firebase.database.DatabaseReference getDatabasePostsReference() {
        return null;
    }
    
    @java.lang.Override()
    public void deletePosts(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> listOfSelectedPosts) {
    }
    
    @java.lang.Override()
    public void deleteActiveChallenge(@org.jetbrains.annotations.NotNull()
    java.lang.String challengeTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String username) {
    }
    
    @javax.inject.Inject()
    public DeleteFirebaseDatabaseImpl(@org.jetbrains.annotations.NotNull()
    com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth firebaseAuth) {
        super();
    }
}