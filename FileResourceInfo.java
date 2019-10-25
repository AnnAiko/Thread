package lab_7_1;

import java.io.FileWriter;
import java.io.IOException;

//Ресурс
public class FileResourceInfo {

    private FileWriter fileWriter;

    public FileResourceInfo(String fileName) throws IOException {
        //Проверить наличие файла
        fileWriter = new FileWriter(fileName, true);
    }

    public synchronized void writing(String str, int i) {
        try {
            //Дописать текст в файл
            fileWriter.append(str + i);
            System.out.print(str + i);
            //Приостановить текущий поток на указанное время
            Thread.sleep((long) (Math.random() * 100));
            fileWriter.append("->" + i + " ");
            System.out.println("->" + i + " ");
        } catch (IOException e) {
            System.err.println("Ошибка файла: " + e);
            //Если какой-то поток прервал текущий поток
        } catch (InterruptedException e) {
            System.err.println("Ошибка потока: " + e);
        }
    }

    //Закрыть файл
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.err.print("Ошибка закрытия файла: " + e);
        }
    }
}
