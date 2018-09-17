package org.mightyfrog.android.kotlindelegatesobservablesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

/**
 * https://kotlinlang.org/docs/reference/delegated-properties.html#observable
 *
 * @author Shigehiro Soejima
 */
class MainActivity : AppCompatActivity() {

    private var orderBy: OrderBy by Delegates.observable<OrderBy>(OrderBy.Ascending) { _, old, new ->
        if (old == new) {
            return@observable
        }

        when (new) {
            is OrderBy.Ascending -> { // totally contrived
                recyclerView.swapAdapter(NumAdapter((1..100).toList()), false)
            }
            is OrderBy.Descending -> {
                recyclerView.swapAdapter(NumAdapter((1..100).toList().reversed()), false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NumAdapter((1..100).toList())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_ascending -> orderBy = OrderBy.Ascending // triggers callback
            R.id.action_descending -> orderBy = OrderBy.Descending // triggers callback
        }

        return super.onOptionsItemSelected(item)
    }
}
