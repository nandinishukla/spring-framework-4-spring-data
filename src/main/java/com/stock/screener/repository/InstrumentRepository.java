package com.stock.screener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.screener.model.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	Instrument findByTicker(String ticker);

}