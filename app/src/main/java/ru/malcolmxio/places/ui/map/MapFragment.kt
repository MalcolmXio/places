package ru.malcolmxio.places.ui.map

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_map.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.malcolmxio.places.R
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.presentation.map.MapPresenter
import ru.malcolmxio.places.presentation.map.MapView
import ru.malcolmxio.places.ui.base.BaseFragment
import ru.malcolmxio.places.ui.base.bottomSheet.BaseBottomSheetDialog
import ru.malcolmxio.places.util.argument
import ru.malcolmxio.places.util.extensions.addSystemBottomPadding
import ru.malcolmxio.places.util.extensions.addSystemTopPadding
import ru.malcolmxio.places.util.extensions.getApplication
import javax.inject.Inject

class MapFragment : BaseFragment(), MapView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override val layoutRes = R.layout.fragment_map

    private val countryData: Country by argument(ARG_COUNTRY_DATA)

    private var map: GoogleMap? = null

    @Inject
    lateinit var presenterProvider: MapPresenter

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() = presenterProvider

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapContainer.addSystemBottomPadding()
        toolbar.title = countryData.name
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        toolbar.addSystemTopPadding()

        val map = childFragmentManager.findFragmentById(R.id.map)
        (map as? SupportMapFragment)?.apply {
            getMapAsync(this@MapFragment)
        }
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed(): OnBackPressedCallback {
        return requireActivity().onBackPressedDispatcher.addCallback(this) {
            Toast.makeText(requireContext(), "Fuck You!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
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
        map?.setOnMarkerClickListener(this)
    }


    override fun onMarkerClick(p0: Marker?): Boolean {
        p0?.let {
            val cameraPosition = CameraPosition.builder()
                .target(it.position)
                .zoom(CAMERA_MARKER_ZOOM)
                .build()
            map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            BaseBottomSheetDialog(bs as ViewGroup)
        }
        return true
    }

    override fun injectDependencies() {
        super.injectDependencies()
        getApplication().components[fragmentScopeName]?.inject(this)
    }

    companion object {

        private const val MAP_PADDING_FROM_BOUNDS = 30
        private const val CAMERA_MARKER_ZOOM = 10F

        private const val ARG_COUNTRY_DATA = "countryData"

    }

}