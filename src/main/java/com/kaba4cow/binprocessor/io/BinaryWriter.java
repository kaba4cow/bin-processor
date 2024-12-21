package com.kaba4cow.binprocessor.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import com.kaba4cow.binprocessor.BinaryConverter;
import com.kaba4cow.binprocessor.enums.ByteOrder;
import com.kaba4cow.binprocessor.enums.EnumFormat;

/**
 * A utility class for writing binary data to an {@link OutputStream}.
 * <p>
 * Supports writing various primitive types, arrays, strings, and enumerations with customizable {@link ByteOrder}.
 * </p>
 */
public class BinaryWriter implements Closeable {

	private final OutputStream output;
	private ByteOrder order;
	private long position;

	/**
	 * Constructs a {@code BinaryWriter} with the specified {@link OutputStream} with {@link ByteOrder#BIG_ENDIAN} byte order.
	 *
	 * @param output the output stream to write to
	 */
	public BinaryWriter(OutputStream output) {
		this.output = output;
		this.order = ByteOrder.BIG_ENDIAN;
		this.position = 0L;
	}

	/**
	 * Writes a single byte to the output stream.
	 *
	 * @param b the byte to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeByte(byte b) throws IOException {
		output.write(b);
		position++;
		return this;
	}

	/**
	 * Writes a {@code short} to the output stream in the specified byte order.
	 *
	 * @param s the short value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeShort(short s) throws IOException {
		writeByteArray(BinaryConverter.fromShort(s, order));
		return this;
	}

	/**
	 * Writes a {@code char} to the output stream in the specified byte order.
	 *
	 * @param c the char value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeChar(char c) throws IOException {
		writeByteArray(BinaryConverter.fromChar(c, order));
		return this;
	}

	/**
	 * Writes a {@code int} to the output stream in the specified byte order.
	 *
	 * @param i the int value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeInt(int i) throws IOException {
		writeByteArray(BinaryConverter.fromInt(i, order));
		return this;
	}

	/**
	 * Writes a {@code long} to the output stream in the specified byte order.
	 *
	 * @param l the long value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeLong(long l) throws IOException {
		writeByteArray(BinaryConverter.fromLong(l, order));
		return this;
	}

	/**
	 * Writes a half-precision floating point number (16-bit) to the output stream.
	 *
	 * @param h the half-precision float to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeHalf(float h) throws IOException {
		writeByteArray(BinaryConverter.fromHalf(h, order));
		return this;
	}

	/**
	 * Writes a {@code float} to the output stream in the specified byte order.
	 *
	 * @param f the float value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeFloat(float f) throws IOException {
		writeByteArray(BinaryConverter.fromFloat(f, order));
		return this;
	}

	/**
	 * Writes a {@code double} to the output stream in the specified byte order.
	 *
	 * @param d the double value to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeDouble(double d) throws IOException {
		writeByteArray(BinaryConverter.fromDouble(d, order));
		return this;
	}

	/**
	 * Writes an array of bytes to the output stream.
	 *
	 * @param array the byte array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeByteArray(byte[] array) throws IOException {
		if (array.length > 0) {
			output.write(array);
			position += array.length;
		}
		return this;
	}

	/**
	 * Writes an array of {@code short}s to the output stream in the specified byte order.
	 *
	 * @param array the short array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeShortArray(short[] array) throws IOException {
		for (short s : array)
			writeShort(s);
		return this;
	}

	/**
	 * Writes an array of {@code char}s to the output stream in the specified byte order.
	 *
	 * @param array the char array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeCharArray(char[] array) throws IOException {
		for (char c : array)
			writeChar(c);
		return this;
	}

	/**
	 * Writes an array of {@code int}s to the output stream in the specified byte order.
	 *
	 * @param array the int array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeIntArray(int[] array) throws IOException {
		for (int i : array)
			writeInt(i);
		return this;
	}

	/**
	 * Writes an array of {@code long}s to the output stream in the specified byte order.
	 *
	 * @param array the long array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeLongArray(long[] array) throws IOException {
		for (long l : array)
			writeLong(l);
		return this;
	}

	/**
	 * Writes an array of half-precision floating point numbers (16-bit) to the output stream.
	 *
	 * @param array the float array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeHalfArray(float[] array) throws IOException {
		for (float f : array)
			writeHalf(f);
		return this;
	}

	/**
	 * Writes an array of {@code float}s to the output stream in the specified byte order.
	 *
	 * @param array the float array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeFloatArray(float[] array) throws IOException {
		for (float f : array)
			writeFloat(f);
		return this;
	}

	/**
	 * Writes an array of {@code double}s to the output stream in the specified byte order.
	 *
	 * @param array the double array to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeDoubleArray(double[] array) throws IOException {
		for (double d : array)
			writeDouble(d);
		return this;
	}

	/**
	 * Writes a string to the output stream.
	 *
	 * @param string the string to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeStringFixed(String string) throws IOException {
		return writeByteArray(string.getBytes());
	}

	/**
	 * Writes a variable-length string to the output stream, preceded by its length as an {@code int}.
	 *
	 * @param string the string to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeStringVarying(String string) throws IOException {
		byte[] bytes = string.getBytes();
		return writeInt(bytes.length).writeByteArray(bytes);
	}

	/**
	 * Writes a null-terminated string to the output stream.
	 *
	 * @param string the string to write
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeStringTerminated(String string) throws IOException {
		return writeByteArray(string.getBytes()).writeByte((byte) '\0');
	}

	/**
	 * Writes an enum constant to the output stream in the specified format.
	 *
	 * @param constant the enum constant to write
	 * @param format   the format in which to write the enum
	 * 
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter writeEnum(Enum<?> constant, EnumFormat format) throws IOException {
		switch (format) {
			case ORDINAL:
				return writeInt(constant.ordinal());
			case STRING:
				return writeStringTerminated(constant.toString());
			default:
				throw new IllegalArgumentException(String.format("Unreachable: unknown format ", format));
		}
	}

	/**
	 * Sets the byte order for this writer.
	 *
	 * @param order the byte order to use
	 * 
	 * @return a reference to this object
	 */
	public BinaryWriter setOrder(ByteOrder order) {
		this.order = order;
		return this;
	}

	/**
	 * Returns the current byte order of this writer.
	 *
	 * @return the current byte order
	 */
	public ByteOrder getOrder() {
		return order;
	}

	/**
	 * Gets the current position in the output stream.
	 *
	 * @return the current position in bytes from the start of the stream
	 */
	public long getPosition() {
		return position;
	}

	/**
	 * Flushes the output stream, ensuring that all data is written.
	 *
	 * @return a reference to this object
	 * 
	 * @throws IOException if an I/O error occurs
	 */
	public BinaryWriter flush() throws IOException {
		output.flush();
		return this;
	}

	/**
	 * Closes the underlying output stream.
	 *
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void close() throws IOException {
		output.close();
	}

}
