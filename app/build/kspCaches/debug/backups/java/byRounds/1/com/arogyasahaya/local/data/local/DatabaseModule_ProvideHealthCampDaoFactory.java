package com.arogyasahaya.local.data.local;

import com.arogyasahaya.local.data.local.dao.HealthCampDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class DatabaseModule_ProvideHealthCampDaoFactory implements Factory<HealthCampDao> {
  private final Provider<ArogyaDatabase> databaseProvider;

  public DatabaseModule_ProvideHealthCampDaoFactory(Provider<ArogyaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public HealthCampDao get() {
    return provideHealthCampDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideHealthCampDaoFactory create(
      Provider<ArogyaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideHealthCampDaoFactory(databaseProvider);
  }

  public static HealthCampDao provideHealthCampDao(ArogyaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHealthCampDao(database));
  }
}
