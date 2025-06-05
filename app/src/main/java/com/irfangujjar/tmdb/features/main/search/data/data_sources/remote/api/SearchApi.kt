package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api

import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("trending/all/day")
    suspend fun trendingSearch(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.name,
    ): SearchResponse

}

//export class SearchApiPaths {
//   public static readonly TRENDING = `${BASE_URL}/trending/all/day?`+
//     `${APIQueryFields.LANGUAGE}=${APIQueryFieldValuesLanguage.ENGLISH}`;
//   private static readonly MULTI_SEARCH=`${BASE_URL}/search/multi`;
//   private static readonly SEARCH=`${BASE_URL}/search`;


//   private static append(apiPath:string, query:string, pageNo:number):string {
//     return `${apiPath}?${APIQueryFields.SEARCH_QUERY}=${query}&
//     ${APIQueryFields.LANGUAGE}=${APIQueryFieldValuesLanguage.ENGLISH}&${APIQueryFields.PAGE}=${pageNo}`;
//   }

//   public static multiSearch(query:string, pageNo:number) {
//     return this.append(this.MULTI_SEARCH, query, pageNo);
//   }

//   public static searchMovies(query:string, pageNo:number):string {
//     return this.append(this.SEARCH+"/movie", query, pageNo);
//   }

//   public static searchTvShows(query:string, pageNo:number):string {
//     return this.append(this.SEARCH+"/tv", query, pageNo);
//   }

//   public static searchCelebs(query:string, pageNo:number):string {
//     return this.append(this.SEARCH+"/person", query, pageNo);
//   }
// }