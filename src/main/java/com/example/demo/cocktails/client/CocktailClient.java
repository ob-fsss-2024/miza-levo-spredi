package com.example.demo.cocktails.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import com.example.demo.cocktails.client.dto.CocktailResponse;


public interface CocktailClient {
    @GetExchange( "/api/json/v1/1/search.php?s={s}")
    CocktailResponse getCocktail(@PathVariable String s);

    @GetExchange("/api/json/v1/1/filter.php?i={alcohol}")
    CocktailResponse getCocktailByAlcohol(@PathVariable String alcohol);

    @GetExchange("/api/json/v1/1/lookup.php?i={id}")
    CocktailResponse getCocktailById(@PathVariable String id);
}
