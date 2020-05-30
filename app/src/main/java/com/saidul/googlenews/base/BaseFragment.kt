package com.saidul.googlenews.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saidul.googlenews.R

open class BaseFragment : Fragment(), IView {
    override fun noInternetConnectionFound() {
        Toast.makeText(requireContext(), getString(R.string.no_internet_found), Toast.LENGTH_LONG)
            .show()
    }

    override fun showProgress() {


    }

    override fun hiddenProgress() {

    }


    override fun onFailure(message: String?) {

    }


}