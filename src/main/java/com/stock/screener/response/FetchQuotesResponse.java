package com.stock.screener.response;

import java.util.List;

import com.stock.screener.dto.QuoteDTO;

public class FetchQuotesResponse {

	private List<QuoteDTO> quotes;

	public FetchQuotesResponse() {

	}

	public List<QuoteDTO> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<QuoteDTO> quotes) {
		this.quotes = quotes;
	}

}