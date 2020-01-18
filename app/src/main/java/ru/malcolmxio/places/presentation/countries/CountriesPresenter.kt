package ru.malcolmxio.places.presentation.countries

import moxy.InjectViewState
import ru.malcolmxio.places.Screens
import ru.malcolmxio.places.domain.interactor.country.CountryInteractor
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.presentation.base.BasePresenter
import ru.malcolmxio.places.presentation.flow.FlowRouter
import javax.inject.Inject

@InjectViewState
class CountriesPresenter @Inject constructor(
    private val countriesInteractor: CountryInteractor,
    private val router: FlowRouter
) : BasePresenter<CountriesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        countriesInteractor
            .getCountries()
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe(
                { viewState.showCountries(it) },
                { viewState.showMessage(it.localizedMessage) }
            )
            .connect()
    }

    fun onItemClicked(countryData: Country) = router.startFlow(Screens.MapFlow(countryData))

    fun onBackPressed() = router.exit()

}