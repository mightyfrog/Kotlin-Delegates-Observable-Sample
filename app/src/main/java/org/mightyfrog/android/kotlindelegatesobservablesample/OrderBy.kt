package org.mightyfrog.android.kotlindelegatesobservablesample

/**
 * @author Shigehiro Soejima
 */
sealed class OrderBy {

    object Ascending : OrderBy()
    object Descending : OrderBy()
}
