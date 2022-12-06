package boggle;

/**
 * BackgroundTimer Class.
 */
public class BackgroundTimer extends Thread{

    private Integer timeLimit;

    public BackgroundTimer(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public void run() {
        while (timeLimit > 0) {
            try {
                if (timeLimit % 5 == 0) {
                    System.out.println(timeLimit + " seconds remaining!");
                }
                if (timeLimit == 1) {
                    System.out.println("Time is up! Enter your last word!");
                }
                timeLimit --;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Integer getTime() {
        return timeLimit;
    }
}
