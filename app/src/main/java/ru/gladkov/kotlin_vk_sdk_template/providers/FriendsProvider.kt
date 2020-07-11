package ru.gladkov.kotlin_vk_sdk_template.providers

import android.os.Handler
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.models.FriendModel
import ru.gladkov.kotlin_vk_sdk_template.presenters.FriendsPresenter

class FriendsProvider (var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends:Boolean) {
        log("fun testLoadFriends (FriendsProvider)")
        Handler().postDelayed({
            log("Handler2 (FriendsProvider)")
            val friendsList:ArrayList<FriendModel> = ArrayList();
        if (hasFriends) {
            val friend1 = FriendModel(name = "Ivan", surname = "Petrov", city = null,
                avatar = "https://schlock.ru/wp-content/uploads/2015/12/sshot-362.jpg", isOnline = false)
            val friend2 = FriendModel(name = "Alexey", surname = "Gladkov", city = "Omsk",
                avatar = "https://www.kino-teatr.ru/acter/photo/6/7/426876/914620.jpg", isOnline = false)
            val friend3 = FriendModel(name = "Dmitriy", surname = "Vinogradov", city = "Moscow",
                avatar = "https://static.merlion.ru/data/news/2016/vinogradov.d.jpg", isOnline = false)
            friendsList.add(friend1)
            friendsList.add(friend2)
            friendsList.add(friend3)

        }
            presenter.friendsLoaded(friendsList = friendsList)
        }, 2000)
    }
}