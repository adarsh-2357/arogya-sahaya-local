package com.arogyasahaya.local.data.local;

import com.arogyasahaya.local.data.local.dao.MedicineDao;
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
public final class DatabaseModule_ProvideMedicineDaoFactory implements Factory<MedicineDao> {
  private final Provider<ArogyaDatabase> databaseProvider;

  public DatabaseModule_ProvideMedicineDaoFactory(Provider<ArogyaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MedicineDao get() {
    return provideMedicineDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMedicineDaoFactory create(
      Provider<ArogyaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMedicineDaoFactory(databaseProvider);
  }

  public static MedicineDao provideMedicineDao(ArogyaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMedicineDao(database));
  }
}
