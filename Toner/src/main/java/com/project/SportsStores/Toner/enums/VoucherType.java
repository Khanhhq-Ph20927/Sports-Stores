package com.project.SportsStores.Toner.enums;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoucherType {
    PERCENT,
    FIX;
}
