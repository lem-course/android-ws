package ep.ws

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<SearchResponse> {
    private val tag = this::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_btn.setOnClickListener {
            Log.i(tag, "Searching ...")
            OMDBService.instance.search(query.text.toString()).enqueue(this)
            query.setText("")
        }
    }

    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
        if (!response.isSuccessful) {
            Log.i(tag, "An unsuccessful request: ${response.errorBody()?.string()}")
            return
        }

        Log.i(tag, "Got results: ${response.body()?.totalResults}")
        results.text = ""

        response.body()?.Search?.forEach {
            results.text = "${results.text} ${it.Title}, ${it.Type} (${it.Year})\n"
        }
    }

    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
        Log.w(tag, "Got error", t)
    }
}
