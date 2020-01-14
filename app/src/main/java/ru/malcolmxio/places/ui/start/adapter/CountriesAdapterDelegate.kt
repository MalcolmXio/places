package ru.malcolmxio.places.ui.start.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import ru.malcolmxio.places.R
import ru.malcolmxio.places.domain.model.country.Country
import ru.malcolmxio.places.util.extensions.inflate
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapterDelegate(private val clickListener: (Country) -> Unit) :
    AdapterDelegate<MutableList<Any>>() {

    override fun isForViewType(items: MutableList<Any>, position: Int) =
        items[position] is Country

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_country))

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        viewHolder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) =
        (viewHolder as ViewHolder).bind(items[position] as Country)

    private inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private lateinit var country: Country

        init {
            containerView.setOnClickListener { clickListener(country) }
        }

        fun bind(country: Country) {
            this.country = country
            containerView.titleTextView.text = country.name
            //subtitleTextView.text = country.license.getHumanName(subtitleTextView.resources)
        }
    }
}