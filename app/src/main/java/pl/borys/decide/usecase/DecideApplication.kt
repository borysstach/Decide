package pl.borys.decide.usecase

import android.app.Application
import org.kodein.di.KodeinAware
import pl.borys.decide.common.KodeinProvider

class DecideApplication: Application(), KodeinAware {
    override val kodein = KodeinProvider.kodeinInstance
}