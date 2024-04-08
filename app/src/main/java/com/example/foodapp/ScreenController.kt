
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.RecipeList

object RecipeNavController {
    private val navController = rememberNavController()


    fun navigateToLoadingScreen() {
        navController.navigate("loading")
    }

    fun navigateToRecipeList() {
        navController.navigate(RecipeList.RecipeList.name)
    }

    fun navigateToRecipeDetail(recipeId: String) {
        navController.navigate("${RecipeList.RecipeDetail.name}/$recipeId")
    }
}
