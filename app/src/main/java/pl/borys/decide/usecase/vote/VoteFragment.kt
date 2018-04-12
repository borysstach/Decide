package pl.borys.decide.usecase.vote

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.vote_fragment.*
import org.kodein.di.generic.instance
import pl.borys.decide.R
import pl.borys.decide.common.KodeinProvider
import pl.borys.decide.common.views.BaseFragment
import pl.borys.decide.usecase.vote.model.VoteApi
import pl.borys.decide.usecase.vote.dto.VoteSheet

class VoteFragment : BaseFragment() {

    override val layout = R.layout.vote_fragment

    private var sheetsDisposable: Disposable? = null
    private val sheetsRepository: VoteApi by KodeinProvider.kodeinInstance.instance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSheets()
    }

    //TODO: move it to LiveData
    private fun getSheets() {
        sheetsDisposable = sheetsRepository.getSheets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        changeMessage,
                        showError
                )
    }

    private val changeMessage: (List<VoteSheet>) -> Unit = {
        message.text = it.map { it.title }.toString()
    }

    private val showError: (Throwable) -> Unit = {
        message.text = it.message
    }

    override fun onStop() {
        super.onStop()
        sheetsDisposable?.dispose()
    }

    companion object {
        fun getInstance(): Fragment = VoteFragment()
    }
}