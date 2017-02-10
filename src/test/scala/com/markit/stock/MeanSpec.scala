package com.markit.stock

import java.time.LocalDate

import org.scalatest._

class MeanSpec extends FlatSpec with Matchers {
  it should "have mean value as 752.4103164466402" in {
    val service = new HistoricalPriceService with YahooPrices
    val mean = service.getMean("GOOG")
    mean should be(752.4103164466402)
  }
}