package com.tghc.recipebook.ui.add

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.tghc.recipebook.R
import com.tghc.recipebook.constant.REQUEST_CODE_IMAGE
import com.tghc.recipebook.extention.create
import com.tghc.recipebook.extention.pos
import com.tghc.recipebook.extention.setGlideImage
import com.tghc.recipebook.extention.withAdapter
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.add_pic.*
import kotlinx.android.synthetic.main.row_edit_pic.*


class AddPic(private val addFragment: AddFragment) : Fragment() {

    lateinit var addPicAdapter: RecyclerAdapter<Uri>
    lateinit var uri: MutableList<Uri>
    private val recipe = addFragment.recipe

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        create(R.layout.add_pic, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uri = ArrayList()
        for (image in recipe.images) {
            uri.add(Uri.parse(image))
        }


        addPicAdapter = pic_recycler_view.withAdapter(uri, R.layout.row_edit_pic, { data, position ->
            image_row_pic.setGlideImage(data, isCenterCrop = true)
        }, {
            image_pic_delete.setOnClickListener {
                uri.removeAt(pos())
                addPicAdapter.notifyDataSetChanged()
            }
        })


        fab_pic.setOnClickListener {
            val permissions = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            Permissions.check(activity, permissions, null, null, object : PermissionHandler() {
                override fun onGranted() {
                    imageSelector()
                }
            })
        }
    }

    private fun imageSelector() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_IMAGE)
    }

    fun getImages(): MutableList<String> {
        return recipe.images
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data?.clipData == null) {
                val uri = data?.data
                this.uri.add(uri!!)
                recipe.images.add(uri.toString())
            } else {
                val numberOfImages = data.clipData!!.itemCount
                for (i in 0 until numberOfImages) {
                    val uri = data.clipData!!.getItemAt(i).uri
                    this.uri.add(uri)
                    recipe.images.add(uri.toString())
                }
            }
            addPicAdapter.notifyDataSetChanged()
        }
    }
}