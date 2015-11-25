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
        Md5,
        Sha1,
        Sha256,
        Sha512
    };
    
    RDigester(const ChecksumAlg a);
    ~RDigester();
    size_t serialize(unsigned char *dst);
    void ctx(unsigned char *ctx);
    void update(const void *data, size_t len);
    std::string finalHex();
    
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
    
    static size_t serialize(unsigned char *dst, const MD5_CTX *ctx);
    static MD5_CTX* deserializeMd5Ctx(unsigned char *src);
    
    /*
         typedef struct SHA256state_st
         {
             SHA_LONG h[8];
             SHA_LONG Nl,Nh;
             SHA_LONG data[SHA_LBLOCK];
         unsigned int num,md_len;
         } SHA256_CTX;
     */
    
    static size_t serialize(unsigned char *dst, const SHA256_CTX *ctx);
    static SHA256_CTX* deserializeSha256Ctx(unsigned char *src);
    
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
    static size_t serialize(unsigned char *dst, const SHA512_CTX *ctx);
    static SHA512_CTX* deserializeSha512Ctx(unsigned char *src);
    
    /*
         struct SHAstate_st
         {
             SHA_LONG h0,h1,h2,h3,h4;
             SHA_LONG Nl,Nh;
             SHA_LONG data[SHA_LBLOCK];
             unsigned int num;
         } SHA_CTX;
     */
    
    static size_t serialize(unsigned char *dst, const SHA_CTX *ctx);
    static SHA_CTX* deserializeSha1Ctx(unsigned char *src);
    
    ChecksumAlg alg;
    void*       digestCtx;
};
} } } }

#endif /* RDigester_hpp */
