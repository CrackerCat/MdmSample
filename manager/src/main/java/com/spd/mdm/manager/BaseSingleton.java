package com.spd.mdm.manager;

/**
 * @author :Reginer on  2020/4/13 17:10.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public abstract class BaseSingleton<T> {
    private T mInstance;

    /**
     * 创建实例
     *
     * @return T
     */
    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
