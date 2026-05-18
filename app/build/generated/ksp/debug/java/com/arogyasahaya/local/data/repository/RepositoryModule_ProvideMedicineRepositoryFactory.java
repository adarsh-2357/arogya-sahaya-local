package com.arogyasahaya.local.data.repository;

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
public final class RepositoryModule_ProvideMedicineRepositoryFactory implements Factory<MedicineRepository> {
  private final Provider<MedicineDao> medicineDaoProvider;

  public RepositoryModule_ProvideMedicineRepositoryFactory(
      Provider<MedicineDao> medicineDaoProvider) {
    this.medicineDaoProvider = medicineDaoProvider;
  }

  @Override
  public MedicineRepository get() {
    return provideMedicineRepository(medicineDaoProvider.get());
  }

  public static RepositoryModule_ProvideMedicineRepositoryFactory create(
      Provider<MedicineDao> medicineDaoProvider) {
    return new RepositoryModule_ProvideMedicineRepositoryFactory(medicineDaoProvider);
  }

  public static MedicineRepository provideMedicineRepository(MedicineDao medicineDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideMedicineRepository(medicineDao));
  }
}
