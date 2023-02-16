package recio.aitor.fakebot

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import recio.aitor.fakebot.databinding.ChatMessageItemBinding

private val TAG = FbAdapter::class.java.simpleName
class FbAdapter : ListAdapter<FakeBotMessage, FbAdapter.FbViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<FakeBotMessage>() {

        override fun areItemsTheSame(oldItem: FakeBotMessage, newItem: FakeBotMessage) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FakeBotMessage, newItem: FakeBotMessage) =
            oldItem == newItem
    }

//    fun updateItems( newItems: List<FakeBotMessage>){
//        this.items.clear()
//        this.item.addAll(newItems)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FbAdapter.FbViewHolder {

        val binding = ChatMessageItemBinding.inflate(LayoutInflater.from(parent.context))
        return FbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FbAdapter.FbViewHolder, position: Int) {

        val chatMessage = getItem(position)
        holder.bind(chatMessage)
    }

    inner class FbViewHolder ( private val binding: ChatMessageItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(chatMessage : FakeBotMessage){
                binding.messageText.text = chatMessage.text
                if (chatMessage.user){
                    binding.messageText.gravity = Gravity.END
                    binding.messageText.setBackgroundColor(Color.argb(255,209,230,218))
                }else{
                    binding.messageText.setBackgroundColor(Color.argb(255,235,236,243))
                }
                binding.executePendingBindings()
            }
        }
}