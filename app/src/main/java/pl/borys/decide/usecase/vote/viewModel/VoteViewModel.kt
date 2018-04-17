package pl.borys.decide.usecase.vote.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.generic.instance
import pl.borys.decide.common.KodeinProvider
import pl.borys.decide.common.viewModel.Response
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.model.VoteApi

class VoteViewModel : ViewModel() {

    private val voteSheetsLiveData: MutableLiveData<VoteSheetsResponse> = MutableLiveData()
    private val sheetsRepository: VoteApi by KodeinProvider.kodeinInstance.instance()
    private var sheetsDisposable: Disposable? = null

    fun getVoteSheets(): LiveData<VoteSheetsResponse> {
        // TODO: make refresh method and getSheets only on first run
        getSheets()
        return voteSheetsLiveData
    }

    private fun getSheets() {
        sheetsDisposable = sheetsRepository.getSheets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    postLoading()
                }
                .subscribe(
                        postSuccess,
                        postError
                )
    }

    private val postSuccess: (List<VoteSheet>) -> Unit = {
        voteSheetsLiveData.setValue(Response.success(it))
    }

    private val postError: (Throwable) -> Unit = {
        voteSheetsLiveData.setValue(Response.error(it))
    }

    private val postLoading: () -> Unit = {
        voteSheetsLiveData.setValue(Response.loading())
    }

    override fun onCleared() {
        sheetsDisposable?.dispose()
        super.onCleared()
    }
}