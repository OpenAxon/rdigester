//
//  RDigester.hpp
//  rdigester
//
//  Created by Prasad Mahendra on 11/24/15.
//  Copyright © 2015 TASER International. All rights reserved.
//

#ifndef RDigester_hpp
#define RDigester_hpp

#include <iostream>
#include <openssl/sha.h>
#include <openssl/md5.h>


namespace com { namespace evidence { namespace tools { namespace digest
{
class RDigester
{
public:
    enum ChecksumAlg {
        Md5 = 0,
        Sha1 = 1,
        Sha256 = 2,
        Sha512 = 3
    };
    
    RDigester(ChecksumAlg a);
    RDigester(ChecksumAlg a, unsigned char *ctxSerialized);
    ~RDigester();
    size_t serialize(unsigned char *dst, size_t dstSize);
    void setCtx(unsigned char *ctxSerialized);
    void update(const void *data, size_t len);
    std::string finalChecksumHex();
    
    static std::string toHex(unsigned char *data, int len);
private:
    /*
        typedef struct MD5state_st
        {
            MD5_LONG A,B,C,D;
            MD5_LONG Nl,Nh;
            MD5_LONG data[MD5_LBLOCK];
            unsigned int num;
        } MD5_CTX;
    */
    
    size_t serialize(unsigned char *dst, size_t dstSize, const MD5_CTX *ctx);
    MD5_CTX* deserializeMd5Ctx(unsigned char *src);
    
    /*
         typedef struct SHA256state_st
         {
             SHA_LONG h[8];
             SHA_LONG Nl,Nh;
             SHA_LONG data[SHA_LBLOCK];
         unsigned int num,md_len;
         } SHA256_CTX;
     */
    
    size_t serialize(unsigned char *dst, size_t dstSize, const SHA256_CTX *ctx);
    SHA256_CTX* deserializeSha256Ctx(unsigned char *src);
    
    /*
         typedef struct SHA512state_st
         {
             SHA_LONG64 h[8];
             SHA_LONG64 Nl,Nh;
             union {
             SHA_LONG64	d[SHA_LBLOCK];
             unsigned char	p[SHA512_CBLOCK];
             } u;
             unsigned int num,md_len;
         } SHA512_CTX;
     */
    size_t serialize(unsigned char *dst, size_t dstSize, const SHA512_CTX *ctx);
    SHA512_CTX* deserializeSha512Ctx(unsigned char *src);
    
    /*
         struct SHAstate_st
         {
             SHA_LONG h0,h1,h2,h3,h4;
             SHA_LONG Nl,Nh;
             SHA_LONG data[SHA_LBLOCK];
             unsigned int num;
         } SHA_CTX;
     */
    
    size_t serialize(unsigned char *dst, size_t dstSize, const SHA_CTX *ctx);
    SHA_CTX* deserializeSha1Ctx(unsigned char *src);
    
    ChecksumAlg alg;
    void*       digestCtx;
};
} } } }

#endif /* RDigester_hpp */
