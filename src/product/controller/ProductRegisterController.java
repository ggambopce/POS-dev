package product.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.controller.Controller;
import product.entity.Product;
import product.service.ProductService;
import product.service.ProductServiceImplement;
import product.view.ProductUI;

import java.util.List;

public class ProductRegisterController implements Controller {

    private final InputProvider input;
    private final OutputRenderer output;
    private final ProductUI view;

    public ProductRegisterController(InputProvider input, OutputRenderer output, ProductUI view) {
        this.input = input;
        this.output = output;
        this.view = view;
    }

    @Override
    public void run() {
        while (true) {
            view.displayHeader();
            view.displayMenu();
            view.displayLast();

            ProductService productService = new ProductServiceImplement();
            Controller controller = null;

            String choice = input.readLine();
            switch (choice) {
                case "1" :
                    break;
                case "2" :
                    break;
                case "3" :
                    view.promptProductList();
                    List<Product> products = productService.findAllProducts();
                    break;
                case "4" :
                    break;
                case "5" :
                    view.promptInputProductName();
                    String name = input.readLine();
                    view.promptInputManufacturer();
                    String manufacturer = input.readLine();
                    view.promptInputGender();
                    char gender = input.readLine().toUpperCase().charAt(0);
                    if (gender != 'Y' && gender != 'N') {
                        output.println("Y 또는 N으로 입력해야 합니다.");
                    } else {
                        output.println("다시 입력해주세요");
                        return;
                    }
                    view.promptInputPrice();
                    int price = Integer.parseInt(input.readLine());
                    Product product = new Product(name, manufacturer, gender, price);
                    productService.registerProduct(product);
                    break;
                case "0" :
                    view.showProductClose();

                    return;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
            }
        }
    }
}
