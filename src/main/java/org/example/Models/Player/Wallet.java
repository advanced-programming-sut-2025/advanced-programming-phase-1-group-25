package org.example.Models.Player;

public class Wallet {
    int coin;
    public Wallet(int coin) {
        this.coin = coin;
    }
    public int getCoin() {
        return coin;
    }

    public void increaseCoin(int coin) {
        this.coin += coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
    public void decreaseCoin(int coin) {
        this.coin -= coin;
    }

}
