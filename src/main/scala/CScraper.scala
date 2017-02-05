import com.mashape.unirest.http.Unirest
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import purecsv.safe._

/**
  * @author Salvador Guzman
  * @version 1.0.0
  */
object CScraper {
  def debug[A](a: A): A = {
    println(a)
    a
  }

  case class CSVRow(idx: Int, univ: String, score: String, rank: Int) {
    override def toString: String = s"CSVRow($idx, $univ, $score, $rank)"
  }

  def main(args: Array[String]): Unit = {
    val pagesize = 1000
    val year = 2016

    /**
      * Results of ranking query
      */
    val results = Unirest.post("http://nturanking.lis.ntu.edu.tw/DataPage/OverallRanking.aspx")
      .queryString("pagesize", pagesize)
      .queryString("y", year)
      .asString.getBody

    val jsoup = Jsoup.parse(results)
    val jsoupResults = jsoup.body.select("#MainContain_GridView1 > tbody").select("tr").toArray.tail
    val csvResults = (0 until jsoupResults.size)
      .map(idx => (idx, jsoupResults(idx))).map(_.asInstanceOf[(Int, Element)])
      .map(t =>
        (t._1 + 1, t._2.child(1).child(0).html, t._2.child(2).child(0).html, t._2.child(3).child(0).html))

    println(csvResults.map(t => s"${t._1},${t._2},${t._3},${t._4}").mkString("\n"))
  }
}
