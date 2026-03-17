package cn.silently9527.actions;

import cn.silently9527.domain.ToolkitCommand;
import cn.silently9527.service.ToolkitCommandService;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolkitCommandAction extends AnAction {
    private final ToolkitCommandService toolkitCommandService;
    private final ToolkitCommand command;

    public ToolkitCommandAction(ToolkitCommand command) {
        super(command.getCommand());
        this.command = command;
        this.toolkitCommandService = ServiceManager.getService(ToolkitCommandService.class);

        Presentation presentation = getTemplatePresentation();
        presentation.setText(command.getCommand(), false);
        presentation.setIcon(AllIcons.General.ExternalTools);
        presentation.setDescription(command.getDescription());
    }

    private ToolkitCommandService getToolkitCommandService(){
        if (toolkitCommandService == null) {
            toolkitCommandService= ApplicationManager.getApplication().getService(ToolkitCommandService.class);
        }
        return toolkitCommandService;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        toolkitCommandService.execute(this.command, e.getDataContext());
    }

    @Nullable
    @Override
    public String getTemplateText() {
        return this.command.getCommand();
    }
}
