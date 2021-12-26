import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<String> cars = new ArrayList<>();
    public static final int TIME_TO_BUILD = 1000;
    public static final int MAX_COUNT = 10;


    public synchronized void buyCar() {
        try {
            String consumerName = Thread.currentThread().getName();
            System.out.printf("%s зашел в автосалон\n", consumerName);
            while (cars.isEmpty()) {
                System.out.println("Машин в продаже нет");
                wait();
            }
            System.out.printf("%s уехал на новеньком авто\n", consumerName);
            cars.remove(0);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }

    public synchronized void buildCar(){
        try {
            String vendorName = Thread.currentThread().getName();
            System.out.printf("Производитель %s начал производство машины\n", vendorName);
            while (true){
                if (cars.size() < MAX_COUNT) {
                    Thread.sleep(TIME_TO_BUILD);
                    cars.add(vendorName);
                    System.out.printf("Производитель %s выпустил авто\n", vendorName);
                    notify();
                } else {
                    System.out.println("Производство машин закончено");
                    break;
                }
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }
}
