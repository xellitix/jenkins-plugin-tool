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
   * @throws ParameterConverterNotFoundException If a {@link ParameterConverter} could not be found.
   */
  @Override
  public IStringConverter<?> getConverterInstance(
      final Parameter parameter,
      final Class<?> forType,
      final String optionName) throws ParameterConverterNotFoundException {

    // Get the converter
    final IStringConverter converter = converters.get(forType);

    // Throw exception if a converter is not registered for the conversion type
    if (converter == null) {
      throw new ParameterConverterNotFoundException(forType);
    }

    return converter;
  }
}
