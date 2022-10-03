package ru.gb.cardsystem.controllers.impl;

import org.springframework.context.ApplicationContext;
import ru.gb.cardsystem.CardSystemApplication;
import ru.gb.cardsystem.controllers.JComponentController;
import ru.gb.cardsystem.controllers.JComponentWriterController;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PanelController extends JComponentController<JComponent> {

    public static final int WIDTH_MAIN_PANEL = 300;
    public static final int HEIGHT_MAIN_PANEL = 200;
    public static final String NAME_MAIN_PANEL = "User's card system";

    private final ApplicationContext appContext;
    private final JComponentController<JTextField> textFieldController;
    private final JComponentWriterController<JButton> buttonWriterToLoggerController;
    private  final JComponentWriterController<JButton> buttonWriterToFileController;
    private  final JComponentWriterController<JButton> buttonWriterToCSVFileController;

    public PanelController(final ApplicationContext appContext) {
        super(new ArrayList<>());
        this.appContext = appContext;
        this.textFieldController = new TextFieldController();
        this.buttonWriterToLoggerController = new ButtonWriterToLoggerController();
        this.buttonWriterToFileController = new ButtonWriterToFileController();
        this.buttonWriterToCSVFileController = new ButtonWriterToCSVFileController();
        initTextInputPanel();
        initButtonPanel();
    }

    public void initPanels() {
        final CardSystemApplication app = appContext.getBean(CardSystemApplication.class);
        app.setTitle(NAME_MAIN_PANEL);
        app.setSize(WIDTH_MAIN_PANEL, HEIGHT_MAIN_PANEL);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final Container mc = app.getContentPane();
        final BoxLayout layout = new BoxLayout(mc, BoxLayout.Y_AXIS);
        mc.setLayout(layout);

        for (final JComponent panel : getComponents()) {
            mc.add(panel.toString(), panel);
        }
    }

    private void initTextInputPanel() {
        final JPanel textInputPanel = new JPanel();
        textInputPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        for (final JTextField textField : textFieldController.getComponents()) {
            textInputPanel.add(textField);
        }
        getComponents().add(textInputPanel);
    }
    private void initButtonPanel() {
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        for (final JButton button : buttonWriterToLoggerController.getComponents()) {
            buttonPanel.add(button);
        }

        for (final JButton button : buttonWriterToFileController.getComponents()) {
            buttonPanel.add(button);
        }

        for (final JButton button : buttonWriterToCSVFileController.getComponents()) {
            buttonPanel.add(button);
        }

        getComponents().add(buttonPanel);
    }
}
