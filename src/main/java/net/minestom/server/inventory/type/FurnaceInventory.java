package net.minestom.server.inventory.type;

import net.kyori.adventure.text.Component;
import net.minestom.server.ServerProcess;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryProperty;
import net.minestom.server.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public class FurnaceInventory extends Inventory {

    private short remainingFuelTick;
    private short maximumFuelBurnTime;
    private short progressArrow;
    private short maximumProgress;

    public FurnaceInventory(ServerProcess serverProcess, @NotNull Component title) {
        super(serverProcess, InventoryType.FURNACE, title);
    }

    public FurnaceInventory(ServerProcess serverProcess, @NotNull String title) {
        super(serverProcess, InventoryType.FURNACE, title);
    }

    /**
     * Represents the amount of tick until the fire icon come empty.
     *
     * @return the amount of tick until the fire icon come empty
     */
    public short getRemainingFuelTick() {
        return remainingFuelTick;
    }

    /**
     * Represents the amount of tick until the fire icon come empty.
     *
     * @param remainingFuelTick the amount of tick until the fire icon is empty
     */
    public void setRemainingFuelTick(short remainingFuelTick) {
        this.remainingFuelTick = remainingFuelTick;
        sendProperty(InventoryProperty.FURNACE_FIRE_ICON, remainingFuelTick);
    }

    public short getMaximumFuelBurnTime() {
        return maximumFuelBurnTime;
    }

    public void setMaximumFuelBurnTime(short maximumFuelBurnTime) {
        this.maximumFuelBurnTime = maximumFuelBurnTime;
        sendProperty(InventoryProperty.FURNACE_MAXIMUM_FUEL_BURN_TIME, maximumFuelBurnTime);
    }

    public short getProgressArrow() {
        return progressArrow;
    }

    public void setProgressArrow(short progressArrow) {
        this.progressArrow = progressArrow;
        sendProperty(InventoryProperty.FURNACE_PROGRESS_ARROW, progressArrow);
    }

    public short getMaximumProgress() {
        return maximumProgress;
    }

    public void setMaximumProgress(short maximumProgress) {
        this.maximumProgress = maximumProgress;
        sendProperty(InventoryProperty.FURNACE_MAXIMUM_PROGRESS, maximumProgress);
    }
}
