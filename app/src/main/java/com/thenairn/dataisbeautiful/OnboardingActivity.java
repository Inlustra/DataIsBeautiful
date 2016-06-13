package com.thenairn.dataisbeautiful;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import butterknife.BindView;

/**
 * Created by thoma on 11/06/2016.
 */

public class OnboardingActivity extends AppIntro2 {

    private static final int RC_SIGN_IN = 100;

    @BindView(android.R.id.content)
    View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(
                getString(R.string.onboard_first_title),
                getString(R.string.onboard_first_desc),
                R.drawable.ic_multiline_chart_white_48dp,
                R.color.onboard_first));
        addSlide(AppIntroFragment.newInstance(
                getString(R.string.onboard_second_title),
                getString(R.string.onboard_second_desc),
                R.drawable.ic_multiline_chart_white_48dp,
                R.color.onboard_second));
        addSlide(AppIntroFragment.newInstance(
                getString(R.string.onboard_third_title),
                getString(R.string.onboard_third_desc),
                R.drawable.ic_multiline_chart_white_48dp,
                R.color.onboard_third));
        addSlide(AppIntroFragment.newInstance(
                getString(R.string.onboard_fourth_title),
                getString(R.string.onboard_fourth_desc),
                R.drawable.ic_multiline_chart_white_48dp,
                R.color.onboard_fourth));

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER,
                                AuthUI.GOOGLE_PROVIDER)
                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("SFasf ", resultCode + " " + requestCode + " " + data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
            return;
        }
    }


    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Switcher.initialActivity(this);
            finish();
            return;
        }

        if (resultCode == RESULT_CANCELED) {
            showSnackbar(R.string.sign_in_cancelled);
            return;
        }

        showSnackbar(R.string.sign_in_unknown_response);
    }

    @MainThread
    private void showSnackbar(@StringRes int errorMessageRes) {
        Snackbar.make(getPager(), errorMessageRes, Snackbar.LENGTH_LONG).show();
    }
}
