package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {
        Sandwich mySandwich = new Sandwich();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");

            String mainName = name.getString("mainName");
            mySandwich.setMainName(mainName);

            JSONArray alosKnownAs = name.getJSONArray("alsoKnownAs");
            ArrayList<String>  alsoKnownAsList = new ArrayList<>();
            for(int i=0; i< alosKnownAs.length();i++)
            {
                alsoKnownAsList.add(alosKnownAs.getString(i));
            }
            mySandwich.setAlsoKnownAs(alsoKnownAsList);

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            mySandwich.setPlaceOfOrigin(placeOfOrigin);

            String description = jsonObject.getString("description");
            mySandwich.setDescription(description);

            String image = jsonObject.getString("image");
            mySandwich.setImage(image);

            JSONArray ingredients = jsonObject.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<>();
            for (int i=0; i<ingredients.length();i++)
            {
                ingredientsList.add(ingredients.getString(i));
            }
            mySandwich.setIngredients(ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mySandwich;
    }
}
