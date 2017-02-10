package com.markit.stock

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class StockPriceSpec extends FlatSpec with Matchers {
  it should "retrieve correct interval data" in {
    val service = new HistoricalPriceService with YahooPrices
    val today =  LocalDate.parse("2017-02-09")
    val lastYear = today.minusYears(1)
    val prices = service.getPrices(lastYear, today, "GOOG")
    prices.head.date should be (LocalDate.parse("2017-02-08"))
    prices.reverse.head.date should be (today.minusYears(1))
  }
}
