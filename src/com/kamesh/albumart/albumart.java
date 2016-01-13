package com.kamesh.albumart; 
 
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class albumart
  implements IXposedHookLoadPackage
{
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam paramLoadPackageParam)
    throws Throwable
  {
    if (!paramLoadPackageParam.packageName.equals("com.android.systemui")) {
      return;
    }
    Class localClass = XposedHelpers.findClass("com.android.systemui.statusbar.phone.PhoneStatusBar", paramLoadPackageParam.classLoader);
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Boolean.TYPE;
    XposedBridge.hookMethod(XposedHelpers.findMethodBestMatch(localClass, "updateMediaMetaData", arrayOfClass), new XC_MethodHook()
    {
      protected void beforeHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
        throws Throwable
      {
        XposedHelpers.setObjectField(paramAnonymousMethodHookParam.thisObject, "mMediaMetadata", null);
      }
    });
  }
}

