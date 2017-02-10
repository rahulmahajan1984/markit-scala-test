package com.markit.stock

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class YahooPricesSpec extends FlatSpec with Matchers {
  it should "request correct url" in {
    val service = new HistoricalPriceService with YahooPrices
    val today =  LocalDate.parse("2017-02-09")
    val lastYear = today.minusYears(1)
    val url = service.getUrl(lastYear, today, "GOOG")
    url should be ("http://real-chart.finance.yahoo.com/table.csv?s=GOOG&a=1&b=9&c=2016&d=1&e=9&f=2017&g=d&ignore=.csv")
  }
}
