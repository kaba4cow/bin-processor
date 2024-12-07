package com.kaba4cow.binprocessor;

/**
 * Utility class for converting between 16-bit half-precision floating-point numbers and 32-bit single-precision floating-point
 * numbers.
 * <p>
 * This class provides methods to convert:
 * <ul>
 * <li>Half-precision (16-bit) float to its {@code short} representation.</li>
 * <li>{@code short} representation of half-precision float back to a 32-bit float.</li>
 * </ul>
 * Half-precision floating-point numbers use 1 bit for the sign, 5 bits for the exponent, and 10 bits for the mantissa.
 * </p>
 */
public class FloatConverter {

	private FloatConverter() {}

	/**
	 * Converts a 16-bit half-precision floating-point value to its {@code short} bit representation.
	 *
	 * @param half the half-precision floating-point value
	 * 
	 * @return the {@code short} representing the half-precision float in its binary form
	 */
	public static short halfToShortBits(float half) {
		int bits = Float.floatToIntBits(half);
		int sign = (bits >> 31) & 0x1;
		int exponent = (bits >> 23) & 0xFF;
		int mantissa = bits & 0x7FFFFF;
		int halfExponent = exponent - 127 + 15;
		if (halfExponent <= 0)
			return (short) (sign << 15);
		else if (halfExponent >= 31)
			return (short) ((sign << 15) | (0x1F << 10));
		int halfMantissa = mantissa >> 13;
		return (short) ((sign << 15) | (halfExponent << 10) | halfMantissa);
	}

	/**
	 * Converts a 16-bit {@code short} bit representation of a half-precision floating-point value back to a 32-bit
	 * single-precision float.
	 *
	 * @param bits the {@code short} representing the half-precision float in its binary form
	 * 
	 * @return the corresponding single-precision floating-point value
	 */
	public static float shortBitsToHalf(short bits) {
		int sign = (bits >> 15) & 0x1;
		int exponent = (bits >> 10) & 0x1F;
		int mantissa = bits & 0x3FF;
		int floatExponent = exponent - 15 + 127;
		if (exponent == 0) {
			if (mantissa == 0)
				return sign == 0 ? 0.0f : -0.0f;
			else
				return (float) (sign == 0 ? mantissa / Math.pow(2.0f, 10.0f) * Math.pow(2.0f, -126.0f)
						: -mantissa / Math.pow(2.0f, 10.0f) * Math.pow(2.0f, -126.0f));
		} else if (exponent == 31)
			return sign == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
		float normalizedMantissa = 1.0f + mantissa / (float) (1 << 10);
		float result = (float) (Math.pow(2.0f, floatExponent - 127.0f) * normalizedMantissa);
		return sign == 0 ? result : -result;
	}

}
