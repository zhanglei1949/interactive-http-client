package com.alibaba.graphscope.interactive;

final class Decoder {
    public Decoder(byte[] bs) {
        this.bs = bs;
        this.loc = 0;
        this.len = this.bs.length;
    }

    public static int get_int(byte[] bs, int loc) {
        int ret = (bs[loc + 3] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 2] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 1] & 0xff);
        ret <<= 8;
        ret |= (bs[loc] & 0xff);
        return ret;
    }

    public static long get_long(byte[] bs, int loc) {
        long ret = (bs[loc + 7] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 6] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 5] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 4] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 3] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 2] & 0xff);
        ret <<= 8;
        ret |= (bs[loc + 1] & 0xff);
        ret <<= 8;
        ret |= (bs[loc] & 0xff);
        return ret;
    }

    public long get_long() {
        long ret = get_long(this.bs, this.loc);
        this.loc += 8;
        return ret;
    }

    public int get_int() {
        int ret = get_int(this.bs, this.loc);
        this.loc += 4;
        return ret;
    }

    public byte get_byte() {
        return (byte) (bs[loc++] & 0xFF);
    }

    public String get_string() {
        int strlen = this.get_int();
        String ret = new String(this.bs, this.loc, strlen);
        this.loc += strlen;
        return ret;
    }

    public boolean empty() {
        return loc == len;
    }

    byte[] bs;
    int loc;
    int len;
}
