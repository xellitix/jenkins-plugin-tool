package com.xellitix.jenkins.plugintool.cli.converter;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IStringConverterInstanceFactory;
import com.beust.jcommander.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link ParameterConverter} locator.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class ParameterConverterLocator implements IStringConverterInstanceFactory {

  // Properties
  private final Map<Class, ParameterConverter> converters = new HashMap<>();

  /**
   * Constructor.
   *
   * @param converters The {@link ParameterConverter}s.
   */
  @Inject
  ParameterConverterLocator(final Set<ParameterConverter> converters) {
    // Index the converters
    converters.forEach(converter ->
        this.converters.put(
            converter.getConversionType(),
            converter));
  }

  /**
   * Obtain a converter instance for parsing {@code parameter} as type {@code forType}
   *
   * @param parameter the parameter to parse
   * @param forType the type class
   * @param optionName the name of the option used on the command line
   * @return a converter instance
   */
  @Override
  public IStringConverter<?> getConverterInstance(
      final Parameter parameter,
      final Class<?> forType,
      final String optionName) {

    return converters.get(forType);
  }
}
