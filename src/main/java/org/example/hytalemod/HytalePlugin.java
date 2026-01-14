package org.example.hytalemod;

import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.example.hytalemod.commands.ExampleCommand;
import org.example.hytalemod.systems.HudSystem;

public class HytalePlugin extends JavaPlugin {
    public HytalePlugin(@NonNullDecl JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        // register commands
        CommandManager.get().register(new ExampleCommand());

        // register systems
        this.getEntityStoreRegistry().registerSystem(new HudSystem());
    }
}
