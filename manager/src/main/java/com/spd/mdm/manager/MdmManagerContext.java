package com.spd.mdm.manager;

import android.content.Context;

/**
 * @author :Reginer in  2021/3/29 17:18.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class MdmManagerContext {


    private Context context;
    private static final BaseSingleton<MdmManagerContext> INSTANCE = new BaseSingleton<MdmManagerContext>() {
        @Override
        protected MdmManagerContext create() {
            return new MdmManagerContext();
        }
    };

    public static MdmManagerContext getInstance() {
        return INSTANCE.get();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
