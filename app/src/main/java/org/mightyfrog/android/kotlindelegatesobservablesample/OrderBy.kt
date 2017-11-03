package org.mightyfrog.android.kotlindelegatesobservablesample

/**
 * @author Shigehiro Soejima
 */
sealed class OrderBy {

    class Ascending : OrderBy()
    class Descending : OrderBy()
}
