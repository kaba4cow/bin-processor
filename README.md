# Binary Processor Library

A utility **Java** library for reading and writing binary data with support for various data types and byte orders.

## Features

- Flexible binary data reading and writing
- Support for multiple primitive types
- Customizable byte order (Big Endian / Little Endian)
- Enum handling with different serialization formats
- Comprehensive type conversion

## Usage

### BinaryReader

The `BinaryReader` allows you to read binary data from an input stream with ease:

```java
try (BinaryReader reader = new BinaryReader(new FileInputStream("data.bin"))) {
    int intValue = reader.readInt();
    float floatValue = reader.readFloat();
    String stringValue = reader.readStringTerminated();
    
    reader.setOrder(ByteOrder.LITTLE_ENDIAN);
    
    int[] intArray = reader.readIntArray(10);
}
```

### BinaryWriter

The `BinaryWriter` provides a flexible way to write binary data to an output stream:

```java
try (BinaryWriter writer = new BinaryWriter(new FileOutputStream("output.bin"))) {
    writer.writeInt(42)
          .writeFloat(3.14f)
          .writeStringTerminated("Hello, World!");
    
    writer.setOrder(ByteOrder.LITTLE_ENDIAN);
    
    writer.writeIntArray(new int[]{1, 2, 3, 4, 5});
}
```

### Enum Handling

The library supports multiple enum serialization formats:

```java
writer.writeEnum(DayOfWeek.TUESDAY, EnumFormat.ORDINAL);
writer.writeEnum(DayOfWeek.FRIDAY, EnumFormat.STRING);
```

## Supported Types

The library supports reading and writing:
- Primitive types: `byte`, `short`, `int`, `long`, `float`, `double`, `char`
- Half-precision floats
- Byte arrays and arrays of primitives
- Strings (fixed-length, variable-length, and null-terminated)
- Enumerations

## Byte Order Support

Easily switch between Big Endian and Little Endian byte orders:

```java
reader.setOrder(ByteOrder.BIG_ENDIAN);
writer.setOrder(ByteOrder.LITTLE_ENDIAN);
```

## Error Handling

- `IOException` - for I/O errors
- `NoSuchElementException` for invalid enum names