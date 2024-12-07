package com.kaba4cow.binprocessor;

import com.kaba4cow.binprocessor.enums.ByteOrder;

/**
 * Utility class for converting between primitive data types and their binary representations in {@code byte[]} arrays,
 * considering byte order (big-endian or little-endian).
 * <p>
 * This class provides methods to:
 * <ul>
 * <li>Convert {@code byte[]} to primitive types (e.g., {@code byte}, {@code short}, {@code int}, etc.)</li>
 * <li>Convert primitive types to their binary {@code byte[]} representations</li>
 * <li>Handle byte order through {@link ByteOrder}</li>
 * </ul>
 * <p>
 * All methods ensure that the provided byte arrays are of the correct length for the target data type. For operations requiring
 * a specific byte order, the byte array is modified in-place to reflect the desired order.
 */
public class BinaryConverter {

	private BinaryConverter() {}

	/**
	 * Converts a single-byte array to a {@code byte}.
	 *
	 * @param bytes the byte array (must have length 1)
	 * 
	 * @return the {@code byte} value
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 1
	 */
	public static byte toByte(byte[] bytes) {
		checkLength(bytes, Byte.BYTES);
		return bytes[0];
	}

	/**
	 * Converts a 2-byte array to a {@code short}, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 2)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the {@code short} value
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 2
	 */
	public static short toShort(byte[] bytes, ByteOrder order) {
		checkLength(bytes, Short.BYTES);
		applyOrder(bytes, order);
		return (short) (0//
				| (bytes[0] << 8) //
						& 0xFF00//
				| (bytes[1] << 0) //
						& 0x00FF//
		);
	}

	/**
	 * Converts a 2-byte array to a {@code char}, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 2)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the {@code char} value
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 2
	 */
	public static char toChar(byte[] bytes, ByteOrder order) {
		checkLength(bytes, Character.BYTES);
		applyOrder(bytes, order);
		return (char) (0//
				| ((char) bytes[0] << 8) //
						& 0xFF00//
				| ((char) bytes[1] << 0) //
						& 0x00FF//
		);
	}

	/**
	 * Converts a 4-byte array to an {@code int}, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 4)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the {@code int} value
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 4
	 */
	public static int toInt(byte[] bytes, ByteOrder order) {
		checkLength(bytes, Integer.BYTES);
		applyOrder(bytes, order);
		return (0//
				| (bytes[0] << 24) //
						& 0xFF000000//
				| (bytes[1] << 16) //
						& 0x00FF0000//
				| (bytes[2] << 8) //
						& 0x0000FF00//
				| (bytes[3] << 0) //
						& 0x000000FF//
		);
	}

	/**
	 * Converts an 8-byte array to a {@code long}, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 8)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the {@code long} value
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 8
	 */
	public static long toLong(byte[] bytes, ByteOrder order) {
		checkLength(bytes, Long.BYTES);
		applyOrder(bytes, order);
		return (0L//
				| ((long) bytes[0] << 56) //
						& 0xFF00000000000000L//
				| ((long) bytes[1] << 48) //
						& 0x00FF000000000000L//
				| ((long) bytes[2] << 40) //
						& 0x0000FF0000000000L//
				| ((long) bytes[3] << 32) //
						& 0x000000FF00000000L//
				| ((long) bytes[4] << 24) //
						& 0x00000000FF000000L//
				| ((long) bytes[5] << 16) //
						& 0x0000000000FF0000L//
				| ((long) bytes[6] << 8) //
						& 0x000000000000FF00L//
				| ((long) bytes[7] << 0) //
						& 0x00000000000000FFL//
		);
	}

	/**
	 * Converts a 2-byte array to a 16-bit floating-point value, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 2)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting half-precision {@code float}
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 2
	 */
	public static float toHalf(byte[] bytes, ByteOrder order) {
		return FloatConverter.shortBitsToHalf(toShort(bytes, order));
	}

	/**
	 * Converts a 4-byte array to a 32-bit floating-point value, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 4)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting {@code float}
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 4
	 */
	public static float toFloat(byte[] bytes, ByteOrder order) {
		return Float.intBitsToFloat(toInt(bytes, order));
	}

