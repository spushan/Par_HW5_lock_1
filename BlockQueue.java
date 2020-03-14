import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue {
    
    private ArrayList<String> bQueue = new ArrayList<String>(10);
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void addQueue(String s) throws InterruptedException {
        lock.lock();
        try {
            if(bQueue.size()==10) {
                notFull.wait();
        }
        bQueue.add(s);
        notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void removeQueue() throws InterruptedException {
        lock.lock();
        try {
            if(bQueue.size()==0) {
                notEmpty.wait();
        }
        bQueue.remove(0);
        notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return bQueue.size();
    }
}
