package technicaltest.com.app.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import technicaltest.com.app.core.common.ext.view.loadImage
import technicaltest.com.app.data.model.User
import technicaltest.com.app.databinding.ItemUserBinding


class UserVH(private val viewBinding: ItemUserBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: User) {
        item.picture?.let { viewBinding.ivThumbnail.loadImage(it) }
        viewBinding.tvName.text = "${item.firstName} ${item.lastName}"
    }
}