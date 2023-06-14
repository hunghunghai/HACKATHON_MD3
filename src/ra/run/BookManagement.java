package ra.run;

import ra.bussiness.Book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BookManagement {
    private static final int MAX_BOOKS = 100;
    private static Book[] library = new Book[MAX_BOOKS];
    private static int nextBookId = 1; // Mã sách tiếp theo
    private static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            displayMenu();
            System.out.print("Chọn một chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự '\n' dư thừa

            switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    break;
                case 4:
                    removeBook(scanner);
                    break;
                case 5:
                    searchBooks(scanner);
                    break;
                case 6:
                    updateBook(scanner);
                    break;
                case 7:
                    System.out.println("Thoát khỏi chương trình.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
            System.out.println();
        } while (choice != 7);
    }

    private static void displayMenu() {
        System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
        System.out.println("1. Thêm mới sách trong thư viện");
        System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
        System.out.println("3. Sắp xếp sách theo giá");
        System.out.println("4. Xóa sách theo mã");
        System.out.println("5. Tìm kiếm tên hoặc mô tả của sách");
        System.out.println("6. Thay đổi thông tin sách");
        System.out.println("7. Thoát");
    }

    private static void addBooks(Scanner scanner) {
        System.out.print("Nhập số lượng sách cần thêm mới: ");
        int numBooks = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự '\n' dư thừa

        if (bookCount + numBooks > MAX_BOOKS) {
            System.out.println("***********************************************************");
            System.out.println("Không thể thêm sách. Vượt quá số lượng tối đa cho phép.");
            return;
        }

        for (int i = 0; i < numBooks; i++) {
            System.out.println("***********************************************************");
            System.out.println("Nhập thông tin cho sách thứ " + (i + 1) + ":");
            Book book = new Book();
            book.setBookId(nextBookId); // Gán mã sách tự tăng
            nextBookId++; // Tăng mã sách tiếp theo
            book.inputData();
            library[bookCount] = book;
            bookCount++;
            System.out.println("***********************************************************");
            System.out.println("Thêm sách thành công.");
        }
    }


    private static void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("***********************************************************");
            System.out.println("Thư viện không có sách.");
            return;
        }

        System.out.println("Thông tin tất cả sách trong thư viện:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("***********************************************************");
            System.out.println("Sách thứ " + (i + 1) + ":");
            library[i].displayData();
            System.out.println();
        }
    }

    private static void sortBooksByInterest() {
        if (bookCount == 0) {
            System.out.println("***********************************************************");
            System.out.println("Thư viện không có sách.");
            return;
        }

        // Sử dụng Comparator để sắp xếp sách theo lợi nhuận
        Arrays.sort(library, 0, bookCount, Comparator.comparingDouble(Book::getInterest));

        System.out.println("Sách đã được sắp xếp theo lợi nhuận:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("***********************************************************");
            System.out.println("Sách thứ " + (i + 1) + ":");
            library[i].displayData();
        }
    }


    private static void removeBook(Scanner scanner) {
        System.out.println("***********************************************************");
        System.out.print("Nhập mã sách cần xóa: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự '\n' dư thừa

        int index = findBookIndexById(bookId);
        if (index == -1) {
            System.out.println("***********************************************************");
            System.out.println("Không tìm thấy sách với mã sách đã nhập.");
            return;
        }

        for (int i = index; i < bookCount - 1; i++) {
            library[i] = library[i + 1];
        }
        bookCount--;

        System.out.println("***********************************************************");
        System.out.println("Xóa sách thành công.");
    }

    private static void searchBooks(Scanner scanner) {
        System.out.println("***********************************************************");
        System.out.print("Nhập chuỗi tìm kiếm: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("***********************************************************");
            Book book = library[i];
            if (book.getBookName().toLowerCase().contains(keyword) || book.getDescriptions().toLowerCase().contains(keyword)) {
                book.displayData();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("***********************************************************");
            System.out.println("Không tìm thấy sách phù hợp với từ khóa đã nhập.");
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("***********************************************************");
        System.out.print("Nhập mã sách cần thay đổi thông tin: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự '\n' dư thừa

        int index = findBookIndexById(bookId);
        if (index == -1) {
            System.out.println("***********************************************************");
            System.out.println("Không tìm thấy sách với mã sách đã nhập.");
            return;
        }

        System.out.println("***********************************************************");
        System.out.println("Nhập thông tin mới cho sách:");
        library[index].inputData();

        System.out.println("***********************************************************");
        System.out.println("Cập nhật thông tin sách thành công.");
    }

    private static int findBookIndexById(int bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (library[i].getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }
}
