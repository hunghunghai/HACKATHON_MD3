package ra.bussiness;

import java.util.Scanner;

public class Book {

    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private Double importPrice;
    private Double exportPrice;
    private Float interest;
    private Boolean bookStatus;

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String descriptions, Double importPrice, Double exportPrice, Float interest, Boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public Double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(Double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        this.interest = interest;
    }

    public Boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID: ");
        bookId = scanner.nextInt();

        scanner.nextLine(); // Đọc ký tự '\n' dư thừa

        System.out.print("Enter Book Name: ");
        bookName = scanner.nextLine();

        System.out.print("Enter Author: ");
        author = scanner.nextLine();

        System.out.print("Enter Descriptions: ");
        descriptions = scanner.nextLine();

        System.out.print("Enter Import Price: ");
        importPrice = scanner.nextDouble();

        System.out.print("Enter Export Price: ");
        exportPrice = scanner.nextDouble();

        interest = (float) (exportPrice - importPrice);

        bookStatus = true;
    }

    public void displayData() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Descriptions: " + descriptions);
        System.out.println("Import Price: " + importPrice);
        System.out.println("Export Price: " + exportPrice);
        System.out.println("Interest: " + interest);
        System.out.println("Book Status: " + bookStatus);
    }
}