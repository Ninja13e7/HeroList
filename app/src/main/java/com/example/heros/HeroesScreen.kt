package com.example.heros

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heros.model.Hero
import com.example.heros.model.descriptionHero
import com.example.heros.ui.theme.SuperheroesTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroList(heroList: List<Hero> ,modifier: Modifier = Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply {

            targetState = true
        }
    }


    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {

    }
    LazyColumn(modifier = modifier) {
        itemsIndexed(heroList) {index, hero ->
        nameAndDescription(
            hero = hero,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)

            )

        }
    }
}

@Composable
fun nameAndDescription(hero: Hero, modifier: Modifier = Modifier
    .fillMaxWidth()
) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
        .clip(MaterialTheme.shapes.medium)
            ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = LocalContext.current.getString(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                )
                Text(
                    text = LocalContext.current.getString(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.small),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun heroPreview() {
   val hero = Hero(
       R.string.hero1,
       R.string.description1,
       R.drawable.android_superhero1
   )
    SuperheroesTheme() {
        nameAndDescription(hero = hero)
    }
}



