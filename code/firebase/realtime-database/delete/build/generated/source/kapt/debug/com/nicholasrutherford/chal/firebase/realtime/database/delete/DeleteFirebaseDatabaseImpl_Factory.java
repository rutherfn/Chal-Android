package com.nicholasrutherford.chal.firebase.realtime.database.delete;

import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DeleteFirebaseDatabaseImpl_Factory implements Factory<DeleteFirebaseDatabaseImpl> {
  private final Provider<ChalFirebaseAuth> firebaseAuthProvider;

  public DeleteFirebaseDatabaseImpl_Factory(Provider<ChalFirebaseAuth> firebaseAuthProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public DeleteFirebaseDatabaseImpl get() {
    return newInstance(firebaseAuthProvider.get());
  }

  public static DeleteFirebaseDatabaseImpl_Factory create(
      Provider<ChalFirebaseAuth> firebaseAuthProvider) {
    return new DeleteFirebaseDatabaseImpl_Factory(firebaseAuthProvider);
  }

  public static DeleteFirebaseDatabaseImpl newInstance(ChalFirebaseAuth firebaseAuth) {
    return new DeleteFirebaseDatabaseImpl(firebaseAuth);
  }
}
