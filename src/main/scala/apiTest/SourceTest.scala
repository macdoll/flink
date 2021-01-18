package apiTest

import java.util.Properties

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

case class SensorReading(id:String,timestamp:Long,tem:Double);
object SourceTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val stream1:DataStream[SensorReading] = env.fromCollection(List(
      SensorReading("sensor_1",123213,12.1),
      SensorReading("sensor_2",123223,12.2),
      SensorReading("sensor_3",123113,12.3),
      SensorReading("sensor_4",123413,12.4),
      SensorReading("sensor_5",123613,12.5),
      SensorReading("sensor_6",123713,12.6),
      SensorReading("sensor_7",123813,12.7)
    ))

    val stream2:DataStream[String] = env.readTextFile("D:\\git_code\\github\\flink\\src\\main\\resources\\temp.txt")

//    kafka
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","localhost:9092")
    properties.setProperty("group.id","consumer-group")
    properties
    val stream3 = env.addSource(new FlinkKafkaConsumer011[String]())
    stream2.print()
    stream1.print()
    env.execute("source test job")
  }

}
