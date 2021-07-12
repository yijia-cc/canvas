import ui.VendingMachineUI;
import ui.terminal.VendingMachineTerminalUI;
import vending_machine.VendingMachineAutomator;

public class Main {
    public static void main(String[] args) {
        VendingMachineUI vendingMachineUI = new VendingMachineTerminalUI();
        VendingMachineAutomator vendingMachineAutomator = new VendingMachineAutomator(vendingMachineUI);
        vendingMachineAutomator.run();
    }
}

