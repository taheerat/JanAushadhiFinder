package com.janaushadhi.finder.di;

import com.janaushadhi.finder.data.local.JanAushadhiDatabase;
import com.janaushadhi.finder.data.local.dao.MedicineDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideMedicineDaoFactory implements Factory<MedicineDao> {
  private final Provider<JanAushadhiDatabase> databaseProvider;

  public DatabaseModule_ProvideMedicineDaoFactory(Provider<JanAushadhiDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MedicineDao get() {
    return provideMedicineDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMedicineDaoFactory create(
      javax.inject.Provider<JanAushadhiDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMedicineDaoFactory(Providers.asDaggerProvider(databaseProvider));
  }

  public static DatabaseModule_ProvideMedicineDaoFactory create(
      Provider<JanAushadhiDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMedicineDaoFactory(databaseProvider);
  }

  public static MedicineDao provideMedicineDao(JanAushadhiDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMedicineDao(database));
  }
}
