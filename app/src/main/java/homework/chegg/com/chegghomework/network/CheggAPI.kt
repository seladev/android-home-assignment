package homework.chegg.com.chegghomework.network

import homework.chegg.com.chegghomework.Consts
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by seladev
 */
interface CheggAPI {

    /**
     * get data source A
     */
    @GET(Consts.DATA_SOURCE_A_URL)
    @Headers("${Consts.CACHE_CONTROL_HEADER}: ${Consts.MAX_AGO}5")
    fun getDataSourceA(): Observable<DataADto>

    /**
     * get data source B
     */
    @GET(Consts.DATA_SOURCE_B_URL)
    @Headers("${Consts.CACHE_CONTROL_HEADER}: ${Consts.MAX_AGO}30")
    fun getDataSourceB(): Observable<DataBDto>

    /**
     * get data source C
     */
    @GET(Consts.DATA_SOURCE_C_URL)
    @Headers("${Consts.CACHE_CONTROL_HEADER}: ${Consts.MAX_AGO}60")
    fun getDataSourceC(): Observable<List<DataCDto>>


}