package ru.gladkov.kotlin_vk_sdk_template.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.gladkov.kotlin_vk_sdk_template.R
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.models.FriendModel
import ru.gladkov.kotlin_vk_sdk_template.providers.FriendsProvider
import ru.gladkov.kotlin_vk_sdk_template.views.FriendsView

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {
    fun loadFriends() {
        log("loadFriends (FriendsPresenter)")
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(hasFriends = true)
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        log("friendsLoaded ${friendsList.size} (FriendsPresenter)")
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(textResource = R.string.friends_no_items)
        } else {
            viewState.setupFriendsList(friendsList = friendsList)
        }
    }
}