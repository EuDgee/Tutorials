package ru.livetex.log.hadoop

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class HadoopFs(uri: URI) {
  val hadoopConf = new Configuration()
  hadoopConf.addResource(new Path("core-site.xml"))
  hadoopConf.addResource(new Path("hdfs-site.xml"))

  val hdfs = FileSystem.get(uri, hadoopConf)

  def delete(fileName: String) {
    hdfs.delete(new Path(fileName), true)
  }

  def create(fileName: String) = {
    hdfs.create(new Path(fileName))
  }

  def read(fileName: String) = {
    val file = hdfs.open(new Path(fileName))
    var data = ""
    var b = new Array[Byte](1024)
    var numBytes = file.read(b)
    while (numBytes > 0) {
      data ++= b.toString
      numBytes = file.read(b)
    }

    file.close()
    data
  }

  def write(fileName: String, data: String) {
    val file = this.create(fileName)
    file.write(data.getBytes, 0, data.getBytes.length)
    file.close()
  }

  def append(fileName: String, data: String): Unit = {
    val file = hdfs.append(new Path(fileName))
    file.write(data.getBytes)
    file.close()
  }

  def exists(fileName: String) = {
    hdfs.exists(new Path(fileName))
  }
}
