package ru.gladkov.kotlin_vk_sdk_template.presenters

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.gladkov.kotlin_vk_sdk_template.R
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.views.LoginView

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean) {
        log("fun login (LoginPresenter)")
        viewState.startLoading()
        Handler().postDelayed({
            log("Handler1 (LoginPresenter)")
            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            } else {
                viewState.showError(textResources = R.string.login_error_credentials)
            }
        },500)
    }
}