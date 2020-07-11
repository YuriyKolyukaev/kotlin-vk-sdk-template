package ru.gladkov.kotlin_vk_sdk_template.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import ru.gladkov.kotlin_vk_sdk_template.R
import ru.gladkov.kotlin_vk_sdk_template.gone
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.presenters.LoginPresenter
import ru.gladkov.kotlin_vk_sdk_template.views.LoginView
import ru.gladkov.kotlin_vk_sdk_template.visible

class LoginActivity : MvpAppCompatActivity(), LoginView  {

    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mBnEnter: Button
    private lateinit var mTvHello: TextView

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        log("fun OnCreate (LoginActivity)")

        mBnEnter = findViewById(R.id.btn_login_enter)
        mTvHello = findViewById(R.id.tv_login_hello)
        mCpvWait = findViewById(R.id.cpv_login)

        mBnEnter.setOnClickListener {
            loginPresenter.login(isSuccess = true)
        }
    }

    override fun startLoading() {
        mBnEnter.gone()
        mCpvWait.visible()
        log("fun startLoading (LoginActivity)")
    }

    override fun endLoading() {
        mBnEnter.visibility = View.VISIBLE
        mCpvWait.visibility = View.GONE
        log("fun endLoading (LoginActivity)")

    }

    override fun showError(textResources: Int) {
        Toast.makeText(applicationContext, textResources, Toast.LENGTH_SHORT).show()
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
        log("fun openFriends (LoginActivity)")
    }
}
