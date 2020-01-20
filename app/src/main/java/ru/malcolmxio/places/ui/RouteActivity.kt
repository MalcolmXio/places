package ru.malcolmxio.places.ui

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.layout_container.*
import moxy.MvpAppCompatActivity
import ru.malcolmxio.places.App
import ru.malcolmxio.places.R
import ru.malcolmxio.places.Screens
import ru.malcolmxio.places.di.NavHolder
import ru.malcolmxio.places.ui.base.BaseFragment
import ru.malcolmxio.places.ui.base.MessageDialogFragment
import ru.malcolmxio.places.util.extensions.doOnApplyWindowInsets
import ru.malcolmxio.places.util.extensions.updatePadding
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class RouteActivity : MvpAppCompatActivity() {

    @Inject
    @NavHolder("Global")
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    //@Inject
    //lateinit var systemMessageNotifier: SystemMessageNotifier

    private var notifierDisposable: Disposable? = null

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    decorView.systemUiVisibility = decorView.systemUiVisibility or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }

                statusBarColor = ContextCompat.getColor(context, R.color.black_20)
                navigationBarColor = ContextCompat.getColor(context, R.color.white_50)
            }
        }

        (application as App).appComponent.inject(this)
        //appLauncher.onLaunch()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        if (savedInstanceState == null) {
            //appLauncher.coldStart()
            //Cold start
            val rootScreen = Screens.StartFlow
            router.newRootScreen(rootScreen)
        }
        container.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(
                left = initialPadding.left + insets.systemWindowInsetLeft,
                right = initialPadding.right + insets.systemWindowInsetRight
            )
            insets.replaceSystemWindowInsets(
                Rect(
                    0,
                    insets.systemWindowInsetTop,
                    0,
                    insets.systemWindowInsetBottom
                )
            )
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        //subscribeOnSystemMessages()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        //unsubscribeOnSystemMessages()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun showAlertMessage(message: String) {
        MessageDialogFragment.create(
            message = message
        ).show(supportFragmentManager, null)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //private fun subscribeOnSystemMessages() {
    //    notifierDisposable = systemMessageNotifier.notifier
    //        .subscribe { msg ->
    //            when (msg.type) {
    //               SystemMessageType.ALERT -> showAlertMessage(msg.text)
    //               SystemMessageType.TOAST -> showToastMessage(msg.text)
    //           }
    //       }
    //}

    //private fun unsubscribeOnSystemMessages() {
    //    notifierDisposable?.dispose()
    //}
}