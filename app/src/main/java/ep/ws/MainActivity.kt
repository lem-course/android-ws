package ep.ws

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val API_KEY = ""
        const val WS_URL = "https://www.omdbapi.com/?s=%s&apikey=$API_KEY"
        val TAG: String = MainActivity::class.java.canonicalName!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_btn.setOnClickListener {
            Log.i(TAG, "Searching ...")
            query.setText("")
        }
    }
}
