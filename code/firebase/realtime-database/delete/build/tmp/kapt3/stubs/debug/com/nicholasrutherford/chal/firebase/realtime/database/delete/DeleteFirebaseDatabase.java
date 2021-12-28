package com.nicholasrutherford.chal.firebase.realtime.database.delete;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J\u0016\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u00a8\u0006\u0011"}, d2 = {"Lcom/nicholasrutherford/chal/firebase/realtime/database/delete/DeleteFirebaseDatabase;", "", "databaseAllActiveChallengesReference", "Lcom/google/firebase/database/DatabaseReference;", "getDatabaseAllActiveChallengesReference", "()Lcom/google/firebase/database/DatabaseReference;", "databasePostsReference", "getDatabasePostsReference", "deleteActiveChallenge", "", "challengeTitle", "", "username", "deletePosts", "listOfSelectedPosts", "", "", "delete_debug"})
public abstract interface DeleteFirebaseDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.firebase.database.DatabaseReference getDatabasePostsReference();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.firebase.database.DatabaseReference getDatabaseAllActiveChallengesReference();
    
    public abstract void deletePosts(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> listOfSelectedPosts);
    
    public abstract void deleteActiveChallenge(@org.jetbrains.annotations.NotNull()
    java.lang.String challengeTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String username);
}