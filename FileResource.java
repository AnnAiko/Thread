package lab_7_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Ресурс 
public class FileResource {

    private FileReader fileReader;

    private Scanner in;
    private String fileName;

    public FileResource(String fileName) throws IOException {
        this.fileName = fileName;
    }

    public synchronized void reading(String str) {
        try {
            String s = "";
            //System.out.println("Чтение файла потоком № " + str);
            fileReader = new FileReader(fileName);
            in = new Scanner(fileReader);
            while (in.hasNext()) {
                s += in.nextLine();//+ "\r\n"
            }
            System.out.println("Чтение файла: " + s + str);
            //System.out.println("Поток " + str + " закончил чтение");
            System.out.println("---------------------------------");
            //Приостановить текущий поток на указанное время
            Thread.sleep((long) (Math.random() * 100));
            //Если файл не найден
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e);
            //Если какой-то поток прервал текущий поток
        } catch (InterruptedException e) {
            System.err.println("Ошибка потока: " + e);
        }
    }

    //Закрыть файл
    public void close() {

        if (in != null) {
            in.close();
        }
    }
}
