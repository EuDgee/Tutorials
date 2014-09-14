package ru.livetex.log

import java.net.URI
import org.scalatest.{FlatSpec, Matchers}
import ru.livetex.log.hadoop.HadoopFs

class Hadoop extends FlatSpec with Matchers {
  val hdfsURI = new URI("hdfs://hadoopy.ddns.net")
  val hdfs = new HadoopFs(hdfsURI)
  val folder = "/user/eudgee/"

  ignore should "create and delete a file" in {
    val fileName = folder ++ "create-delete"

    assume(!hdfs.exists(fileName), "File already exists")

    hdfs.create(fileName)
    assert(hdfs.exists(fileName), "File wasn't created")

    hdfs.delete(fileName)
    assert(!hdfs.exists(fileName), "File wasn't deleted")
  }

  ignore should "write and read data from a file" in {
    val fileName = folder ++ "write-read"
    val data = "ololo"

    hdfs.create(fileName)
    assert(hdfs.read(fileName) == "", "File wasn't empty at start")

    hdfs.write(fileName, data)
    assert(hdfs.read(fileName) == data, "File had wrong data")

    hdfs.delete(fileName)
  }
}
