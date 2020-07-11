package ru.gladkov.kotlin_vk_sdk_template.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.rahatarmanahmed.cpv.CircularProgressView
import kotlinx.android.synthetic.main.activity_friends.*
import ru.gladkov.kotlin_vk_sdk_template.R
import ru.gladkov.kotlin_vk_sdk_template.adapters.FriendAdapter
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.models.FriendModel
import ru.gladkov.kotlin_vk_sdk_template.presenters.FriendsPresenter
import ru.gladkov.kotlin_vk_sdk_template.views.FriendsView

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    private lateinit var mAdapter: FriendAdapter
    private lateinit var mRvFriends: RecyclerView
    private lateinit var mTvNoItems: TextView
    private lateinit var mCpvWai: CircularProgressView

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        log("fun onCreate (FriendsActivity)")


        mRvFriends = rv_friends
        mTvNoItems = tv_friends_no_items
        mCpvWai = cpv_friends

        // Подключаем EditText и прикрепляем к нему слушатель.
        val mTvSearch: EditText = this.tv_friends_search
        mTvSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                log("onTextChanged (EditText) (FriendsActivity)")
                mAdapter.filter(s.toString())
            }
        })

        friendsPresenter.loadFriends()
        // Создаем адаптер
        mAdapter = FriendAdapter()

        // Передаем адаптер RecyclerView
        mRvFriends.adapter = mAdapter

        mRvFriends.layoutManager =
            LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)
    }

    override fun showError(textResource: Int) {
        log("showError (FriendsActivity)")
        mTvNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        log("setupEmptyList (FriendsActivity)")
        mRvFriends.visibility = View.GONE
        mTvNoItems.visibility = View.VISIBLE
    }


    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        log("setupFriendsList (FriendsActivity)")
        mRvFriends.visibility = View.VISIBLE
        mTvNoItems.visibility = View.GONE

        mAdapter.setupFriends(friendList = friendsList)
    }

    override fun startLoading() {
        log("startLoading (FriendsActivity)")
        mRvFriends.visibility = View.GONE
        mTvNoItems.visibility = View.GONE
        mCpvWai.visibility = View.VISIBLE
    }

    override fun endLoading() {
        log("endLoading (FriendsActivity)")
        mCpvWai.visibility = View.GONE
    }
}
