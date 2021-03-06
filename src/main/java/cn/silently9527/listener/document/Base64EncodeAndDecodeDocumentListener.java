package cn.silently9527.listener.document;

import cn.silently9527.domain.ToolkitCommand;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.ui.EditorTextField;
import com.intellij.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class Base64EncodeAndDecodeDocumentListener implements DocumentListener {
    private EditorTextField urlTextField;
    private EditorTextField resultTextField;
    private ToolkitCommand command;

    public Base64EncodeAndDecodeDocumentListener(ToolkitCommand command, EditorTextField urlTextField, EditorTextField resultTextField) {
        this.command = command;
        this.urlTextField = urlTextField;
        this.resultTextField = resultTextField;
    }

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        String text = this.urlTextField.getText();
        if (StringUtils.isBlank(text)) {
            return;
        }
        String result;
        if (ToolkitCommand.Base64Decode.equals(command)) {
            result = new String(Base64.decode(text), StandardCharsets.UTF_8);
        } else {
            result = Base64.encode(text.getBytes(StandardCharsets.UTF_8));
        }
        this.resultTextField.setText(result);
    }

}