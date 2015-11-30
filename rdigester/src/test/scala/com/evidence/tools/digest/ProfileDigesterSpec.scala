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

package com.evidence.tools.digest

import java.security.MessageDigest
import com.evidence.tools.digest.RDigester.Algorithm
import com.typesafe.scalalogging.LazyLogging
import org.joda.time.{Seconds, DateTime}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import org.bouncycastle.crypto.digests.SHA256Digest
import org.apache.commons.codec.binary.Hex

/**
 * Created by pmahendra on 11/25/15.
 */

class ProfileDigesterSpec extends FlatSpec with Matchers with LazyLogging with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
  }

  /*
      MacBook Pro (Retina, 15-inch, Late 2013), 2.3 GHz Intel Core i7, 16 GB 1600 MHz DDR3
      MessageDigest:  ~ 8 seconds
      BouncyCastle:   ~10 seconds
      OpenSSL/JNI:    ~77 seconds
   */

  it should "test how fast is java messagedigest (sha-256) impl?" in {
    var counter = 0
    val startTime = DateTime.now()
    val digester = MessageDigest.getInstance("SHA-256")
    val data = "Hello World".getBytes

    while( counter < 100000000 ) {
      digester.update(data)
      counter += 1
    }

    val elapsed = Seconds.secondsBetween(startTime, DateTime.now()).getSeconds
    logger.info(s"messagedigest elapsed: $elapsed counter: $counter ${Hex.encodeHexString(digester.digest())}")
  }

  it should "test how fast is bouncy castle (sha-256) impl?" in {
    var counter = 0
    val startTime = DateTime.now()
    val digester = new SHA256Digest()
    val data = "Hello World".getBytes

    while( counter < 100000000 ) {
      digester.update(data, 0, data.length)
      counter += 1
    }

    val resBuf:Array[Byte] = new Array[Byte](digester.getDigestSize())
    digester.doFinal(resBuf, 0)

    val elapsed = Seconds.secondsBetween(startTime, DateTime.now()).getSeconds
    logger.info(s"bouncycastle elapsed: $elapsed counter: $counter ${Hex.encodeHexString(resBuf)}")
  }

  it should "test how fast is the openssl over jni (sha-256) impl?" in {
    var counter = 0
    val startTime = DateTime.now()
    val digester = new RDigester(Algorithm.Sha256)
    val data = "Hello World".getBytes

    while( counter < 100000000 ) {
      digester.update(data, data.length)
      counter += 1
    }

    val elapsed = Seconds.secondsBetween(startTime, DateTime.now()).getSeconds
    logger.info(s"openssl elapsed: $elapsed counter: $counter ${digester.finalChecksumHex()}")
  }
}

