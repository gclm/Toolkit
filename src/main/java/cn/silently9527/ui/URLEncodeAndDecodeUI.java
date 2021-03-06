package cn.silently9527.ui;

import cn.silently9527.domain.ToolkitCommand;
import cn.silently9527.listener.action.CopyContentActionListener;
import cn.silently9527.listener.document.UrlEncodeAndDecodeDocumentListener;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorSettingsProvider;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class URLEncodeAndDecodeUI {
    private JPanel panel;
    private EditorTextField textField;
    private JButton copy;
    private EditorTextField resultTextField;

    private Project project;

    public URLEncodeAndDecodeUI(Project project, ToolkitCommand command) {
        this.project = project;
        copy.addActionListener(new CopyContentActionListener(this.resultTextField));

        this.textField.addDocumentListener(new UrlEncodeAndDecodeDocumentListener(command, textField, resultTextField));
    }

    private void createUIComponents() {
        this.textField = new EditorTextField(EditorFactory.getInstance().createDocument(""), project, FileTypes.PLAIN_TEXT, false, false);
        this.textField.addSettingsProvider(getEditorSettingsProvider());

        this.resultTextField = new EditorTextField(EditorFactory.getInstance().createDocument(""), project, FileTypes.PLAIN_TEXT, true, false);
        this.resultTextField.addSettingsProvider(getEditorSettingsProvider());
    }

    @NotNull
    private EditorSettingsProvider getEditorSettingsProvider() {
        return editor -> {
            EditorSettings settings = editor.getSettings();
            settings.setUseSoftWraps(true);
            settings.setLineNumbersShown(true);
        };
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
