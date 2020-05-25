package com.spd.mdmsample.allapn

import android.content.ContentValues
import android.os.Bundle
import android.provider.Telephony
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.spd.mdm.manager.MdmManager
import com.spd.mdmsample.AppMdm
import com.spd.mdmsample.R
import com.spd.mdmsample.allapn.adapter.ApnAdapter
import com.spd.mdmsample.allapn.contract.AllApnContract
import com.spd.mdmsample.allapn.presenter.AllApnPresenter
import com.spd.mdmsample.base.BaseMvpActivity
import com.spd.mdmsample.constant.MdmConstant.*
import com.spd.mdmsample.utils.AlertUtils
import com.spd.mdmsample.utils.SpUtils
import kotlinx.android.synthetic.main.activity_all_apn.*
import org.jetbrains.anko.startActivity

/**
 * @author :Reginer in  2019/2/14 13:31.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
class AllApnActivity : BaseMvpActivity<AllApnPresenter>(), AllApnContract.View, SearchView.OnQueryTextListener, OnItemChildClickListener, OnItemClickListener, View.OnClickListener {


    private lateinit var mAdapter: ApnAdapter

    override fun createPresenter(): AllApnPresenter {
        return AllApnPresenter()
    }

    override fun initToolBar() {
    }


    override fun getActLayoutId(): Int {
        return R.layout.activity_all_apn
    }

    override fun initView(savedInstanceState: Bundle?) {
        mAdapter = ApnAdapter(null)
        rvApn.adapter = mAdapter
        rvApn.addItemDecoration(DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL))
        mAdapter.setOnItemChildClickListener(this)
        mAdapter.setOnItemClickListener(this)
        searchApn.onActionViewExpanded()
        searchApn.setOnQueryTextListener(this)
        apn_using.setOnClickListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.getAllApn()
    }

    override fun showAllApn(apnValues: List<ContentValues>?) {
        mAdapter.setNewData(apnValues as MutableList<ContentValues>?)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu_apn_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        SpUtils.put(AppMdm.getInstance(), MDM_ALLAPN_METHOD, ALLAPN_CREATE)
        startActivity<EditApnActivity>()
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        mPresenter.queryApn(p0)
        return false
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val id = mAdapter.data[position].getAsInteger(Telephony.Carriers._ID)
        val result = MdmManager.getInstance().getApnInfo(id)
        if (!result.isNullOrEmpty()) {
            SpUtils.put(AppMdm.getInstance(), MDM_ALLAPN_METHOD, ALLAPN_CHOOSE)
            SpUtils.put(AppMdm.getInstance(), MDM_ALLAPN_QUERY, id)
            startActivity<EditApnActivity>()
        } else {
            AlertUtils.dialog(this, "查询apn失败")
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        MdmManager.getInstance().deleteApn(mAdapter.data[position].getAsInteger(Telephony.Carriers._ID))
        mAdapter.remove(position)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.apn_using) {
                val apnBean = MdmManager.getInstance().getCurrentApn()
                if (apnBean == null){
                    AlertUtils.dialog(this,"获取当前APN失败")
                }else{
                    val mList: MutableList<ContentValues>? = null
                    mList?.add(apnBean)
                    mAdapter.setNewData(mList)
                }

            }
        }
    }


}
