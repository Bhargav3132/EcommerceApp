package com.heady.ecommerceapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.utils.ActivityUtils
import com.heady.ecommerceapp.utils.Util


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Returns the resource id of the layout which will be used for setContentView() for the Activity
     *
     * @return resource id of the xml layout
     */
    abstract fun defineLayoutResource(): Int

    /**
     * Initialize the components on activity create
     */
    protected abstract fun initializeComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        //        supportFragmentManager.fragmentFactory = MyFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(defineLayoutResource())

        initializeComponents()
    }

    override fun onClick(v: View) {
        Util.hideSoftKeyBoard(this, v)
    }

    /**
     * Adds the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(
        fragmentContainerResourceId: Int, currentFragment: Fragment,
        nextFragment: Fragment, requiredAnimation: Boolean,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        return ActivityUtils.addFragment(
            this,
            fragmentContainerResourceId,
            currentFragment,
            nextFragment,
            requiredAnimation,
            commitAllowingStateLoss
        )
    }

    /**
     * Adds the Fragment into layout container with animation
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(
        fragmentContainerResourceId: Int, currentFragment: Fragment,
        nextFragment: Fragment, enter: Int = R.anim.no_enim, exit: Int = R.anim.no_enim,
        popEnter: Int = R.anim.no_enim, popExit: Int = R.anim.no_enim,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        return ActivityUtils.addFragment(
            this,
            fragmentContainerResourceId,
            currentFragment,
            nextFragment,
            enter,
            exit,
            popEnter,
            popExit,
            commitAllowingStateLoss
        )

    }

    /**
     * Replaces the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param fragmentManager             FRAGMENT MANGER
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(
        fragmentContainerResourceId: Int, fragmentManager: FragmentManager,
        nextFragment: Fragment, requiredAnimation: Boolean = false,
        commitAllowingStateLoss: Boolean = false
    ): Boolean {
        return ActivityUtils.replaceFragment(
            this,
            fragmentContainerResourceId,
            fragmentManager,
            nextFragment,
            requiredAnimation,
            commitAllowingStateLoss
        )
    }
}