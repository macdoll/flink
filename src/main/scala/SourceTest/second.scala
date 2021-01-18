package SourceTest

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment


object second {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val stream2 = env.readTextFile("D:\\git_code\\github\\flink\\src\\main\\resources\\temp.txt")
    stream2.print()
    env.execute()
  }
}
