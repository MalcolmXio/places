package ru.malcolmxio.places.ui.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import ru.malcolmxio.places.util.extensions.getApplication
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class BaseFragment : MvpAppCompatFragment() {
    abstract val layoutRes: Int

    private var instanceStateSaved: Boolean = false

    private val viewHandler = Handler()

    @Inject
    lateinit var globalRouter: Router
    //protected open val parentScopeName: String by lazy {
    //(parentFragment as? BaseFragment)?.fragmentScopeName
    //   ?: DI.SERVER_SCOPE
    //}

    //private lateinit var fragmentScopeName: String
    //protected lateinit var scope: Scope
    //private set

    //protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        //fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()

        //if (Toothpick.isScopeOpen(fragmentScopeName)) {
        //    Timber.d("Get exist UI scope: $fragmentScopeName")
        //    scope = Toothpick.openScope(fragmentScopeName)
        //} else {
        //    Timber.d("Init new UI scope: $fragmentScopeName -> $parentScopeName")
        //    scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
        //    installModules(scope)
        //}
        getApplication().appComponent.inject(this)
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(layoutRes, container, false)

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    // Fix for async views (like swipeToRefresh and RecyclerView)
    // If synchronously call actions on swipeToRefresh in sequence show and hide then swipeToRefresh will not hidden
    protected fun postViewAction(action: () -> Unit) {
        viewHandler.post(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewHandler.removeCallbacksAndMessages(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
        //outState.putString(STATE_SCOPE_NAME, fragmentScopeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (needCloseScope()) {
            val scopeName = (parentFragment as? FlowFragment)?.fragmentScopeName
            getApplication().destroyFlowComponent(scopeName)
        }
    }

    // This is android, baby!
    private fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) || // Because isRemoving == true for fragment in backstack on screen rotation
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    // It will be valid only for 'onDestroy()' method
    private fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    protected fun showProgressDialog(progress: Boolean) {
        if (!isAdded || instanceStateSaved) return

        val fragment = childFragmentManager.findFragmentByTag(PROGRESS_TAG)
        if (fragment != null && !progress) {
            (fragment as ProgressDialog).dismissAllowingStateLoss()
            childFragmentManager.executePendingTransactions()
        } else if (fragment == null && progress) {
            ProgressDialog().show(childFragmentManager, PROGRESS_TAG)
            childFragmentManager.executePendingTransactions()
        }
    }

    abstract fun injectDependencies()

    open fun onBackPressed() = Unit

    companion object {
        private const val PROGRESS_TAG = "bf_progress"
    }

}