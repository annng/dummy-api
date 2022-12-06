package technicaltest.com.app.ui.adapter

import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import technicaltest.com.app.R
import technicaltest.com.app.core.common.ext.data.dashIfEmpty
import technicaltest.com.app.core.common.ext.view.loadImageCircle
import technicaltest.com.app.core.common.ext.view.loadImageRounded
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.mapper.toEntity
import technicaltest.com.app.data.model.Post
import technicaltest.com.app.databinding.ChipTagBinding
import technicaltest.com.app.databinding.ItemPostBinding


class LikeVH(private val viewBinding: ItemPostBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: PostEntity, dao: PostDao, onLike: (PostEntity, Boolean) -> Unit) {
        item.image?.let { viewBinding.ivPhoto.loadImageRounded(it) }
        item.owner?.picture?.let { viewBinding.ivProfile.loadImageCircle(it) }
        val isExist = dao.isExist(item.id)

        viewBinding.llTag.removeAllViews()
        viewBinding.tvName.text = "${item.owner?.firstName} ${item.owner?.lastName}"
        viewBinding.tvDate.text = item.publishDate.dashIfEmpty()
        viewBinding.tvDesc.text = item.text.dashIfEmpty()
        viewBinding.tvLink.text = item.link.dashIfEmpty()
        viewBinding.tvLike.text = "${item.likes} Likes"

        viewBinding.ivLove.setImageResource(R.drawable.ic_like_fill)


        viewBinding.tvLink.setOnClickListener {
            Toast.makeText(
                viewBinding.root.context,
                "Underconstruction ${item.link}",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewBinding.ivLove.setOnClickListener {
            item.id?.let { id ->
                onLike(item, isExist)
            }
        }

    }

    private fun showTag(): ChipTagBinding {
        return ChipTagBinding.inflate(
            LayoutInflater.from(viewBinding.root.context),
            viewBinding.llTag,
            true
        )
    }
}