package com.organization.software;

import com.organization.software.model.Price;
import com.organization.software.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crea objetos Price con los datos de ejemplo y guárdalos en la base de datos
        Price price1 = new Price();
        price1.setBrandId(1L);
        price1.setProductId(35455L);
        price1.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price1.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price1.setPriceValue(35.50);
        price1.setCurrency("EUR");

        Price price2 = new Price();
        price2.setBrandId(1L);
        price2.setProductId(35455L);
        price2.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        price2.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));
        price2.setPriceValue(25.45);
        price2.setCurrency("EUR");

        Price price3 = new Price();
        price3.setBrandId(1L);
        price3.setProductId(35455L);
        price3.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        price3.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));
        price3.setPriceValue(30.50);
        price3.setCurrency("EUR");

        Price price4 = new Price();
        price4.setBrandId(1L);
        price4.setProductId(35455L);
        price4.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        price4.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price4.setPriceValue(38.95);
        price4.setCurrency("EUR");

        // Guardar los objetos Price en la base de datos
        priceRepository.save(price1);
        priceRepository.save(price2);
        priceRepository.save(price3);
        priceRepository.save(price4);
    }
}
