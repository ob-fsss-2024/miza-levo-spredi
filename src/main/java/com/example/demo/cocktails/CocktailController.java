package com.example.demo.cocktails;

import org.springframework.web.bind.annotation.*;
import com.example.demo.cocktails.client.dto.CocktailResponse;
import com.example.demo.cocktails.client.CocktailClient;

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

    @GetMapping("/getCocktailByAlcohol")
    public CocktailResponse getCocktailByAlcohol(@RequestParam final String alcohol) {
        for (LinkedHashMap drink : cocktailClient.getCocktailByAlcohol(alcohol).drinks()) {
            System.out.println(drink.get("strDrink"));
            String drinkId =drink.get("idDrink").toString();
        }
        return cocktailClient.getCocktailByAlcohol(alcohol);
    }

    @GetMapping("/getCocktailById")
    public LinkedHashMap getCocktailById(@RequestParam final String id) {
        System.out.println(cocktailClient.getCocktailById(id).drinks().getFirst());
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
