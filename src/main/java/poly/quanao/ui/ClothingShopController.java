package poly.quanao.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import poly.quanao.ui.manager.CardManager;
import poly.quanao.ui.manager.OrderManagerJDialog;
import poly.quanao.ui.manager.ProductCategoriesManager;
import poly.quanao.ui.manager.ProductsManagerJDialog;
import poly.quanao.ui.manager.RevenueManagerJDialog;
import poly.quanao.ui.manager.UserManagerJDialog;
import poly.quanao.util.XDialog;

/**
 */
public interface ClothingShopController {

     void init();

    default void exit() {
       if(XDialog.confirm("Bạn muốn kết thúc?")){
        System.exit(0);
        }
    }
    


    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


    default void showWelcomeJDialog(JFrame frame){
        this.showJDialog(new WelcomeJDialog(frame, true));
    }

    default void showLoginJDialog(JFrame frame){
        this.showJDialog(new LoginJDialog(frame, true));
    }


    default void showChangePasswordJDialog(JFrame frame) {
       this.showJDialog(new ChangePasswordJDialog(frame, true));
    }

    default void showSalesJDialog(JFrame frame) {
         this.showJDialog(new Sales(frame, true));
    }

    default void showHistoryJDialog(JFrame frame) {
        this.showJDialog(new HistoryJDialog(frame, true));
    }

    default void showProductsManagerJDialog(JFrame frame){
        this.showJDialog(new ProductsManagerJDialog(frame, true));
    }
    default void showProductCategoriesManager(JFrame frame){
       this.showJDialog(new ProductCategoriesManager(frame, true));
    }
    default void showCardManagerJDialog(JFrame frame){
        this.showJDialog(new CardManager(frame, true));
    }
    default void showOrderManagerJDialog(JFrame frame){
        this.showJDialog(new OrderManagerJDialog(frame, true));
    }
    default void showUserManagerJDialog(JFrame frame){
        this.showJDialog(new UserManagerJDialog(frame, true));
    }
    default void showRevenueManagerJDialog(JFrame frame){
        this.showJDialog(new RevenueManagerJDialog(frame, true));
    }
}
