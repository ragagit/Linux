import scala.util.matching.Regex
import scala.util.parsing.combinator._
import scala.util.parsing.input._

//object MyParser extends App {
//
//  case class WordFreq(word: String, count: Int) {
//    override def toString = s"Word <$word> occurs with frequency $count"
//  }
//
//  class SimpleParser extends RegexParsers {
//    def word: Parser[String]   = """[a-z]+""".r       ^^ { _.toString }
//    def number: Parser[Int]    = """(0|[1-9]\d*)""".r ^^ { _.toInt }
//    def freq: Parser[WordFreq] = word ~ number        ^^ { case wd ~ fr => WordFreq(wd,fr) }
//  }
//
//  val p = new SimpleParser
//
//  p.parse(p.freq, "johnny 121") match {
//    case Success(matched,_) => println(matched)
//    case Failure(msg,_) => println(s"FAILURE: $msg")
//    case Error(msg,_) => println(s"ERROR: $msg")
//
//}

case class WordFreq(word: String, count: Int) {
  override def toString = s"Word <$word> occurs with frequency $count"
}

class SimpleParser extends RegexParsers {
  def word: Parser[String]   = """[a-z]+""".r       ^^ { _.toString }
  def numberr: Parser[Int]    = """(0|[1-9]\d*)""".r ^^ { _.toInt }
  def freq: Parser[WordFreq] = word ~ number        ^^ { case wd ~ fr => WordFreq(wd,fr) }

  val eol: Parser[String] = ".*".r
  val eow: Parser[String] = "\\S+".r
  val eown: Parser[String] = "\\S+(\\n|\\r)+".r // last word of current line, works better than eol sometimes. It'd skip empty lines.
  val eoc: Parser[String] = "\\S+(\\ \\S+)*".r // "end of column", single spaces could be observed within data column.
  def eocLimit(count: Int): Parser[String] = ("\\S+(\\ \\S+){0," + count + "}").r
  val eos: Parser[String] = "(\\S|\\s)*+".r
  val number: Parser[Int] = """\d+""".r ^^ { case str => str.toInt }
  val long: Parser[Long] = """\d+""".r ^^ { case str => str.toLong }
  val double: Parser[Double] = """\d+(\.\d+)?""".r ^^ { case str => str.toDouble }
  def eowOr(until: String): Parser[String] = regex(new Regex(s"\\S+(?=${Regex.quote(until)})|\\S+"))
  def eowOr(until: Regex): Parser[String] = regex(new Regex(s"\\S+(?=$until)|\\S+"))
  def eolOr(until: String): Parser[String] = regex(new Regex(s"((?!${Regex.quote(until)}).)*"))
  def eolOr(until: Regex): Parser[String] = regex(new Regex(s"((?!$until).)*"))

  def uptoAndInc(s: String): Parser[CharSequence] = { // these tokens are often ignored, leave them as CharSequence
    require(s.nonEmpty)
    if (s.length == 1) {
      uptoAndInc(s.charAt(0))
    } else {
      uptoAndInc(Regex.quote(s).r)
    }
  }

  def uptoAndInc(r: Regex): Parser[CharSequence] = new Parser[CharSequence] { // these tokens are often ignored, leave them as CharSequence
    def apply(in: Input) = {
      val source = in.source
      val offset = in.offset
      val rest = source.subSequence(offset, source.length)
      r.findFirstMatchIn(rest) match {
        case Some(matched) =>
          val token = source.subSequence(offset, offset + matched.end)
          val unparsed = in.drop(matched.end)
          Success(token, unparsed)
        case None =>
          Failure("reached end of string without finding " + r, in)
      }
    }
  }
  def uptoAndInc(c: Char): Parser[CharSequence] = new Parser[CharSequence] { // these tokens are often ignored, leave them as CharSequence
    def apply(in: Input) = {
      val source = in.source
      val offset = in.offset
      val len = source.length()
      var i = offset
      while (i < len && source.charAt(i) != c) {
        i += 1
      }
      if (i < len) {
        val token = source.subSequence(offset, i + 1)
        val unparsed = in.drop((i + 1) - offset)
        Success(token, unparsed)
      } else {
        Failure(s"reached end of string without finding '$c'", in)
      }
    }
  }

  def until(s: String): Parser[String] = until(s.r)
  def until(r: Regex): Parser[String] = new Parser[String] {
    def apply(in: Input) = {
      val source = in.source
      val offset = in.offset
      val rest = source.subSequence(offset, source.length)
      r.findFirstMatchIn(rest) match {
        case Some(matched) =>
          val token = source.subSequence(offset, offset + matched.start).toString
          val unparsed = in.drop(matched.start)
          Success(token, unparsed)
        case None =>
          Failure("reached end of string without finding " + r, in)
      }
    }
  }

  def uptoAndIncOrAll(r: Regex): Parser[String] = new Parser[String] {
    def apply(in: Input) = {
      val source = in.source
      val offset = in.offset
      val rest = source.subSequence(offset, source.length)
      r.findFirstMatchIn(rest) match {
        case Some(matched) =>
          val token = source.subSequence(offset, offset + matched.end).toString
          val unparsed = in.drop(matched.end)
          Success(token, unparsed)
        case None =>
          if (rest.length > 0) {
            val token = rest.toString
            val unparsed = in.drop(rest.length)
            Success(token, unparsed)
          } else {
            Failure("could not get substring", in)
          }
      }
    }
  }


}



