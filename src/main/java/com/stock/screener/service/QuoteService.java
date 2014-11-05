package com.stock.screener.service;

import com.stock.screener.response.FetchQuotesResponse;

public interface QuoteService {

	FetchQuotesResponse fetchLatestQuotesForEachInstrument();

	FetchQuotesResponse fetchQuotesByTickerAndNumberOfDays(String ticker,
			Integer numberOfDays);

}