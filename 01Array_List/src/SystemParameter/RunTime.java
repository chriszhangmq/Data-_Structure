package SystemParameter;

/**
 * Created by Chris on 2019/10/25.
 */
public class RunTime {
    private long startTime;
    private long endTime;

    public RunTime(){
        this.startTime = 0;
        this.endTime = 0;
    }

    public RunTime(long startTime, long endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 开始运行程序
     */
    public void startProgram(){
        startTime = System.currentTimeMillis();
    }

    /**
     * 程序运行结束
     * @return
     */
    public long endProgram(){
        endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("The time of program executing = " + runTime + " ms");
        return runTime;
    }

}
