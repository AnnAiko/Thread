package lab_7_1;

public class FileThread extends Thread {

    private FileResource fileRes;
    private FileResourceInfo fileInfo;

    public FileThread(String name, FileResource resInfo, FileResourceInfo resText) {
        super(name);
        this.fileRes = resInfo;
        this.fileInfo = resText;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            //Место срабатывания синхронизации
            //Записать текст в файл
            fileInfo.writing(getName(), i);
        }
        //Место срабатывания синхронизации
        //Читать данные из файла
        fileRes.reading(getName());
    }
}
