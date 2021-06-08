package com.spd.mdm.manager;

/**
 * @author :Reginer on  2021/6/7 13:30.
 * 联系方式:QQ:282921012
 * 功能描述:应用卸载回调
 */
interface IPackageDeleteObserver {
  oneway  void packageDeleted(in String packageName, in int returnCode);
}
