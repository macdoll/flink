package SourceTest

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._

case class sensording(name:String,id:Int,tmp:Double)
object first {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val stream1:DataStream[sensording] = env.fromCollection( List(
      sensording("sensor_1",123213,12.1),
      sensording("sensor_2",123223,12.2),
      sensording("sensor_3",123113,12.3),
      sensording("sensor_4",123413,12.4)
    ))
    stream1.print()
    env.execute()
  }
}
