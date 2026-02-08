/*
 * Copyright (c) 2024 m2049r
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.m2049r.xmrwallet.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.m2049r.xmrwallet.R;
import com.m2049r.xmrwallet.model.Wallet;

import java.util.regex.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Crypto {
    XMR("XMR", "XMR", "XMR", "monero:tx_amount:recipient_name:tx_description", Pattern.compile("^[48][a-zA-Z|\\d]{94}([a-zA-Z|\\d]{11})?$"));

    @Getter
    @NonNull
    private final String symbol;
    @Getter
    @NonNull
    private final String network;
    @Getter
    @NonNull
    private final String label;
    @NonNull
    private final String uriSpec;
    @NonNull
    private final Pattern regex;

    @Nullable
    public static Crypto withScheme(@NonNull String scheme) {
        for (Crypto crypto : values()) {
            if (crypto.getUriScheme().equals(scheme)) return crypto;
        }
        return null;
    }

    @Nullable
    public static Crypto withSymbol(@NonNull String symbol) {
        final String upperSymbol = symbol.toUpperCase();
        for (Crypto crypto : values()) {
            if (crypto.symbol.equals(upperSymbol)) return crypto;
        }
        return null;
    }

    // TODO maybe cache these segments
    String getUriScheme() {
        return uriSpec.split(":")[0];
    }

    String getUriAmount() {
        return uriSpec.split(":")[1];
    }

    String getUriLabel() {
        return uriSpec.split(":")[2];
    }

    String getUriMessage() {
        return uriSpec.split(":")[3];
    }

    public boolean validate(String address) {
        if (this == XMR) return Wallet.isAddressValid(address);
        return regex.matcher(address).find();
    }
}
