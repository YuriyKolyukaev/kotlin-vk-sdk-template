package ru.gladkov.kotlin_vk_sdk_template.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.cell_friend.view.*
import ru.gladkov.kotlin_vk_sdk_template.R
import ru.gladkov.kotlin_vk_sdk_template.log
import ru.gladkov.kotlin_vk_sdk_template.models.FriendModel

class FriendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSourceList: ArrayList<FriendModel> = ArrayList()
    private var mFriendsList: ArrayList<FriendModel> = ArrayList()

    fun setupFriends(friendList: ArrayList<FriendModel>) {
        log("setupFriends (FriendAdapter)")
        mSourceList.clear()
        mSourceList.addAll(friendList)

        filter(query = "")
    }

    // ignoreCase - игнорирует регистр символов.
    fun filter(query: String) {
        log("filter (FriendAdapter)")
        mFriendsList.clear()
        mSourceList.forEach { friendModel ->
            if (friendModel.name.contains(query, ignoreCase = false) || friendModel.surname.contains(query, ignoreCase = false)) {
                mFriendsList.add(friendModel)
            } else {
                friendModel.city?.let { city ->
                    if (city.contains(query, ignoreCase = false)) {
                        mFriendsList.add(friendModel)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        log("onCreateViewHolder (FriendAdapter)")
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)
        return FriendsViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        log("getItemCount ${mFriendsList.count()}(FriendAdapter)")
        return mFriendsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendsViewHolder) {
            holder.bind(friendModel = mFriendsList[position])
            log("onBindViewHolder ${mFriendsList[position]}(FriendAdapter)")
        }
    }



    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mCivAvatar: CircleImageView = itemView.friend_cv_avatar
        private var mTvUserName: TextView = itemView.friend_txt_username
        private var mTvCity: TextView = itemView.friend_txt_city
        private var mImgOnline: View = itemView.friend_img_online

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel) {
            log("bind(friendModel: FriendModel) (FriendAdapter)")
            friendModel.avatar?.let { url ->
                Glide.with(itemView).load(url).into(mCivAvatar)
            }
            mTvUserName.text = "${friendModel.name} ${friendModel.surname}"
            mTvCity.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let { mTvCity.text = it }

            if (friendModel.isOnline) {
                mImgOnline.visibility = View.VISIBLE
            } else {
                mImgOnline.visibility = View.GONE
            }
        }
    }

}