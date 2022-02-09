package homework.chegg.com.chegghomework.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mViewModel: CheggViewModel by viewModels()
    private lateinit var mBinding: ActivityMainBinding
    private var mCheggAdapter: CheggAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        buildUI()
        setupObservers()
    }



    private fun setupToolbar() {
        setSupportActionBar(mBinding.toolbar.root)
    }

    private fun buildUI() {
        setupToolbar()

        mBinding.myRecyclerView.layoutManager = LinearLayoutManager(this);
        mCheggAdapter = CheggAdapter()
        mBinding.myRecyclerView.adapter = mCheggAdapter
    }

    private fun setupObservers() {
        mViewModel.getCheggData().observe(this) { cheggData ->
            mCheggAdapter?.mData = cheggData
        }

        mViewModel.getErrorLiveData().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                onRefreshData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onRefreshData() {
        mViewModel.loadData()
    }



}