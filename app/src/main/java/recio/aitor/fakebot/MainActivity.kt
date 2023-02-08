package recio.aitor.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import recio.aitor.fakebot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbRecycler.layoutManager =LinearLayoutManager(this)
        val fbChatList = mutableListOf<FakeBotMessage>()

        if (fbChatList.isEmpty()){
            binding.fbEmptyView.visibility = View.VISIBLE
        }else{
            binding.fbEmptyView.visibility = View.GONE
        }
    }
}