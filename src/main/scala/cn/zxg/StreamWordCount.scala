package cn.zxg

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class StreamWordCount {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(4)
    val inputDataStream = env.socketTextStream("localhost",7777)
    val resultDataStream = inputDataStream
      .flatMap(_.split(" "))
      .map((_,1))
      .keyBy(0)
      .sum(1)
    resultDataStream.print()
//    启动一个进程，来一个事件处理一下
    env.execute("stream word count")
  }
}
