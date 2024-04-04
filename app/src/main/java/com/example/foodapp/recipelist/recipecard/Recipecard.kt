package com.example.foodapp.recipelist.recipecard

import android.content.Context
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@Composable
fun RecipeCard(ImageUrl : String, RecipeName : String) {
OutlinedCard {
    Column {
        var context : Context = LocalContext.current
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)
        val painterImage = Glide.with(context).asBitmap().load("https://cataas.com/cat").into(ImageView(context))
        //Image(painter = painterImage, contentDescription = "description")
        Text(text = RecipeName);
    }
}
}