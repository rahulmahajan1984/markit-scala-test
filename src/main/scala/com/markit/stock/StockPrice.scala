package com.markit.stock

import java.io.FileNotFoundException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

import com.markit.model.CompanyHistoricalPrice

import scala.collection.mutable
import scala.io.Source

trait StockPrice extends Prices {
def getPrices(from: LocalDate, to: LocalDate, ticker:String) : List[CompanyHistoricalPrice] = {
  var formatter = DateTimeFormatter.ofPattern("yyyy-M-dd")
  formatter = formatter.withLocale(Locale.UK)

  var list = mutable.MutableList[CompanyHistoricalPrice]()
    try {
      val lines = Source.fromURL(getUrl(from, to, ticker), "UTF-8")
        .getLines()
        .drop(1) // Drop the header

      for (line <- lines) {
        val rec = line.split(",")
        list += CompanyHistoricalPrice(LocalDate.parse(rec(0), formatter), rec(1).toDouble, rec(2).toDouble, rec(3).toDouble, rec(4).toDouble, rec(5).toLong, rec(6).toDouble)
      }
      list.toList
    }
    catch {
      case fne: FileNotFoundException => throw new IllegalArgumentException("Ticker Not Found", fne)
      case others: Exception => {
        //Do some Logging
        others.printStackTrace()
        throw others
      }
    }
  }

  def getClosingPrices(from: LocalDate, to: LocalDate, ticker:String) : List[Double] = {
    getPrices(from, to, ticker).map(_.close)
  }
}

trait Prices {
  def getUrl(from: LocalDate, to: LocalDate, ticker:String) : String
}

trait YahooPrices extends Prices {

  //Yahoo Api specific rules
  private val getMonth:Int => String = {
    case 1 => "00"
    case month => (month - 1).toString
  }

  override def getUrl(from: LocalDate, to: LocalDate, ticker:String) : String = {
    val fromMonth = getMonth(from.getMonthValue)
    val toMonth = getMonth(to.getMonthValue)

    //todo: This resource should come form a resource file, not hardcoded
    f"http://real-chart.finance.yahoo.com/table.csv?s=$ticker&a=${fromMonth}&b=${from.getDayOfMonth}" +
      f"&c=${from.getYear}&d=${toMonth}&e=${to.getDayOfMonth}&f=${to.getYear}&g=d&ignore=.csv"
  }
}


