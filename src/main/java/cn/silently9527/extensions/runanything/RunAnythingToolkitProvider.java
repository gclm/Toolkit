package cn.silently9527.extensions.runanything;

import cn.silently9527.actions.ToolkitCommandAction;
import cn.silently9527.domain.ToolkitCommand;
import cn.silently9527.service.CacheService;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.ide.actions.runAnything.activity.RunAnythingAnActionProvider;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class RunAnythingToolkitProvider extends RunAnythingAnActionProvider<AnAction> {
    private static final String CACHE_KEY = "ToolkitCommandActionInstances";

    private CacheService cacheService;

    private CacheService getCacheService() {
        if (cacheService == null) {
            cacheService = ApplicationManager.getApplication().getService(CacheService.class);
        }
        return cacheService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull Collection<AnAction> getValues(@NotNull DataContext dataContext, @NotNull String pattern) {
        CacheService cacheService = getCacheService();
        if (cacheService == null) {
            return createActions();
        }

        Object value = cacheService.get(CACHE_KEY);
        if (Objects.nonNull(value)) {
            return (Collection<AnAction>) value;
        }
        Collection<AnAction> actions = createActions();
        cacheService.put(CACHE_KEY, actions);
        return actions;
    }

    private Collection<AnAction> createActions() {
        return Arrays.stream(ToolkitCommand.values())
                .map(ToolkitCommandAction::new)
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull String getCommand(@NotNull AnAction value) {
        return this.getHelpCommand() + " " + ObjectUtils.notNull(value.getTemplatePresentation().getText(),
                IdeBundle.message("run.anything.actions.undefined"));
    }

    @Override
    public @Nullable AnAction findMatchingValue(@NotNull DataContext dataContext, @NotNull String pattern) {
        String helpCommand = getHelpCommand();
        if (!pattern.startsWith(helpCommand + " ")) {
            return null;
        }
        String commandPart = pattern.substring(helpCommand.length()).trim();
        if (commandPart.isEmpty()) {
            return null;
        }
        for (AnAction action : getValues(dataContext, pattern)) {
            String text = action.getTemplatePresentation().getText();
            if (text != null && text.equalsIgnoreCase(commandPart)) {
                return action;
            }
        }
        return null;
    }

    @Override
    public @Nullable String getHelpGroupTitle() {
        return "Toolkit";
    }

    @NotNull
    @Override
    public String getHelpCommand() {
        return "toolkit";
    }

    @NotNull
    @Override
    public String getHelpCommandPlaceholder() {
        return "toolkit <command name>";
    }

    @Override
    @Nullable
    public Icon getHelpIcon() {
        return AllIcons.General.ExternalTools;
    }

    @NotNull
    @Override
    public String getCompletionGroupTitle() {
        return "Toolkit"; //过滤界面中的名称
    }

}
