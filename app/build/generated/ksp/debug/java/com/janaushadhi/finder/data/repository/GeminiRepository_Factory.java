package com.janaushadhi.finder.data.repository;

import com.janaushadhi.finder.data.remote.GeminiApiService;
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
public final class GeminiRepository_Factory implements Factory<GeminiRepository> {
  private final Provider<GeminiApiService> apiServiceProvider;

  public GeminiRepository_Factory(Provider<GeminiApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public GeminiRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static GeminiRepository_Factory create(
      javax.inject.Provider<GeminiApiService> apiServiceProvider) {
    return new GeminiRepository_Factory(Providers.asDaggerProvider(apiServiceProvider));
  }

  public static GeminiRepository_Factory create(Provider<GeminiApiService> apiServiceProvider) {
    return new GeminiRepository_Factory(apiServiceProvider);
  }

  public static GeminiRepository newInstance(GeminiApiService apiService) {
    return new GeminiRepository(apiService);
  }
}
