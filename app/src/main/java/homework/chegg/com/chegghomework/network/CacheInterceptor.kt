package homework.chegg.com.chegghomework.network

import homework.chegg.com.chegghomework.Consts
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Created by seladev
 */
class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val cacheHeader = request.header(Consts.CACHE_CONTROL_HEADER)
        val shouldUseCache = cacheHeader?.startsWith(Consts.MAX_AGO)
        if (shouldUseCache == false) return originalResponse

        //get MAX_AGO value
        val index = cacheHeader?.indexOf("=")
        if(index == null || index < 0 ){
            return originalResponse
        }
        val cacheTime = cacheHeader.subSequence(index+1, cacheHeader.length).toString().toInt()
        val cacheControl = CacheControl.Builder()
            .maxAge(cacheTime, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(Consts.CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}