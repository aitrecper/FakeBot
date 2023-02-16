package recio.aitor.fakebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import recio.aitor.fakebot.databinding.ActivityMainBinding
import kotlin.random.Random

private val responses = arrayOf("Si", "Pregunta de nuevo", "No", "Es muy probable", "No lo creo",
    "Tal vez", "No se")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbRecycler.layoutManager =LinearLayoutManager(this)
        val fbChatList = mutableListOf<FakeBotMessage>()


        val adapter = FbAdapter()

        binding.fbRecycler.adapter = adapter
        adapter.submitList(fbChatList)
        binding.sendMessageButton.setOnClickListener {
            if(binding.messageEdit.text.isEmpty()){
                Toast.makeText(this,"No puedes enviar un mensaje vacío",Toast.LENGTH_SHORT).show()
            }else{
                binding.fbEmptyView.visibility = View.GONE
                fbChatList.add(FakeBotMessage((fbChatList.size+1).toString(),binding.messageEdit.text.toString(),true))
                Log.d("Test","${fbChatList.size}")
                adapter.notifyDataSetChanged()
                Handler().postDelayed({
                    val r = Random.nextInt(0, 7)
                    fbChatList.add(FakeBotMessage((fbChatList.size+1).toString(), responses[r],false))
                    adapter.notifyDataSetChanged()
                }, 1000)
            }
        }
        if (fbChatList.isEmpty()){
            binding.fbEmptyView.visibility = View.VISIBLE
        }else{
            binding.fbEmptyView.visibility = View.GONE
        }
    }
}