package com.example.demo.cocktails.client.dto;

import java.util.LinkedHashMap;
import java.util.List;

public record CocktailResponse (
    List<LinkedHashMap> drinks
) {}
