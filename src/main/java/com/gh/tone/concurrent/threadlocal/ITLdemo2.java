package com.gh.tone.concurrent.threadlocal;

/**
 * InheritableThreadLocal 父线程传递给子线程 传递的是引用
 */
public class ITLdemo2 {
    private static ThreadLocal<Integer> tl1 = new InheritableThreadLocal<>();
    private static ThreadLocal<De> tl2 = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        De de = new De();
        de.setName("de");
        tl1.set(1);
        tl2.set(de);
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: tl = %s，tl2.name = %s",
            Thread.currentThread().getName(), tl1.get(),tl2.get().getName()));
        fc();
        new Thread(() -> {
            De de2 = tl2.get();
            de2.setName("de2");
            fc();
        }).start();
        Thread.sleep(1000L);
        fc();
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: tl = %s，tl2.name = %s",
            Thread.currentThread().getName(), tl1.get(),tl2.get().getName()));
    }
    public static class De {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
