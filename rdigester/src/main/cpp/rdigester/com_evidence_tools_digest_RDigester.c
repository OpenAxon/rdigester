#include <jni.h>
#include "com_evidence_tools_digest_RDigester.h"

JNIEXPORT jbyteArray JNICALL Java_com_evidence_tools_digest_RDigester_init
  (JNIEnv *e, jobject o, jstring s)
{
    jbyteArray rv;

    return rv;
}

JNIEXPORT jbyteArray JNICALL Java_com_evidence_tools_digest_RDigester_update
  (JNIEnv *env, jobject o, jbyteArray arr, jbyteArray arr2, jint size)
{
    jbyteArray rv;

    return rv;
}

JNIEXPORT jbyteArray JNICALL Java_com_evidence_tools_digest_RDigester_finalHash
  (JNIEnv *env, jobject o, jbyteArray arr)
{
    jbyteArray rv;

    return rv;
}

JNIEXPORT jstring JNICALL Java_com_evidence_tools_digest_RDigester_finalHashHex
  (JNIEnv *env, jobject o, jbyteArray arr)
{
    jstring s;

    return s;
}
