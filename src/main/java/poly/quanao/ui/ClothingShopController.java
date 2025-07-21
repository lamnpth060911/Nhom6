package poly.quanao.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 */
public interface ClothingShopController {

     void init();

    default void exit() {
        System.exit(0);
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
        // TODO: Mở giao diện bán hàng
    }

    default void showHistoryJDialog(JFrame frame) {
        // TODO: Mở giao diện lịch sử đơn hàng
    }

    default void showProductJDialog(JFrame frame) {
        // TODO: Mở giao diện sản phẩm
    }

    default void showCardJDialog(JFrame frame) {
        // TODO: Mở giao diện quản lý thẻ
    }

    default void showOrderJDialog(JFrame frame) {
        // TODO: Mở giao diện phiếu bán hàng
    }

    default void showUserJDialog(JFrame frame) {
        // TODO: Mở giao diện tài khoản người dùng
    }

    default void showWarehouseJDialog(JFrame frame) {
        // TODO: Mở giao diện quản lý kho hàng
    }

    default void showRevenueJDialog(JFrame frame) {
        // TODO: Mở giao diện thống kê doanh thu
    }
}
