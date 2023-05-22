import javax.swing.*;


public class Result {
    /* 结束界面图片加载 */
    static JLabel result = new JLabel(new ImageIcon("src/resource/image/result.png"));
    /* 建一个成员函数 */
    public void resultPage(int perfect, int good, int miss, int maxCombo) {
        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();

        JLabel perfect1 = new JLabel(ComboFigure.figureIcons[perfect % 10]);
        JLabel perfect10 = new JLabel(ComboFigure.figureIcons[perfect / 10 % 10]);
        JLabel perfect100 = new JLabel(ComboFigure.figureIcons[perfect / 100 % 10]);

        JLabel good1 = new JLabel(ComboFigure.figureIcons[good % 10]);
        JLabel good10 = new JLabel(ComboFigure.figureIcons[good / 10 % 10]);
        JLabel good100 = new JLabel(ComboFigure.figureIcons[good / 100 % 10]);

        JLabel miss1 = new JLabel(ComboFigure.figureIcons[miss % 10]);
        JLabel miss10 = new JLabel(ComboFigure.figureIcons[miss / 10 % 10]);
        JLabel miss100 = new JLabel(ComboFigure.figureIcons[miss / 100 % 10]);

        JLabel maxCombo1 = new JLabel(ComboFigure.figureIcons[maxCombo % 10]);
        JLabel maxCombo10 = new JLabel(ComboFigure.figureIcons[maxCombo / 10 % 10]);
        JLabel maxCombo100 = new JLabel(ComboFigure.figureIcons[maxCombo / 100 % 10]);

        perfect1.setVisible(false);
        perfect10.setVisible(false);
        perfect100.setVisible(false);

        good1.setVisible(false);
        good10.setVisible(false);
        good100.setVisible(false);

        miss1.setVisible(false);
        miss10.setVisible(false);
        miss100.setVisible(false);

        maxCombo1.setVisible(false);
        maxCombo10.setVisible(false);
        maxCombo100.setVisible(false);

        PageController.win.getContentPane().add(perfect1);
        PageController.win.getContentPane().add(perfect10);
        PageController.win.getContentPane().add(perfect100);

        PageController.win.getContentPane().add(good1);
        PageController.win.getContentPane().add(good10);
        PageController.win.getContentPane().add(good100);

        PageController.win.getContentPane().add(miss1);
        PageController.win.getContentPane().add(miss10);
        PageController.win.getContentPane().add(miss100);

        PageController.win.getContentPane().add(maxCombo1);
        PageController.win.getContentPane().add(maxCombo10);
        PageController.win.getContentPane().add(maxCombo100);

        result.setVisible(false);
        PageController.win.getContentPane().add(result);
        result.setBounds(0, 0, 1280, 800);
        result.setVisible(true);

        perfect1.setBounds(878, 210, 34, 86);
        perfect10.setBounds(842, 210, 34, 86);
        perfect100.setBounds(806, 210, 34, 86);

        good1.setBounds(878, 314, 34, 86);
        good10.setBounds(842, 314, 34, 86);
        good100.setBounds(806, 314, 34, 86);

        miss1.setBounds(878, 418, 34, 86);
        miss10.setBounds(842, 418, 34, 86);
        miss100.setBounds(806, 418, 34, 86);

        maxCombo1.setBounds(878, 522, 34, 86);
        maxCombo10.setBounds(842, 522, 34, 86);
        maxCombo100.setBounds(806, 522, 34, 86);

        try {
            /*  Thread.sleep 使当前线程休眠，进入阻塞状态（暂停执行）  */
            Thread.sleep(500);
            perfect1.setVisible(true);
            perfect10.setVisible(true);
            perfect100.setVisible(true);

            Thread.sleep(500);
            good1.setVisible(true);
            good10.setVisible(true);
            good100.setVisible(true);

            Thread.sleep(500);
            miss1.setVisible(true);
            miss10.setVisible(true);
            miss100.setVisible(true);

            Thread.sleep(500);
            maxCombo1.setVisible(true);
            maxCombo10.setVisible(true);
            maxCombo100.setVisible(true);
        } catch (InterruptedException e) {
        }
    }
}
