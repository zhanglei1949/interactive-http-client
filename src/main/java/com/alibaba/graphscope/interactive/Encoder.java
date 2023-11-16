package com.alibaba.graphscope.interactive;

public class Encoder {
    public Encoder(byte[] bs) {
        this.bs = bs;
        this.loc = 0;
    }

    public static int serialize_long(byte[] bytes, int offset, long value) {
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        return offset;
    }

    public static int serialize_double(byte[] bytes, int offset, double value) {
        long long_value = Double.doubleToRawLongBits(value);
        return serialize_long(bytes, offset, long_value);
    }

    public static int serialize_int(byte[] bytes, int offset, int value) {
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        value >>= 8;
        bytes[offset++] = (byte) (value & 0xFF);
        return offset;
    }

    public static int serialize_byte(byte[] bytes, int offset, byte value) {
        bytes[offset++] = value;
        return offset;
    }

    public static int serialize_bytes(byte[] bytes, int offset, byte[] value) {
        offset = serialize_int(bytes, offset, value.length);
        System.arraycopy(value, 0, bytes, offset, value.length);
        return offset + value.length;
    }

    public static int serialize_raw_bytes(byte[] bytes, int offset, byte[] value) {
        System.arraycopy(value, 0, bytes, offset, value.length);
        return offset + value.length;
    }

    public static byte[] serialize_long_byte(long value, byte type) {
        byte[] bytes = new byte[9];
        serialize_long(bytes, 0, value);
        serialize_byte(bytes, 8, type);
        return bytes;
    }

    public static byte[] serialize_long_long_byte(long a, long b, byte type) {
        byte[] bytes = new byte[17];
        serialize_long(bytes, 0, a);
        serialize_long(bytes, 8, b);
        serialize_byte(bytes, 16, type);
        return bytes;
    }

    public static byte[] serialize_long_int_byte(long a, int b, byte type) {
        byte[] bytes = new byte[13];
        serialize_long(bytes, 0, a);
        serialize_int(bytes, 8, b);
        serialize_byte(bytes, 12, type);
        return bytes;
    }

    public static byte[] serialize_long_long_long_byte(long a, long b, long c, byte type) {
        byte[] bytes = new byte[25];
        serialize_long(bytes, 0, a);
        serialize_long(bytes, 8, b);
        serialize_long(bytes, 16, c);
        serialize_byte(bytes, 24, type);
        return bytes;
    }

    public static byte[] serialize_long_long_int_byte(long a, long b, int c, byte type) {
        byte[] bytes = new byte[21];
        serialize_long(bytes, 0, a);
        serialize_long(bytes, 8, b);
        serialize_int(bytes, 16, c);
        serialize_byte(bytes, 20, type);
        return bytes;
    }

    public static byte[] serialize_long_string_byte(long value, String b, byte type) {
        byte[] b_ba = b.getBytes();
        byte[] bytes = new byte[13 + b_ba.length];
        serialize_long(bytes, 0, value);
        int offset = serialize_bytes(bytes, 8, b_ba);
        serialize_byte(bytes, offset, type);
        return bytes;
    }

    public void put_int(int value) {
        this.loc = serialize_int(this.bs, this.loc, value);
    }

    public void put_byte(byte value) {
        this.loc = serialize_byte(this.bs, this.loc, value);
    }

    public void put_long(long value) {
        this.loc = serialize_long(this.bs, this.loc, value);
    }

    public void put_double(double value) {
        this.loc = serialize_double(this.bs, this.loc, value);
    }

    public void put_bytes(byte[] bytes) {
        this.loc = serialize_bytes(this.bs, this.loc, bytes);
    }

    byte[] bs;
    int loc;

}
