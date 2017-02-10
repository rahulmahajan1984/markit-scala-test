package com.markit

import java.time.LocalDate

package object model {
  case class CompanyHistoricalPrice(date: LocalDate, open: Double, high:Double,	low: Double, close: Double, volume: Long, adjClose: Double)

  implicit class ImplHistoricalPricesUtils(values: Seq[CompanyHistoricalPrice]) {
    def mean: Double = values.map(_.close).sum / values.length
  }
}
