package com.janaushadhi.finder.ui.maps;

import com.janaushadhi.finder.data.remote.GeocodingApiService;
import com.janaushadhi.finder.data.repository.StoreRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class MapsViewModel_Factory implements Factory<MapsViewModel> {
  private final Provider<StoreRepository> repositoryProvider;

  private final Provider<GeocodingApiService> geocodingApiServiceProvider;

  public MapsViewModel_Factory(Provider<StoreRepository> repositoryProvider,
      Provider<GeocodingApiService> geocodingApiServiceProvider) {
    this.repositoryProvider = repositoryProvider;
    this.geocodingApiServiceProvider = geocodingApiServiceProvider;
  }

  @Override
  public MapsViewModel get() {
    return newInstance(repositoryProvider.get(), geocodingApiServiceProvider.get());
  }

  public static MapsViewModel_Factory create(
      javax.inject.Provider<StoreRepository> repositoryProvider,
      javax.inject.Provider<GeocodingApiService> geocodingApiServiceProvider) {
    return new MapsViewModel_Factory(Providers.asDaggerProvider(repositoryProvider), Providers.asDaggerProvider(geocodingApiServiceProvider));
  }

  public static MapsViewModel_Factory create(Provider<StoreRepository> repositoryProvider,
      Provider<GeocodingApiService> geocodingApiServiceProvider) {
    return new MapsViewModel_Factory(repositoryProvider, geocodingApiServiceProvider);
  }

  public static MapsViewModel newInstance(StoreRepository repository,
      GeocodingApiService geocodingApiService) {
    return new MapsViewModel(repository, geocodingApiService);
  }
}
