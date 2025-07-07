package product;

import stock.StockDao;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductDao productDao = new ProductDao();
    private final StockDao stockDao = new StockDao();
    private final Scanner sc = new Scanner(System.in);

    /** 제품등록
     *  새로운 제품을 등록합니다.
     *  입고등록과 다름
     */
    public void registerProduct() {
        System.out.print("제품명 입력: ");
        String productName = sc.nextLine();

        System.out.print("제조사 입력: ");
        String manufacturer = sc.nextLine();

        System.out.print("성인 전용 여부 입력 (Y/N): ");
        char adultOnly = sc.nextLine().toUpperCase().charAt(0);
        if (adultOnly != 'Y' && adultOnly != 'N') {
            System.out.println("Y 또는 N으로 입력해야 합니다.");
            return;
        }

        System.out.print("가격 입력: ");
        int price = sc.nextInt();


        Product product = new Product(0, productName, manufacturer,adultOnly, price, 0);
        productDao.saveProduct(product);

    }

    public void showAllProducts() {
        List<Product> products = productDao.findAllProducts();
        if (products.isEmpty()) {
            System.out.println("등록된 제품이 없습니다.");
            return;
        }
        System.out.println("==============================================================================");
        System.out.printf("%-4s | %-10s | %-10s | %-8s | %-8s | %-5s%n",
                "ID", "제품명", "제조사", "성인전용", "가격", "재고");
        System.out.println("------------------------------------------------------------------------------");

        for (Product p : products) {
            System.out.printf("%-4d | %-10s | %-10s | %-8s | %,8d원 | %3d개%n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getManufacturer(),
                    p.getAdultOnly() == 'Y' ? "19세 이상" : "모두 가능",
                    p.getPrice(),
                    p.getStock());
        }

        System.out.println("==============================================================================");
    }


    private void printProduct(Product p) {
        String stars = "*".repeat(p.getStock());
        System.out.printf("""
        -------------------------------
        제품ID: %d
        제품명: %s
        제조사: %s
        성인전용: %s
        가격: %,d원
        재고: %s (%d개)
        -------------------------------
        """,
                p.getProductId(),
                p.getProductName(),
                p.getManufacturer(),
                p.getAdultOnly() == 'Y' ? "19세 이상" : "모두 가능",
                p.getPrice(),
                stars,
                p.getStock());
    }


    public void searchByProductName() {
        System.out.print("검색할 제품이름: ");
        String keyword = sc.nextLine();

        Product result = productDao.findByName(keyword);

        if (result == null) {
            System.out.println("해당 물건 없음");
        } else {
            printProduct(result);
        }
    }
}