package homework.chegg.com.chegghomework.network

import homework.chegg.com.chegghomework.Consts
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by seladev
 */
interface CheggAPI {

    /**
     * get data source A
     */
    @GET(Consts.DATA_SOURCE_A_URL)
    fun getDataSourceA(): Observable<DataADto>

    /**
     * get data source B
     */
    @GET(Consts.DATA_SOURCE_B_URL)
    fun getDataSourceB(): Observable<DataBDto>

    /**
     * get data source C
     */
    @GET(Consts.DATA_SOURCE_C_URL)
    fun getDataSourceC(): Observable<List<DataCDto>>


}