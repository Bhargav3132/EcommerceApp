package com.heady.ecommerceapp.ui.base

import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.heady.ecommerceapp.R
import com.heady.ecommerceapp.utils.Constant
import com.heady.ecommerceapp.utils.Util


abstract class BaseFragment : Fragment(), View.OnClickListener {

    private val TAG = this::class.java.simpleName
    private var lastClickedTime: Long = 0
    private var llEmptyView: LinearLayout? = null
    private var pbProgress: ProgressBar? = null
    private var tvTitle: TextView? = null


    protected abstract fun defineLayoutResources(): Int
    protected abstract fun initializeComponent(view: View)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(defineLayoutResources(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        header_ibMenu?.let {
        //            header_ibMenu.setOnClickListener(this)
        //        }

        pbProgress = view.findViewById(R.id.layout_pbProgress)
        llEmptyView = view.findViewById(R.id.layout_empty_view_llMain)

        if (llEmptyView != null) {
            (llEmptyView as LinearLayout).visibility = View.GONE
            (llEmptyView as LinearLayout).setOnClickListener(this)
        }

        initializeComponent(view)

    }

    protected fun setTitle(title: String) {
        if (tvTitle != null && !TextUtils.isEmpty(title)) {
            tvTitle!!.text = title
        }
    }

    /**
     * Sets visibility of empty view
     */
    protected fun setEmptyView(isVisible: Boolean) {
        if (llEmptyView != null) {
            (llEmptyView as LinearLayout).setVisibility(if (isVisible) View.VISIBLE else View.GONE)
        }
    }

    protected fun setEmptyViewCustomMessage(isVisible: Boolean, message: String) {
        if (llEmptyView != null) {
            (llEmptyView as LinearLayout).setVisibility(if (isVisible) View.VISIBLE else View.GONE)
        }
    }

    protected fun setProgressBarVisible(isVisible: Boolean) {
        if (pbProgress != null) {
            (pbProgress as ProgressBar).setVisibility(if (isVisible) View.VISIBLE else View.GONE)
        }
    }

    /**
     * Adds the Fragment into layout container.
     *
     * @param container               Resource id of the layout in which Fragment will be added
     * @param currentFragment         Current loaded Fragment to be hide
     * @param nextFragment            New Fragment to be loaded into container
     * @param requiredAnimation       true if screen transition animation is required
     * @param commitAllowingStateLoss true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws ClassCastException    Throws exception if getActivity() is not an instance of BaseActivity
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(ClassCastException::class, IllegalStateException::class)
    protected fun addFragment(
        container: Int, currentFragment: Fragment, nextFragment: Fragment,
        requiredAnimation: Boolean = false, commitAllowingStateLoss: Boolean = false
    ): Boolean {
        return if (activity != null) {
            if (activity is BaseActivity) {
                (activity as BaseActivity).addFragment(
                    container,
                    currentFragment,
                    nextFragment,
                    requiredAnimation,
                    commitAllowingStateLoss
                )
            } else {
                throw ClassCastException(BaseActivity::class.java.name + " can not be cast into " + activity!!::class.java.simpleName)
            }
        } else false
    }

    /**
     * Adds the Fragment into layout container with animation.
     *
     * @param container               Resource id of the layout in which Fragment will be added
     * @param currentFragment         Current loaded Fragment to be hide
     * @param nextFragment            New Fragment to be loaded into container
     * @param requiredAnimation       true if screen transition animation is required
     * @param commitAllowingStateLoss true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws ClassCastException    Throws exception if getActivity() is not an instance of BaseActivity
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(ClassCastException::class, IllegalStateException::class)
    protected fun addFragment(
        container: Int, currentFragment: Fragment, nextFragment: Fragment,
        enter: Int = R.anim.no_enim, exit: Int = R.anim.no_enim, popEnter: Int = R.anim.no_enim,
        popExit: Int = R.anim.no_enim, commitAllowingStateLoss: Boolean = false
    ): Boolean {
        return if (activity != null) {
            if (activity is BaseActivity) {
                (activity as BaseActivity).addFragment(
                    container,
                    currentFragment,
                    nextFragment,
                    enter,
                    exit,
                    popEnter,
                    popExit,
                    commitAllowingStateLoss
                )

            } else {
                throw ClassCastException(BaseActivity::class.java.name + " can not be cast into " + activity!!::class.java.simpleName)
            }
        } else false
    }


    /**
     * Replaces the Fragment into layout container.
     *
     * @param container               Resource id of the layout in which Fragment will be added
     * @param fragmentManager         Activity fragment manager
     * @param nextFragment            New Fragment to be loaded into container
     * @param requiredAnimation       true if screen transition animation is required
     * @param commitAllowingStateLoss true if commitAllowingStateLoss is needed
     * @throws ClassCastException    Throws exception if getActivity() is not an instance of BaseActivity
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(ClassCastException::class, IllegalStateException::class)
    protected fun replaceFragment(
        container: Int, fragmentManager: FragmentManager,
        nextFragment: Fragment, requiredAnimation: Boolean = false,
        commitAllowingStateLoss: Boolean = false
    ) {
        if (activity != null) {
            if (activity is BaseActivity) {
                (activity as BaseActivity).replaceFragment(
                    container,
                    fragmentManager,
                    nextFragment,
                    requiredAnimation,
                    commitAllowingStateLoss
                )
            } else {
                throw ClassCastException(BaseActivity::class.java.name + " can not be cast into " + activity!!::class.java.simpleName)
            }
        }
    }

    override fun onClick(v: View) {
        Util.hideSoftKeyBoard(activity, v)
        //Util.hideKeyboard(getActivity());
        /*
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - lastClickedTime < Constant.MAX_CLICK_INTERVAL) {

            return
        }
        lastClickedTime = SystemClock.elapsedRealtime()
        when (v.id) {
            //            R.id.header_ibMenu -> openDrawer()
            //            R.id.ibBack -> activity?.onBackPressed()

            /*R.id.layout_empty_view_llMain -> reloadData()*/
        }
    }

}