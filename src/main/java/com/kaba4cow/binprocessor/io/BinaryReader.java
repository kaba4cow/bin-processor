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
	private long position;

	/**
	 * Constructs a {@code BinaryReader} with the specified {@link InputStream} with {@link ByteOrder#BIG_ENDIAN} byte order.
	 *
	 * @param input the input stream to read from
	 */
	public BinaryReader(InputStream input) {
		this.input = input;
		this.order = ByteOrder.BIG_ENDIAN;
		this.position = 0L;
	}

	/**
	 * Reads a single byte from the input stream.
	 *
	 * @return the byte read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public byte readByte() throws IOException {
		int value = input.read();
		if (value != -1)
			position++;
		return (byte) value;
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
		return readByteArray(new byte[length]);
	}

	/**
	 * Reads a byte array into the specified target array from the input stream.
	 *
	 * @param target the target array to store the bytes
	 * 
	 * @return the target array with the bytes read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public byte[] readByteArray(byte[] target) throws IOException {
		input.read(target);
		position += target.length;
		return target;
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
		return readShortArray(new short[length]);
	}

	/**
	 * Reads an array of {@code short}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the shorts
	 * 
	 * @return the target array with the shorts read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public short[] readShortArray(short[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readShort();
		return target;
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
		return readCharArray(new char[length]);
	}

	/**
	 * Reads an array of {@code char}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the chars
	 * 
	 * @return the target array with the chars read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public char[] readCharArray(char[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readChar();
		return target;
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
		return readIntArray(new int[length]);
	}

	/**
	 * Reads an array of {@code int}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the ints
	 * 
	 * @return the target array with the ints read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public int[] readIntArray(int[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readInt();
		return target;
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
		return readLongArray(new long[length]);
	}

	/**
	 * Reads an array of {@code long}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the longs
	 * 
	 * @return the target array with the longs read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public long[] readLongArray(long[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readLong();
		return target;
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
		return readHalfArray(new float[length]);
	}

	/**
	 * Reads an array of half-precision floats (16-bit) into the specified target array from the input stream.
	 *
	 * @param target the target array to store the half-precision floats
	 * 
	 * @return the target array with the half-precision floats read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float[] readHalfArray(float[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readHalf();
		return target;
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
		return readFloatArray(new float[length]);
	}

	/**
	 * Reads an array of {@code float}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the floats
	 * 
	 * @return the target array with the floats read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public float[] readFloatArray(float[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readFloat();
		return target;
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
		return readDoubleArray(new double[length]);
	}

	/**
	 * Reads an array of {@code double}s into the specified target array from the input stream.
	 *
	 * @param target the target array to store the doubles
	 * 
	 * @return the target array with the doubles read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public double[] readDoubleArray(double[] target) throws IOException {
		for (int i = 0; i < target.length; i++)
			target[i] = readDouble();
		return target;
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
	public String readStringFixed(int length) throws IOException {
		return new String(readByteArray(length));
	}

	/**
	 * Reads a variable-length string from the input stream, preceded by its length as an {@code int}.
	 * 
	 * @return the string read
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public String readStringVarying() throws IOException {
		return readStringFixed(readInt());
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
			if (b != -1)
				position++;
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
		switch (format) {
			case ORDINAL:
				return type.getEnumConstants()[readInt()];
			case STRING:
				return Enum.valueOf(type, readStringTerminated());
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
	 * 
	 * @see InputStream#skip(long)
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
	 * Gets the current position in the input stream.
	 *
	 * @return the current position in bytes from the start of the stream
	 */
	public long getPosition() {
		return position;
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
