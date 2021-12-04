package ep.ws

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ep.ws.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = this::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            Log.i(tag, "Searching ...")
            // TODO: Klic storitve in prikaz rezultatov
            binding.query.setText("")
        }
    }
}
