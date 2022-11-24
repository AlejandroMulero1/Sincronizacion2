import java.util.concurrent.Semaphore;

public class CarniceriaDificil implements Runnable{
    public static void main(String[] args) {
        CarniceriaDificil carniceria= new CarniceriaDificil();
        for (int i=0; i<10; i++){
            Thread hilo= new Thread(carniceria);
            hilo.setName("Hilo "+i);
            hilo.start();
        }

    }

    Semaphore semaphore= new Semaphore(4);
    Semaphore semaphore2=new Semaphore(2);


    @Override
    public void run() {
        boolean atendido=false;
        boolean atendidoCarniceria=false;
        boolean atendidoCharcuteria=false;
        do {

            if (!atendidoCarniceria && semaphore.availablePermits()>0){
                try {
                    semaphore.acquire();
                    System.out.println("Soy el hilo "+ Thread.currentThread().getName()+ " y estoy siendo atendido en la carniceria");
                    Thread.sleep(1000);
                    System.out.println("Soy el hilo "+ Thread.currentThread().getName()+ " y he salido de la carniceria");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                atendidoCarniceria=true;
            }
             if(!atendidoCharcuteria && semaphore2.availablePermits()>0){
                try {
                    semaphore2.acquire();
                    System.out.println("Soy el hilo "+ Thread.currentThread().getName()+ " y estoy siendo atendido en la charcuteria");
                    Thread.sleep(1000);
                    System.out.println("Soy el hilo "+ Thread.currentThread().getName()+ " y he salido de la charcuteria");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                atendidoCharcuteria=true;
            }

             if (atendidoCarniceria && atendidoCharcuteria){
                System.out.println("Soy el hilo" + Thread.currentThread().getName() + "y he terminado");
                atendido=true;
            }
        } while (!atendido);
    }
}
