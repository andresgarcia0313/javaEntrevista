package com.organization.software.service;

import com.organization.software.model.Price;
import com.organization.software.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Price getPriceForProductAndBrand(LocalDateTime dateTime, Long productId, Long brandId) {
        List<Price> prices = priceRepository.findValidPrices(brandId, productId, dateTime);

        if (prices.isEmpty()) {
            return null;
        }

        return selectPrice(prices);
    }

    private Price selectPrice(List<Price> prices) {
        prices.sort(Comparator.comparing(Price::getPriority).reversed());
        return prices.get(0);
    }
}
