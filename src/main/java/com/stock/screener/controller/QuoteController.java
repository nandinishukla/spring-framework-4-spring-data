package com.stock.screener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stock.screener.response.FetchQuotesResponse;
import com.stock.screener.service.QuoteService;

@Controller
@RequestMapping(value = "/quote")
public class QuoteController {

	@Autowired
	QuoteService quoteService;

	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	public String latestQuotes() {

		return "quote-list";

	}

	@RequestMapping(value = "/fetch-latest", method = RequestMethod.GET)
	@ResponseBody
	public FetchQuotesResponse fetchLatestQuotes() {

		return quoteService.fetchLatestQuotesForEachInstrument();

	}

	@RequestMapping(value = "/fetch-by-ticker", method = RequestMethod.GET)
	@ResponseBody
	public FetchQuotesResponse fetchQuotesByTicker(
			@RequestParam("ticker") String ticker,
			@RequestParam("numberOfDays") Integer numberOfDays) {

		return quoteService.fetchQuotesByTickerAndNumberOfDays(ticker,
				numberOfDays);

	}

}