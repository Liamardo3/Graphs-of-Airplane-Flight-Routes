//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class Menu {
    private static final int MAX_TITLE_LENGTH = 70;
    private static final int MAX_OPTION_LENGTH = 70;
    private static final int MAX_NUM_OPTIONS = 20;
    private int numberOfOptions;
    private String[] menuText;
    private Scanner keyboard;

    private void pause() {
        System.out.print("Press Enter to continue ... ");
        this.keyboard.nextLine();
    }

    public Menu() {
        this.keyboard = new Scanner(System.in);
        this.numberOfOptions = 0;
        this.menuText = new String[21];
        this.menuText[0] = "Empty Menu";
    }

    public Menu(String var1) {
        this.keyboard = new Scanner(System.in);
        this.numberOfOptions = 0;
        this.menuText = new String[21];
        if (var1.length() > 70) {
            this.menuText[0] = "Empty Menu";
            System.out.println("\n===== Error: Title cannot exceed 70 characters.\nThe title\n" + var1 + "\nwas not added to the menu." + "\nThis menu has been given the default title: Empty Menu");
            this.pause();
        } else {
            this.menuText[0] = var1;
        }
    }

    public void setTitle(String var1) {
        if (var1.length() > 70) {
            System.out.println("\n===== Error: Menu title cannot exceed 70 characters in length.\nThe title\n" + var1 + "\nwas not added to the menu.");
            this.pause();
        } else {
            this.menuText[0] = var1;
        }
    }

    public void addOption(String var1) {
        if (this.numberOfOptions == 20) {
            System.out.println("\n===== Error: Maximum number of menu options (20) exceeded.\nThe option\n" + var1 + "\nwas not added to the menu.");
            this.pause();
        } else if (var1.length() > 70) {
            System.out.println("\n===== Error: Option cannot exceed 70 characters in length.\nThe option\n" + var1 + "\nwas not added to the menu.");
            this.pause();
        } else {
            ++this.numberOfOptions;
            if (this.numberOfOptions == 10) {
                for(int var2 = 1; var2 < this.numberOfOptions; ++var2) {
                    this.menuText[var2] = " " + this.menuText[var2];
                }
            }

            String var3 = (new Integer(this.numberOfOptions)).toString();
            var3 = var3 + ". ";
            var3 = var3 + var1;
            this.menuText[this.numberOfOptions] = var3;
        }
    }

    public void display() {
        for(int var1 = 1; var1 <= (24 - this.numberOfOptions - 2) / 2 + 15; ++var1) {
            System.out.println();
        }

        int var2;
        int var3;
        if (this.numberOfOptions == 0) {
            var2 = 0;
            var3 = 0;
        } else {
            int var4 = 0;
            int var5 = 0;

            for(int var6 = 1; var6 <= this.numberOfOptions; ++var6) {
                if (this.menuText[var6].length() > var4) {
                    var4 = this.menuText[var6].length();
                }

                var5 += this.menuText[var6].length();
            }

            int var10000 = var5 / this.numberOfOptions;
            int var7 = this.menuText[0].length();
            var2 = 38 - (var7 + 1) / 2;
            var3 = 38 - (var4 + 1) / 2;
        }

        for(int var8 = 1; var8 <= var2; ++var8) {
            System.out.print(" ");
        }

        System.out.println(this.menuText[0] + "\n");
        if (this.numberOfOptions == 0) {
            System.out.println("This menu currently has no options ... ");
        }

        for(int var9 = 1; var9 <= this.numberOfOptions; ++var9) {
            for(int var11 = 1; var11 <= var3; ++var11) {
                System.out.print(" ");
            }

            System.out.println(this.menuText[var9]);
        }

        for(int var10 = 1; var10 <= (24 - this.numberOfOptions - 2) / 2; ++var10) {
            System.out.println();
        }

    }

    public int getChoice(String var1, int var2) {
        if (this.numberOfOptions == 0) {
            System.out.println("\n===== Error: Menu has no options from which to choose.");
            this.pause();
            return -1;
        } else {
            if (var1.equals("")) {
                var1 = "Enter choice: ";
            }

            if (var2 <= 2) {
                var2 = 2;
            }

            String var3 = " ";

            for(int var4 = 1; var4 < (80 - var1.length()) / 2 - 2; ++var4) {
                var3 = var3 + ' ';
            }

            var1 = var3 + var1;
            boolean var6 = false;
            int var7 = 0;

            String var5;
            do {
                System.out.print(var1);
                System.out.flush();
                var5 = new String(this.keyboard.nextLine());
                ++var7;
                boolean var8 = var5.length() <= 2 && !var5.equals("");
                if (var8) {
                    for(int var9 = 0; var9 < var5.length(); ++var9) {
                        if (!"0123456789".contains("" + var5.charAt(var9))) {
                            var8 = false;
                        }
                    }
                }

                if (var8) {
                    int var11 = Integer.parseInt(var5);
                    var8 = var11 >= 1 && var11 <= this.numberOfOptions;
                }

                if (var8) {
                    var6 = true;
                } else if (var7 < var2) {
                    var6 = false;
                    System.out.print("\nError: Menu option choice invalid.\nChoice must be a value from 1 to " + this.numberOfOptions + ". Try again.\n");
                    this.pause();
                    System.out.println();
                }
            } while(!var6 && var7 < var2);

            if (var7 == var2 && !var6) {
                System.out.println("\nSorry, but that last choice was also invalid, and you only get " + var2 + " chances.");
                this.pause();
                return -1;
            } else {
                return Integer.parseInt(var5);
            }
        }
    }

    public int getChoice() {
        return this.getChoice("Enter number of your menu choice here and then press Enter: ", 3);
    }

    public int getChoice(String var1) {
        return this.getChoice(var1, 3);
    }

    public int getChoice(int var1) {
        return this.getChoice("Enter number of your menu choice here and then press Enter: ", var1);
    }
}
