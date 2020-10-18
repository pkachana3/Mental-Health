package mentalhealth.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_chat_bot.*


class ChatBotFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_bot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.loadUrl("https://console.dialogflow.com/api-client/demo/embedded/33b70d6a-2e7c-4517-bb6c-9b6d106b4a6f")
        webView.webViewClient = WebViewClient()
        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
    }
}