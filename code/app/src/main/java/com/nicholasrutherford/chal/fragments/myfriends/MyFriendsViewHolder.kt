package com.nicholasrutherford.chal.fragments.myfriends

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.FriendsListLayoutBinding
import com.squareup.picasso.Picasso

class MyFriendsViewHolder(private var context: Context, private var binding: FriendsListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    private val listOfNames: ArrayList<String> = ArrayList()
    private val listOfUsers: ArrayList<String> = ArrayList()

    fun bind(position: Int) {
        listOfNames.add("Nick Rutherford")
        listOfUsers.add("https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg")

        Picasso.get().load(listOfUsers[position]).into(binding.cvFriends)
        binding.tvProfileName.text = listOfNames[position]

        binding.clFriendsList.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(context)

            // set message of alert dialog
            dialogBuilder.setMessage("What did you want to do")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("View Nick Profile", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
                .setPositiveButton("Send Nick a message", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
                // negative button text and action
                .setNegativeButton("Delete Nick", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("AlertDialogExample")
            // show alert dialog
            alert.show()
        }
        }
    }