object MyParser extends SimpleParser {
  def main(args: Array[String]) = {

    val output =
      """|auvik@ICX-REN-pa220-01(active)> show routing route
         |
         |flags: A:active, ?:loose, C:connect, H:host, S:static, ~:internal, R:rip, O:ospf, B:bgp,
         |       Oi:ospf intra-area, Oo:ospf inter-area, O1:ospf ext-type-1, O2:ospf ext-type-2, E:ecmp, M:multicast
         |
         |
         |VIRTUAL ROUTER: Primary-VR (id 1)
         |  ==========
         |destination                                 nexthop                                 metric flags      age   interface          next-AS
         |0.0.0.0/0                                   12.185.156.169                          10     A S              ethernet1/2
         |10.159.160.0/20                             vr Backup-VR                            10     A S              Primary-VR/i3
         |12.185.156.168/29                           12.185.156.170                          0      A C              ethernet1/2
         |12.185.156.170/32                           0.0.0.0                                 0      A H
         |192.168.4.0/24                              vr Backup-VR                            10     A S              Primary-VR/i3
         |total routes shown: 5
         |
         |VIRTUAL ROUTER: Backup-VR (id 2)
         |  ==========
         |destination                                 nexthop                                 metric flags      age   interface          next-AS
         |0.0.0.0/0                                   74.42.95.193                            10     A S              ethernet1/4
         |10.5.1.0/24                                 10.64.85.246                                   A B        443905 tunnel.1           64520
         |10.10.1.0/24                                10.64.85.246                                   A B        443905 tunnel.1           64520
         |total routes shown: 52
      """.stripMargin

    val output1 =
      """|auvik@ICX-REN-pa220-01(active)> show routing route
         |
         |flags: A:active, ?:loose, C:connect, H:host, S:static, ~:internal, R:rip, O:ospf, B:bgp,
         |       Oi:ospf intra-area, Oo:ospf inter-area, O1:ospf ext-type-1, O2:ospf ext-type-2, E:ecmp, M:multicast
         |
         |
         |VIRTUAL ROUTER: Primary-VR (id 1)
         |  ==========
         |destination                                 nexthop                                 metric flags      age   interface          next-AS
         |0.0.0.0/0                                   12.185.156.169                          10     A S              ethernet1/2
         |10.159.160.0/20                             vr Backup-VR                            10     A S              Primary-VR/i3
         |12.185.156.168/29                           12.185.156.170                          0      A C              ethernet1/2
         |12.185.156.170/32                           0.0.0.0                                 0      A H
         |192.168.4.0/24                              vr Backup-VR                            10     A S              Primary-VR/i3
         |total routes shown: 5
      """.stripMargin



//    def route = rep((uptoAndInc("VIRTUAL ROUTER:")) ~> until("total".r)) ^^ {
//
//          case route1 =>
//            //route1.flatMap((a,b) => a.toString().concat(b))
//          println(route1)
//    }
//    parse(route, output) match {
//      case Success(matched,_) => println("Success")
//      case Failure(msg,_) => println(s"Failure: $msg")
//      case Error(msg,_) => println(s"ERROR: $msg")
//    }

//    parse(freq, "johnny 121") match {
//      case Success(matched,_) => println(matched)
//      case Failure(msg,_) => println(s"FAILURE: $msg")
//      case Error(msg,_) => println(s"ERROR: $msg")
//    }

    val output3 = """bash-2.04# cat /proc/net/vlan/config
                 |VLAN Dev name    | VLAN ID
                 |Name-Type: VLAN_NAME_TYPE_RAW_PLUS_VID_NO_PAD  bad_proto_recvd: 0
                 |eth2.100       | 100  | eth2
                 |eth2.200       | 200  | eth2
                 |eth2.300       | 300  | eth2
               """.stripMargin

//    def vlans = uptoAndInc("Name-Type:") ~ eol ^^ {
//      case vlan =>
//        println(vlan)
//    }
//
//        parse(vlans, output3) match {
//          case Success(matched,_) => println("Success")
//          case Failure(msg,_) => println(s"Failure: $msg")
//          case Error(msg,_) => println(s"ERROR: $msg")
//        }

    case class ShowVersion( deviceId: Long, version: String)
    case class ShowDot11Assoc( deviceId: Long)
    case class ShowInterface( deviceId: Long, date: java.util.Date)

    val showVersion = ShowVersion(12345, "2.3")
    val showDot11Assoc1 = ShowDot11Assoc(12345)
    val showDot11Assoc2 = ShowDot11Assoc(12345)
    val showInterface = ShowInterface(12345, new java.util.Date())
    val mydate = new java.util.Date(2019,4,12)

    //val myMaps = Iterable( Map( "Table1" -> List(showVersion), "Table2" -> List(showDot11Assoc1,showDot11Assoc2), "Table3" -> List(showInterface) ) )
    val myMaps = Option(Map( "Table1" -> List(showVersion), "Table2" -> List(showDot11Assoc1,showDot11Assoc2), "Table3" -> List(showInterface) ))

    //val MyMapsFlat = myMaps.flatMap(x => x.values.flatMap(x => x)).map( s => if(s.isInstanceOf[ShowInterface]) s.asInstanceOf[ShowInterface].copy(date = mydate))
    val MyMapsFlat = myMaps.get.mapValues( v => v.map(s => if(s.isInstanceOf[ShowInterface]){ s.asInstanceOf[ShowInterface].copy(date = mydate) } else { s }))

    MyMapsFlat.foreach(println)

  }
}
