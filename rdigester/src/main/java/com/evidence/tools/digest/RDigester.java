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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class RDigester {
    private static Config config = ConfigFactory.load();
    private byte[] ctx;

    static {
        System.load(config.getString("rdigester.library.path"));
    }

    public enum Algorithm {
        Md5(0), Sha1(1), Sha256(2), Sha512(3);

        private final int id;
        Algorithm(int id) { this.id = id; }
        public int getValue() { return id; }
    }

    public RDigester(Algorithm alg) {
        this.alg = alg;
        this.ctx = init(alg.getValue());
    }

    public RDigester(Algorithm alg, byte[] ctx) {
        this.alg = alg;
        this.ctx = ctx;
    }

    public byte[] getCtx() {
        return this.ctx;
    }

    public static String md5Hex(String data) {
        RDigester r = new RDigester(Algorithm.Md5);
        r.update(data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex();
    }

    public static String sha1Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha1);

        r.update( data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex();
    }

    public static String sha256Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha256);

        r.update(data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex();
    }

    public static String sha512Hex(String data) {
        RDigester r = new RDigester(Algorithm.Sha512);

        r.update(data.getBytes(), data.getBytes().length);
        return r.finalChecksumHex();
    }

    public void update(byte[] data, int length) {
        this.ctx = update(alg.getValue(), this.ctx, data, length);
    }

    public String finalChecksumHex() {
        return finalChecksumHex(alg.getValue(), this.ctx);
    }

    private native byte[] init(int alg);

    private native byte[] update(int alg, byte[] serilizedCtx, byte[] data, int length);

    private native String finalChecksumHex(int alg, byte[] serilizedCtx);

    private Algorithm alg;
}
