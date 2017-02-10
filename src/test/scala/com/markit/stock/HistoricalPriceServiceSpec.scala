package com.markit.stock
import java.time.LocalDate

import org.scalatest._

class HistoricalPriceServiceSpec extends FlatSpec with Matchers {
  it should "have correct number of records in daily Prices" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyPrices("GOOG")
    prices.length should be (253)
  }

  it should "have 808.380005 as top item for Daily Prices" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyPrices("GOOG")
    prices.head should be (808.380005)
  }

  it should "have 678.109985 as last item for Daily Prices" in {
    val service = new HistoricalPriceService with YahooPrices
    val prices = service.getDailyPrices("GOOG")
    prices.reverse.head should be(678.109985)
  }

  it should "throw IllegalArgumentException if wrong ticker is given" in {
    val service = new HistoricalPriceService with YahooPrices
    a [IllegalArgumentException] should be thrownBy {
      service.getDailyPrices("Unknown")
    }
  }
}