package com.stock.screener.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.screener.model.Instrument;
import com.stock.screener.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

	List<Quote> findByInstrument(Instrument instrument);

	List<Quote> findByInstrument(Instrument instrument, Pageable pageable);

}