package technicaltest.com.app.ui.main.child.like

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import technicaltest.com.app.R
import technicaltest.com.app.core.common.ext.view.toBinding
import technicaltest.com.app.core.common.ext.view.viewBinding
import technicaltest.com.app.data.local.dao.PostDao
import technicaltest.com.app.data.local.entity.PostEntity
import technicaltest.com.app.databinding.FragmentLikeBinding
import technicaltest.com.app.ui.adapter.LikeVH
import technicaltest.com.app.ui.base.BaseFragment
import technicaltest.com.app.ui.base.GenericAdapter
import android.widget.Toast

class LikeFragment : BaseFragment(R.layout.fragment_like), KoinComponent {

    private val viewBinding: FragmentLikeBinding by viewBinding(FragmentLikeBinding::bind)
    private val viewModel: LikeViewModel by viewModel()

    private val postDao : PostDao by inject()


    private val postAdapter = object : GenericAdapter<PostEntity>(
        itemClickListener = {
            Toast.makeText(requireContext(), it.text, Toast.LENGTH_SHORT).show()
        }
    ) {
        override fun getViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            return LikeVH(parent.toBinding())
        }

        override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
            val data = getItem(position)
            val isLike = viewModel.isExist(data.id)
            (holder as LikeVH).bind(data, isLike){ item, isExist ->
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

        viewModel.getLikePosts()
    }

    private fun observeData(){
        viewModel.post.observe(viewLifecycleOwner){
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