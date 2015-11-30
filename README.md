# rdigester
Serializable Java checksum (md5, sha1, sha256, sha512 using openssl native libraries) digester experiment.

```sh
    var digester = new RDigester(Algorithm.Sha256)
    digester.update("Hello World  0".getBytes, 14)
    var ctx = digester.getCtx()
    ...
    digester = new RDigester(Algorithm.Sha256, ctx)
    digester.update("Hello World  1".getBytes, 14)
    ...
    digester = new RDigester(Algorithm.Sha256, ctx)
    digester.update("Hello World  2".getBytes, 14)
    ...    
    val finalCheckSum = digester.finalChecksumHex()
```

# Perf: MessageDigest vs. BouncyCastle Crypto vs. This
MacBook Pro (Retina, 15-inch, Late 2013), 2.3 GHz Intel Core i7, 16 GB 1600 MHz DDR3

```sh
      MessageDigest:                                ~ 9 seconds
      BouncyCastle:                                 ~ 10 seconds
      BouncyCastle with Encoded Ctx per iter:       ~ 25 seconds
      This (OpenSSL/JNI):                           ~ 70+ seconds
```

# Authors
- [Prasad Mahendra](https://github.com/prasadmahendra)