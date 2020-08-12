package com.gasolinecalculation.ui.calculation.model

import android.view.View
import com.gasolinecalculation.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_refuel.*


/**
 * An item class represents refuel in calculation screen
 *
 * @property refuel Refuel model
 */
class RefuelItem(private val refuel: Refuel) : AbstractItem<RefuelItem.ViewHolder>() {

    /**
     * The type of item. id of parent recycler
     */
    override val type: Int
        get() = R.id.item_refuel

    override val layoutRes: Int
        get() = R.layout.item_refuel


    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    /**
     * ViewHolder for storing item information and binding it to view.
     */
    class ViewHolder(override val containerView: View) : FastAdapter.ViewHolder<RefuelItem>(containerView), LayoutContainer {

        override fun bindView(item: RefuelItem, payloads: List<Any>) {
            val refuel = item.refuel
            tvCost.text = refuel.cost.toString()
            tvAllCost.text = (refuel.cost * refuel.mileage).toString()
            tvMileage.text = refuel.mileage.toString()
            tvDateTime.text = refuel.dateTime
        }

        override fun unbindView(item: RefuelItem) {
            tvCost.text = null
            tvAllCost.text = null
            tvMileage.text = null
            tvDateTime.text = null
        }
    }
}
