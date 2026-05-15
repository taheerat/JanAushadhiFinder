package com.janaushadhi.finder.ui.chat;

import com.janaushadhi.finder.data.repository.GeminiRepository;
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
public final class AIChatViewModel_Factory implements Factory<AIChatViewModel> {
  private final Provider<GeminiRepository> repositoryProvider;

  public AIChatViewModel_Factory(Provider<GeminiRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AIChatViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AIChatViewModel_Factory create(
      javax.inject.Provider<GeminiRepository> repositoryProvider) {
    return new AIChatViewModel_Factory(Providers.asDaggerProvider(repositoryProvider));
  }

  public static AIChatViewModel_Factory create(Provider<GeminiRepository> repositoryProvider) {
    return new AIChatViewModel_Factory(repositoryProvider);
  }

  public static AIChatViewModel newInstance(GeminiRepository repository) {
    return new AIChatViewModel(repository);
  }
}
