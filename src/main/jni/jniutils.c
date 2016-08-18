//
// Created by car on 2016/8/17.
//

#include "com_quan_car_qmeeting_JniUtils.h"
/*
* Class:     Java_com_ndkjnidemo_quan_ndkjnidemo_JniUtils
* Method:    getStringFormC
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_com_quan_car_qmeeting_JniUtils_getStringFormC
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env, "权兴权意-这里是来自C的string");
}

JNIEXPORT jstring JNICALL Java_com_quan_car_qmeeting_JniUtils_updateString
        (JNIEnv *env, jobject obj,jstring js){
    //jstring js
    //生成native的char指针
    const char* c = (*env)->GetStringUTFChars(env,js,NULL);
//    if(c != NULL){
//        LOGV("from Java const char*%s",c);
//    }
//    if(js != NULL){
//        //LOGV("from Java jstring%s",js);
//        return (*env)->NewStringUTF(env, js);
//    }
    return (*env)->NewStringUTF(env, c);
    //return (*env)->NewStringUTF(env, "权兴权意-来自updateString");
}

JNIEXPORT jstring JNICALL Java_com_quan_car_qmeeting_JniUtils_encodeFromC
        (JNIEnv *env, jobject obj,jstring passWord,jint length){
    //生成native的char指针
    const char* c = (*env)->GetStringUTFChars(env,passWord,NULL);
    char* cStr = c;
    int i ;
    for(i = 0;i < length;i++){
        *(cStr + i) += 1;
    }
    //将c语言字符串转化为java字符串
    return (*env)->NewStringUTF(env, c);
}

JNIEXPORT jstring JNICALL Java_com_quan_car_qmeeting_JniUtils_decodeFromC
        (JNIEnv *env, jobject obj,jstring passWord,jint length){
    //生成native的char指针
    const char* c = (*env)->GetStringUTFChars(env,passWord,NULL);
    char* cStr = c;
    int i ;
    for(i = 0;i < length;i++){
        *(cStr + i) -= 1;
    }
    return (*env)->NewStringUTF(env, c);
}