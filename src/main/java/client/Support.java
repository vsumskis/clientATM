package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Support {
    private Socketor socketor;
    private Dialog dialog;
    private String number;
    private String pin;


    public Support(Socketor socketor) {
        this.socketor = socketor;
        dialog = new Dialog();
    }

    public void start() {
        dialog.print("Sveiki atvyke i bankomata!");
        while (true) {
            number = dialog.input("Iveskite korteles numer: ");
            pin = dialog.input("iveskite pin koda: ");
            while (true) {
                switch (showMenu()) {
                    case "1":
                        doGetBalance();
                        continue;
                    case "2":
                        doCashOut();
                        continue;
                    case "3":
                        doCashIn();
                        continue;
                    case "4":
                        doChangePin();
                        continue;
                    case "5":
                        doShowHistory();
                        continue;
                    case "0":
                        break;
                    default:
                        dialog.print("Klaida! Iveskite numeri nuo 0 iki 4!");
                        continue;
                }
                break;
            }
            dialog.print("Viso gero! \n\n\n");
            number = pin = "";
        }
    }

   private String showMenu() {
        dialog.print("1. Parodyti korteles balansa");
        dialog.print("2. Pasiimti pinigu");
        dialog.print("3. Ideti pinigu");
        dialog.print("4. Pakeisti pin koda");
        dialog.print("5. Parodyti veiksmu istorija");
        dialog.print("0. Baigti darba");
        return dialog.input("pasirinkite meniu punkta (0-4):");
    }

    private void doShowHistory() {
        String response = doRequest("ShowHistory", number, pin);
        dialog.print(response.replace("/", "\n"));
    }

    private void doGetBalance() {
        String response = doRequest("GetBalance", number, pin);
        dialog.print(response);

    }

    private String doRequest(String... args) {
        socketor.connect();
        List<String> request = Arrays.asList(args);
        socketor.sendRequest(request);
        String response = socketor.getResponse();
        socketor.close();
        return response;
    }

    private void doCashOut() {
        String amount = dialog.input("Kiek pinigu norite isimti: ");
        String response = doRequest("CashOut", number, pin, amount);
        dialog.print(response);
    }

    private void doCashIn() {
        String amount = dialog.input("Kiek pinigu norite ideti: ");
        String response = doRequest("CashIn", number, pin, amount);
        dialog.print(response);
    }

    private void doChangePin() {
        String newPin = dialog.input("Iveskite nauja pin koda: ");
        String response = doRequest("ChangePin", number, pin, newPin);
        pin = newPin;
        dialog.print(response);
    }
}
