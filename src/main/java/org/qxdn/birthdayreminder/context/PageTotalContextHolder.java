package org.qxdn.birthdayreminder.context;

public class PageTotalContextHolder {

    private static final ThreadLocal<Long> totalLocal = new ThreadLocal<>();

    public static void set(Long total) {
        totalLocal.set(total);
    }

    public static Long get(){
        return totalLocal.get();
    }

    public static void remove(){
        totalLocal.remove();
    }
}
