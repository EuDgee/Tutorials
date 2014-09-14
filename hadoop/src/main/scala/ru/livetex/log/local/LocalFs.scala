package ru.livetex.log.local

import java.io.{FileOutputStream, PrintWriter, File}
import scala.io.Source

class LocalFs {

  def delete(fileName: String) {
    new File(fileName).delete()
  }

  def create(fileName: String) {
    new File(fileName).createNewFile()
  }

  def read(fileName: String) = {
    var data = ""
    Source.fromFile(fileName).foreach(c => {
      data ++= c.toString
      data
    })
    data
  }

  def write(fileName: String, data: String): Unit = {
    val writer = new PrintWriter(new File(fileName))
    writer.write(data)
    writer.close()
  }

  def append(fileName: String, data: String): Unit = {
    val writer = new PrintWriter(new FileOutputStream(new File(fileName), true))
    writer.append(data)
    writer.close()
  }

  def exists(fileName: String) = {
    new File(fileName).exists()
  }
}
