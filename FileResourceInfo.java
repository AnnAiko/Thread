package lab_7_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Ресурс
public class FileResourceInfo {

    private FileReader fileReader;
    private FileWriter fileWriter;
    private Scanner in;
    private String fileNameRead;

    public FileResourceInfo(String fileNameWrit, String fileNameRead) throws IOException {
        //Проверить наличие файла
        fileWriter = new FileWriter(fileNameWrit, true);
        this.fileNameRead = fileNameRead;
    }

    public synchronized void writing(String str, int i, boolean busy) {

        try {
            //
            while (busy) {
                wait();
            }
            busy = true;
            //Дописать текст в файл
            fileWriter.append(str + i);
            System.out.print(str + i);
            //Приостановить текущий поток на указанное время
            Thread.sleep((long) (Math.random() * 200));
            fileWriter.append("->" + i + " ");
            System.out.println("->" + i + " ");
            notify();
        } catch (IOException e) {
            System.err.println("Ошибка файла: " + e);
            //Если какой-то поток прервал текущий поток
        } catch (InterruptedException e) {
            System.err.println("Ошибка потока: " + e);
        }
    }

    public synchronized void reading(String str, boolean stop) {
        try {
            while (stop) {
                wait();
            }
            stop = true;
            String s = "";
            fileReader = new FileReader(fileNameRead);
            in = new Scanner(fileReader);
            while (in.hasNext()) {
                s += in.nextLine();//+ "\r\n"
            }
            System.out.println("Чтение файла: " + s + str);
            //System.out.println("Поток " + str + " закончил чтение");
            System.out.println("---------------------------------");
            //Приостановить текущий поток на указанное время
            Thread.sleep((long) (Math.random() * 50));
            notify();
            //Если файл не найден
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e);
            //Если какой-то поток прервал текущий поток
        } catch (InterruptedException e) {
            System.err.println("Ошибка потока: " + e);
        }
    }

    //Закрыть файл
    public void closeWri() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.print("Ошибка закрытия файла: " + e);
        }
    }

    //Закрыть файл
    public void closeRead() {
        if (in != null) {
            in.close();
        }
    }
}
