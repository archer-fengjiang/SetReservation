#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>


jint Java_com_cmu_setreservation_MySyscall_GetProcessComputePlotPoint(JNIEnv *env, jobject this, jint pid)
{
	long l = syscall(378,pid,100);
	if(l > 0){
		return 0;
	} else if (l == 0){
		return 1;
	}
}
