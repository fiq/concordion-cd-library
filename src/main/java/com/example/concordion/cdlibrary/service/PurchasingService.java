package com.example.concordion.cdlibrary.service;

import com.example.concordion.cdlibrary.domain.CreditCard;

public interface PurchasingService {
    Double purchase(String cdTitle, String cdArtist, CreditCard creditCard);
}
