package ru.livetex.log

import java.net.URI

import org.apache.hadoop.conf._
import org.apache.hadoop.fs._

object App {
  def main(args: Array[String]) {
    val hadoopConf = new Configuration()
    hadoopConf.addResource(new Path("core-site.xml"))
    hadoopConf.addResource(new Path("hdfs-site.xml"))

    val hdfs = FileSystem.get(new URI("hdfs://hadoopy.ddns.net"), hadoopConf)

    println("Scala is awesome")

    hdfs.delete(new Path("/user/eudgee/SPARK-README.md"), true)
  }
}
