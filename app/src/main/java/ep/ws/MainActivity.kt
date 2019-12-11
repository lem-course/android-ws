package ep.ws

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

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
            val task = LookUp(this)
            task.execute(query.text.toString())
            query.setText("")
        }
    }

    class LookUp(private val activity: MainActivity) : AsyncTask<String, Unit, JSONObject>() {
        override fun doInBackground(vararg params: String?): JSONObject {
            if (params.size != 1)
                throw IllegalArgumentException("Metoda potrebuje 1 parameter")

            try {
                val url = URL(String.format(WS_URL, params[0]))

                val conn = (url.openConnection() as HttpURLConnection).apply {
                    doInput = true
                    requestMethod = "GET"
                    setRequestProperty("accept", "application/json")
                }

                val scanner = Scanner(conn.inputStream).useDelimiter("\\A")
                val content = if (scanner.hasNext()) scanner.next() else ""

                return JSONObject(content)
            } catch (e: Exception) {
                Log.w(TAG, "Exception: ${e.localizedMessage}")
                return JSONObject()
            }
        }

        override fun onPostExecute(result: JSONObject) {
            activity.results.text = result.toString(2)
        }
    }
}
