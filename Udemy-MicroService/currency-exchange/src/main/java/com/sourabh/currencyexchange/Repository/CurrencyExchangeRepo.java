package com.sourabh.currencyexchange.Repository;

import com.sourabh.currencyexchange.Entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange ,Long> {
    CurrencyExchange findByFromAndTo(String from ,String to);
}
