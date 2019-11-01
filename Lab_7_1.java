package lab_7_1;

import java.io.IOException;

public class Lab_7_1 {

    public static void main(String[] args) {

        FileResourceInfo fileinfo = null;
        try {
            fileinfo = new FileResourceInfo("info.txt", "text.txt");
            FileThread t1 = new FileThread("First", fileinfo);
            FileThread t2 = new FileThread("Second", fileinfo);
            FileThread t3 = new FileThread("Third", fileinfo);
            FileThread t4 = new FileThread("Fourth", fileinfo);
            //Создать и запустить поток
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            /*Приостановить выполнение текущего потока 
            до тех пор, пока другой поток не закончит свое выполнение*/
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (IOException e) {
            System.err.print("Ошибка файла: " + e);
        } catch (InterruptedException e) {
            System.err.print("Ошибка потока: " + e);
        } finally {
            fileinfo.closeWri();
            fileinfo.closeRead();
        }
    }
}
