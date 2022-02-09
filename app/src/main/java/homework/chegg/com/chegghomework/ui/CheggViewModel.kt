package homework.chegg.com.chegghomework.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import homework.chegg.com.chegghomework.data.CheggDataModel
import homework.chegg.com.chegghomework.logic.CheggRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by seladev
 */
class CheggViewModel : ViewModel() {

    companion object {
        private const val TAG = "CheggViewModel"
    }

    private val compositeDisposable = CompositeDisposable()

    private val cheggData: MutableLiveData<List<CheggDataModel>> by lazy {
        MutableLiveData<List<CheggDataModel>>().also {
            loadData()
        }
    }

    fun getCheggData(): LiveData<List<CheggDataModel>> {
        return cheggData
    }

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()
    fun getErrorLiveData(): LiveData<String> = errorLiveData


    fun loadData() {
        compositeDisposable.add(
            Observable.zip(
                loadDataSourceA(),
                loadDataSourceB(),
                loadDataSourceC(),
                Function3<List<CheggDataModel>, List<CheggDataModel>, List<CheggDataModel>, List<CheggDataModel>> { a, b, c ->
                    val cheggDataModelList = mutableListOf<CheggDataModel>()
                    cheggDataModelList.addAll(a)
                    cheggDataModelList.addAll(b)
                    cheggDataModelList.addAll(c)
                    return@Function3 cheggDataModelList
                }
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    cheggData.value = it
                }, {
                    Log.e(Companion.TAG, "error loadData: "+it.localizedMessage )
                    cheggData.value = emptyList()
                    errorLiveData.value = it.localizedMessage
                })
        )

    }


    fun loadDataSourceA(): Observable<MutableList<CheggDataModel>>? {
        return CheggRepository.getDataSourceA()
            .flatMap {
                val cheggDataModelList = mutableListOf<CheggDataModel>()

                it.stories.forEach {
                    val cheggDataModel = CheggDataModel()
                    cheggDataModel.title = it.title
                    cheggDataModel.subtitle = it.subtitle
                    cheggDataModel.imageUrl = it.imageUrl
                    cheggDataModelList.add(cheggDataModel)
                }

                return@flatMap Observable.just(cheggDataModelList)
            }
    }


    fun loadDataSourceB(): Observable<MutableList<CheggDataModel>>? {
        return CheggRepository.getDataSourceB()
            .flatMap {
                val cheggDataModelList = mutableListOf<CheggDataModel>()

                it.metadata.innerdata.forEach {
                    val cheggDataModel = CheggDataModel()
                    cheggDataModel.title = it.articlewrapper.header
                    cheggDataModel.subtitle = it.articlewrapper.description
                    cheggDataModel.imageUrl = it.picture
                    cheggDataModelList.add(cheggDataModel)
                }

                return@flatMap Observable.just(cheggDataModelList)
            }

    }

    fun loadDataSourceC(): Observable<MutableList<CheggDataModel>>? {
        return CheggRepository.getDataSourceC()
            .flatMap {
                val cheggDataModelList = mutableListOf<CheggDataModel>()

                it.forEach { dataCDto ->
                    val cheggDataModel = CheggDataModel()
                    cheggDataModel.title = dataCDto.topLine
                    cheggDataModel.subtitle = "${dataCDto.subLine1} ${dataCDto.subline2}"
                    cheggDataModel.imageUrl = dataCDto.image
                    cheggDataModelList.add(cheggDataModel)
                }

                return@flatMap Observable.just(cheggDataModelList)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

}