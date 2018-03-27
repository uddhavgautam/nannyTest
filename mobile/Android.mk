LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

# Module name should match apk name to be installed.
LOCAL_MODULE := GRR_Rapid_Response
LOCAL_SRC_FILES := $(LOCAL_MODULE).apk
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)

LOCAL_PACKAGE_NAME := forensics.nexuslab.com.grr.mobile
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true

include $(BUILD_PREBUILT)
