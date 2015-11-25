/**
 * Copyright 2015 TASER International, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evidence.tools.digest;

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

    private Algorithm alg;
}
