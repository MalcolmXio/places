package ru.malcolmxio.places.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ru.malcolmxio.places.R
import ru.malcolmxio.places.di.FragmentNavigatorHolder
import ru.malcolmxio.places.presentation.flow.FlowRouter
import ru.malcolmxio.places.util.extensions.getApplication
import ru.malcolmxio.places.util.extensions.setLaunchScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

abstract class FlowFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.layout_container

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @Inject
    @FragmentNavigatorHolder
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: FlowRouter

    override fun injectDependencies() {
        super.injectDependencies()
        getApplication().flowComponent?.inject(this@FlowFragment)
    }

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity, childFragmentManager, R.id.container) {
            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(getLaunchScreen())
        }
    }

    abstract fun getLaunchScreen(): SupportAppScreen

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}