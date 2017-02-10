package com.markit.stock
import java.time.LocalDate

import scala.collection.mutable

abstract class HistoricalPriceService extends StockPrice {
  def getDailyPrices(ticker: String) : List[Double] = {
    val today =  LocalDate.now()
    val lastYear = today.minusYears(1)

    getClosingPrices(lastYear, today, ticker)
  }

  def getDailyReturn(ticker: String) : List[Double] = {
    val today =  LocalDate.now()
    val lastYear = today.minusYears(1).minusDays(1)

    val closingPrices = getClosingPrices(lastYear, today, ticker)

    var index = 0
    var dailyReturnList = mutable.MutableList[Double]()
    for(x <- closingPrices){
      if(index <= closingPrices.length - 2){
        dailyReturnList += x - closingPrices(index + 1)
      }
      index+=1
    }

    dailyReturnList.toList
  }

  def getMean(ticker:String) : Double = {
    val today =  LocalDate.now()
    val lastYear = today.minusYears(1)
    getPrices(lastYear, today, ticker).mean
  }
}
