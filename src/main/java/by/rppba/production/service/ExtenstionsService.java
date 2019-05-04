package by.rppba.production.service;

import by.rppba.production.dao.CountryRepository;
import by.rppba.production.dao.CurrencyRepository;
import by.rppba.production.dao.UnitQtyRepository;
import by.rppba.production.model.CountryCode;
import by.rppba.production.model.Currency;
import by.rppba.production.model.UnitQty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExtenstionsService {
    private final CurrencyRepository currencyRepository;

    private final UnitQtyRepository unitQtyRepository;

    private final CountryRepository countryRepository;

    @Autowired
    public ExtenstionsService(CurrencyRepository currencyRepository, UnitQtyRepository unitQtyRepository, CountryRepository countryRepository) {
        this.currencyRepository = currencyRepository;
        this.unitQtyRepository = unitQtyRepository;
        this.countryRepository = countryRepository;
    }

    public List<Currency> getCurrencies() {
        return (List<Currency>) currencyRepository.findAll();
    }

    public List<CountryCode> getCountries() {
        return (List<CountryCode>) countryRepository.findAll();
    }

    public List<UnitQty> getUnitQty() {
        return (List<UnitQty>) unitQtyRepository.findAll();
    }

    public Map<String, List> getAllAsMap() {
        Map<String, List> map = new HashMap<>();
        map.put("currency", getCurrencies());
        map.put("country", getCountries());
        map.put("unit", getUnitQty());
        return map;
    }
}
