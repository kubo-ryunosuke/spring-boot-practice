# JDK 21 (Alpine Linux版)
FROM eclipse-temurin:21-jdk-alpine

# タイムゾーンデータをインストールし、日本時間に設定する
RUN apk add --no-cache tzdata
ENV TZ=Asia/Tokyo

WORKDIR /app

# Gradleのキャッシュを効かせるために設定ファイルを先にコピー
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./

# 改行コード修正 & 実行権限付与 (Windows/Mac混在チームでのトラブル防止)
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

# 依存関係をダウンロード (キャッシュ層を作成)
RUN ./gradlew dependencies --no-daemon

# ソースコードをコピー
COPY src ./src

# デフォルトコマンド (Compose側で上書きしますが念のため)
CMD ["./gradlew", "bootRun"]
