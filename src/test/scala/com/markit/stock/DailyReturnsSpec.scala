package com.markit.stock

import org.scalatest.{FlatSpec, Matchers}

class DailyReturnsSpec extends FlatSpec with Matchers {
  it should "have correct number of records" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyReturn("GOOG")
    prices.length should be (253)
  }

  it should "have top item as 1.410033999999996" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyReturn("GOOG")
    prices.head should be (1.410033999999996)
  }

  it should "have last item as -4.630004999999983" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyReturn("GOOG")
    prices.reverse.head should be(-4.630004999999983)
  }
}
