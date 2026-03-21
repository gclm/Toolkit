package cn.silently9527.actions;

import cn.silently9527.domain.ToolkitCommand;
import cn.silently9527.service.ToolkitCommandService;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

public class ToolkitCommandAction extends AnAction {
    private ToolkitCommandService toolkitCommandService;
    private final ToolkitCommand command;

    public ToolkitCommandAction(ToolkitCommand command) {
        super(command.getCommand());
        this.command = command;
        Presentation presentation = getTemplatePresentation();
        presentation.setText(command.getCommand(), false);
        presentation.setIcon(AllIcons.General.ExternalTools);
        presentation.setDescription(command.getDescription());
    }

    private ToolkitCommandService getToolkitCommandService() {
        if (toolkitCommandService == null) {
            toolkitCommandService = ApplicationManager.getApplication().getService(ToolkitCommandService.class);
        }
        return toolkitCommandService;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final ToolkitCommandService service = getToolkitCommandService();
        service.execute(this.command, e.getDataContext());
    }
}
