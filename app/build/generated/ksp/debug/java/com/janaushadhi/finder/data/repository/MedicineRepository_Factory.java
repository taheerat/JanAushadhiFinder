package com.janaushadhi.finder.data.repository;

import com.janaushadhi.finder.data.local.dao.MedicineDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class MedicineRepository_Factory implements Factory<MedicineRepository> {
  private final Provider<MedicineDao> daoProvider;

  public MedicineRepository_Factory(Provider<MedicineDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public MedicineRepository get() {
    return newInstance(daoProvider.get());
  }

  public static MedicineRepository_Factory create(javax.inject.Provider<MedicineDao> daoProvider) {
    return new MedicineRepository_Factory(Providers.asDaggerProvider(daoProvider));
  }

  public static MedicineRepository_Factory create(Provider<MedicineDao> daoProvider) {
    return new MedicineRepository_Factory(daoProvider);
  }

  public static MedicineRepository newInstance(MedicineDao dao) {
    return new MedicineRepository(dao);
  }
}
