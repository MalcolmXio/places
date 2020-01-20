package ru.malcolmxio.places.ui.map

import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.malcolmxio.places.R
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.presentation.map.MapPresenter
import ru.malcolmxio.places.presentation.map.MapView
import ru.malcolmxio.places.ui.base.BaseFragment
import ru.malcolmxio.places.ui.base.FlowFragment
import ru.malcolmxio.places.util.argument
import ru.malcolmxio.places.util.extensions.addSystemBottomPadding
import ru.malcolmxio.places.util.extensions.addSystemTopPadding
import ru.malcolmxio.places.util.extensions.getApplication
import javax.inject.Inject

class MapFragment : BaseFragment(), MapView, OnMapReadyCallback {

    override val layoutRes = R.layout.fragment_map

    private val countryData: Country by argument(ARG_COUNTRY_DATA)

    @Inject
    lateinit var presenterProvider: MapPresenter

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() = presenterProvider

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.title = countryData.name
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
        toolbar.addSystemTopPadding()

        val map = childFragmentManager.findFragmentById(R.id.map)
        (map as? SupportMapFragment)?.apply {
            view?.addSystemBottomPadding()
            getMapAsync(this@MapFragment)
        }
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onBackPressed()
    }

    override fun onMapReady(p0: GoogleMap?) {
        val bounds = LatLngBounds(
            LatLng(
                countryData.bounds.bottomLeftPoint.lat,
                countryData.bounds.bottomLeftPoint.lon
            ),
            LatLng(countryData.bounds.upperRightPoint.lat, countryData.bounds.upperRightPoint.lon)
        )
        countryData.places.forEach {
            p0?.addMarker(
                MarkerOptions()
                    .position(LatLng(it.coordinates.lat, it.coordinates.lon))
                    .title(it.name)
            )
        }
        p0?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, MAP_PADDING_FROM_BOUNDS))
        p0?.setLatLngBoundsForCameraTarget(bounds)
    }

    override fun injectDependencies() {
        val parentFragmentName = (parentFragment as? FlowFragment)?.fragmentScopeName
        getApplication().components[parentFragmentName]?.inject(this)
    }

    companion object {
        private const val MAP_PADDING_FROM_BOUNDS = 30

        private const val ARG_COUNTRY_DATA = "ARG_COUNTRY_DATA"

        fun create(countryData: Country) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_COUNTRY_DATA, countryData)
                }
            }

    }

}