/* GENERATED SOURCE. DO NOT MODIFY. */
// © 2016 and later: Unicode, Inc. and others.
// License & terms of use: http://www.unicode.org/copyright.html#License
/*
 *******************************************************************************
 * Copyright (C) 1996-2016, Google, International Business Machines Corporation and
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */

package android.icu.text;

import java.text.ParsePosition;
import java.util.Locale;

import android.icu.impl.number.PatternString;
import android.icu.impl.number.Properties;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;

/**
 * The CompactDecimalFormat produces abbreviated numbers, suitable for display in environments will
 * limited real estate. For example, 'Hits: 1.2B' instead of 'Hits: 1,200,000,000'. The format will
 * be appropriate for the given language, such as "1,2 Mrd." for German.
 *
 * <p>For numbers under 1000 trillion (under 10^15, such as 123,456,789,012,345), the result will be
 * short for supported languages. However, the result may sometimes exceed 7 characters, such as
 * when there are combining marks or thin characters. In such cases, the visual width in fonts
 * should still be short.
 *
 * <p>By default, there are 2 significant digits. After creation, if more than three significant
 * digits are set (with setMaximumSignificantDigits), or if a fixed number of digits are set (with
 * setMaximumIntegerDigits or setMaximumFractionDigits), then result may be wider.
 *
 * <p>The "short" style is also capable of formatting currency amounts, such as "$1.2M" instead of
 * "$1,200,000.00" (English) or "5,3 Mio. €" instead of "5.300.000,00 €" (German). Localized data
 * concerning longer formats is not available yet in the Unicode CLDR. Because of this, attempting
 * to format a currency amount using the "long" style will produce an UnsupportedOperationException.
 *
 * <p>At this time, negative numbers and parsing are not supported, and will produce an
 * UnsupportedOperationException. Resetting the pattern prefixes or suffixes is not supported; the
 * method calls are ignored.
 *
 * <p>Note that important methods, like setting the number of decimals, will be moved up from
 * DecimalFormat to NumberFormat.
 *
 * @author markdavis
 */
public class CompactDecimalFormat extends DecimalFormat {

  private static final long serialVersionUID = 4716293295276629682L;

  /**
   * Style parameter for CompactDecimalFormat.
   */
  public enum CompactStyle {
    /**
     * Short version, like "1.2T"
     */
    SHORT,
    /**
     * Longer version, like "1.2 trillion", if available. May return same result as SHORT if not.
     */
    LONG
  }

  /**
   * Creates a CompactDecimalFormat appropriate for a locale. The result may be affected by the
   * number system in the locale, such as ar-u-nu-latn.
   *
   * @param locale the desired locale
   * @param style the compact style
   */
  public static CompactDecimalFormat getInstance(ULocale locale, CompactStyle style) {
    return new CompactDecimalFormat(locale, style);
  }

  /**
   * Creates a CompactDecimalFormat appropriate for a locale. The result may be affected by the
   * number system in the locale, such as ar-u-nu-latn.
   *
   * @param locale the desired locale
   * @param style the compact style
   */
  public static CompactDecimalFormat getInstance(Locale locale, CompactStyle style) {
    return new CompactDecimalFormat(ULocale.forLocale(locale), style);
  }

  /**
   * The public mechanism is CompactDecimalFormat.getInstance().
   *
   * @param locale the desired locale
   * @param style the compact style
   */
  CompactDecimalFormat(ULocale locale, CompactStyle style) {
    // Use the locale's default pattern
    String pattern = getPattern(locale, 0);
    symbols = DecimalFormatSymbols.getInstance(locale);
    properties = new Properties();
    properties.setCompactStyle(style);
    exportedProperties = new Properties();
    setPropertiesFromPattern(pattern, PatternString.IGNORE_ROUNDING_ALWAYS);
    if (style == CompactStyle.SHORT) {
      // TODO: This was setGroupingUsed(false) in ICU 58. Is it okay that I changed it for ICU 59?
      properties.setMinimumGroupingDigits(2);
    }
    refreshFormatter();
  }

  /**
   * Parsing is currently unsupported, and throws an UnsupportedOperationException.
   */
  @Override
  public Number parse(String text, ParsePosition parsePosition) {
    throw new UnsupportedOperationException();
  }

  /**
   * Parsing is currently unsupported, and throws an UnsupportedOperationException.
   */
  @Override
  public CurrencyAmount parseCurrency(CharSequence text, ParsePosition parsePosition) {
    throw new UnsupportedOperationException();
  }
}
