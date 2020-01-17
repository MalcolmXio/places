package ru.malcolmxio.places.ui.start

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_countries.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.malcolmxio.places.R
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.domain.model.country.Place
import ru.malcolmxio.places.presentation.countries.CountriesPresenter
import ru.malcolmxio.places.presentation.countries.CountriesView
import ru.malcolmxio.places.ui.base.BaseFragment
import ru.malcolmxio.places.ui.start.adapter.CountriesAdapterDelegate
import ru.malcolmxio.places.util.extensions.addDividerItemDecoration
import ru.malcolmxio.places.util.extensions.addSystemBottomPadding
import ru.malcolmxio.places.util.extensions.addSystemTopPadding
import ru.malcolmxio.places.util.extensions.getApplication
import javax.inject.Inject

class CountriesFragment : BaseFragment(), CountriesView {

    override val layoutRes = R.layout.fragment_countries

    @Inject
    lateinit var presenterProvider: CountriesPresenter

    @InjectPresenter
    lateinit var presenter: CountriesPresenter

    @ProvidePresenter
    fun providePresenter() = presenterProvider

    private val adapter: CountryAdapter by lazy { CountryAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
        toolbar.addSystemTopPadding()

        with(recyclerView) {
            addSystemBottomPadding()
            layoutManager = LinearLayoutManager(context)
            addDividerItemDecoration()
            setHasFixedSize(true)
            adapter = this@CountriesFragment.adapter
        }
    }

    override fun showCountries(countries: List<Country>) {
        adapter.setData(countries)
    }

    override fun showProgress(show: Boolean) {
        showProgressDialog(show)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun injectDependencies() {
        super.injectDependencies()
        getApplication().flowComponent?.inject(this)
    }

    private inner class CountryAdapter : ListDelegationAdapter<MutableList<Any>>() {
        init {
            items = mutableListOf()
            delegatesManager.addDelegate(CountriesAdapterDelegate { presenter.onItemClicked(it.places as ArrayList<Place>) })
        }

        fun setData(libraries: List<Country>) {
            items.clear()
            items.addAll(libraries)

            notifyDataSetChanged()
        }
    }

}