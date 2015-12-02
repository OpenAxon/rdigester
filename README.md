# rdigester
Resumable/Serializable Java checksum (md5, sha1, sha256, sha512) digester using openssl native libraries.

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

### Version
1.0.0

# Perf: MessageDigest vs. BouncyCastle Crypto vs. This
MacBook Pro (Retina, 15-inch, Late 2013), 2.3 GHz Intel Core i7, 16 GB 1600 MHz DDR3

```sh
      Tiny (14 bytes) blocks x 100,000,000 iterations

      MessageDigest:                                ~ 9 seconds
      BouncyCastle:                                 ~ 10 seconds
      BouncyCastle with Encoded Ctx per iter:       ~ 25 seconds
      This (OpenSSL/JNI):                           ~ 70 seconds
      
      6mb (avg block size @ axon.io) blocks x 1,000 iterations

      MessageDigest:                                ~ 40 seconds
      BouncyCastle:                                 ~ 50 seconds
      BouncyCastle with Encoded Ctx per iter:       ~ 50 seconds
      This (OpenSSL/JNI):                           ~ 40 seconds
```

# Authors
- [Prasad Mahendra](https://github.com/prasadmahendra)

License
----
Apache License, Version 2.0