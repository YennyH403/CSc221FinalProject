package csc221finalproject.calendarcalorie.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Implementing the API for recipes
 */
public class RecipeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
    }

}
