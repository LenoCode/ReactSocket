package com.reactlibrary.socketWrapper.components.threadCaller;

import async_communicator.AsyncCommunicator;
import async_communicator.thread_id_holder.ThreadIdHolder;
import thread_watcher.models.abstractions.user_method.UserMethod;
import thread_watcher.thread.caller.ThreadCaller;

public class AndroidThreadCaller {
    private final static AndroidThreadCaller androidThreadCaller = new AndroidThreadCaller();
    private final ThreadCaller threadCaller = ThreadCaller.getThreadCaller();
    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();


    public static <A extends UserMethod> ThreadIdHolder callThreadAndReturnId(Class<A> userMethodClass, String methodName, boolean waitForFinish, Object... args){
        return new ThreadIdHolder( call(userMethodClass,methodName,waitForFinish,args) );
    }
    public static <A extends UserMethod> void callThread(Class<A> userMethodClass, String methodName, boolean waitForFinish , Object... args){
       call(userMethodClass,methodName,waitForFinish,args);
    }
    public static<A extends UserMethod> ThreadIdHolder callThreadAndReturnId(Class<A> userMethodClass, String methodName, Object... args){
        return new ThreadIdHolder( call(userMethodClass,methodName,false,args) );
    }
    public static<A extends UserMethod> void callThread(Class<A> userMethodClass, String methodName, Object... args){
        call(userMethodClass,methodName,false,args);
    }



    private static <A extends UserMethod> long call(Class<A> userMethodClass, String methodName, boolean waitForFinish, Object...args){
        try {
            long id = androidThreadCaller.threadCaller.callThread(userMethodClass,methodName,args);
            if (waitForFinish){
                androidThreadCaller.asyncCommunicator.waitThreadToFinish(id);
            }
            return id;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
