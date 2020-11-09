package cn.zxg

import org.apache.flink.api.scala.ExecutionEnvironment

//引入scala下的隐式转换
import org.apache.flink.api.scala._
/**
  * object是单例对象
  * main后直接enter可以加快速度
*/
object WordCount {
  def main(args: Array[String]): Unit = {
//    创建一个批处理执行环境
//    要注意引入的是java的依赖还是scala的依赖，因为依赖出错的错遍地都是
    val env:ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
//    从文件中读取数据，这里如果给的路径不对肯定是会报错的
    val inputPath:String = "D:\\MyIDEACode\\RealTimeWarehouse\\FlinkTutorial\\src\\main\\resources\\hello.txt"
    val inputDataSet:DataSet[String] = env.readTextFile(inputPath)
//    对每行数据打散，然后按照word分组，然后计数
    val resultDataSet:DataSet[(String,Int)] = inputDataSet
      .flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0)
      .sum(1)
    resultDataSet.print()

  }
}
