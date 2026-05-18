package com.arogyasahaya.local.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DatabaseModule_ProvideArogyaDatabaseFactory implements Factory<ArogyaDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideArogyaDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ArogyaDatabase get() {
    return provideArogyaDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideArogyaDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideArogyaDatabaseFactory(contextProvider);
  }

  public static ArogyaDatabase provideArogyaDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArogyaDatabase(context));
  }
}
