package com.resellerapp.service;

import com.resellerapp.model.OfferCreateBindingModel;
import com.resellerapp.model.OfferHomeDTO;

import java.util.UUID;

public interface OfferService {

    OfferHomeDTO getOffersFromHomePage();

    boolean create(OfferCreateBindingModel offerCreateBindingModel);

    void buy(UUID id);
}
