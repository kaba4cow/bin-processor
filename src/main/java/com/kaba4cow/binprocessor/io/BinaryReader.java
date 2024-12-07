package com.kaba4cow.binprocessor.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import com.kaba4cow.binprocessor.BinaryConverter;
import com.kaba4cow.binprocessor.enums.ByteOrder;
import com.kaba4cow.binprocessor.enums.EnumFormat;

/**
 * A utility class for reading binary data from an {@link InputStream}.
 * <p>
 * Supports reading various primitive types, arrays, strings, and enumerations with customizable {@link ByteOrder}.
 * </p>
 */
public class BinaryReader implements Closeable {

	private final InputStream input;
	private ByteOrder order;

	/**
	 * Constructs a {@code BinaryReader} with the specified {@link InputStream} with {@link ByteOrder#BIG_ENDIAN} byte order.
	 *
	 * @param input the input stream to read from
	 */
	public BinaryReader(InputStream input) {
		this.input = input;
		this.order = ByteOrder.BIG_ENDIAN;
	}

	/**
	 * Reads a single byte from the input stream.
	 *
	 * @return the byte read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public byte readByte() throws IOException {
		return (byte) input.read();
	}

	/**
	 * Reads a {@code short} from the input stream using the current byte order.
	 *
	 * @return the short value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public short readShort() throws IOException {
		return BinaryConverter.toShort(readByteArray(Short.BYTES), order);
	}

	/**
	 * Reads a {@code char} from the input stream using the current byte order.
	 *
	 * @return the char value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public char readChar() throws IOException {
		return BinaryConverter.toChar(readByteArray(Character.BYTES), order);
	}

	/**
	 * Reads an {@code int} from the input stream using the current byte order.
	 *
	 * @return the int value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public int readInt() throws IOException {
		return BinaryConverter.toInt(readByteArray(Integer.BYTES), order);
	}

	/**
	 * Reads a {@code long} from the input stream using the current byte order.
	 *
	 * @return the long value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public long readLong() throws IOException {
		return BinaryConverter.toLong(readByteArray(Long.BYTES), order);
	}

	/**
	 * Reads a half-precision float (16-bit) from the input stream.
	 *
	 * @return the half-precision float value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float readHalf() throws IOException {
		return BinaryConverter.toHalf(readByteArray(Short.BYTES), order);
	}

	/**
	 * Reads a {@code float} from the input stream using the current byte order.
	 *
	 * @return the float value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float readFloat() throws IOException {
		return BinaryConverter.toFloat(readByteArray(Float.BYTES), order);
	}

	/**
	 * Reads a {@code double} from the input stream using the current byte order.
	 *
	 * @return the double value read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public double readDouble() throws IOException {
		return BinaryConverter.toDouble(readByteArray(Double.BYTES), order);
	}

	/**
	 * Reads a byte array of the specified length from the input stream.
	 *
	 * @param length the number of bytes to read
	 * 
	 * @return the byte array read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public byte[] readByteArray(int length) throws IOException {
		byte[] array = new byte[length];
		input.read(array);
		return array;
	}

	/**
	 * Reads an array of {@code short}s from the input stream.
	 *
	 * @param length the number of shorts to read
	 * 
	 * @return the array of shorts read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public short[] readShortArray(int length) throws IOException {
		short[] array = new short[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readShort();
		return array;
	}

	/**
	 * Reads an array of {@code char}s from the input stream.
	 *
	 * @param length the number of chars to read
	 * 
	 * @return the array of chars read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public char[] readCharArray(int length) throws IOException {
		char[] array = new char[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readChar();
		return array;
	}

	/**
	 * Reads an array of {@code int}s from the input stream.
	 *
	 * @param length the number of ints to read
	 * 
	 * @return the array of ints read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public int[] readIntArray(int length) throws IOException {
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readInt();
		return array;
	}

	/**
	 * Reads an array of {@code long}s from the input stream.
	 *
	 * @param length the number of longs to read
	 * 
	 * @return the array of longs read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public long[] readLongArray(int length) throws IOException {
		long[] array = new long[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readLong();
		return array;
	}

	/**
	 * Reads an array of half-precision floats (16-bit) from the input stream.
	 *
	 * @param length the number of half-precision floats to read
	 * 
	 * @return the array of half-precision floats read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float[] readHalfArray(int length) throws IOException {
		float[] array = new float[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readHalf();
		return array;
	}

	/**
	 * Reads an array of {@code float}s from the input stream.
	 *
	 * @param length the number of floats to read
	 * 
	 * @return the array of floats read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float[] readFloatArray(int length) throws IOException {
		float[] array = new float[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readFloat();
		return array;
	}

	/**
	 * Reads an array of {@code double}s from the input stream.
	 *
	 * @param length the number of doubles to read
	 * 
	 * @return the array of doubles read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public double[] readDoubleArray(int length) throws IOException {
		double[] array = new double[length];
		for (int i = 0; i < array.length; i++)
			array[i] = readDouble();
		return array;
	}

	/**
	 * Reads a string of specified length from the input stream.
	 *
	 * @param length the number of bytes to read for the string
	 * 
	 * @return the string read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public String readString(int length) throws IOException {
		return new String(readByteArray(length));
	}

	/**
	 * Reads a null-terminated string from the input stream.
	 *
	 * @return the string read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public String readStringTerminated() throws IOException {
		StringBuilder string = new StringBuilder();
		while (true) {
			int b = input.read();
			if (b == '\0' || b == -1)
				break;
			string.append((char) b);
		}
		return string.toString();
	}

	/**
	 * Reads an enumeration value from the input stream.
	 *
	 * @param <T>    the type of the enum
	 * @param type   the class of the enum
	 * @param format the format used to represent the enum (ordinal or string)
	 * 
	 * @return the enum value read
	 * 
	 * @throws IOException            if an I/O error occurs
	 * @throws NoSuchElementException if the string representation does not match any enum constant
	 */
	public <T extends Enum<T>> T readEnum(Class<T> type, EnumFormat format) throws IOException {
		T[] constants = type.getEnumConstants();
		switch (format) {
			case ORDINAL:
				return constants[readInt()];
			case STRING:
				String string = readStringTerminated();
				for (T constant : constants)
					if (string.equals(constant.toString()))
						return constant;
				throw new NoSuchElementException(
						String.format("%s does not contain enum constant %s", type.getSimpleName(), string));
			default:
				throw new IllegalArgumentException(String.format("Unreachable: unknown format ", format));
		}
	}

	/**
	 * Skips the specified number of bytes in the input stream.
	 *
	 * @param bytes the number of bytes to skip
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryReader skipBytes(long bytes) throws IOException {
		input.skip(bytes);
		return this;
	}

	/**
	 * Sets the byte order for this reader.
	 *
	 * @param order the byte order to use
	 * 
	 * @return a reference to this object
	 */
	public BinaryReader setOrder(ByteOrder order) {
		this.order = order;
		return this;
	}

	/**
	 * Returns the current byte order of this reader.
	 *
	 * @return the current byte order
	 */
	public ByteOrder getOrder() {
		return order;
	}

	/**
	 * Closes the underlying input stream.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		input.close();
	}

}
