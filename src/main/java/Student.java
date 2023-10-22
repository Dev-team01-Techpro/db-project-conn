import java.util.Scanner;

public class Student {

    StudentRepository repo = new StudentRepository();
    Scanner input = new Scanner(System.in);
    boolean isRun = true;

    public void studentList() {
        repo.getStudents();
    }

    public void add() {
        System.out.println("Ogrenci ismi giriniz.");
        input.nextLine();
        String name = input.nextLine();

        repo.addStudent(name);
    }

    public void delete() {
        repo.getStudents();
        System.out.println("Silmek istediginiz ogrenci id sini giriniz.");
        int id = input.nextInt();
        repo.deleteStudent(id);
    }

    public void update() {
        repo.getStudents();
        System.out.println("Guncellemek istediginiz ogrenci id sini giriniz.");
        int id = input.nextInt();
        System.out.println("Yeni ogrenci ismi giriniz.");
        input.nextLine();
        String name = input.nextLine();
        repo.updateStudent(id, name);
    }

    public void displayMenu() {
        while (isRun) {
            System.out.println("Menu");
            System.out.println("1. Ogrencileri listele");
            System.out.println("2. Ogrenci ekle");
            System.out.println("3. Ogrenci sil");
            System.out.println("4. Ogrenci bilgisi guncelle");
            System.out.println("0. Cikis yap");

            int select = input.nextInt();
            switch (select) {
                case 1:
                    studentList();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    update();
                    break;
                case 0:
                    isRun = false;
                    System.out.println("Cikis yapildi.");
                    break;
            }
        }


    }
}
