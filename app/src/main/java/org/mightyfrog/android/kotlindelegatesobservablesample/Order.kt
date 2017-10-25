package org.mightyfrog.android.kotlindelegatesobservablesample

/**
 * @author Shigehiro Soejima
 */
sealed class Order {

    class Ascending : Order()
    class Descending : Order()
}
