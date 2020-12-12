package cn.zxg.Source

import org.apache.flink.streaming.api.scala._

//定义一个温度传感器，定义一个样例类
case class SensorReading(id:String,timestamp:Long,temperature:Double)

object SourceTest {
  def main(args: Array[String]): Unit = {
//    创建执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    从集合中读取数据
    val dataList = List(
      SensorReading("sensor1",6127,7),
      SensorReading("sensor2",2123,7),
      SensorReading("sensor3",6123,7),
      SensorReading("sensor4",6435,7)
    )
    val stream1 = env.fromCollection(dataList)
    val inputPath = "D:\\git_code\\github\\flink\\src\\main\\resources\\temp.txt"
    val stream2 = env.readTextFile(inputPath)
    stream2.print()
    stream1.print()
//从文件中读取数据
    env.execute("source test")
  }
}
