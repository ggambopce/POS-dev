package product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductDao productDao = new ProductDao();
    private final Scanner sc = new Scanner(System.in);

    public void registerProduct() {
        try {
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

            Date receivedDate = new Date();

            Product product = new Product(0, productName, manufacturer,adultOnly, price);
            productDao.saveProduct(product);

        } catch (ParseException e) {
            System.out.println("날짜 형식 오류: yyyy-MM-dd 형식으로 입력하세요.");
        }
    }

    public void showAllProducts() {
        List<Product> products = productDao.findAllProducts();
        if (products.isEmpty()) {
            System.out.println("등록된 제품이 없습니다.");
            return;
        }
        for (Product p : products) {
            printProduct(p);
        }
    }


    private void printProduct(Product p) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("""
        -------------------------------
        제품ID: %d
        제품명: %s
        제조사: %s
        유통기한: %s
        성인전용: %s
        가격: %,d원
        입고일자: %s
        재고: %d개
        -------------------------------
        """,
                p.getProductId(), p.getProductName(), p.getManufacturer(),
                sdf.format(p.getExpiryDate()), p.getAdultOnly() == 'Y' ? "19세 이상" : "모두 가능",
                p.getPrice(), sdf.format(p.getReceivedDate()), p.getStock());
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