package com.udacity.sandwichclub;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        try {
            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwiches[position];
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }

            populateUI(sandwich);

            //setTitle(sandwich.getMainName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

   private void populateUI(Sandwich sandwichType) {
        //DetailActivity detailActivity = new DetailActivity();
       ImageView ingredientsIv = findViewById(R.id.image_iv);
       TextView name_textView = findViewById(R.id.name_displaytv);
       TextView alsoKnownAs_textView = findViewById(R.id.also_known_displaytv);
       TextView description_textView = findViewById(R.id.description_displaytv);
       TextView placeOfOrigin_textView = findViewById(R.id.origin_displaytv);
       TextView ingredients_textView = findViewById(R.id.ingredients_displaytv);


       Picasso.with(this)
                .load(sandwichType.getImage())
                .into(ingredientsIv);

        setTitle(sandwichType.getMainName());
       name_textView.append(sandwichType.getMainName());

        List<String> alsoKnownAs_List = sandwichType.getAlsoKnownAs();
        for(int i=0; i<alsoKnownAs_List.size(); i++)
        {
            alsoKnownAs_textView.append(alsoKnownAs_List.get(i));
        }
        //sandwichType.setAlsoKnownAs(alsoKnownAs_List);

        placeOfOrigin_textView.append(sandwichType.getPlaceOfOrigin());

        description_textView.append(sandwichType.getDescription());

        List<String> ingredients_List = sandwichType.getIngredients();
        for (int i=0; i<ingredients_List.size(); i++)
        {
            ingredients_textView.append(ingredients_List.get(i));
        }


    }
}
