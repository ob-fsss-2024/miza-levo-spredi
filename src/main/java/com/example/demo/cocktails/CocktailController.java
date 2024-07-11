package com.example.demo.cocktails;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.*;
import com.example.demo.cocktails.client.dto.CocktailResponse;
import com.example.demo.cocktails.client.CocktailClient;

import java.util.LinkedHashMap;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cocktail")
public class CocktailController {

    private final CocktailClient cocktailClient;

    public CocktailController(final CocktailClient cocktailClient, final MeterRegistry meterRegistry) {
        this.cocktailByIdCounter = meterRegistry.counter("cocktail_by_id_counter");
        this.cocktailClient = cocktailClient;
        this.cocktailByAlcoholCounter = meterRegistry.counter("cocktail_by_alcohol_counter");
    }

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Counter cocktailByIdCounter;
    private final Counter cocktailByAlcoholCounter;


    @GetMapping("/getCocktail")
    public CocktailResponse getCocktail(@RequestParam final String s) {
        logger.info("Fetching cocktail with name " + s);
        CocktailResponse drinks = cocktailClient.getCocktail(s);
        for (LinkedHashMap drink : drinks.drinks()) {
            System.out.println(drink.get("strDrink"));
        }
        return cocktailClient.getCocktail(s);
    }

    @GetMapping("/getCocktailByAlcohol")
    public CocktailResponse getCocktailByAlcohol(@RequestParam final String alcohol) {
        logger.info("Fetching cocktails that contain " + alcohol);
        for (LinkedHashMap drink : cocktailClient.getCocktailByAlcohol(alcohol).drinks()) {
            System.out.println(drink.get("strDrink"));
            cocktailByAlcoholCounter.increment();
            String drinkId =drink.get("idDrink").toString();
        }
        return cocktailClient.getCocktailByAlcohol(alcohol);
    }

    @GetMapping("/getCocktailById")
    public LinkedHashMap getCocktailById(@RequestParam final String id) {
        //System.out.println(cocktailClient.getCocktailById(id).drinks().getFirst());
        logger.info("Fetching cocktail by id " + id);
        cocktailByIdCounter.increment();
        LinkedHashMap<String, String> drink = cocktailClient.getCocktailById(id).drinks().getFirst();
        LinkedHashMap<String, String> ingredients = new LinkedHashMap<>();

        for (int i = 1; i <= 15; i++) {
            String ingredient = "strIngredient" + i;
            String measure = "strMeasure" + i;
            if (drink.get(ingredient) != null) {
                ingredients.put(drink.get(ingredient), drink.get(measure));
            }
        }
        System.out.println(ingredients);
        LinkedHashMap usefulInfo = new LinkedHashMap();
        usefulInfo.put("id", drink.get("idDrink"));
        usefulInfo.put("name", drink.get("strDrink"));
        usefulInfo.put("instructions", drink.get("strInstructions"));
        usefulInfo.put("ingredients", ingredients);
        return usefulInfo;
    }
}
