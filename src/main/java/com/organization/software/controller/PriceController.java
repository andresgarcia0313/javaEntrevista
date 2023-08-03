package com.organization.software.controller;

import com.organization.software.model.Price;
import com.organization.software.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public ResponseEntity<Price> getPrice(@RequestParam("date") String dateTimeString,
                                          @RequestParam("productId") Long productId,
                                          @RequestParam("brandId") Long brandId) {

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);

        Price selectedPrice = priceService.getPriceForProductAndBrand(dateTime, productId, brandId);

        if (selectedPrice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(selectedPrice, HttpStatus.OK);
    }
}
