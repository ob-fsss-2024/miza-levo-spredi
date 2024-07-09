package com.example.demo.cocktails;

import jakarta.json.JsonObject;
import org.springframework.web.bind.annotation.*;
import com.example.demo.cocktails.client.dto.CocktailResponse;
import com.example.demo.cocktails.client.CocktailClient;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cocktail")
public class CocktailController {

    private final CocktailClient cocktailClient;

    public CocktailController(final CocktailClient cocktailClient) {
        this.cocktailClient = cocktailClient;
    }

    @GetMapping("/getCocktail")
    public CocktailResponse getCocktail(@RequestParam final String s) {
        //System.out.println(cocktailClient.getCocktail(s).drinks().getClass().getName());
        for (LinkedHashMap drink : cocktailClient.getCocktail(s).drinks()) {
            System.out.println(drink.get("strDrink"));
        }
        return cocktailClient.getCocktail(s);
    }
}
