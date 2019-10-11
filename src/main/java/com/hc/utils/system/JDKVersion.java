package com.hc.utils.system;

/**
 * JDKVersion
 *
 * @author han.congcong
 * @date 2019/10/11
 */

public class JDKVersion {
    private static final String JAVA_VERSION = "java.version";
    public static final String JDK_1_8 = "1.8";
    public static final String JDK_1_7 = "1.7";
    public static final String JDK_OTHER = "jdk.other";
    private static String JDK_VERSION = null;


    public static String getJavaVersion() {
        if(JDK_VERSION == null){
            synchronized (JDK_VERSION){
                if(System.getProperty(JAVA_VERSION).startsWith(JDK_1_7)){
                    JDK_VERSION = JDK_1_7;
                }else if(System.getProperty(JAVA_VERSION).startsWith(JDK_1_8)){
                    JDK_VERSION = JDK_1_8;
                }else{
                    JDK_VERSION = JDK_OTHER;
                }
            }
        }
        return JDK_VERSION;
    }

    public static boolean isJava8() {
        return JDK_VERSION == JDK_1_8;
    }

    public static boolean isJava7(){
        return JDK_VERSION == JDK_1_7;
    }
}
