package org.school.demoapp

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class Downloader
{
    var context: Context? = null
    private var fileName: String? = null
    private var downloadManager: DownloadManager? = null
    private var downloadId: Long = 0
    private var filter: IntentFilter? = null
    var isDownloaded: MutableLiveData<Boolean> = MutableLiveData()

    fun downloaderDm(context: Context?, fileName: String?) {
        this.context = context
        this.fileName = fileName

        isDownloaded.value = false
    }

    fun downloadManager(url: String) {
        var url = url
        filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        filter!!.addAction(DownloadManager.COLUMN_REASON)
        filter!!.addAction(DownloadManager.COLUMN_STATUS)
        context!!.registerReceiver(downloadReceiver, filter)
        var downloadReference: Long

        downloadManager = context!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        if (url.toLowerCase().contains("http"))
        {
            val request = DownloadManager.Request(Uri.parse(url))

            //Setting title of request
            request.setTitle(fileName)
            request.setAllowedOverRoaming(true)

            val mimeType = MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
            request.setAllowedOverMetered(true)

            if ( mimeType != null ) request.setMimeType(mimeType)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            request.setDestinationInExternalPublicDir( Environment.DIRECTORY_PICTURES , fileName)

            try {
                downloadId = downloadManager!!.enqueue(request)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val downloadReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //check if the broadcast message is for our Enqueued download
            val referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == intent.action && downloadId == referenceId) {
                isDownloaded.value = true
                context.unregisterReceiver(this)
            }
        }
    }
}