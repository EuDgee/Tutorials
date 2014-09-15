package ru.livetex.log

import org.scalatest.{FlatSpec, Matchers}
import ru.livetex.log.local.LocalFs

class Local extends FlatSpec with Matchers {
  val fs = new LocalFs

  val folder = "/Users/eudgee/"

  it should "create and delete a file" in {
    val fileName = folder ++ "create-delete"

    assume(!fs.exists(fileName), "File already exists")

    fs.create(fileName)
    assert(fs.exists(fileName), "File wasn't created")

    fs.delete(fileName)
    assert(!fs.exists(fileName), "File wasn't deleted")
  }

  it should "write and read data from a file" in {
    val fileName = folder ++ "write-read"
    val data = "ololo"

    fs.create(fileName)
    assert(fs.read(fileName) == "", "File wasn't empty at start")

    fs.write(fileName, data)
    assert(fs.read(fileName) == data, "File had wrong data")

    fs.delete(fileName)
  }

  it should "append data to a file" in {
    val fileName = folder ++ "append"
    val data1 = "uu"
    val data2 = "ff"

    fs.create(fileName)
    assert(fs.read(fileName) == "", "File wasn't empty at start")

    fs.append(fileName, data1)
    assert(fs.read(fileName) == data1, "File had wrong data")

    fs.append(fileName, data2)
    assert(fs.read(fileName) == data1 ++ data2, "File had wrong data after " +
      "second append")

    fs.delete(fileName)
  }
}
