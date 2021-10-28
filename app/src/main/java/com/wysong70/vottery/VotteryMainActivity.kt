package com.wysong70.vottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.wysong70.vottery.fragment.FragmentAdapter
import com.wysong70.vottery.fragment.FragmentMakeSurvey
import com.wysong70.vottery.fragment.FragmentMyResultList
import com.wysong70.vottery.fragment.surveyList.FragmentSurveyList
import com.wysong70.vottery.databinding.ActivityVotteryMainBinding

class VotteryMainActivity : AppCompatActivity() {

    val vMBinding by lazy { ActivityVotteryMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vMBinding.root)

        val binding = (DataBindingUtil.setContentView(
            this,
            R.layout.activity_vottery_main
        ) as ActivityVotteryMainBinding)
            .apply {
                lifecycleOwner = this@VotteryMainActivity
            }

        val tabTitles = listOf<String>(
            this.getString(R.string.surveylist),
            this.getString(R.string.makesurvey),
            this.getString(R.string.myresult)
        )

        val pagerAdapter = PagerFragmentStateAdapter(this)
            .apply {
                addFragment(FragmentSurveyList())
                addFragment(FragmentMakeSurvey())
                addFragment(FragmentMyResultList())
            }

        // 2.ViewPager2의 Adapter 설정
        val viewPager: ViewPager2 = binding.ViewPager.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("ViewPagerFragment", "Page ${position+1}")
                }
            })
        }

        // 3.TabLayout과 ViewPager 연결
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

//        var adapterRecycler = SurveyRecyclerAdapter()
//        adapterRecycler.listData = loadSurveyList()

//        adapter.fragmentList[0].
//        vSurveyListBinding.SurveyListRView.adapter = adapter

    }
}