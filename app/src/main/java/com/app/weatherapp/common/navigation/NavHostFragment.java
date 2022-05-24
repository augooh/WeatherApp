package com.app.weatherapp.common.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.DialogFragmentNavigator;

import com.app.weatherapp.R;

public class NavHostFragment extends Fragment implements NavHost {
    private static final String KEY_GRAPH_ID = "android-support-nav:fragment:graphId";
    private static final String KEY_START_DESTINATION_ARGS =
            "android-support-nav:fragment:startDestinationArgs";
    private static final String KEY_NAV_CONTROLLER_STATE =
            "android-support-nav:fragment:navControllerState";
    private static final String KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost";


    public static NavController findNavController(@NonNull Fragment fragment) {
        Fragment findFragment = fragment;
        while (findFragment != null) {
            if (findFragment instanceof NavHostFragment) {
                return ((NavHostFragment) findFragment).getNavController();
            }
            Fragment primaryNavFragment = findFragment.getParentFragmentManager()
                    .getPrimaryNavigationFragment();
            if (primaryNavFragment instanceof NavHostFragment) {
                return ((NavHostFragment) primaryNavFragment).getNavController();

            }
            findFragment = findFragment.getParentFragment();
        }
        View view = fragment.getView();
        if (view != null) {
            return Navigation.findNavController(view);
        }
        throw new IllegalStateException("Fragment " + fragment
                + " does not have a NavController set");

    }

    private NavHostController mNavController;
    private Boolean mIsPrimaryBeforeOnCreate = null;

    private int mGraphId;
    private boolean mDefaultNavHost;

    @NonNull
    public static NavHostFragment create(@NavigationRes int graphResId,
                                         @Nullable Bundle startDestinationArgs) {
        Bundle b = null;
        if (graphResId != 0) {
            b = new Bundle();
            b.putInt(KEY_GRAPH_ID,graphResId);
        }
        if (startDestinationArgs != null) {
            if (b == null) {
                b = new Bundle();
            }
            b.putBundle(KEY_START_DESTINATION_ARGS, startDestinationArgs);
        }

        final NavHostFragment result = new NavHostFragment();
        if (b != null) {
            result.setArguments(b);
        }
        return result;
    }

    @NonNull
    @Override
    public final NavController getNavController() {
        if (mNavController == null) {
            throw new IllegalStateException("NavController is not available before onCreate()");
        }
        return mNavController;
    }

    @CallSuper
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (mDefaultNavHost) {
            getParentFragmentManager().beginTransaction()
                    .setPrimaryNavigationFragment(this)
                    .commit();
        }
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = requireContext();

        mNavController = new NavHostController(context);
        mNavController.setLifecycleOwner(this);
        mNavController.setOnBackPressedDispatcher(requireActivity().getOnBackPressedDispatcher());

        mNavController.enableOnBackPressed(
                mIsPrimaryBeforeOnCreate != null && mIsPrimaryBeforeOnCreate
        );
        mIsPrimaryBeforeOnCreate = null;
        mNavController.setViewModelStore(getViewModelStore());
        onCreateNavController(mNavController);

        Bundle navState = null;
        if (savedInstanceState != null) {
            navState = savedInstanceState.getBundle(KEY_NAV_CONTROLLER_STATE);
            if (savedInstanceState.getBoolean(KEY_DEFAULT_NAV_HOST, false)) {
                mDefaultNavHost = true;
                getParentFragmentManager().beginTransaction()
                        .setPrimaryNavigationFragment(this)
                        .commit();
            }
            mGraphId = savedInstanceState.getInt(KEY_GRAPH_ID);
        }

        if (navState != null) {
            mNavController.restoreState(navState);
        }
        if (mGraphId != 0) {
            mNavController.setGraph(mGraphId);
        } else {
            final Bundle args = getArguments();
            final int graphId = args != null ? args.getInt(KEY_GRAPH_ID) : 0;
            final Bundle startDestinationArgs = args != null
                    ? args.getBundle(KEY_START_DESTINATION_ARGS)
                    : null;
        if (graphId != 0) {
            mNavController.setGraph(graphId, startDestinationArgs);
        }
        }
    }
    @CallSuper
    protected void onCreateNavController(@NonNull NavController navController) {
        navController.getNavigatorProvider().addNavigator(
                new DialogFragmentNavigator(requireContext(), getChildFragmentManager()));
        navController.getNavigatorProvider().addNavigator(createFragmentNavigator());
    }

    @CallSuper
    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        if (mNavController != null) {
            mNavController.enableOnBackPressed(isPrimaryNavigationFragment);
        } else {
            mIsPrimaryBeforeOnCreate = isPrimaryNavigationFragment;
        }
    }
    @Deprecated
    @NonNull
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        return new FragmentNavigator(requireContext(), getChildFragmentManager(),
                getContainerId());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentContainerView containerView = new FragmentContainerView(inflater.getContext());
        // When added via XML, this has no effect (since this FragmentContainerView is given the ID
        // automatically), but this ensures that the View exists as part of this Fragment's View
        // hierarchy in cases where the NavHostFragment is added programmatically as is required
        // for child fragment transactions
        containerView.setId(getContainerId());
        return containerView;
    }
    private int getContainerId() {
        int id = getId();
        if (id != 0 && id != View.NO_ID) {
            return id;
        }
        // Fallback to using our own ID if this Fragment wasn't added via
        // add(containerViewId, Fragment)
        return R.id.nav_host_fragment_container;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!(view instanceof ViewGroup)) {
            throw new IllegalStateException("created host view " + view + " is not a ViewGroup");
        }
        Navigation.setViewNavController(view, mNavController);
        // When added programmatically, we need to set the NavController on the parent - i.e.,
        // the View that has the ID matching this NavHostFragment.
        if (view.getParent() != null) {
            View rootView = (View) view.getParent();
            if (rootView.getId() == getId()) {
                Navigation.setViewNavController(rootView, mNavController);
            }
        }
    }

    @CallSuper
    @Override
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attrs,
                          @Nullable Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        final TypedArray navHost = context.obtainStyledAttributes(attrs, R.styleable.NavHost);
        final int graphId = navHost.getResourceId(R.styleable.NavHost_navGraph, 0);
        if (graphId != 0) {
            mGraphId = graphId;
        }
        navHost.recycle();

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavHostFragment);
        final boolean defaultHost = a.getBoolean(R.styleable.NavHostFragment_defaultNavHost, false);
        if (defaultHost) {
            mDefaultNavHost = true;
        }
        a.recycle();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle navState = mNavController.saveState();
        if (navState != null) {
            outState.putBundle(KEY_NAV_CONTROLLER_STATE, navState);
        }
        if (mDefaultNavHost) {
            outState.putBoolean(KEY_DEFAULT_NAV_HOST, true);
        }
        if (mGraphId != 0) {
            outState.putInt(KEY_GRAPH_ID, mGraphId);
        }
    }
}
