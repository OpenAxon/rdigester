package com.evidence.tools.digest;

/**
 * Created by pmahendra on 11/24/15.
 */


public class RDigester {
    static {
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("rdigesterjni"); // hello.dll (Windows) or libhello.so (Unixes)
    }

    public enum Algorithm {
        Md5(0), Sha1(1), Sha256(2), Sha512(3);

        private final int id;
        Algorithm(int id) { this.id = id; }
        public int getValue() { return id; }
    }

    public RDigester(Algorithm alg) {
        this.alg = alg;
    }

    public static String md5Hex(String data) {
        RDigester r = new RDigester(Algorithm.Md5);

        byte[] ctx = r.init();
        ctx = r.update(ctx, data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex(ctx);
    }

    public static String sha1Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha1);

        byte[] ctx = r.init();
        ctx = r.update(ctx, data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex(ctx);
    }

    public static String sha256Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha256);

        byte[] ctx = r.init();
        ctx = r.update(ctx, data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex(ctx);
    }

    public static String sha512Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha512);

        byte[] ctx = r.init();
        ctx = r.update(ctx, data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex(ctx);
    }

    public byte[] init() {
        return init(alg.getValue());
    }

    public byte[] update(byte[] serilizedCtx, byte[] data, int length) {
        return update(alg.getValue(), serilizedCtx, data, length);
    }

    public String finalChecksumHex(byte[] serilizedCtx) {
        return finalChecksumHex(alg.getValue(), serilizedCtx);
    }

    private native byte[] init(int alg);

    private native byte[] update(int alg, byte[] serilizedCtx, byte[] data, int length);

    private native String finalChecksumHex(int alg, byte[] serilizedCtx);

    /*
    // Test Driver
    public static void main(String[] args) {
        int alg = Algorithm.Md5.getValue();
        RDigester rDigester = new RDigester(Algorithm.Md5);

        byte[] digestCtx = rDigester.init(alg);

        System.out.println(String.format("digestCtx 2: %s %d", digestCtx, digestCtx.length));

        digestCtx = rDigester.update(alg, digestCtx, "Hello, ".getBytes(), 7);

        System.out.println(String.format("digestCtx 2: %s %d", digestCtx, digestCtx.length));

        digestCtx = rDigester.update(alg, digestCtx, "world!2".getBytes(), 7);
        digestCtx = rDigester.update(alg, digestCtx, "world!2xx".getBytes(), 7);

        System.out.println(String.format("digestCtx 3: %s %d", digestCtx, digestCtx.length));

        String checkSum = rDigester.finalChecksumHex(alg, digestCtx);

        System.out.println(String.format("checkSum: %s", checkSum));
    }
    */

    private Algorithm alg;
}
