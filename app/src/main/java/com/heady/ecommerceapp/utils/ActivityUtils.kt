package com.heady.ecommerceapp.utils


import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import com.heady.ecommerceapp.ui.base.BaseActivity


/**
 * Provides animations for the Fragment Transaction.
 */
object ActivityUtils {

    /**
     * Sets default Animation to [FragmentTransaction]
     *
     * @param transaction FragmentTransaction on which animation needs to be set
     */

    private fun setDefaultFragmentAnimation(transaction: FragmentTransaction?) {
        transaction?.setTransition(FragmentTransaction.TRANSIT_NONE)
    }

    fun setStatusBarColor(activity: AppCompatActivity?, color: Int) {
        if (activity == null) {
            return
        }

        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color
    }

    /**
     * Adds the Fragment into layout container
     *
     * @param activity                    The target activity
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     */
    @Throws(IllegalStateException::class)
    fun addFragment(activity: BaseActivity?, fragmentContainerResourceId: Int, currentFragment: Fragment?, nextFragment: Fragment?, requiredAnimation: Boolean, commitAllowingStateLoss: Boolean): Boolean {
        if (activity == null || currentFragment == null || nextFragment == null) {
            return false
        }
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (requiredAnimation) {
            setDefaultFragmentAnimation(fragmentTransaction)
        }

        fragmentTransaction.add(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)
        fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

        val parentFragment = currentFragment.parentFragment
        fragmentTransaction.hide(parentFragment ?: currentFragment)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    /**
     * Adds the Fragment into layout container with animation
     *
     * @param activity                    The target activity
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     */
    @Throws(IllegalStateException::class)
    fun addFragment(activity: BaseActivity?, fragmentContainerResourceId: Int, currentFragment: Fragment?, nextFragment: Fragment?, enter: Int, exit: Int, popEnter: Int, popExit: Int, commitAllowingStateLoss: Boolean): Boolean {
        if (activity == null || currentFragment == null || nextFragment == null) {
            return false
        }
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit)

        fragmentTransaction.add(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)
        fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

        val parentFragment = currentFragment.parentFragment
        fragmentTransaction.hide(parentFragment ?: currentFragment)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }



    @Throws(IllegalStateException::class)
    fun addDialogFragment(manager: FragmentManager?, nextFragment: Fragment?, commitAllowingStateLoss: Boolean): Boolean {
        if (manager == null || nextFragment == null) {
            return false
        }
        val fragmentTransaction = manager.beginTransaction()

        fragmentTransaction.add(nextFragment, nextFragment.javaClass.simpleName)
        fragmentTransaction.addToBackStack(nextFragment.javaClass.simpleName)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }
        return true
    }

    /**
     * Replaces the Fragment into layout container
     *
     * @param activity                    The target activity
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param fragmentManager             FRAGMENT MANGER
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(activity: BaseActivity?, fragmentContainerResourceId: Int, fragmentManager: FragmentManager?, nextFragment: Fragment?, requiredAnimation: Boolean, commitAllowingStateLoss: Boolean): Boolean {
        if (activity == null || nextFragment == null || fragmentManager == null) {
            return false
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (requiredAnimation) {
            setDefaultFragmentAnimation(fragmentTransaction)
        }
        fragmentTransaction.replace(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    @Throws(IllegalStateException::class)
    fun replaceHomeFragment(activity: BaseActivity?, fragmentContainerResourceId: Int, fragmentManager: FragmentManager?, nextFragment: Fragment?): Boolean {
        if (activity == null || nextFragment == null || fragmentManager == null) {
            return false
        }
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(fragmentContainerResourceId, nextFragment, nextFragment.javaClass.simpleName)
        fragmentTransaction.commit()
        return true
    }
}
