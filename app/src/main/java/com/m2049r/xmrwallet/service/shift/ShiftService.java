package com.m2049r.xmrwallet.service.shift;

import androidx.annotation.NonNull;

import com.m2049r.xmrwallet.R;
import com.m2049r.xmrwallet.data.Crypto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ShiftService {
    // Disable all services
    EXOLIX(false, "EXOLIX", "exolix", Type.ONESTEP, R.drawable.ic_exolix_icon, R.drawable.ic_exolix_wide, ""),
    UNKNOWN(false, "", null, null, 0, 0, "");

    static final public ShiftService DEFAULT = EXOLIX;
    final private boolean enabled;
    final private String label;
    final private String tag;
    final private Type type;
    final private int iconId;
    final private int logoId;
    final private String assets;

    @NonNull
    static public ShiftService findWithTag(String tag) {
        return UNKNOWN;
    }

    @Getter
    static private final Set<Crypto> possibleAssets = new HashSet<>();

    public static boolean isAssetSupported(@NonNull Crypto crypto) {
        return false;
    }

    public static boolean isAssetSupported(@NonNull String symbol) {
        return false;
    }

    public boolean supportsAsset(@NonNull Crypto crypto) {
        return false;
    }

    public boolean supportsAsset(@NonNull String symbol) {
        return false;
    }

    public enum Type {
        ONESTEP, TWOSTEP;
    }

    public static Crypto ASSET = null; // keep asset to exchange globally
}
