class Main {
    
    public static void main(String[] args) {

        int prodNum = 5;
        int consNum = 2;

        BlockQueue mainQueue = new BlockQueue();

        for (int i = 0; i < prodNum; i++) {
            Thread temp = new Thread(new Producer(mainQueue));
            temp.start();
        }
        for (int i = 0; i < consNum; i++) {
            Thread temp = new Thread(new Consumer(mainQueue));
            temp.start();
        }
    }
}

class Producer implements Runnable {

    private BlockQueue queue;

    public Producer(BlockQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Item " + i);
            try {
                queue.addQueue("Item");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(queue.size());
        }
    }
}

class Consumer implements Runnable {
    private BlockQueue queue;

    public Consumer(BlockQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.removeQueue();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            System.out.println("Sleeping");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}