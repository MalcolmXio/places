package ru.malcolmxio.places.ui.map

import android.os.Bundle
import android.widget.Toast
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.malcolmxio.places.R
import ru.malcolmxio.places.domain.model.country.Place
import ru.malcolmxio.places.presentation.map.MapPresenter
import ru.malcolmxio.places.presentation.map.MapView
import ru.malcolmxio.places.ui.base.BaseFragment
import ru.malcolmxio.places.util.argument
import ru.malcolmxio.places.util.extensions.addSystemTopPadding
import javax.inject.Inject

class MapFragment : BaseFragment(), MapView, OnMapReadyCallback {

    override val layoutRes = R.layout.fragment_map

    private val geopointData: ArrayList<Place> by argument(ARG_GEOPOINT_DATA, arrayListOf())

    @Inject
    lateinit var presenterProvider: MapPresenter

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() = presenterProvider

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackPressed() }
        toolbar.addSystemTopPadding()


        (map as SupportMapFragment).getMapAsync(this)
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    companion object {

        private const val ARG_GEOPOINT_DATA = "ARG_GEOPOINT_DATA"

        fun create(geopointData: ArrayList<Place>) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_GEOPOINT_DATA, geopointData)
                }
            }

    }

    override fun onMapReady(p0: GoogleMap?) {
        val target = LatLng(geopointData[0].coordinates.lat, geopointData[0].coordinates.lon)
        geopointData.forEach {
            p0?.addMarker(
                MarkerOptions()
                    .position(LatLng(it.coordinates.lat, it.coordinates.lon))
                    .title(it.name)
            )
        }
        p0?.moveCamera(CameraUpdateFactory.newLatLng(target))
    }

}