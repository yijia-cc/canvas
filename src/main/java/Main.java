import ui.VendingMachineUI;
import ui.terminal.VendingMachineTerminalUI;
import vending_machine.Inventory;
import vending_machine.VendingMachineAutomator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VendingMachineUI vendingMachineUI = new VendingMachineTerminalUI();
        List<Inventory> inventories = new ArrayList<>();
        VendingMachineAutomator vendingMachineAutomator = new VendingMachineAutomator(vendingMachineUI, inventories);
        vendingMachineAutomator.run();
    }
}

