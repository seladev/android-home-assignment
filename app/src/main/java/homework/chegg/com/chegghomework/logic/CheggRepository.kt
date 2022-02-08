package homework.chegg.com.chegghomework.logic

import homework.chegg.com.chegghomework.network.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


/**
 * Created by seladev
 */
object CheggRepository {

    private val cheggAPI = CheggRetrofitService.buildService(CheggAPI::class.java)

    fun getDataSourceA(): Observable<DataADto> {
        return cheggAPI.getDataSourceA()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun  getDataSourceB(): Observable<DataBDto>{
        return cheggAPI.getDataSourceB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun  getDataSourceC(): Observable<List<DataCDto>>{
        return cheggAPI.getDataSourceC()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}