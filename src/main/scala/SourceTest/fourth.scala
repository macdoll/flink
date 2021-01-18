package SourceTest

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

import scala.util.Random
case class SensorReading(str: String, timestamp: Long, ts: Double)
object fourth {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val stream5 = env.addSource(new MySensorSource())
    stream5.print()
    env.execute()
  }
}


class MySensorSource() extends  SourceFunction[SensorReading]{
  //数据源是否运行
  var running:Boolean = true
  override def cancel(): Unit = running = false

  override def run(ctx: SourceFunction.SourceContext[SensorReading]): Unit = {
    val rand = new Random()
    var curTemps = 1.to(10).map(
      i => ("sensor_"+i, 60 + rand.nextGaussian() * 20)
    )
    while(running){
      curTemps = curTemps.map(
        data => (data._1,data._2 + rand.nextGaussian())
      )
      val curTs = System.currentTimeMillis()
      curTemps.foreach(
        data => ctx.collect(SensorReading(data._1,curTs,data._2))
      )
      Thread.sleep(1000L)
    }
  }
}