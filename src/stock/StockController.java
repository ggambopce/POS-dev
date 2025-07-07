package stock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StockController {

    private final StockDao stockDao = new StockDao();
    private final Scanner sc = new Scanner(System.in);
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void registerStock() {
        try {
            System.out.print("상품 ID 입력: ");
            int productId = Integer.parseInt(sc.nextLine());

            System.out.print("입고 수량 입력: ");
            int quantity = Integer.parseInt(sc.nextLine());

            System.out.print("유통기한 입력 (yyyy-MM-dd): ");
            String expiryInput = sc.nextLine();
            Date expiryDate = sdf.parse(expiryInput);

            stockDao.saveStock(productId, quantity, expiryDate);
            System.out.println("입고 등록 완료");

        } catch (Exception e) {
            System.out.println("입고 등록 중 오류: " + e.getMessage());
        }
    }
}
