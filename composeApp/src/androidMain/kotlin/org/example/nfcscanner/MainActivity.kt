package org.example.nfcscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.nfc.NfcAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.widget.TextView

class MainActivity : ComponentActivity() {
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusTextView = findViewById(R.id.nfcStatus)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            statusTextView.text = "NFC not available"
        } else {
            statusTextView.text = "NFC ready"
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(
            this,
            NfcAdapter.createPendingIntent(this, 0, Intent(this, javaClass), 0),
            null,
            null
        )
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)

        if (tag != null) {
            val tagId = tag.id.joinToString("") { byte -> "%02X".format(byte) }
            statusTextView.text = "NFC Detected $tagId"
        } else {
            statusTextView.text = "Not detected"
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}