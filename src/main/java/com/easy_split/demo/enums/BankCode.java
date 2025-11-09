package com.easy_split.demo.enums;

public enum BankCode {

    BANCODOBRASIL("001"),
    SANTANDER("033"),
    BANRISUL("041"),
    BANCOINTER("077"),
    CAIXA("104"),
    UNICRED("136"),
    BRADESCO("237"),
    ITAU("341"),
    SICREDI("748");

    final String code;

    BankCode(String code) {
        this.code = code;
    }

    String getCode() { return this.code; }

}
