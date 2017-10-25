package org.mightyfrog.android.kotlindelegatesobservablesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

/**
 * https://kotlinlang.org/docs/reference/delegated-properties.html#observable
 *
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private var order: Order by Delegates.observable<Order>(Order.Ascending()) { _, old, new ->
        if (old == new) {
            return@observable
        }

        when (new) {
            is Order.Ascending -> { // totally contrived
                recyclerView.swapAdapter(NumAdapter((1..100).toList()), false)
            }
            is Order.Descending -> {
                recyclerView.swapAdapter(NumAdapter((1..100).toList().reversed()), false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NumAdapter((1..100).toList())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_ascending -> order = Order.Ascending() // triggers callback
            R.id.action_descending -> order = Order.Descending() // triggers callback
        }

        return super.onOptionsItemSelected(item)
    }

    private class NumAdapter(private val list: List<Int>) : RecyclerView.Adapter<NumViewHolder>() {

        override fun getItemCount() = list.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NumViewHolder {
            return NumViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_holder, parent, false))
        }

        override fun onBindViewHolder(holder: NumViewHolder?, position: Int) {
            holder?.tv?.text = list[position].toString()
        }
    }

    private class NumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tv = view.findViewById<TextView>(R.id.text)!!
    }
}
