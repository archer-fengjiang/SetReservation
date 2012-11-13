LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_LDLIBS := -llog -L$(SYSROOT)/usr/lib
LOCAL_MODULE := ndk1
LOCAL_SRC_FILES := native.c
include $(BUILD_SHARED_LIBRARY)