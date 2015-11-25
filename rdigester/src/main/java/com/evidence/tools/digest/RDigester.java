package com.evidence.tools.digest;

/**
 * Created by pmahendra on 11/24/15.
 */
public class RDigester {
    static {
        System.out.println(System.getProperty("java.library.path"));
        //System.setProperty("java.library.path", "/Users/pmahendra/Projects/EDC/rdigester/rdigester/src/main/cpp/rdigester/");
        System.loadLibrary("rdigesterjni"); // hello.dll (Windows) or libhello.so (Unixes)
    }

    private native byte[] init(String alg);

    private native byte[] update(byte[] ctx, byte[] data, int length);

    private native byte[] finalHash(byte[] ctx);

    private native String finalHashHex(byte[] ctx);

    // Test Driver
    public static void main(String[] args) {
        new RDigester().init("md5");  // Invoke native method
    }
}
