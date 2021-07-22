package com.yijiacc.canvas.app.vending_machine;

import com.yijiacc.canvas.app.exceptions.NoPaymentMethodException;
import com.yijiacc.canvas.app.ui.VendingMachineUI;
import com.yijiacc.canvas.app.ui.terminal.VendingMachineTerminalUI;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    public static void main(String[] args) throws NoPaymentMethodException {
        VendingMachineUI vendingMachineUI = new VendingMachineTerminalUI();
        List<Inventory> inventories = new ArrayList<>();
        VendingMachineAutomator vendingMachineAutomator = new VendingMachineAutomator(vendingMachineUI, inventories);
        vendingMachineAutomator.run();
    }
}

