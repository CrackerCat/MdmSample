package com.spd.mdmsample.allapn;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.spd.mdm.manager.MdmManager;
import com.spd.mdmsample.AppMdm;
import com.spd.mdmsample.R;
import com.spd.mdmsample.allapn.presenter.EditApnPresenter;
import com.spd.mdmsample.base.BaseMvpActivity;
import com.spd.mdmsample.bean.AllApnBean;
import com.spd.mdmsample.utils.AlertUtils;
import com.spd.mdmsample.utils.SpUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.spd.mdmsample.constant.MdmConstant.ALLAPN_CREATE;
import static com.spd.mdmsample.constant.MdmConstant.MDM_ALLAPN_METHOD;
import static com.spd.mdmsample.constant.MdmConstant.MDM_ALLAPN_QUERY;
import static com.spd.mdmsample.expand.StringExpandKt.apnShow;

/**
 * @author :Reginer in  2019/2/15 10:13.
 * 联系方式:QQ:282921012
 * 功能描述:apn编辑
 */
public class EditApnActivity extends BaseMvpActivity<EditApnPresenter> {

    private EditText mName;
    private EditText mApn;
    private EditText mType;
    private EditText mNumeric;
    private EditText mProxy;
    private EditText mPort;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mServer;
    private EditText mMmsc;
    private EditText mMmsProxy;
    private EditText mMmsPort;
    private EditText mMcc;
    private EditText mMnc;

    private Button mCreate;

    private boolean isCreate;
    private int id;

    private List<AllApnBean> mList;

    @NotNull
    @Override
    protected EditApnPresenter createPresenter() {
        return new EditApnPresenter();
    }

    @Override
    protected int getActLayoutId() {
        return R.layout.activity_edit_apn;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        mList = new ArrayList<>();

        mName = findViewById(R.id.apnName);
        mApn = findViewById(R.id.apnApn);
        mType = findViewById(R.id.apnType);
        mNumeric = findViewById(R.id.apnNumeric);
        mProxy = findViewById(R.id.apnProxy);
        mPort = findViewById(R.id.apnPort);
        mUsername = findViewById(R.id.apnUsername);
        mPassword = findViewById(R.id.apnPassword);
        mServer = findViewById(R.id.apnServer);
        mMmsc = findViewById(R.id.apnMmsc);
        mMmsProxy = findViewById(R.id.apnMmsProxy);
        mMmsPort = findViewById(R.id.apnMmsPort);
        mMcc = findViewById(R.id.apnMcc);
        mMnc = findViewById(R.id.apnMnc);


        mCreate = findViewById(R.id.apnCreate);
        mCreate.setOnClickListener(v -> {
            if (isCreate) {
                int result = MdmManager.getInstance().createApn(EditApnActivity.this.toString());
                StringBuilder msg = new StringBuilder();
                if (result == -1) {
                    msg.append("创建APN失败").append("\n");
                } else {
                    msg.append("创建APN成功，").append("\n").append("APN ID:").append(result);
                }
                AlertUtils.dialog(EditApnActivity.this, msg.toString());
            } else {

                boolean result = MdmManager.getInstance().setCurrentApn(id);
                StringBuilder msg = new StringBuilder();
                if (result) {
                    msg.append("选择APN失败").append("\n");
                } else {
                    msg.append("选择APN成功").append("\n");
                }
                AlertUtils.dialog(EditApnActivity.this, msg.toString());

            }


        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        isCreate = (((String) SpUtils.get(AppMdm.getInstance(), MDM_ALLAPN_METHOD, ALLAPN_CREATE)).equals(ALLAPN_CREATE));

        if (isCreate) {
            mName.setText(apnShow(Telephony.Carriers.NAME));
            mApn.setText(apnShow(Telephony.Carriers.APN));
            mType.setText(apnShow(Telephony.Carriers.TYPE));
            mNumeric.setText(apnShow(Telephony.Carriers.NUMERIC));
            mProxy.setText(apnShow(Telephony.Carriers.PROXY));
            mPort.setText(apnShow(Telephony.Carriers.PORT));
            mUsername.setText(apnShow(Telephony.Carriers.USER));
            mPassword.setText(apnShow(Telephony.Carriers.PASSWORD));
            mServer.setText(apnShow(Telephony.Carriers.SERVER));
            mMmsc.setText(apnShow(Telephony.Carriers.MMSC));
            mMmsProxy.setText(apnShow(Telephony.Carriers.MMSPROXY));
            mMmsPort.setText(apnShow(Telephony.Carriers.MMSPORT));
            mMcc.setText(apnShow(Telephony.Carriers.MCC));
            mMnc.setText(apnShow(Telephony.Carriers.MNC));
        } else {
            //查询此apn
            id = (int) SpUtils.get(AppMdm.getInstance(), MDM_ALLAPN_QUERY, -1);
            if (id == -1) {
                AlertUtils.dialog(EditApnActivity.this, "无Apn ID");
            } else {
                String result = MdmManager.getInstance().getApnInfo(id);
                AllApnBean allApnBean = new Gson().fromJson(result, AllApnBean.class);

                mName.setText(allApnBean.getName());
                mApn.setText(allApnBean.getApn());
                mType.setText(allApnBean.getType());
                mNumeric.setText(allApnBean.getNumeric());
                mProxy.setText(allApnBean.getProxy());
                mPort.setText(allApnBean.getPort());
                mUsername.setText(allApnBean.getUser());
                mPassword.setText(allApnBean.getPassword());
                mServer.setText(allApnBean.getServer());
                mMmsc.setText(allApnBean.getMmsc());
                mMmsProxy.setText(allApnBean.getMmsport());
                mMmsPort.setText(allApnBean.getMmsport());
                mMcc.setText(allApnBean.getMcc());
                mMnc.setText(allApnBean.getMnc());

                setEditEnable();
            }
        }


    }


    private void setEditEnable() {
        mName.setEnabled(false);
        mApn.setEnabled(false);
        mType.setEnabled(false);
        mNumeric.setEnabled(false);
        mProxy.setEnabled(false);
        mPort.setEnabled(false);
        mUsername.setEnabled(false);
        mPassword.setEnabled(false);
        mServer.setEnabled(false);
        mMmsc.setEnabled(false);
        mMmsProxy.setEnabled(false);
        mMmsPort.setEnabled(false);
        mMcc.setEnabled(false);
        mMnc.setEnabled(false);
    }

    @NotNull
    @Override
    public String toString() {
        return "[{" +
                "name=" + mName.getText().toString() +
                ", apn=" + mApn.getText().toString() +
                ", type=" + mType.getText().toString() +
                ", numeric=" + mNumeric.getText().toString() +
                ", mcc=" + mMcc.getText().toString() +
                ", mnc=" + mMnc.getText().toString() +
                ", proxy=" + mProxy.getText().toString() +
                ", port=" + mPort.getText().toString() +
                ", mmsproxy=" + mMmsProxy.getText().toString() +
                ", mmsport=" + mMmsPort.getText().toString() +
                ", user=" + mUsername.getText().toString() +
                ", server=" + mServer.getText().toString() +
                ", password=" + mPassword.getText().toString() +
                ", mmsc=" + mMmsc.getText().toString() +
                "}]";
    }
}
