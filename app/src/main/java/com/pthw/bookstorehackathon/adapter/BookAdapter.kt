package com.pthw.bookstorehackathon.adapter

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer.pthw.retrofittest.Api.Api
import com.developer.pthw.retrofittest.Api.ApiClient
import com.pthw.bookstorehackathon.R
import com.pthw.bookstorehackathon.model.Book
import kotlinx.android.synthetic.main.custom_dialog.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class BookAdapter(var context: Context, var booklist: List<Book>) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    val api: Api = ApiClient.client.create(Api::class.java)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.all_item_list, viewGroup, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val book = booklist.get(i)
        myViewHolder.title.text = book.title
        myViewHolder.price.text = book.price
        Log.i("author", book.author.name)
        Log.i("publisher", book.publisher.name)
        Log.i("genre", book.genre.type)

        Glide.with(context)
            .load(ApiClient.BASE_URL + book.image)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .dontAnimate()
            ).into(myViewHolder.image)

        myViewHolder.itemView.setOnClickListener {
            //---cusotm round dialog

            val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(context!!, R.style.customizedAlert)
                .setView(mDialogView)

            Glide.with(context!!).load(ApiClient.BASE_URL + book.image)
                .apply(RequestOptions().placeholder(R.drawable.logoblack))
                .into(mDialogView.image)

            mDialogView.title.text = book.title
            mDialogView.price.text = book.price
            mDialogView.descr.text = book.description
            mDialogView.author.text = book.author.name
            mDialogView.publisher.text = book.publisher.name
            mDialogView.genre.text = book.genre.type

            //---custom dialog for book detail and download
            mBuilder.setPositiveButton("Download") { dialog, which ->
                val call = api.downloadFileWithDynamicUrlSync(ApiClient.BASE_URL.plus(book.pdf).replace(" ", ""))
                Log.i("pdf", ApiClient.BASE_URL.plus(book.pdf).replace(" ", ""))

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            Log.i("server", "server contacted and has file")
                            val name = book.pdf.split("/")
                            val n = name[name.size - 1]
                            val writtenToDisk = writeResponseBodyToDisk(response.body()!!, n)

                            Log.i("server", "file download was a success? $writtenToDisk")
                        } else {
                            Log.i("server", "server contact failed")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e(TAG, "error")
                    }
                })
            }.setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }

            //show dialog
            mBuilder.show()
        }

    }

    override fun getItemCount(): Int {
        return booklist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var price: TextView
        var image: ImageView

        init {

            title = itemView.findViewById(R.id.title)
            price = itemView.findViewById(R.id.price)
            image = itemView.findViewById(R.id.image)

        }
    }

    //--file download method from server
    private fun writeResponseBodyToDisk(body: ResponseBody, filename: String): Boolean {
        try {
            // todo change the file location/name according to your needs

            val futureStudioIconFile =
                File(File.separator + context.getExternalFilesDir(null) + "/$filename")

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)

                    if (read == -1) {
                        break
                    }

                    outputStream!!.write(fileReader, 0, read)

                    fileSizeDownloaded += read.toLong()

                    Log.i("gggg", "file download: $fileSizeDownloaded of $fileSize")
                }

                outputStream!!.flush()

                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream!!.close()
                }

                if (outputStream != null) {
                    outputStream!!.close()
                }
            }
        } catch (e: IOException) {
            return false
        }

    }


}
