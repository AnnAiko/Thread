package lab_7_1;

public class FileThread extends Thread {

    private FileResourceInfo fileInfo;
    private boolean busy = false;
    private boolean stop = false;

    public FileThread(String name, FileResourceInfo resText) {
        super(name);
        this.fileInfo = resText;
    }

    public void run() {

        if (busy == false) {
            for (int i = 0; i < 3; i++) {
                //Место срабатывания синхронизации
                //Записать текст в файл
                fileInfo.writing(getName(), i, busy);

            }
            busy = true;
        }
        if (stop == false) {
            System.out.println("---------------------");
            //Место срабатывания синхронизации
            //Читать данные из файла
            fileInfo.reading(getName(), stop);
            stop = true;
        }
        /*for (int i = 0; i < 3; i++) {
            //Место срабатывания синхронизации
            //Записать текст в файл
            fileInfo.writing(getName(), i);

        }

        System.out.println("---------------------");
        //Место срабатывания синхронизации
        //Читать данные из файла
        fileInfo.reading(getName());
        stop = true;*/
    }
}
