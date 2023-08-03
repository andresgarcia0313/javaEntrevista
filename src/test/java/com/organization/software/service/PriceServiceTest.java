package com.organization.software.service;

import com.organization.software.model.Price;
import com.organization.software.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Price price1 = new Price();
        price1.setBrandId(1L);
        price1.setProductId(35455L);
        price1.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        price1.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        price1.setPriceValue(35.50);
        price1.setCurrency("EUR");
        price1.setPriority(0);

        Price price2 = new Price();
        price2.setBrandId(1L);
        price2.setProductId(35455L);
        price2.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        price2.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        price2.setPriceValue(25.45);
        price2.setCurrency("EUR");
        price2.setPriority(1);

        Price price3 = new Price();
        price3.setBrandId(1L);
        price3.setProductId(35455L);
        price3.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        price3.setEndDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0));
        price3.setPriceValue(30.50);
        price3.setCurrency("EUR");
        price3.setPriority(1);

        Price price4 = new Price();
        price4.setBrandId(1L);
        price4.setProductId(35455L);
        price4.setStartDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0));
        price4.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        price4.setPriceValue(38.95);
        price4.setCurrency("EUR");
        price4.setPriority(1);

        when(priceRepository.findValidPrices(1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0, 0)))
                .thenReturn(Arrays.asList(price1));
        when(priceRepository.findValidPrices(1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0, 0)))
                .thenReturn(Arrays.asList(price2));
        when(priceRepository.findValidPrices(1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0, 0)))
                .thenReturn(Arrays.asList(price2)); // Price2 is still valid at 21:00 on day 14
        when(priceRepository.findValidPrices(1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0, 0)))
                .thenReturn(Arrays.asList(price3));
        when(priceRepository.findValidPrices(1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0, 0)))
                .thenReturn(Arrays.asList(price4)); // Price4 is valid on day 16

        // Add more test cases for all the scenarios.
    }

    @Test
    public void testGetPrice_At10AMOnDay14_Product35455_Brand1() {
        Price price = priceService.getPriceForProductAndBrand(LocalDateTime.of(2020, 6, 14, 10, 0, 0), 35455L, 1L);
        assertEquals(35.50, price.getPriceValue());
    }

    @Test
    public void testGetPrice_At4PMOnDay14_Product35455_Brand1() {
        Price price = priceService.getPriceForProductAndBrand(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 35455L, 1L);
        assertEquals(25.45, price.getPriceValue());
    }

    @Test
    public void testGetPrice_At9PMOnDay14_Product35455_Brand1() {
        Price price = priceService.getPriceForProductAndBrand(LocalDateTime.of(2020, 6, 14, 21, 0, 0), 35455L, 1L);
        assertEquals(25.45, price.getPriceValue());
    }

    @Test
    public void testGetPrice_At10AMOnDay15_Product35455_Brand1() {
        Price price = priceService.getPriceForProductAndBrand(LocalDateTime.of(2020, 6, 15, 10, 0, 0), 35455L, 1L);
        assertEquals(30.50, price.getPriceValue());
    }

    @Test
    public void testGetPrice_At9PMOnDay16_Product35455_Brand1() {
        Price price = priceService.getPriceForProductAndBrand(LocalDateTime.of(2020, 6, 16, 21, 0, 0), 35455L, 1L);
        assertEquals(38.95, price.getPriceValue());
    }
}
