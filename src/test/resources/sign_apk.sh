#!/bin/zsh

# Paths
APK_INPUT="/Users/geeky/Downloads/XOME/src/test/resources/base.apk"
APK_CLEAN="/Users/geeky/Downloads/XOME/src/test/resources/base-clean.apk"
APK_ALIGNED="/Users/geeky/Downloads/XOME/src/test/resources/base-aligned.apk"

# Debug keystore info
KEYSTORE="$HOME/.android/debug.keystore"
KEY_ALIAS="androiddebugkey"
STOREPASS="android"
KEYPASS="android"

echo "Cleaning APK..."
mkdir -p temp_apk
unzip -q "$APK_INPUT" -d temp_apk

# Avoid errors if no matches
setopt +o nomatch

# Remove old signatures & unnecessary META-INF entries
rm -f temp_apk/META-INF/*.{SF,RSA,DSA}
rm -f temp_apk/META-INF/*.version
rm -rf temp_apk/META-INF/com.android.*
rm -f temp_apk/META-INF/*.kotlin_module

# Repackage cleaned APK
cd temp_apk
zip -qr "../$(basename "$APK_CLEAN")" .
cd ..
rm -rf temp_apk

echo "Cleaned APK created: $APK_CLEAN"

echo "Zipaligning..."
~/Library/Android/sdk/build-tools/36.1.0/zipalign -p 4 "$APK_CLEAN" "$APK_ALIGNED"
echo "Aligned APK created: $APK_ALIGNED"

echo "Signing APK..."
~/Library/Android/sdk/build-tools/36.1.0/apksigner sign \
    --ks "$KEYSTORE" \
    --ks-key-alias "$KEY_ALIAS" \
    --ks-pass pass:"$STOREPASS" \
    --key-pass pass:"$KEYPASS" \
    --v1-signing-enabled true \
    --v2-signing-enabled true \
    "$APK_ALIGNED"

echo "Verifying APK..."
~/Library/Android/sdk/build-tools/36.1.0/apksigner verify --verbose "$APK_ALIGNED"
