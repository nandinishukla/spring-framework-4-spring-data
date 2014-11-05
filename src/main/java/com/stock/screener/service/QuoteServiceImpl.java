package com.stock.screener.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stock.screener.dto.QuoteDTO;
import com.stock.screener.model.Instrument;
import com.stock.screener.model.Quote;
import com.stock.screener.repository.InstrumentRepository;
import com.stock.screener.repository.QuoteRepository;
import com.stock.screener.response.FetchQuotesResponse;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	QuoteRepository quoteRepository;

	@Autowired
	InstrumentRepository instrumentRepository;

	@Override
	@Transactional
	public FetchQuotesResponse fetchLatestQuotesForEachInstrument() {

		FetchQuotesResponse response = new FetchQuotesResponse();

		List<Instrument> allInstruments = instrumentRepository.findAll();

		List<QuoteDTO> allQuotes = new ArrayList<QuoteDTO>();

		for (Instrument instrument : allInstruments) {

			List<Quote> quotes = quoteRepository
					.findByInstrument(instrument, new PageRequest(0, 1,
							new Sort(Sort.Direction.DESC, "date")));

			if (quotes.size() > 0) {

				Quote quote = quotes.get(0);

				QuoteDTO quoteDTO = new QuoteDTO();

				quoteDTO.setClose(quote.getClose());
				quoteDTO.setDate(quote.getDate());
				quoteDTO.setHigh(quote.getHigh());
				quoteDTO.setLow(quote.getLow());
				quoteDTO.setOpen(quote.getOpen());
				quoteDTO.setIndustry(quote.getInstrument().getIndustry());
				quoteDTO.setName(quote.getInstrument().getName());
				quoteDTO.setSector(quote.getInstrument().getSector());
				quoteDTO.setTicker(quote.getInstrument().getTicker());

				allQuotes.add(quoteDTO);

			}

		}

		response.setQuotes(allQuotes);

		return response;

	}

	@Override
	@Transactional
	public FetchQuotesResponse fetchQuotesByTickerAndNumberOfDays(
			String ticker, Integer numberOfDays) {

		FetchQuotesResponse response = new FetchQuotesResponse();

		Instrument instrument = instrumentRepository.findByTicker(ticker);

		if (instrument == null) {
			return response;
		}

		List<Quote> quotes = quoteRepository.findByInstrument(instrument,
				new PageRequest(0, numberOfDays, new Sort(Sort.Direction.DESC,
						"date")));

		List<QuoteDTO> allQuotes = new ArrayList<QuoteDTO>();

		for (Quote quote : quotes) {

			QuoteDTO quoteDTO = new QuoteDTO();

			quoteDTO.setClose(quote.getClose());
			quoteDTO.setDate(quote.getDate());
			quoteDTO.setHigh(quote.getHigh());
			quoteDTO.setLow(quote.getLow());
			quoteDTO.setOpen(quote.getOpen());
			quoteDTO.setIndustry(quote.getInstrument().getIndustry());
			quoteDTO.setName(quote.getInstrument().getName());
			quoteDTO.setSector(quote.getInstrument().getSector());
			quoteDTO.setTicker(quote.getInstrument().getTicker());

			allQuotes.add(quoteDTO);

		}

		response.setQuotes(allQuotes);

		return response;

	}

}