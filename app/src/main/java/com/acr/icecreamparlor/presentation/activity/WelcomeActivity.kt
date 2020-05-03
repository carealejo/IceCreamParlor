package com.acr.icecreamparlor.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.acr.icecreamparlor.R
import com.acr.icecreamparlor.core.platform.BaseActivity
import com.acr.icecreamparlor.domain.model.IceCream
import com.acr.icecreamparlor.domain.model.IceCreamsData
import com.acr.icecreamparlor.domain.model.Response
import com.acr.icecreamparlor.presentation.adapter.IceCreamAdapter
import com.acr.icecreamparlor.presentation.viewmodel.WelcomeViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject

class WelcomeActivity : BaseActivity(), IceCreamAdapter.Listener {

    @Inject
    lateinit var welcomeViewModel: WelcomeViewModel

    private lateinit var iceCreamAdapter: IceCreamAdapter
    private lateinit var iceCreamAdapterManager: RecyclerView.LayoutManager
    private val selectedIceCreams = hashMapOf<String, IceCream>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        appComponent.inject(this)

        iceCreamAdapterManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        iceCreamAdapter = IceCreamAdapter(this, this)
        iceCreams.apply {
            setHasFixedSize(true)
            layoutManager = iceCreamAdapterManager
            adapter = iceCreamAdapter
        }

        welcomeViewModel.iceCreamsData.observe(this@WelcomeActivity, Observer {
            onIceCreamsDataResponse(it)
        })

        welcomeViewModel.getIceCreamsData()
    }

    override fun onStop() {
        super.onStop()

        welcomeViewModel.dispose()
    }

    fun onIceCreamsDataResponse(response: Response<List<IceCream>>){
        val iceCreamsData = response.value
        if (response.success && iceCreamsData != null) {
            iceCreamAdapter.updateOptionsSpaceList(iceCreamsData)
        } else {
            Toast.makeText(this, getString(R.string.error_getting_data), Toast.LENGTH_LONG).show()
        }
    }

    override fun onIceCreamClick(iceCream: IceCream) {
        if (iceCream.counter != 0) {
            selectedIceCreams[iceCream.completeName] = iceCream
        } else {
            if (selectedIceCreams.containsKey(iceCream.completeName)) {
                selectedIceCreams.remove(iceCream.completeName)
            }
        }
        val items = selectedIceCreams.values.map { it.counter }.sum()
        val itemsOrder = if (items > 1) "ITEMS" else "ITEM"
        order.text = "ORDER $items $itemsOrder"
    }
}
