import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable {
    public static void main(String[] args) {
        Carniceria carniceria= new Carniceria();
        for (int i=0; i<10; i++){
            Thread hilo= new Thread(carniceria);
            hilo.setName("Hilo "+i);
            hilo.start();
        }
    }

    Semaphore semaphore= new Semaphore(4);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Soy el " +Thread.currentThread().getName()+" y estoy siendo atendido.");
                Thread.sleep((int) Math.random()*1000);
            System.out.println("Soy el " +Thread.currentThread().getName()+" y acabo de salir de la carniceria.");
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
