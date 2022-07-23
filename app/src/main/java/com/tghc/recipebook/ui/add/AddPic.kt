package com.tghc.recipebook.ui.add

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.tghc.recipebook.constant.REQUEST_CODE_IMAGE
import com.tghc.recipebook.databinding.AddPicBinding
import com.tghc.recipebook.databinding.RowEditPicBinding
import com.tghc.recipebook.extention.setGlideImage
import com.tghc.recipebook.ui.adapter.RecyclerAdapter
import com.tghc.recipebook.ui.adapter.withAdapter
import com.tghc.recipebook.ui.base.BaseFragment


class AddPic(private val addFragment: AddFragment) : BaseFragment<AddPicBinding>(
    AddPicBinding::inflate
) {

    private lateinit var addPicAdapter: RecyclerAdapter<Uri, RowEditPicBinding>
    private val recipe = addFragment.recipe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (image in recipe.imagePath) {
            addFragment.imageUri.add(Uri.parse(image))
        }

        addPicAdapter = binding.picRecyclerView.withAdapter(RowEditPicBinding::inflate) { data, _ ->
            binding.imageRowPic.setGlideImage(data, isCenterCrop = true)
        }

        addPicAdapter.setClickListeners = {
            binding.imagePicDelete.setOnClickListener {
                addFragment.imageUri.removeAt(adapterPosition)
                addPicAdapter.updateData(addFragment.imageUri)
            }
        }


        binding.fabPic.setOnClickListener {
            /* getCameraPermissions({
                 imageSelector()
             },{

             })*/
            imageSelector()
        }
    }

    private fun imageSelector() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_IMAGE)
    }

    fun getImages(): MutableList<String> {
        return recipe.imagePath
    }


    // save file path in recipe.images

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK) {
            /*if (data?.clipData == null) {
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
            }*/

            if (data?.clipData == null) {
                val uri = data?.data
                addFragment.imageUri.add(uri!!)
                recipe.imagePath.add(uri.toString())
            } else {
                val numberOfImages = data.clipData!!.itemCount
                for (i in 0 until numberOfImages) {
                    val uri = data.clipData!!.getItemAt(i).uri
                    addFragment.imageUri.add(uri)
                    recipe.imagePath.add(uri.toString())
                }
            }
            addPicAdapter.notifyDataSetChanged()
        }
    }
}