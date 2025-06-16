package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.ProductionCompanyModel
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.LanguageCode
import com.irfangujjar.tmdb.core.ui.util.formatDollarAmount
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel


@Composable
fun MovieDetailsInformationComp(
    releaseDate: String?,
    language: String?,
    budget: Long,
    revenue: Long,
    productionCompanies: List<ProductionCompanyModel>
) {
    Column {
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = DividerBottomPadding.OneAndHalf
        )
        TextRow("Information")
        Row {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.weight(1f)
            ) {
                if (releaseDate != null)
                    InformationItemTitle("Release Date : ")
                if (language != null)
                    InformationItemTitle("Language : ")
                if (budget != 0L)
                    InformationItemTitle("Budget : ")
                if (revenue != 0L)
                    InformationItemTitle("Revenue : ")
                if (productionCompanies.isNotEmpty())
                    InformationItemTitle("Production Companies : ")
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                if (releaseDate != null)
                    InformationItemData(releaseDate)
                if (language != null)
                    InformationItemData(LanguageCode.fromCode(language))
                if (budget != 0L)
                    InformationItemData(formatDollarAmount(budget))
                if (revenue != 0L)
                    InformationItemData(formatDollarAmount(revenue))
                if (productionCompanies.isNotEmpty())
                    repeat(productionCompanies.size) {
                        InformationItemData(productionCompanies[it].name)
                    }
            }
        }
    }
}


@Composable
private fun InformationItemTitle(category: String) {
    Text(
        category,
        textAlign = TextAlign.End,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        color = Color.White.copy(alpha = 0.7f),
    )
}

@Composable
private fun InformationItemData(data: String) {
    Text(
        data,
        textAlign = TextAlign.Start,
        fontSize = 13.sp,
        fontWeight = FontWeight.W500,
        color = Color.Gray,
    )
}

@Preview
@Composable
private fun MovieDetailsInformationCompPreview() {
    TMDbTheme {
        Surface {
            val movieDetails = MovieDetailsModel.dummyData()
            MovieDetailsInformationComp(
                releaseDate = movieDetails.releaseDate,
                language = movieDetails.language,
                budget = movieDetails.budget,
                revenue = movieDetails.revenue,
                productionCompanies = movieDetails.productionCompanies
            )
        }
    }
}