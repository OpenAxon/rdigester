# rdigester
Serializable Java checksum (md5, sha1, sha256, sha512 using openssl native libraries) digester.

```sh
    var digester = new RDigester(Algorithm.Sha256)
    var ctx = digester.init()
    ...
    digester = new RDigester(Algorithm.Sha256)
    ctx = digester.update(ctx, "Hello World  1".getBytes, 14)
    ...
    digester = new RDigester(Algorithm.Sha256)
    ctx = digester.update(ctx, "Hello World  2".getBytes, 14)
    ...    
    val finalCheckSum = digester.finalChecksumHex(ctx)
```

# authors
- [Prasad Mahendra](https://github.com/prasadmahendra)