package com.yashagrawal.fchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.yashagrawal.fchat.MessageModel
import com.yashagrawal.fchat.R

class MessageAdapter(val context : Context, val messageList : ArrayList<MessageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val item_Receive = 1
    val item_Sent = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == 1){
            //inflate receive
            val view : View = LayoutInflater.from(context).inflate(R.layout.receivemessage,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            //inflate sent
            val view : View = LayoutInflater.from(context).inflate(R.layout.sendmessage,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return item_Sent
        }
        else{
            return item_Receive
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentMessage = messageList[position]
        if (holder.javaClass == SentViewHolder::class.java){
            // do the stuff for the sent viewHolder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text= currentMessage.message
        }
        else{
            // do the stuff for receive viewHolder
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }
    class SentViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }
    class ReceiveViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)

    }

}