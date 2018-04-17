package pl.borys.decide.usecase.vote

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import androidx.core.view.isGone
import kotlinx.android.synthetic.main.fullscreen_loader.*
import kotlinx.android.synthetic.main.vote_fragment.*
import pl.borys.decide.R
import pl.borys.decide.common.views.BaseFragment
import pl.borys.decide.usecase.vote.dto.VoteSheet
import pl.borys.decide.usecase.vote.viewModel.VoteSheetsResponse
import pl.borys.decide.usecase.vote.viewModel.VoteViewModel

class VoteFragment : BaseFragment() {

    override val layout = R.layout.vote_fragment
    private var voteModel: VoteViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSheets()
    }

    private fun getSheets() {
        voteModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)
        voteModel?.getVoteSheets()?.observe(this, processResponse)
    }

    private val processResponse = Observer<VoteSheetsResponse> { response ->
        response?.map(
                onLoading = showLoader,
                onSuccess = changeMessage,
                onError = showError,
                onFinish = hideLoader
        )
    }

    private val showLoader: () -> Unit = {
        loader.isGone = false
    }

    private val hideLoader: () -> Unit = {
        loader.isGone = true
    }

    private val changeMessage: (List<VoteSheet>?) -> Unit = {
        message.text = it?.map { it.title }.toString()
    }

    private val showError: (Throwable?) -> Unit = {
        message.text = it?.message ?: "Error with no message" //TODO: get this from resources
    }

    companion object {
        fun getInstance(): Fragment = VoteFragment()
    }
}