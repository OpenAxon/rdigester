//
//  main.cpp
//  rdigester
//
//  Created by Prasad Mahendra on 11/19/15.
//  Copyright © 2015 TASER International. All rights reserved.
//

#include <iostream>
#include <openssl/sha.h>
#include <openssl/md5.h>
#include "RDigester.hpp"

using namespace com::evidence::tools::digest;

class RDigesterTester
{
public:
    static void sha512Test() {
        unsigned char* digestState = new unsigned char[2048];
        memset(digestState, 0, sizeof(unsigned char) * 2048);
        
        {
            std::cout << "digest 512 ...\n";
            RDigester rdigester = RDigester(RDigester::Sha512);
            rdigester.update("Hello, ", 7);
            
            std::cout << "save state ...\n";
            rdigester.serialize(digestState);
        }
        
        {
            std::cout << "restore state ...\n";
            
            RDigester rdigester = RDigester(RDigester::Sha512);
            rdigester.ctx(digestState);
            
            std::cout << "digest ...\n";
            rdigester.update("world!2", 7);
            
            std::string checksum = rdigester.finalHex();
            std::cout << "checksum (1) == " << checksum << "\n";
        }
        
        {
            SHA512_CTX ctx;
            SHA512_Init(&ctx);
            SHA512_Update(&ctx, "Hello, ", 7);
            SHA512_Update(&ctx, "world!2", 7);
            
            // When you're done with the data, finalize it:
            unsigned char hash2[SHA512_DIGEST_LENGTH];
            SHA512_Final(hash2, &ctx);
            std::cout << "checksum (2) == " << RDigester::toHex(hash2, SHA512_DIGEST_LENGTH) << "\n";
        }
        
        delete digestState;
    }


    static void sha256Test() {
        unsigned char* digestState = new unsigned char[2048];
        memset(digestState, 0, sizeof(unsigned char) * 2048);
        
        {
            std::cout << "digest 256 ...\n";
            RDigester rdigester = RDigester(RDigester::Sha256);
            rdigester.update("Hello, ", 7);
            
            std::cout << "save state ...\n";
            rdigester.serialize(digestState);
        }
        
        {
            std::cout << "restore state ...\n";
            
            RDigester rdigester = RDigester(RDigester::Sha256);
            rdigester.ctx(digestState);
            
            std::cout << "digest ...\n";
            rdigester.update("world!2", 7);
            
            std::string checksum = rdigester.finalHex();
            std::cout << "checksum (1) == " << checksum << "\n";
        }
        
        {
            SHA256_CTX ctx;
            SHA256_Init(&ctx);
            SHA256_Update(&ctx, "Hello, ", 7);
            SHA256_Update(&ctx, "world!2", 7);
            
            // When you're done with the data, finalize it:
            unsigned char hash2[SHA256_DIGEST_LENGTH];
            SHA256_Final(hash2, &ctx);
            std::cout << "checksum (2) == " << RDigester::toHex(hash2, SHA256_DIGEST_LENGTH) << "\n";
        }
        
        delete digestState;
    }

    static void sha1Test() {
        unsigned char* digestState = new unsigned char[1024];
        memset(digestState, 0, sizeof(unsigned char) * 1024);
        
        {
            std::cout << "digest sha1 ...\n";
            RDigester rdigester = RDigester(RDigester::Sha1);
            rdigester.update("Hello, ", 7);
            
            std::cout << "save state ...\n";
            rdigester.serialize(digestState);
        }
        
        {
            std::cout << "restore state ...\n";
            
            RDigester rdigester = RDigester(RDigester::Sha1);
            rdigester.ctx(digestState);
            
            std::cout << "digest ...\n";
            rdigester.update("world!2", 7);
            
            std::string checksum = rdigester.finalHex();
            std::cout << "checksum (1) == " << checksum << "\n";
        }

        {
            SHA_CTX ctx;
            SHA1_Init(&ctx);
            SHA1_Update(&ctx, "Hello, ", 7);
            SHA1_Update(&ctx, "world!2", 7);
            
            // When you're done with the data, finalize it:
            unsigned char hash2[SHA_DIGEST_LENGTH];
            SHA1_Final(hash2, &ctx);
            std::cout << "checksum (2) == " << RDigester::toHex(hash2, SHA_DIGEST_LENGTH) << "\n";
        }
        
        delete digestState;
    }

    static void md5Test() {
        unsigned char* digestState = new unsigned char[1024];
        memset(digestState, 0, sizeof(unsigned char) * 1024);
        
        {
            std::cout << "digest md5 ...\n";
            RDigester rdigester = RDigester(RDigester::Md5);
            rdigester.update("Hello, ", 7);
            
            std::cout << "save state ...\n";
            rdigester.serialize(digestState);
        }
        
        {
            std::cout << "restore state ...\n";
            
            RDigester rdigester = RDigester(RDigester::Md5);
            rdigester.ctx(digestState);
            
            std::cout << "digest ...\n";
            rdigester.update("world!2", 7);
            rdigester.update("world!2", 7);
            
            std::string checksum = rdigester.finalHex();
            std::cout << "checksum (1) == " << checksum << "\n";
        }
        
        {
            MD5_CTX ctx;
            MD5_Init(&ctx);
            MD5_Update(&ctx, "Hello, ", 7);
            MD5_Update(&ctx, "world!2", 7);
            MD5_Update(&ctx, "world!2", 7);
            
            // When you're done with the data, finalize it:
            unsigned char hash2[MD5_DIGEST_LENGTH];
            MD5_Final(hash2, &ctx);
            std::cout << "checksum (2) == " << RDigester::toHex(hash2, MD5_DIGEST_LENGTH) << "\n";
        }
        
        delete digestState;
    }
};

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Resumable digests ...\n";
    
    
    RDigesterTester::sha1Test();
    RDigesterTester::sha256Test();
    RDigesterTester::sha512Test();
    RDigesterTester::md5Test();

    return 0;
}


