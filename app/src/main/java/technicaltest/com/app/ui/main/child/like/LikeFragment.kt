package technicaltest.com.app.ui.main.child.like

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import technicaltest.com.app.R
import technicaltest.com.app.core.common.cons.RequestCons
import technicaltest.com.app.core.common.ext.view.onEndScroll
import technicaltest.com.app.core.common.ext.view.toBinding
import technicaltest.com.app.core.common.ext.view.viewBinding
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.data.model.Post
import technicaltest.com.app.databinding.FragmentLikeBinding
import technicaltest.com.app.di.localModule
import technicaltest.com.app.ui.adapter.LikeVH
import technicaltest.com.app.ui.adapter.PostVH
import technicaltest.com.app.ui.base.BaseFragment
import technicaltest.com.app.ui.base.GenericAdapter
import technicaltest.com.app.ui.main.child.post.PostViewModel


class LikeFragment : BaseFragment(R.layout.fragment_like), KoinComponent {

    private val viewBinding: FragmentLikeBinding by viewBinding(FragmentLikeBinding::bind)
    private val viewModel: LikeViewModel by viewModel()

    private val postDao : PostDao by inject()


    private val postAdapter = object : GenericAdapter<PostEntity>(
        itemClickListener = {
            //todo onClick
        }
    ) {
        override fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            return LikeVH(parent.toBinding())
        }

        override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
            val data = getItem(position)
            (holder as LikeVH).bind(data, postDao){ item, isExist ->
                if(isExist){
                    data.id?.let { viewModel.deleteLike(it) }
                }
                observeData()
            }
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    private fun initView() {
        viewBinding.rvLike.adapter = postAdapter
    }

    private fun observeData(){
        viewModel.getLikePosts()?.let {
            postAdapter.setItems(it.toMutableList())
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LikeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}