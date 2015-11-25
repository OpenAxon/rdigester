package com.evidence.tools.digest

import java.util.UUID

import com.evidence.tools.digest.RDigester.Algorithm
import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.codec.digest.DigestUtils
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FlatSpec
import org.scalatest.Matchers

/**
 * Created by pmahendra on 11/25/15.
 */

class RDigesterSpec extends FlatSpec with Matchers with LazyLogging with BeforeAndAfterAll {
  it should "compute md5 digest" in {
    val data = UUID.randomUUID().toString

    val expected = DigestUtils.md5Hex(data)
    val calculated = RDigester.md5Hex(data)

    logger.info(s"calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha1 digest" in {
    val data = UUID.randomUUID().toString

    val expected = DigestUtils.sha1Hex(data)
    val calculated = RDigester.sha1Hex(data)

    logger.info(s"calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha256 digest" in {
    val data = UUID.randomUUID().toString

    val expected = DigestUtils.sha256Hex(data)
    val calculated = RDigester.sha256Hex(data)

    logger.info(s"calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha512 digest" in {
    val data = UUID.randomUUID().toString

    val expected = DigestUtils.sha512Hex(data)
    val calculated = RDigester.sha512Hex(data)

    logger.info(s"calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute md5 digest given a series of serilized ctx" in {
    val data = UUID.randomUUID().toString
    val expected = DigestUtils.md5Hex(data)

    var ctx = new RDigester(Algorithm.Md5).init()
    assert(ctx != null)

    data.foreach(char => ctx = new RDigester(Algorithm.Md5).update(ctx, Array(char.toByte), 1))
    val calculated = new RDigester(Algorithm.Md5).finalChecksumHex(ctx)

    logger.info(s"[resumable] calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha1 digest given a series of serilized ctx" in {
    val data = UUID.randomUUID().toString
    val expected = DigestUtils.sha1Hex(data)

    var ctx = new RDigester(Algorithm.Sha1).init()
    assert(ctx != null)

    data.foreach(char => ctx = new RDigester(Algorithm.Sha1).update(ctx, Array(char.toByte), 1))
    val calculated = new RDigester(Algorithm.Sha1).finalChecksumHex(ctx)

    logger.info(s"[resumable] calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha256 digest given a series of serilized ctx" in {
    val data = UUID.randomUUID().toString
    val expected = DigestUtils.sha256Hex(data)

    var ctx = new RDigester(Algorithm.Sha256).init()
    assert(ctx != null)

    data.foreach(char => ctx = new RDigester(Algorithm.Sha256).update(ctx, Array(char.toByte), 1))
    val calculated = new RDigester(Algorithm.Sha256).finalChecksumHex(ctx)

    logger.info(s"[resumable] calculated $calculated == expected $expected")
    assert(calculated == expected)
  }

  it should "compute sha512 digest given a series of serilized ctx" in {
    val data = UUID.randomUUID().toString
    val expected = DigestUtils.sha512Hex(data)

    var ctx = new RDigester(Algorithm.Sha512).init()
    assert(ctx != null)

    data.foreach(char => ctx = new RDigester(Algorithm.Sha512).update(ctx, Array(char.toByte), 1))
    val calculated = new RDigester(Algorithm.Sha512).finalChecksumHex(ctx)

    logger.info(s"[resumable] calculated $calculated == expected $expected")
    assert(calculated == expected)
  }}
