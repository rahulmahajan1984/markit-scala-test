package com.markit

import com.markit.stock.{HistoricalPriceService, YahooPrices}

object Application extends App{

  val service = new HistoricalPriceService with YahooPrices

  println(service.getDailyPrices("GOOG").mkString(", "))

  println(service.getDailyReturn("GOOG").mkString(", "))

  println(service.getMean("GOOG"))
}
