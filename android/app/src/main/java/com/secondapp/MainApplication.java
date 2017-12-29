package com.secondapp;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.microsoft.codepush.react.CodePush;
import com.microsoft.appcenter.reactnative.analytics.AppCenterReactNativeAnalyticsPackage;
import com.microsoft.appcenter.reactnative.appcenter.AppCenterReactNativePackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new CodePush(getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey), getApplicationContext(), BuildConfig.DEBUG),
                    new com.microsoft.appcenter.reactnative.crashes.AppCenterReactNativeCrashesPackage(MainApplication.this, getResources().getString(R.string.appcenterCrashes_whenToSendCrashes)),
                    new AppCenterReactNativeAnalyticsPackage(MainApplication.this, getResources().getString(R.string.appcenterAnalytics_whenToEnableAnalytics)),
                    new AppCenterReactNativePackage(MainApplication.this)
            );
        }

        @Override
        public String getJSBundleFile() {
            // Override default getJSBundleFile method with the one CodePush is providing
            return CodePush.getJSBundleFile();
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
