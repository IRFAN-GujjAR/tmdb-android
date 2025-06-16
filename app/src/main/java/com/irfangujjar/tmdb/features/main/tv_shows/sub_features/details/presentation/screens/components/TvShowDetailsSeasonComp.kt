package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.SeasonModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel

@Composable
fun TvShowDetailsSeasonComp(
    preview: Boolean,
    tvId: Int,
    tvShowName: String,
    tvShowPosterPath: String?,
    episodeImagePlaceHolder: String?,
    seasons: List<SeasonModel>,
    onSeeAllPressed: () -> Unit
) {

    val showSeeAll = seasons.size >= 5
    Column {
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = if (showSeeAll) DividerBottomPadding.Half else DividerBottomPadding.OneAndHalf
        )
        TextRow(title = "Seasons", onSeeAllTapped = if (showSeeAll) onSeeAllPressed else null)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = ScreenPadding.getHorizontalPadding()
            )
        ) {
            val config = MediaItemsHorizontalListConfigValues.fromDefault()
            items(seasons) {
                Column {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.TvShow,
                        imageUrl = it.posterPath,
                        width = config.listItemWidth,
                        height = config.imageHeight,
                        contentScale = ContentScale.Crop,
                        placeHolderSize = 72.dp,
                        posterSize = PosterSizes.w185,
                    )
                    Text(
                        it.name,
                        fontSize = 13.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

//         Container(
//           height: 165,
//           child: ListView.separated(
//             physics: const AlwaysScrollableScrollPhysics(),
//             scrollDirection: Axis.horizontal,
//             itemBuilder: (context, index) {
//               String? posterPath = seasons[index].posterPath;
//               if (posterPath != null) {
//                 posterPath = URLS.posterImageUrl(
//                   imageUrl: posterPath,
//                   size: PosterSizes.w185,
//                 );
//               }
//               return GestureDetector(
//                 onTap: () {
//                   appRouterConfig.push(
//                     context,
//                     location: AppRouterPaths.TV_SHOW_SEASON_DETAILS_LOCATION,
//                     extra: TvShowSeasonDetailsPageExtra(
//                       tvId: tvId,
//                       tvShowName: tvShowName,
//                       seasonName: seasons[index].name,
//                       seasonNo: seasons[index].seasonNo,
//                       tvShowPosterPath: tvShowPosterPath,
//                       episodeImagePlaceHolder: episodeImagePlaceHolder,
//                     ),
//                   );
//                 },
//                 child: Container(
//                   margin: const EdgeInsets.only(left: 10),
//                   height: 180,
//                   width: 100,
//                   child: Column(
//                     crossAxisAlignment: CrossAxisAlignment.start,
//                     children: <Widget>[
//                       CustomNetworkImageWidget(
//                         type: MediaImageType.TvShow,
//                         imageUrl: posterPath,
//                         width: 92.5,
//                         height: 139,
//                         fit: BoxFit.fill,
//                         borderRadius: 0,
//                         placeHolderSize: 72,
//                       ),
//                       Padding(
//                         padding: const EdgeInsets.only(top: 6.0),
//                         child: Text(
//                           seasons[index].name,
//                           style: TextStyle(fontSize: 13),
//                           maxLines: 1,
//                           overflow: TextOverflow.ellipsis,
//                         ),
//                       ),
//                     ],
//                   ),
//                 ),
//               );
//             },
//             separatorBuilder: (context, index) {
//               return SizedBox(width: 10);
//             },
//             itemCount: seasons.length,
//           ),
//         ),
//       ],
//     );
//   }
// }


@Preview
@Composable
private fun TvShowDetailsSeasonCompPreview() {
    TMDbTheme {
        Surface {
            val tvShowDetails = TvShowDetailsModel.dummyData()
            TvShowDetailsSeasonComp(
                preview = true,
                tvId = TvShowModel.dummyData().id,
                tvShowName = tvShowDetails.name,
                tvShowPosterPath = tvShowDetails.posterPath,
                episodeImagePlaceHolder = tvShowDetails.backdropPath,
                seasons = tvShowDetails.seasons,
                onSeeAllPressed = {}
            )
        }
    }
}