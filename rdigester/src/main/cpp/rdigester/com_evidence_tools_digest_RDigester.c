#include <jni.h>
#include "com_evidence_tools_digest_RDigester.h"
#include "RDigester.hpp"


using namespace com::evidence::tools::digest;
using namespace std;

jbyteArray toJByteArray(JNIEnv *env, unsigned char* buf, int len) {
    jbyteArray array = env->NewByteArray (len);
    env->SetByteArrayRegion (array, 0, len, reinterpret_cast<jbyte*>(buf));
    return array;
}

unsigned char* fromJByteArray(JNIEnv *env, jbyteArray array) {
    int len = env->GetArrayLength (array);
    unsigned char* buf = new unsigned char[len];
    env->GetByteArrayRegion (array, 0, len, reinterpret_cast<jbyte*>(buf));
    return buf;
}

jstring toJString(JNIEnv * env, const std::string &s) {
    return env->NewStringUTF(s.c_str());
}

jbyteArray serializeCtx(JNIEnv * env, RDigester *digester) {
    size_t ctxBufferSize = 1024;
    unsigned char ctxBuffer[ctxBufferSize];
    memset(ctxBuffer, 0, sizeof(unsigned char) * ctxBufferSize);

    size_t actualCtxBufSize = digester->serialize(ctxBuffer, ctxBufferSize);
    return toJByteArray(env, ctxBuffer, actualCtxBufSize);
}

JNIEXPORT jbyteArray JNICALL Java_com_evidence_tools_digest_RDigester_init
  (JNIEnv *env, jobject o, jint alg)
{
    RDigester digester = RDigester((RDigester::ChecksumAlg)alg);
    return serializeCtx(env, &digester);
}

JNIEXPORT jbyteArray JNICALL Java_com_evidence_tools_digest_RDigester_update
  (JNIEnv *env, jobject o, jint alg, jbyteArray serilizedCtx, jbyteArray data, jint size)
{
    RDigester digester = RDigester((RDigester::ChecksumAlg)alg, fromJByteArray(env, serilizedCtx));
    digester.update(fromJByteArray(env, data), size);

    return serializeCtx(env, &digester);
}

JNIEXPORT jstring JNICALL Java_com_evidence_tools_digest_RDigester_finalChecksumHex
  (JNIEnv *env, jobject o, jint alg, jbyteArray serilizedCtx)
{
    RDigester digester = RDigester((RDigester::ChecksumAlg)alg, fromJByteArray(env, serilizedCtx));
    std::string hash = digester.finalChecksumHex();

    return toJString(env, hash);
}
