package ep.ws

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ep.ws.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<SearchResponse> {
    private val tag = this::class.java.canonicalName
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            Log.i(tag, "Searching ...")
            OMDBService.instance.search(binding.query.text.toString()).enqueue(this)
            binding.query.setText("")
        }
    }

    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
        if (!response.isSuccessful) {
            Log.i(tag, "An unsuccessful request: ${response.errorBody()?.string()}")
            return
        }

        Log.i(tag, "Got results: ${response.body()?.totalResults}")
        binding.results.text = ""

        response.body()?.Search?.forEach {
            binding.results.text = "${binding.results.text} ${it.Title}, ${it.Type} (${it.Year})\n"
        }
    }

    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
        Log.w(tag, "Got error", t)
    }
}
