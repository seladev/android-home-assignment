package homework.chegg.com.chegghomework.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 *  StackOverFlow Retrofit Service - make RestApi call by Retrofit
 */
object CheggRetrofitService {

    const val BASE_URL = "https://chegg-mobile-promotions.cheggcdn.com/android/homework/"

    /**
     * Http log - when making  request show the request in logcat
     */
    private val logInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }


    private val client = OkHttpClient.Builder().apply {
        addInterceptor(logInterceptor)
    }.build()


    /**
     * Retrofit
     */
    private  val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
    .build()

    /**
     * Build the service
     */
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }


}