	/**
	 * Converts an 8-byte array to a 64-bit floating-point value, considering the specified byte order.
	 *
	 * @param bytes the byte array (must have length 8)
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting {@code double}
	 * 
	 * @throws IllegalArgumentException if the length of {@code bytes} is not 8
	 */
	public static double toDouble(byte[] bytes, ByteOrder order) {
		return Double.longBitsToDouble(toLong(bytes, order));
	}

	/**
	 * Converts a single {@code byte} into a 1-byte array.
	 *
	 * @param b the {@code byte} value
	 * 
	 * @return the resulting 1-byte array
	 */
	public static byte[] fromByte(byte b) {
		return new byte[] { b };
	}

	/**
	 * Converts a {@code short} to a 2-byte array, considering the specified byte order.
	 *
	 * @param s     the {@code short} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 2-byte array
	 */
	public static byte[] fromShort(short s, ByteOrder order) {
		return applyOrder(new byte[] { //
				(byte) ((s >> 8) & 0xFF), //
				(byte) ((s >> 0) & 0xFF)//
		}, order);
	}

	/**
	 * Converts a {@code char} to a 2-byte array, considering the specified byte order.
	 *
	 * @param c     the {@code char} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 2-byte array
	 */
	public static byte[] fromChar(char c, ByteOrder order) {
		return applyOrder(new byte[] { //
				(byte) ((c >> 8) & 0xFF), //
				(byte) ((c >> 0) & 0xFF)//
		}, order);
	}

	/**
	 * Converts an {@code int} to a 4-byte array, considering the specified byte order.
	 *
	 * @param i     the {@code int} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 4-byte array
	 */
	public static byte[] fromInt(int i, ByteOrder order) {
		return applyOrder(new byte[] { //
				(byte) ((i >> 24) & 0xFF), //
				(byte) ((i >> 16) & 0xFF), //
				(byte) ((i >> 8) & 0xFF), //
				(byte) ((i >> 0) & 0xFF)//
		}, order);
	}

	/**
	 * Converts a {@code long} to an 8-byte array, considering the specified byte order.
	 *
	 * @param l     the {@code long} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 8-byte array
	 */
	public static byte[] fromLong(long l, ByteOrder order) {
		return applyOrder(new byte[] { //
				(byte) ((l >> 56) & 0xFF), //
				(byte) ((l >> 48) & 0xFF), //
				(byte) ((l >> 40) & 0xFF), //
				(byte) ((l >> 32) & 0xFF), //
				(byte) ((l >> 24) & 0xFF), //
				(byte) ((l >> 16) & 0xFF), //
				(byte) ((l >> 8) & 0xFF), //
				(byte) ((l >> 0) & 0xFF)//
		}, order);
	}

	/**
	 * Converts a {@code float} to a 2-byte half-precision representation, considering the specified byte order.
	 *
	 * @param h     the {@code float} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 2-byte array
	 */
	public static byte[] fromHalf(float h, ByteOrder order) {
		return fromShort(FloatConverter.halfToShortBits(h), order);
	}

	/**
	 * Converts a {@code float} to a 4-byte representation, considering the specified byte order.
	 *
	 * @param f     the {@code float} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 4-byte array
	 */
	public static byte[] fromFloat(float f, ByteOrder order) {
		return fromInt(Float.floatToIntBits(f), order);
	}

	/**
	 * Converts a {@code double} to an 8-byte representation, considering the specified byte order.
	 *
	 * @param d     the {@code double} value
	 * @param order the byte order to use for conversion
	 * 
	 * @return the resulting 8-byte array
	 */
	public static byte[] fromDouble(double d, ByteOrder order) {
		return fromLong(Double.doubleToLongBits(d), order);
	}

	private static byte[] applyOrder(byte[] bytes, ByteOrder order) {
		if (order == ByteOrder.LITTLE_ENDIAN) {
			int length = bytes.length;
			for (int i = 0; i < length / 2; i++) {
				byte temp = bytes[i];
				bytes[i] = bytes[length - 1 - i];
				bytes[length - 1 - i] = temp;
			}
		}
		return bytes;
	}

	private static void checkLength(byte[] bytes, int length) {
		if (bytes.length != length)
			throw new IllegalArgumentException(
					String.format("Invalid byte array length: expected %s, got %s", length, bytes.length));
	}

}
