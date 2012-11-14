#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

void Java_com_cmu_setreservation_MySyscall_CancelBudget(JNIEnv *env, jobject this, jint pid)
{
	syscall(379,pid);
}

void Java_com_cmu_setreservation_MySyscall_WaitUntilNextPeriod(JNIEnv *env, jobject this, jint pid)
{
	syscall(380, pid);
}

jint Java_com_cmu_setreservation_MySyscall_GetProcessComputePlotPoint(JNIEnv *env, jobject this, jint pid)
{
	long l = syscall(383,pid);
	if(l > 0){
		return 0;
	} else if (l == 0){
		return 1;
	}
}

jint Java_com_cmu_setreservation_MySyscall_SetProcessBudget(JNIEnv *env, jobject this, jint pid, jint budget_sec, jint budget_nsec,
		jint period_sec, jint period_nsec, jint rtprio)
{
	struct timespec budget;
	struct timespec period;
	budget.tv_sec = budget_sec;
	budget.tv_nsec = budget_nsec;
	period.tv_sec = period_sec;
	period.tv_nsec = period_nsec;

	return syscall(378,pid,&budget,&period,rtprio);
}


